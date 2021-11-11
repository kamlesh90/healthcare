package in.nit.hc.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.nit.hc.constants.UserRoles;
import in.nit.hc.entity.User;
import in.nit.hc.service.IUserService;
import in.nit.hc.util.EmailUtil;
import in.nit.hc.util.PwdGeneratorUtil;

@Component
public class MasterAccountSetupRunner implements CommandLineRunner{

	@Value("${master.user.name}")
	private String displayname;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private EmailUtil mailUtil;
	
	@Override
	public void run(String... args) throws Exception {
	System.out.println("**********************ADMIN IS REGISTERING**********************");
		if(!userService.findByUsername(username).isPresent()) {
			String pwd = PwdGeneratorUtil.genratePassword();

			User user = new User();
			
			user.setDisplayName(displayname);
			user.setUserName(username); 
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name()); 
			
			Long genId = userService.saveUser(user);
			
			if(genId!=null) {
				new Thread(
						new Runnable() {
							
							@Override
							public void run() {
								String text = "Your username : "+username+" password : "+pwd;
							mailUtil.send(
									user.getUserName(), 
									"ADMIN ADDED", 
									text );
								
							}
						}
						).start();
			}
		}
	System.out.println("**********************ADMIN REGISTRATION HAS BEEN DONE**********************");
	}
}


























