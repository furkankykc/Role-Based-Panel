package com.denemefk.panel;


import Dao.GroupDao;
import Dao.PermissionDao;
import Entity.Group;
import Entity.GroupPerms;
import Utility.Security;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PermissionsController {

    ApplicationContext context =new ClassPathXmlApplicationContext("Module.xml");
    GroupDao groupDao = (GroupDao) context.getBean("groupDao");
    PermissionDao permissionDao = (PermissionDao) context.getBean("permissionDao");
   // Security Security =(Security) context.getBean("Security");

//// TODO: 04.02.2018 aynı jsp de 2 form çalışmıyor


    @RequestMapping(value = "/admin/groups", method = RequestMethod.GET)
    public String init(Model model) {
        if(Security.isUserLoggedin()) {
            if (Security.getLoggedUser().hasAccess(1)) {

                model.addAttribute("groups", groupDao.getGroups());

            }
            model.addAttribute("group_perms", new GroupPerms());
            model.addAttribute("permissions", permissionDao.getPermissions());

            return "index";
        }
        return "login";
    }
    @RequestMapping(params = "add",method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute("groupBean") Group group, @RequestParam String add) {
        if(group!=null){
            groupDao.insert(group);
        }

        init(model);
        return "index";
    }

    @RequestMapping(params = "update",method = RequestMethod.POST)
    public String update(HttpServletRequest request,Model model, @ModelAttribute("group_perms") GroupPerms group_perms, @RequestParam String update) {
      try {
            groupDao.setGroupPerms(group_perms.getGroupId(),request.getParameterValues("chckVals"));

      }catch (Exception ex){
          System.out.println(ex.toString());
      }

        init(model);
        return "index";
    }
}
