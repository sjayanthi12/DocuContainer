package main.java.core.service.doucumentUpload;

import org.springframework.web.multipart.MultipartFile;

import main.java.core.domain.CustomerImage;
import main.java.core.exception.FileArchiveServiceException;

public interface FileArchiveService {

	public CustomerImage saveFileToS3(MultipartFile multipartFile) throws FileArchiveServiceException;
}
