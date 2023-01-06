package main.java.core.service.doucumentUpload;

import main.java.core.domain.Document;

public interface DocumentUploadService {

   public Document create(Document doc);
   
   public Document getDocumentByName(String name);

}
