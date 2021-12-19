package in.nit.hc.controller;

import java.security.Principal;
import java.util.Optional;

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
import in.nit.hc.util.EmailUtil;
import in.nit.hc.util.PwdGeneratorUtil;

@Controller
@RequestMapping("/user")           // /user/showGenPwd   
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private EmailUtil mailUtil;
	
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
	
	@GetMapping("/showGenPwd")
	public String showForgotPwd() {
		
		return "NewPasswordGenerator";
	}
	
	@PostMapping("/genPwd")
	public String forgetPwd(@RequestParam(value = "userName",required = true) String email,Model model) {
		Optional<User> opt = userService.findByUsername(email);
		if(opt.isPresent()) {
			User user = opt.get();
			String pwd = PwdGeneratorUtil.genratePassword();
			userService.updatePassword(pwd, user.getId());
			model.addAttribute("message", "new password generated !!");
			
			if(user.getId()!=null) {
				new Thread(
						() -> {
							String text = "New Password "+pwd;
							mailUtil.send(user.getUserName(), "New password", text);
						}
						).start();
			}
		} else {
			model.addAttribute("message", "User Not Present !!");
		}
		
		return "NewPasswordGenerator";
	}
}






















