package com.galileo.panel;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import Entity.User;
import Utility.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Dao.GroupDao;
import Dao.PermissionDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	ApplicationContext context =new ClassPathXmlApplicationContext("Module.xml");
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
 	GroupDao groupDao = (GroupDao) context.getBean("groupDao");
 	PermissionDao permissionDao = (PermissionDao) context.getBean("permissionDao");
 	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		if(Security.isUserLoggedin())
			model.addAttribute("serverTime", Security.getLoggedUser().getUsername() );
		
		return "home";
	}
	
}
