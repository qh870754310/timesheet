package com.kcfy.techservicemarket.facade;


import com.kcfy.techservicemarket.facade.dto.UserImport;
import com.kcfy.techservicemarket.facade.dto.weixin.UserDetails;
import com.kcfy.techservicemarket.facade.dto.weixin.UserRole;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.facade.dto.UserDTO;

import java.util.List;


public interface UserFacade {
	
	
	public UserDTO getUser(Long id);


	public void importUsers(List<UserImport> userList);

	Long bindWeixinUser(String openid,String  weixinUserId, String email);

	UserRole getRoleNameByUserId(Long userId);


	UserDetails getUserDeatilsByUserId(Long userId);

	InvokeResult getBindStatus(String openId, String weixinUserId);

	InvokeResult unBindUser(Long userId);

	InvokeResult updateUserInfoByUserId(Long userId, String phone);
}
