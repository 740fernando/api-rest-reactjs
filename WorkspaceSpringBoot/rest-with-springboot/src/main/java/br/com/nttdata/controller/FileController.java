package br.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nttdata.data.vo.v1.UploadFileResponseVO;
import br.com.nttdata.service.FileStorageService;
import io.swagger.annotations.Api;

@Api(tags = "FileEndpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	
	
	@PostMapping("/uploadFile")
	public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/v1/downloadFile")
				.path(fileName).
				toUriString();
		
		return new UploadFileResponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
}
