/**
 * 
 */
package com.kcfy.techservicemarket.web.controller.sysmgmt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.openkoala.koala.commons.InvokeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kcfy.techservicemarket.infra.upload.UploadUtils;

/**
 * @author xiongp
 *
 */
@Controller
@RequestMapping("upload")
public class UploadController {

	private static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Value("#{configProperties['upload.file.path']}")
	private String defaultPath;
	
	private static final String SLASH = "/";
	private static final String DOT = ".";

	/**
	 * 图片上传
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("img")
	public InvokeResult img(HttpServletRequest request,String id) {
		String path = "image";
		MultipartHttpServletRequest multipartHttpServletRequest = null;
		try {
			multipartHttpServletRequest = UploadUtils
					.createMultipartHttpServletRequest(request);
			path = StringUtils.defaultIfBlank(multipartHttpServletRequest.getParameter("path"), path);
		} catch (MaxUploadSizeExceededException e) {
			logger.error("上传附件大于20M",e);
			return InvokeResult.failure("图片上传附件大于20M");
		} catch (SizeLimitExceededException e){
			logger.error("上传附件大于20M",e);
			return InvokeResult.failure("图片上传附件大于20M");
		}
		MultipartFile file = multipartHttpServletRequest.getFile("file");
		if(file == null){
			return InvokeResult.failure("请先上传文件");
		}
		String definePath = definePath(path);
		String filePath = defaultPath+definePath;
		String fileName = fileName(file);
		try {
			File dir = new File(filePath);
			File source = new File(filePath + fileName);
			if(!dir.exists()){
				dir.mkdirs();
			}
			file.transferTo(source);
		} catch (IOException e) {
			logger.error("图片上传", e);
			return InvokeResult.failure("图片上传失败");
		}
		Map<String,Object> map =new HashMap<>();
		map.put("showImage", "/showImage?filePath="+filePath+fileName);
		map.put("originalFilename", originalFilename(file));
		map.put("fileName", fileName);
		map.put("filePath", definePath + fileName);
		map.put("suffixation" ,fileSuffixation(file));
		return InvokeResult.success(map);
	}
	/*
	 * 显示图片流
	 */
	@RequestMapping("/showImage")
	private void showImage(String filePath,HttpServletResponse response){
		try {
			File file = new File(filePath);
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[4000];//一次读取4000个字节
			response.reset();//清除首部的空白行
			response.addHeader("Content-Length", Long.toString(file.length()));
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("image/jpeg");
			while(is.read(buffer)>0){
				os.write(buffer);
				os.flush();
			}
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(is);
		} catch (Exception e) {
			logger.error("IO异常", e);
		}
	}
	/*
	 * 获取文件后缀名
	 */
	private String fileSuffixation(MultipartFile file){
		String fileName = StringUtils.defaultString(file.getOriginalFilename());
		String[] fileNames = StringUtils.split(fileName, UploadController.DOT);
		int length = fileNames.length;
		if(fileNames.length <= 1){
			return StringUtils.EMPTY;
		}
		return StringUtils.defaultString(fileNames[length-1]);
	}
	
	/*
	 * 获取文件名
	 */
	private String originalFilename(MultipartFile file){
		String fileName = StringUtils.defaultString(file.getOriginalFilename());
		String[] fileNames = StringUtils.split(fileName, UploadController.DOT);
		return StringUtils.defaultString(fileNames[0]);
	}
	
	/*
	 * 生成文件随机名
	 */
	private String fileName(MultipartFile file){
		return UUID.randomUUID()+"."+fileSuffixation(file);
	}
	/*
	 * 设置文件保存路径
	 */
	private String definePath(String path){
		if(!StringUtils.startsWith(path, UploadController.SLASH)){
			path = UploadController.SLASH + path;
		}
		if(!StringUtils.endsWith(path, UploadController.SLASH)){
			path = path + UploadController.SLASH;
		}
		return path;
	}
	
}
