/**
 * 
 */
package org.openkoala.common.core.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiongp
 *
 */
public class CurrentUserHelper {
	private static ThreadLocal<Map<String,Object>> local = new ThreadLocal<Map<String,Object>>(){
		public Map<String,Object> initialValue(){
			return new HashMap<String,Object>();
		};
	};

	public CurrentUserHelper(String userAccount,String roleName,String telePhone){
		local.get().put("userAccount", userAccount);
		local.get().put("roleName", userAccount);
		local.get().put("telePhone", telePhone);
	}
	
    /**
     * 获取当前用户的账号。
     * @return 用户的账号
     */
	public static String getUserAccount() {
		return (String)local.get().get("userAccount");
	}

    /**
     * 获取当前用户的角色名称。
     * @return 用户的角色名称
     */
	public static String getRoleName() {
		return (String)local.get().get("roleName");
	}

    /**
     * 获取当前用户的联系电话
     * @return 用户的联系电话
     */
    public static String getTelePhone(){
		return (String)local.get().get("telePhone");
    }
}
