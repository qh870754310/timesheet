/**
 * 
 */
package com.kcfy.techservicemarket.infra.upload;

import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author xiongp
 *
 */
public class UploadUtils {

	/**
	 * 上传限制为20M
	 * 
	 * @param request
	 * @return
	 * 
	 * 		<pre>
	 * 使用方式
	 * MultipartFile file = multiRequest.getFile("file");获取文件
	 * InputStream input = file.getInputStream();获取文件流
	 * File source = new File(filePath);file.transferTo(source);保存文件到本地
	 * String name = multiRequest.getParameter("name");获取参数
	 *         </pre>
	 */
	public static MultipartHttpServletRequest createMultipartHttpServletRequest(HttpServletRequest request) throws MaxUploadSizeExceededException,SizeLimitExceededException{
		return createMultipartHttpServletRequest(request, 20 * 1024 * 1024);
	}

	/**
	 * @param request
	 * @param maxSize
	 *            上次限制大小 单位 Byte
	 * @return
	 * 
	 * <pre>
	 * 使用方式
	 * MultipartFile file = multiRequest.getFile("file");获取文件
	 * InputStream input = file.getInputStream();获取文件流
	 * File source = new File(filePath);file.transferTo(source);保存文件到本地
	 * String name = multiRequest.getParameter("name");获取参数
	 * </pre>
	 */
	public static MultipartHttpServletRequest createMultipartHttpServletRequest(HttpServletRequest request, long maxSize) throws MaxUploadSizeExceededException,SizeLimitExceededException{
		CommonsMultipartResolver resolver = null;
		MultipartHttpServletRequest multiRequest = null;
		resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(20 * 1024 * 1024);// 20M
		resolver.setServletContext(request.getSession().getServletContext());
		resolver.setMaxInMemorySize(1024 * 1024);
		multiRequest = resolver.resolveMultipart(request);
		return multiRequest;
	}
}
