package main.java.core.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.common.io.ByteStreams;
import main.java.core.domain.Document;
import main.java.core.service.doucumentUpload.DocumentUploadService;
import main.java.core.service.doucumentUpload.FileArchiveService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class FileUploadController {


	private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

	public static final String ROOT = "upload-dir";

	private final ResourceLoader resourceLoader;
	private final DocumentUploadService documentUploadService;
	private final FileArchiveService fileArchiveService;

	@Autowired
	public FileUploadController(ResourceLoader resourceLoader, DocumentUploadService documentUploadService, FileArchiveService fileArchiveService) {
		this.resourceLoader = resourceLoader;
		this.documentUploadService = documentUploadService;
		this.fileArchiveService = fileArchiveService;
		
	}

    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/document/upload")
	public String provideUploadInfo(Model model) throws IOException {
    	
		model.addAttribute("files", Files.walk(Paths.get(ROOT))
				.filter(path -> !path.equals(Paths.get(ROOT)))
				.map(path -> Paths.get(ROOT).relativize(path))
				.map(path -> linkTo(methodOn(FileUploadController.class).getFile(path.toString())).withRel(path.toString()))
				.collect(Collectors.toList()));

		return "uploader";
	}
    
	@RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String filename) {

		try {
			return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/document/save")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes) {
    	
    	log.debug("Before file upload");
		if (!file.isEmpty()) {
			try {
				log.debug(" file upload beginning "  + file.getOriginalFilename());
				Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
				fileArchiveService.saveFileToS3(file);
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + file.getOriginalFilename() + "!");
			} catch (IOException|RuntimeException e) {
				redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
		}

		return "redirect:/";
	}

}






