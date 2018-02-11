package Entity;

import Dao.GroupDao;
import Dao.PermissionDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GroupPerms {
    int groupId;
    int permId;

    public GroupPerms(int groupId, int permId) {
        this.groupId = groupId;
        this.permId = permId;
    }
    public GroupPerms() {
        this.groupId = 0;
        this.permId = 0;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPermId() {
        return permId;
    }

    public void setPermId(int permId) {
        this.permId = permId;
    }

    @Override
    public String toString() {
        return "GroupPerms{" +
                "groupId=" + groupId +
                ", permId=" + permId +
                '}';
    }
    public static boolean getAcces(int gid,int pid){

        try {
            ApplicationContext context =new ClassPathXmlApplicationContext("Module.xml");
            GroupDao groupDao = (GroupDao) context.getBean("groupDao");
            return groupDao.getAccess(gid,pid);
        }catch (NullPointerException ex){
            return false;
        }


        }

}
