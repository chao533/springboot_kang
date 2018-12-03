package com.szdtoo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.szdtoo.common.constant.Constants;
import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.FdfsClientUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="UploadController",description="upload操作",tags={"upload操作接口"})
@RestController
public class UploadController {
    protected Logger logger = LoggerFactory.getLogger(UploadController.class);
    
    @Autowired
    private FdfsClientUtils fdfsClientUtils;

    @ApiOperation("上传图片测试")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Message<String> upload(HttpServletRequest request,@ApiParam(name="任意",value="图片上传") MultipartFile file1) throws Exception {
    	String uploadFile = fdfsClientUtils.uploadFile(request);
    	if(StringUtils.isNotBlank(uploadFile)) {
    		return new Message<String>(ErrorCode.SUCCESS, uploadFile);	
    	}
    	return new Message<String>(ErrorCode.ERROR);
    }

}
