package com.denemefk.panel;

import Utility.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Dao.UserDao;
import Entity.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	ApplicationContext context =new ClassPathXmlApplicationContext("Module.xml");
	//Security Security =(Security) context.getBean("Security");

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String init(Model model) {
	    model.addAttribute("msg", "HOŞGELDİNİZ");
	    return "login";
	}
	public String initHome(Model model,HttpServletRequest request){
        String referer = request.getHeader("Referer");

        if(Security.isUserLoggedin()){
            if(Security.getLoggedUser().hasAccess(1)){
                return "redirect:/";
            }else {
                return "redirect:/admin/groups";

            }
        }
	    return "index";
    }
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(HttpServletRequest request,Model model, @ModelAttribute("loginBean") User loginBean){
		
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
		    		model.addAttribute("loggedUser",userDao);
					Security.setLoggedUser(userDao);
		    		return initHome(model,request);
		    	}else{
		    		 model.addAttribute("error", "hatali kullanıcı adı yada sifre");
		                return "login";
		    	}
	       } else {
	           model.addAttribute("error", "Lütfen alanları boş bırakmayınız");
	           
	           return "login";
	       }
	   
			
		}
        @RequestMapping(value = "/logout")
		public String logout(){
			Security.logout();
	        return "redirect:/login";
        }

}
