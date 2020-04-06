package com.szdtoo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.model.param.ImgCutParam;
import com.szdtoo.service.UploadService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UploadController {
    
    @Autowired
    private UploadService uploadService;

	@ApiOperation("fast图片裁剪")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Message<String> upload(HttpServletRequest request, ImgCutParam params) {
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
	
	@ApiOperation("fast图片缩放")
	@RequestMapping(value = "/scale", method = RequestMethod.POST)
	public Message<String> scale(HttpServletRequest request, float scale) {
		// 缩放图片并上传
		return uploadService.uploadCutScale(request, "fileKey", scale);
	}
	
	@ApiOperation("fast文件下载")
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public Message<String> downloadFile(HttpServletResponse response, String path) {
		uploadService.downloadFile(response, path);
		return new Message<>(ErrorCode.ERROR.SUCCESS);
	}

}
