package main.java.core.service.doucumentUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.alps.Doc;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import main.java.core.controller.FileUploadController;
import main.java.core.domain.CustomerImage;
import main.java.core.domain.Document;
import main.java.core.exception.FileArchiveServiceException;
import main.java.core.repository.DocumentRepository;


@Service
public class FileArchiveServiceImpl implements FileArchiveService{

	@Autowired
	private DocumentRepository documentRepository;
	
	private AmazonS3Client s3Client;

	private static final String S3_BUCKET_NAME = "aushadhi-medicaldocs";

	private static final Logger log = LoggerFactory.getLogger(FileArchiveServiceImpl.class);

	/**
	 * Save image to S3 and return CustomerImage containing key and public URL
	 * 
	 * @param multipartFile
	 * @return
	 * @throws IOExceptions
	 */
	public CustomerImage saveFileToS3(MultipartFile multipartFile) throws FileArchiveServiceException {

		try{
			log.debug("step 0: " + multipartFile.getOriginalFilename());

			File fileToUpload = convertFromMultiPart(multipartFile);
			String key = Instant.now().getEpochSecond() + "_" + fileToUpload.getName();
			
			//BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "");
			
			log.debug("step 1: " + awsCreds.getAWSAccessKeyId());

			s3Client = new AmazonS3Client(awsCreds);
			
			log.debug("step 2: " + s3Client.getServiceName());

			/* save file */
			PutObjectResult putObjectResult = s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, fileToUpload));
			log.debug("step 3: " + putObjectResult.toString());

			/* get signed URL (valid for one year) */
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, key);
			generatePresignedUrlRequest.setMethod(HttpMethod.GET);
			generatePresignedUrlRequest.setExpiration(DateTime.now().plusYears(1).toDate());
			
			URL signedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
			log.debug("step 4: " + signedUrl);
			
	    	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	         String name = auth.getName(); //get logged in username
	         
				log.debug("step 5: " + key);


			Document doc = new Document();
			doc.setUserId(name);
			doc.setFilename(key);
			doc.setContentType(multipartFile.getContentType());
//			doc.setUrl(signedUrl.toExternalForm());
			documentRepository.save(doc);
			documentRepository.count();
			
			log.debug("step 6: " + name);

			
			return new CustomerImage(key, signedUrl.toString());
		}
		catch(Exception ex){			
			throw new FileArchiveServiceException("An error occurred saving file to S3", ex);
		}		
	}

	/**
	 * Delete image from S3 using specified key
	 * 
	 * @param customerImage
	 */
	public void deleteImageFromS3(CustomerImage customerImage){
		s3Client.deleteObject(new DeleteObjectRequest(S3_BUCKET_NAME, customerImage.getKey()));	
	}

	/**
	 * Convert MultiPartFile to ordinary File
	 * 
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	private File convertFromMultiPart(MultipartFile multipartFile) throws IOException {

		File file = new File(multipartFile.getOriginalFilename());
		file.createNewFile(); 
		FileOutputStream fos = new FileOutputStream(file); 
		fos.write(multipartFile.getBytes());
		fos.close(); 

		return file;
	}
}
