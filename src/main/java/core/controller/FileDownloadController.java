package main.java.core.controller;



import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.core.domain.Document;
import main.java.core.service.documentDownload.DocumentDownloadService;

import org.springframework.security.access.prepost.PreAuthorize;


@Controller
public class FileDownloadController {


	private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

	public static final String ROOT = "upload-dir";

	private final ResourceLoader resourceLoader;
	private final DocumentDownloadService documentDownloadService;

	@Autowired
	public FileDownloadController(ResourceLoader resourceLoader, DocumentDownloadService documentDownloadService) {
		this.resourceLoader = resourceLoader;
		this.documentDownloadService = documentDownloadService;
		
	}
    
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/document/retrieve")
 	public ModelAndView retrieveDocument() throws IOException {
  
	   Document document = documentDownloadService.getDocument("test");
	   log.debug("Message from filedownloadcontroller " + document.getUrl());
    	return new ModelAndView("displaydoc", "document", document);    	
    	
 	} 
  
}







