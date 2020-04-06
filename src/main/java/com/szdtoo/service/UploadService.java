package com.szdtoo.service;

import javax.servlet.http.HttpServletRequest;

import com.szdtoo.common.msg.Message;

public interface UploadService {

	/**
	 *<p>Title: uploadFile</p> 
	 *<p>Description: 文件上传</p> 
	 * @param request
	 * @param fileKey
	 * @return
	 */
	Message<String> uploadFile(HttpServletRequest request, String fileKey);
	
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
	Message<String> uploadCutPic(HttpServletRequest request, String fileKey,int x,int y,int width,int height);
	
	/**
	 *<p>Title: uploadCutScale</p> 
	 *<p>Description: 图片缩放并上传</p> 
	 * @param request
	 * @param fileKey
	 * @param scale
	 * @return
	 */
	Message<String> uploadCutScale(HttpServletRequest request, String fileKey,float scale);
	
	
}
