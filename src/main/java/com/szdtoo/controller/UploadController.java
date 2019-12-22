package com.szdtoo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.FdfsClientUtils;
import com.szdtoo.model.param.ImgCutParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class UploadController {
    protected Logger logger = LoggerFactory.getLogger(UploadController.class);
    
    @Autowired
    private FdfsClientUtils fdfsClientUtils;

	@ApiOperation("fast图片裁剪")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Message<String> upload(HttpServletRequest request, ImgCutParam params) throws Exception {
		int x = params.getX();
		int y = params.getY();
		int width = params.getWidth();
		int height = params.getHeight();
		// 没有裁剪图片，直接上传原图
		if (x == 0 && y == 0 && width == 0 && height == 0) {
			String uploadImg = fdfsClientUtils.uploadFile(request,"fileKey");
			if (StringUtils.isNotBlank(uploadImg)) {
				return new Message<>(ErrorCode.SUCCESS, uploadImg);
			}
			return new Message<>(ErrorCode.ERROR);
		}
		// 裁剪图片并上传
		String subImg = fdfsClientUtils.uploadCutPic(request, "fileKey", x, y, width, height);
		if (StringUtils.isNotBlank(subImg)) {
			return new Message<>(ErrorCode.SUCCESS, subImg);
		}
		return new Message<>(ErrorCode.ERROR);
	}
	
	@ApiOperation("fast图片缩放")
	@RequestMapping(value = "/scale", method = RequestMethod.POST)
	public Message<String> scale(HttpServletRequest request, float scale,
			@ApiParam(name = "fileKey", value = "图片上传") MultipartFile file1) throws Exception {
		// 缩放图片并上传
		String subImg = fdfsClientUtils.uploadCutScale(request, "fileKey", scale);
		if (StringUtils.isNotBlank(subImg)) {
			return new Message<String>(ErrorCode.SUCCESS, subImg);
		}
		return new Message<String>(ErrorCode.ERROR);
	}

}
