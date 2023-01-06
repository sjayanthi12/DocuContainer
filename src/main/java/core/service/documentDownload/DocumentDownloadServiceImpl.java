package main.java.core.service.documentDownload;


import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import main.java.core.domain.Document;
import main.java.core.exception.FileArchiveServiceException;
import main.java.core.repository.DocumentRepository;
import main.java.core.service.doucumentUpload.FileArchiveServiceImpl;

@Service
public class DocumentDownloadServiceImpl implements DocumentDownloadService {

	private AmazonS3Client s3Client;

	@Autowired
	DocumentRepository documentRepository;

	private static final String S3_BUCKET_NAME = "aushadhi-medicaldocs";

	private static final Logger log = LoggerFactory.getLogger(FileArchiveServiceImpl.class);

	public Document getDocument(String key) throws FileArchiveServiceException, IOException {

		log.debug("Step 1: Entry in getDocument");
		
		// Need to move these creditentials to a config file
		//BasicAWSCredentials awsCreds = new BasicAWSCredentials("",
				"");
		s3Client = new AmazonS3Client(awsCreds);

		log.debug("Step 2: Entry in getDocument" + s3Client.getServiceName());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		

		List<Document> docList = documentRepository.findByUserId(name);
		
		log.debug("Step 3: User name to get docs for " + name);


		if (!docList.isEmpty()) {
			log.debug("Step 4: filename " + docList.get(0).getFilename());

			S3Object object = s3Client.getObject(new GetObjectRequest(S3_BUCKET_NAME, docList.get(0).getFilename()));
			
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, docList.get(0).getFilename());
			
			Document document = new Document();
			document.setFilename(docList.get(0).getFilename());

			document.setUrl(s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString());
			
//			InputStream objectData = object.getObjectContent();
//			BufferedImage imBuff = ImageIO.read(objectData);
//
//			objectData.close();
			return document;
		} else
			return null;

	}

}
