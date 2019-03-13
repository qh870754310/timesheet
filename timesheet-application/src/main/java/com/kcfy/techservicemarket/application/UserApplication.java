package com.kcfy.techservicemarket.application;

import java.util.List;
import java.util.Set;

import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.facade.dto.weixin.UserDetails;
import com.kcfy.techservicemarket.facade.dto.weixin.UserRole;
import org.openkoala.koala.commons.InvokeResult;
import com.kcfy.techservicemarket.facade.dto.weixin.UserUpdateInfoDTO;
import org.openkoala.organisation.core.domain.Person;
import org.openkoala.security.core.domain.User;

import java.util.List;
import java.util.Set;


public interface UserApplication {
	
	public User getUser(Long id);
	
	public void creatUser(User user);
	
	public void updateUser(User user);
	
	public void removeUser(User user);
	
	public void removeUsers(Set<User>  user);
	
	public List<User> findAllUser();

	public Long bindWeixinUser(String openid, String weixinUserId, String email);

	UserRole getRoleNameByUserId(Long userId);

	UserDetails getUserDeatilsByUserId(Long userId);

	Set<Project> getUserProjects(Long userId);

	public int updateUserInfoByUserId(Long userId, String phone);

	String getBindStatus(String openId, String weixinUserId);

	int unBindUser(Long userId);
}
