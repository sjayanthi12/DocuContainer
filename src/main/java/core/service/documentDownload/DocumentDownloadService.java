package main.java.core.service.documentDownload;

import java.io.IOException;

import main.java.core.domain.Document;
import main.java.core.exception.FileArchiveServiceException;

public interface DocumentDownloadService  {
	
	public Document getDocument(String key) throws FileArchiveServiceException, IOException;

}
