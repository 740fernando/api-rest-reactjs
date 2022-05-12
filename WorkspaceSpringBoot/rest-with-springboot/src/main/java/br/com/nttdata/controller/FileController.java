package br.com.nttdata.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		return buildUploadFileResponseVO(file);
	}
	
	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponseVO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
		return Arrays.asList(files)
				.stream().map(file -> buildUploadFileResponseVO(file)).collect(Collectors.toList());
	}

	private UploadFileResponseVO buildUploadFileResponseVO(@RequestParam("files")MultipartFile file) {
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/v1/downloadFile/")
				.path(fileStorageService.storeFile(file)).
				toUriString();	
		return new UploadFileResponseVO(fileStorageService.storeFile(file), fileDownloadUri, file.getContentType(), file.getSize());
	}
}
