package com.szdtoo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.FdfsClientUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="UploadController",description="upload操作",tags={"upload操作接口"})
@RestController
public class UploadController {
    protected Logger logger = LoggerFactory.getLogger(UploadController.class);
    
    @Autowired
    private FdfsClientUtils fdfsClientUtils;

	@ApiOperation("fast图片裁剪")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x", value = "x坐标", dataType = "Integer", required = false, paramType = "form"),
			@ApiImplicitParam(name = "y", value = "y坐标", dataType = "Integer", required = false, paramType = "form"),
			@ApiImplicitParam(name = "width", value = "宽度", dataType = "Integer", required = false, paramType = "form"),
			@ApiImplicitParam(name = "height", value = "高度", dataType = "Integer", required = false, paramType = "form") 
	})
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Message<String> upload(HttpServletRequest request, int x, int y, int width, int height,
			@ApiParam(name = "fileKey", value = "图片上传") MultipartFile file1) throws Exception {
		// 没有裁剪图片，直接上传原图
		if (x == 0 && y == 0 && width == 0 && height == 0) {
			String uploadImg = fdfsClientUtils.uploadFile(request,"fileKey");
			if (StringUtils.isNotBlank(uploadImg)) {
				return new Message<String>(ErrorCode.SUCCESS, uploadImg);
			}
			return new Message<String>(ErrorCode.ERROR);
		}
		// 裁剪图片并上传
		String subImg = fdfsClientUtils.uploadCutPic(request, "fileKey", x, y, width, height);
		if (StringUtils.isNotBlank(subImg)) {
			return new Message<String>(ErrorCode.SUCCESS, subImg);
		}
		return new Message<String>(ErrorCode.ERROR);
	}

}
