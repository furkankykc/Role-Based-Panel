package com.galileo.panel;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Entity.User;

@Controller
public class LoginController {

	
	/*private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	ApplicationContext context =new ClassPathXmlApplicationContext("Module.xml");
	*//**
	 * Simply selects the home view to render by returning its name.
	 *//*
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String init(Model model) {
	    model.addAttribute("msg", "HOŞGELDİNİZ");
	    return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(Model model, @ModelAttribute("loginBean") User loginBean){
		
	   	UserDao loginDao = (UserDao) context.getBean("userDao");
	   	if(loginBean== null){
	   		model.addAttribute("error", "boş bırakmayınız");
	   		return "login";
	   	}
	   	if (loginBean.getUsername() != "" || loginBean.getPassword() != "") {
	   		User userDao=loginDao.getUser(loginBean.getUsername());
	   		if(userDao == null){
	   			model.addAttribute("error", "hatali kullanıcı adı yada sifre");
	   			return "login";
	   		}
		    	if(userDao.getPassword().equals(loginBean.getPassword())){
		    		model.addAttribute("loggedUser",loginBean);
		    		loggedUser = userDao;
		    		return initHome(model);
		    	}else{
		    		 model.addAttribute("error", "hatali kullanıcı adı yada sifre");
		                return "login";
		    	}
		    	
		    	
	   	
	       } else {
	           model.addAttribute("error", "Lütfen alanları boş bırakmayınız");
	           
	           return "login";
	       }
	   
			
		}

	*/
}
