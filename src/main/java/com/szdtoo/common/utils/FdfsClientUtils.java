package com.szdtoo.common.utils;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
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


	private Map<String, MultipartFile> getFileMap(HttpServletRequest request){
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
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		return files;
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



	// 封装图片完整URL地址
	private String getResAccessUrl(StorePath storePath) {
		String webserver = Constants.propertiesVal(Constants.FDFSCONFIG, Constants.propertiesKey.FDFS_WEBSERVER);
		String fileUrl = webserver + storePath.getFullPath();
		return fileUrl;
	}

	/**
	 * <p>Title: uploadFile</p>  
	 * <p>Description: 上传文件</p>  
	 * @param request
	 * @return
	 */
	public String uploadFile(HttpServletRequest request) {

		String result = "";
		Map<String, MultipartFile> files = getFileMap(request);
		if (files == null || files.keySet().size() <=0) {
			return result;
		}
		try {
			for(String key:files.keySet()){
				MultipartFile file = files.get(key);
				StorePath storePath=storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
				String fullPath=storePath.getFullPath();
				
				if (StringUtils.isNotBlank(fullPath)) {
					// 添加fastdfs前缀
					String webserver = Constants.propertiesVal(Constants.FDFSCONFIG, Constants.propertiesKey.FDFS_WEBSERVER);
					result += webserver + fullPath + ",";
				}
			}

		}catch (Exception ex){
			ex.printStackTrace();
		}

		if(!StringUtils.isEmpty(result)){
			result = result.substring(0,result.length()-1);
		}
		return result;

	}


	public String uploadFile(HttpServletRequest request, String fileKey, List<String> filterKeys) {

		String result = "";
		Map<String, MultipartFile> files = getFileMap(request);
		if (files == null || files.keySet().size() <=0) {
			return result;
		}
		try {
			for(String key:files.keySet()){
				//指定文件名，过滤非此文件名的文件
				if (!ValidateUtils.isIllegalString(fileKey) && !fileKey.equals(key)) { continue; }
				//过滤文件名，过滤集合中存在此文件名，过滤
				if (!ValidateUtils.isIllegalList(filterKeys) && filterKeys.contains(key)) { continue; }
				MultipartFile file = files.get(key);
				StorePath storePath=storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
				String fullPath=getResAccessUrl(storePath);
				if (fullPath!=null&&!"".equals(fullPath)) {
					result += fullPath + ",";
				}
			}

		}catch (Exception ex){
			ex.printStackTrace();
		}

		if(!StringUtils.isEmpty(result)){
			result = result.substring(0,result.length()-1);
		}
		return result;
	}


	public String uploadFile1(HttpServletRequest request, String fileKey) {
		String result = "";
		List<MultipartFile> files = getFileList(request, fileKey);
		if (files == null || files.size() <=0) {
			return result;
		}
		try {
			for(MultipartFile file:files){
				StorePath storePath=storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
				String fullPath= storePath.getFullPath();
				result += fullPath + ",";
			}

		}catch (Exception ex){
			ex.printStackTrace();
		}

		if(!StringUtils.isEmpty(result)){
			result = result.substring(0,result.length()-1);
		}
		return result;
	}
	
}
