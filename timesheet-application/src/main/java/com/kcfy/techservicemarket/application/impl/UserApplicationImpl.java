package com.kcfy.techservicemarket.application.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.Query;

import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.facade.dto.weixin.UserInfo;
import com.kcfy.techservicemarket.facade.dto.weixin.UserDetails;
import com.kcfy.techservicemarket.facade.dto.weixin.UserRole;
import com.kcfy.techservicemarket.facade.dto.weixin.UserUpdateInfoDTO;
import groovy.sql.Sql;
import org.dayatang.domain.Entity;
import org.dayatang.domain.JpqlQuery;
import org.dayatang.domain.SqlQuery;
import org.openkoala.organisation.core.domain.Person;
import org.openkoala.security.core.domain.Actor;
import org.openkoala.security.core.domain.Authorization;
import org.openkoala.security.core.domain.User;
import org.openkoala.security.org.core.domain.EmployeeUser;
import org.springframework.transaction.annotation.Transactional;

import com.kcfy.techservicemarket.application.UserApplication;


@Named
@Transactional
public class UserApplicationImpl implements UserApplication {

    public User getUser(Long id) {
        return User.get(User.class, id);
    }

    public void creatUser(User user) {
        user.save();
    }

    public void updateUser(User user) {
        user.save();
    }

    public void removeUser(User user) {
        if (user != null) {
            user.remove();
        }
    }

    @Override
    public Set<Project> getUserProjects(Long userId) {
        return User.get(User.class, userId).getProjects();
    }

    public void removeUsers(Set<User> users) {
        for (User user : users) {
            user.remove();
        }
    }

    public List<User> findAllUser() {
        return User.findAll(User.class);
    }

    @Override
    public String getBindStatus(String openId, String weixinUserId) {
        List list = User.getRepository().createJpqlQuery("select id from User where openid = ? or weixinUserId = ?")
                .setParameters(openId,weixinUserId).list();
        return list.size() > 0 ? list.get(0).toString() :"" ;
    }

    @Override
    public Long bindWeixinUser(String openid, String weixinUserId, String email) {
        List<EmployeeUser> list = User.getRepository().createCriteriaQuery(EmployeeUser.class).eq("employee.person.email", email).list();
        if(list.size() > 0) {
            String sql = "update KS_ACTORS set openid=?,weixin_userid=? where EMPLOYEE_ID=(" +
                    "select ID from KO_PARTIES WHERE PERSON_ID=(" +
                    "select ID from KO_PERSONS where EMAIL=?))";
            SqlQuery sqlQuery = new SqlQuery(User.getRepository(), sql);
            sqlQuery.setParameters(openid, weixinUserId, email).executeUpdate();
        }
        return list.size() > 0 ? list.get(0).getId() : null;
    }

    @Override
    public int unBindUser(Long userId) {
        String sql = "update KS_ACTORS set openid='',weixin_userid='' where id=?";
        SqlQuery sqlQuery = new SqlQuery(User.getRepository(), sql);
        return sqlQuery.setParameters(userId).executeUpdate();
    }


    @Override
    public UserRole getRoleNameByUserId(Long userId) {
        List<Authorization> list = Authorization.getRepository().createCriteriaQuery(Authorization.class).eq("actor.id", userId).list();
        String roleName = "";
        for(Authorization authorization: list) {
            roleName = authorization.getAuthority().getName() + ",";
        }
        UserRole userRole = new UserRole();
        if(list.size() > 0) {
            userRole.setUserId(userId.toString());
            userRole.setUserName(list.get(0).getActor().getName());
            userRole.setRoleName(roleName.substring(0, roleName.length() - 1));
        }
        return userRole;
    }

    @Override
    public UserDetails getUserDeatilsByUserId(Long userId) {
        String sql = "select distinct partyorg.name as org,actor.id as userId,per.name as userName,partypost.name as post," +
                "authorities.name as roleName,per.MOBILE_PHONE as phone,per.email as email \n" +
                "from ko_parties partyorg \n" +
                "left join ko_parties partypost \n" +
                "on partyorg.id = partypost.org_id\n" +
                "left join ko_accountabilities account  \n" +
                "on partypost.id = account.commissioner_id \n" +
                "left join ko_parties emp \n" +
                "on account.responsible_id =emp.id \n" +
                "left join ks_actors actor \n" +
                "on actor.EMPLOYEE_ID = emp.id\n" +
                "left join ko_persons per \n" +
                "on per.id = emp.PERSON_ID \n" +
                "left join ks_authorizations auth \n" +
                "on auth.ACTOR_ID=actor.id \n" +
                "left join ks_authorities authorities \n" +
                "on authorities.id = auth.AUTHORITY_ID \n" +
                "where actor.ID = ?";
        SqlQuery sqlQuery = new SqlQuery(User.getRepository(), sql);
        List<Object[]> list = sqlQuery.setParameters(userId).list();
        UserDetails userDetails = new UserDetails();
        if(list.size() > 0) {
            Object[] objects = list.get(0);
            userDetails.setDeptName(objects[0].toString());
            userDetails.setUserId(objects[1].toString());
            userDetails.setUserName(objects[2].toString());
            userDetails.setUserPost(objects[3].toString());
            userDetails.setRoleName(objects[4].toString());
            userDetails.setPhone(objects[5].toString());
            userDetails.setEmail(objects[6].toString());
        }else{
            userDetails = null;
        }
        return userDetails;
    }

    @Override
    public int updateUserInfoByUserId(Long userId, String phone) {
        String sql = "UPDATE ko_persons a SET a.MOBILE_PHONE= ? WHERE a.id =(\n" +
                "SELECT par.PERSON_ID FROM ko_parties par WHERE par.ID = (\n" +
                "SELECT actor.EMPLOYEE_ID FROM ks_actors actor WHERE actor.ID = ? )) ";
        SqlQuery sqlQuery = new SqlQuery(User.getRepository(), sql);
        int count = sqlQuery.setParameters(phone, userId).executeUpdate();
        return count;
    }
}
