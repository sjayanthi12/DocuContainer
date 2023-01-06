package main.java.core.service.doucumentUpload;

import main.java.core.domain.Document;
import main.java.core.repository.DocumentRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentUploadServiceImpl implements DocumentUploadService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentUploadServiceImpl.class);
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentUploadServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
   public Document create(Document doc) {
    	LOGGER.debug("Createing an entry in db with " + doc.toString());
	   return documentRepository.save(doc);
    	
    }
    
    @Override
    public Document getDocumentByName(String name) {
    	LOGGER.debug("Retriving a douc with name " + name);
    	
    	return null;

    }
 
}