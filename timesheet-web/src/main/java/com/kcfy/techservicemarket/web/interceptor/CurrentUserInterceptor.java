/**
 * 
 */
package com.kcfy.techservicemarket.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openkoala.common.core.domain.CurrentUserHelper;
import org.openkoala.security.shiro.CurrentUser;
import org.openkoala.security.shiro.extend.CustomAPIManagePath;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author xiongp
 *
 */
public class CurrentUserInterceptor extends HandlerInterceptorAdapter {

	private String wso2key;
	private String wso2value;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!CustomAPIManagePath.isWSO2(request, wso2key, wso2value)){
			new CurrentUserHelper(CurrentUser.getUserAccount(), CurrentUser.getRoleName(), CurrentUser.getTelePhone());
		}
		return true;
	}

	/**
	 * @return the wso2key
	 */
	public String getWso2key() {
		return wso2key;
	}

	/**
	 * @param wso2key
	 *            the wso2key to set
	 */
	public void setWso2key(String wso2key) {
		this.wso2key = wso2key;
	}

	/**
	 * @return the wso2value
	 */
	public String getWso2value() {
		return wso2value;
	}

	/**
	 * @param wso2value
	 *            the wso2value to set
	 */
	public void setWso2value(String wso2value) {
		this.wso2value = wso2value;
	}

}
