package Utility;

import Dao.GroupDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Security {

    ApplicationContext context =new ClassPathXmlApplicationContext("Module.xml");
    GroupDao groupDao = (GroupDao) context.getBean("groupDao");
    public boolean procced(int groupId, int permId){
        groupDao.getGroupPerms(groupId);

        return false;
    }

}
