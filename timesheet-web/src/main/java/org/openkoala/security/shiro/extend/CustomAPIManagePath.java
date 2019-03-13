/**
 * 
 */
package org.openkoala.security.shiro.extend;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author xiongp
 *
 */
public class CustomAPIManagePath {
	
	private CustomAPIManagePath() {
	}

	public static boolean isWSO2(ServletRequest request,String wso2key,String wso2value) {
		return StringUtils.equals(request.getParameter(wso2key), wso2value);
	}
}
