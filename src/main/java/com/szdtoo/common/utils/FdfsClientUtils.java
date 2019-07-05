package com.szdtoo.common.utils;


import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.szdtoo.common.constant.Constants;

import cn.hutool.core.img.ImgUtil;

/**
 * <p>Title: FdfsClientUtils</p>  
 * <p>Description: fdfs 调用客户端</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@Component
public class FdfsClientUtils {

	private Logger log =  LoggerFactory.getLogger(FdfsClientUtils.class);

	@Autowired
	private FastFileStorageClient storageClient;


//	private Map<String, MultipartFile> getFileMap(HttpServletRequest request){
//		//创建一个通用的多部分解析器
//		CommonsMultipartResolver multipartResovler = new CommonsMultipartResolver();
//		//判断 request 是否有文件上传,即多部分请求
//		if (!multipartResovler.isMultipart(request)) {
//			//无附件上传
//			return null;
//		}
//		// 转型为MultipartHttpRequest：
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		// 获得文件：
//		Map<String, MultipartFile> files = multipartRequest.getFileMap();
//		return files;
//	}


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



	/**
	 * 图片裁剪并上传
	 * @param request
	 * @param fileKey
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public String uploadCutPic(HttpServletRequest request, String fileKey,int x,int y,int width,int height) throws IOException {
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
		return result;
	}
	
	/**
	 * 图片缩放并上传
	 * @param request
	 * @param fileKey
	 * @param scale
	 * @return
	 * @throws IOException
	 */
	public String uploadCutScale(HttpServletRequest request, String fileKey,float scale) throws IOException {
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
		return result;
	}
	
	/**
	 * 图片上传
	 * @param request
	 * @param fileKey
	 * @return
	 * @throws IOException
	 */
	public String uploadFile(HttpServletRequest request, String fileKey) throws IOException {
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
		return result;
	}
	

}
