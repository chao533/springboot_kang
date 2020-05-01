package com.kang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kang.common.msg.Message;
import com.kang.model.param.ImgCutParam;
import com.kang.service.UploadService;

/**
　 * <p>Title: UploadController</p> 
　 * <p>Description: FAST上传</p> 
　 * @author CK 
　 * @date 2020年4月6日
 */
@RestController
public class UploadController {
    
    @Autowired
    private UploadService uploadService;

    /**
     *<p>Title: upload</p> 
     *<p>Description: FAST图片裁剪</p> 
     * @param request
     * @param params
     * @return
     */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Message<?> upload(HttpServletRequest request, ImgCutParam params) {
		int x = params.getX();
		int y = params.getY();
		int width = params.getWidth();
		int height = params.getHeight();
		// 没有裁剪图片，直接上传原图
		if (x == 0 && y == 0 && width == 0 && height == 0) {
			return uploadService.uploadFile(request,"fileKey");
		}
		// 裁剪图片并上传
		return uploadService.uploadCutPic(request, "fileKey", x, y, width, height);
	}
	
	/**
	 *<p>Title: scale</p> 
	 *<p>Description: FAST图片缩放</p> 
	 * @param request
	 * @param scale 缩放比例
	 * @return
	 */
	@RequestMapping(value = "/scale", method = RequestMethod.POST)
	public Message<String> scale(HttpServletRequest request, float scale) {
		// 缩放图片并上传
		return uploadService.uploadCutScale(request, "fileKey", scale);
	}
	
	/**
	 *<p>Title: downloadFile</p> 
	 *<p>Description: FAST文件下载</p> 
	 * @param response
	 * @param path 路径
	 */
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, String path) {
		uploadService.downloadFile(response, path);
	}
	
	/**
	 *<p>Title: deleteFile</p> 
	 *<p>Description: FAST删除文件</p> 
	 * @param path 路径
	 * @return
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	public Message<String> deleteFile(String path) {
		return uploadService.deleteFile(path);
	}

}
