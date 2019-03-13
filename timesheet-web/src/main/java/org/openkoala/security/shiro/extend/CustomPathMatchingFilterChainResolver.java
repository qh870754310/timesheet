package org.openkoala.security.shiro.extend;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomPathMatchingFilterChainResolver.class);

	private CustomDefaultFilterChainManager customDefaultFilterChainManager;
	
	private String wso2key;
	private String wso2value;

	public void setCustomDefaultFilterChainManager(CustomDefaultFilterChainManager customDefaultFilterChainManager) {
		this.customDefaultFilterChainManager = customDefaultFilterChainManager;
		setFilterChainManager(customDefaultFilterChainManager);
	}

	public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
		FilterChainManager filterChainManager = getFilterChainManager();
		if (!filterChainManager.hasChains()) {
			return null;
		}

		String requestURI = getPathWithinApplication(request);
		if(CustomAPIManagePath.isWSO2(request,wso2key,wso2value)){
			return customDefaultFilterChainManager.proxy(originalChain, "/wso2");
		}
		
		for (String pathPattern : filterChainManager.getChainNames()) {
			LOGGER.info("pathPattern:{},requestURI:{}", pathPattern, requestURI);
			if (pathMatches(pathPattern, requestURI)) {
				return customDefaultFilterChainManager.proxy(originalChain, pathPattern);
			}
		}
		return null;

	}

	/**
	 * @param wso2key the wso2key to set
	 */
	public void setWso2key(String wso2key) {
		this.wso2key = wso2key;
	}

	/**
	 * @param wso2value the wso2value to set
	 */
	public void setWso2value(String wso2value) {
		this.wso2value = wso2value;
	}
	
}
