package Utility;

import Dao.GroupDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import Entity.User;
public class Security {

    ApplicationContext context =new ClassPathXmlApplicationContext("Module.xml");
    GroupDao groupDao = (GroupDao) context.getBean("groupDao");
    public boolean procced(int groupId, int permId){
        groupDao.getGroupPerms(groupId);

        return false;
    }
    static User loggedUser= null;

    public static void logout(){
        loggedUser = null;
    }
    public static Boolean isUserLoggedin(){
        if(loggedUser!=null)
            return true;
        return false;
    }
    public static User getLoggedUser(){
        if(loggedUser!=null)
            return loggedUser;
        return null;
    }
    public static void setLoggedUser(User user){
        if(user!=null)
            loggedUser=user;

    }
}
