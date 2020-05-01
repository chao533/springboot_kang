package com.kang.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kang.common.msg.Message;

public interface UploadService {

	/**
	 *<p>Title: uploadFile</p> 
	 *<p>Description: 文件上传</p> 
	 * @param request
	 * @param fileKey
	 * @return
	 */
	Message<?> uploadFile(HttpServletRequest request, String fileKey);
	
	/**
	 *<p>Title: uploadCutPic</p> 
	 *<p>Description: 图片裁剪并上传</p> 
	 * @param request
	 * @param fileKey
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	Message<?> uploadCutPic(HttpServletRequest request, String fileKey,int x,int y,int width,int height);
	
	/**
	 *<p>Title: uploadCutScale</p> 
	 *<p>Description: 图片缩放并上传</p> 
	 * @param request
	 * @param fileKey
	 * @param scale
	 * @return
	 */
	Message<String> uploadCutScale(HttpServletRequest request, String fileKey,float scale);
	
	/**
	 *<p>Title: downloadFile</p> 
	 *<p>Description: 文件下载二</p> 
	 * @param response
	 * @param path
	 */
	void downloadFile_HuTool(HttpServletResponse response, String path);
	
	/**
	 *<p>Title: downloadFile</p> 
	 *<p>Description: 文件下载一</p> 
	 * @param response
	 * @param path
	 */
	void downloadFile(HttpServletResponse response, String path);
	
	/**
	 *<p>Title: deleteFile</p> 
	 *<p>Description: 删除文件</p> 
	 * @param path
	 * @return
	 */
	Message<String> deleteFile(String path);
	
	
}
