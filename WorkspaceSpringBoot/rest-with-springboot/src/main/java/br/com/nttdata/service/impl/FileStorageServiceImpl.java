package br.com.nttdata.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.nttdata.config.FileStorageConfig;
import br.com.nttdata.exception.FileStorageException;
import br.com.nttdata.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private final Path fileStorageLocation;
	
	public FileStorageServiceImpl(FileStorageConfig fileStorageConfig) {
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		}catch(Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will stored",e);
		}
	}
	
	@Override
	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence ".concat(fileName).concat(". Please try again!"));
			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		}catch (Exception e) {
			throw new FileStorageException("Could not stored file ".concat(fileName).concat(". Please try again!"),e);
		}
	}
}
