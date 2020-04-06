package com.szdtoo.service.impl;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.szdtoo.common.constant.Constants;
import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.service.UploadService;

import cn.hutool.core.img.ImgUtil;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService{
	
	@Autowired
	private FastFileStorageClient storageClient;

	@SneakyThrows
	@Override
	public Message<String> uploadFile(HttpServletRequest request, String fileKey) {
		List<MultipartFile> multipartFileList = this.getFileList(request, fileKey);
		String result = "";
		for(MultipartFile multipartFile : multipartFileList) {
			InputStream inputStream = multipartFile.getInputStream();
			String extName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			StorePath storePath = storageClient.uploadFile(inputStream,multipartFile.getSize(), extName,null);
			String fullPath = storePath.getFullPath();
			if(StringUtils.isNotBlank(fullPath)) {
				result += Constants.getFastWebServer() + fullPath + ",";
			}
		}
		if(StringUtils.isNotBlank(result)) {
			result = result.substring(0, result.length() - 1);
		}
		log.info("result:" + result);
		return new Message<>(ErrorCode.SUCCESS,result);
	}
	
	private List<MultipartFile> getFileList(HttpServletRequest request,String key){
		//创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResovler = new CommonsMultipartResolver();
		//判断 request 是否有文件上传,即多部分请求
		if (!multipartResovler.isMultipart(request)) {
			//无附件上传
			return null;
		}
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		List<MultipartFile> files = multipartRequest.getFiles(key);
		return files;
	}

	@SneakyThrows
	@Override
	public Message<String> uploadCutPic(HttpServletRequest request, String fileKey, int x, int y, int width,int height) {
		List<MultipartFile> multipartFileList = this.getFileList(request, fileKey);
		String result = "";
		for(MultipartFile multipartFile : multipartFileList) {
			
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImgUtil.cut(multipartFile.getInputStream(), output, new Rectangle(x,y,width,height));
			
			ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
			String extName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			StorePath storePath = storageClient.uploadFile(input,output.size(), extName,null);
			String fullPath = storePath.getFullPath();
			if(StringUtils.isNotBlank(fullPath)) {
				result += Constants.getFastWebServer() + fullPath + ",";
			}
		}
		if(StringUtils.isNotBlank(result)) {
			result = result.substring(0, result.length() - 1);
		}
		log.info("result:" + result);
		return new Message<>(ErrorCode.SUCCESS,result);
	}

	@SneakyThrows
	@Override
	public Message<String> uploadCutScale(HttpServletRequest request, String fileKey, float scale) {
		List<MultipartFile> multipartFileList = this.getFileList(request, fileKey);
		String result = "";
		for(MultipartFile multipartFile : multipartFileList) {
			
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImgUtil.scale(multipartFile.getInputStream(), output, scale);
			
			ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
			String extName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			StorePath storePath = storageClient.uploadFile(input,output.size(), extName,null);
			String fullPath = storePath.getFullPath();
			if(StringUtils.isNotBlank(fullPath)) {
				result += Constants.getFastWebServer() + fullPath + ",";
			}
		}
		if(StringUtils.isNotBlank(result)) {
			result = result.substring(0, result.length() - 1);
		}
		log.info("result:" + result);
		return new Message<>(ErrorCode.SUCCESS,result);
	}

	@SneakyThrows
	@Override
	public void downloadFile(HttpServletResponse response, String path) {
		StorePath storePath = StorePath.praseFromUrl(path);
		@Cleanup InputStream inputStream = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), (input) -> input);
		//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
		response.setContentType("multipart/form-data");
		 //2.设置文件头：
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("测试文件.jpg", "UTF-8"));//此处需要设置下载文件的默认名称
		
		@Cleanup ServletOutputStream outputStream = response.getOutputStream();
		
		IOUtils.copy(inputStream, outputStream);
	}

	
}
