package in.nit.hc.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nit.hc.entity.User;
import in.nit.hc.service.IUserService;

@Controller
@RequestMapping("/user")              
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/login")
	public String showLogin() {
		
		return "UserLogin";
	}
	
	@GetMapping("/setup")
	public String setup(HttpSession session, Principal p) {
		
		//read current username
		String username = p.getName();
		
		//get User by username load user Object
		User user = userService.findByUsername(username).get(); // here if we use .get() it will not enforce us to return Optional
		
		//set to session
		session.setAttribute("userOb", user); 
		//session.setMaxInactiveInterval(60); 
		
		return "UserHome";
	}
	
	@GetMapping("/showPwdUpdate")
	public String showUpdateUserPassword() {
		
		return "UserPasswordUpdate";
	}
	
	@PostMapping("/pwdUpdate")
	public String updateUserPassword(@RequestParam(name = "pwd1") String pwd, HttpSession session, Model model) {
		User user = (User) session.getAttribute("userOb");
		userService.updatePassword(pwd, user.getId()); 
		model.addAttribute("pwdUpdateMsg", "Password Updated Successfully !!");
		
		return "UserPasswordUpdate";
	}
	
	@GetMapping("/userProfile")
	public String getUserProfile() {
		
		return "UserProfile";
	}
}






















