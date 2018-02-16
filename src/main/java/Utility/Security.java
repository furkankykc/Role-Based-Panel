package com.galileo.panel;

import Dao.GroupDao;
import Dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import Entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

public class Security {

    ApplicationContext context =new ClassPathXmlApplicationContext("Module.xml");
    GroupDao groupDao = (GroupDao) context.getBean("groupDao");
    PermissionDao permissionDao = (PermissionDao) context.getBean("permissionDao");

    public boolean hasAccess(int targetId){
      return groupDao.getAccess(getLoggedUser().getType(),targetId);
    }
    public boolean hasAccess(int groupId,int targetId){
        return groupDao.getAccess(groupId,targetId);
    }
    public boolean procced(int groupId, int permId){
        groupDao.getGroupPerms(groupId);

        return false;
    }
    User loggedUser= null;

    public  void logout(){
        loggedUser = null;
    }
    public Boolean isUserLoggedin(){
        if(loggedUser!=null)
            return true;
        return false;
    }
    public User getLoggedUser(){
        if(loggedUser!=null)
            return loggedUser;
        return null;
    }
    public void setLoggedUser(User user){
        if(user!=null)
            loggedUser=user;

    }
}
