package cn.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

@Controller
public class LoginController {
	private Logger logger = Logger.getLogger(LoginController.class);
	//注解Service
	@Resource
	private UserService userService;
	
	//启动页面
	@RequestMapping(value="/login.html")
	public String login() {
		return "login";
	}
	
	//登陆
	@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,
										 @RequestParam String userPassword,
										 HttpServletRequest request,
										 HttpSession session) throws Exception {
		User user = userService.login(userCode, userPassword);
		if(user != null) {
			session.setAttribute(Constants.USER_SESSION,user);
			return "redirect:/sys/main.html";
		}
		request.setAttribute("error", "用户名或密码不正确");
		return "login";
	}
	
	//注销
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.USER_SESSION);
		return "login";
	}
	
	//登陆页面
	@RequestMapping(value="/sys/main.html")
	public String main() {
		return "frame";
	}
}
