package in.nit.hc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import in.nit.hc.constants.UserRoles;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 // authenticated
		 http.authorizeRequests()
		 
		.antMatchers("/user/login","/login").permitAll()
		.antMatchers("/user/**").permitAll()
		.antMatchers("/patient/patient-register","/patient/save").permitAll()
		
		.antMatchers("/appoint/view","/appoint/viewSlot").hasAuthority(UserRoles.PATIENT.name())
		.antMatchers("/slots/book").hasAuthority(UserRoles.PATIENT.name())
		.antMatchers("/slots/patient-slots").hasAuthority(UserRoles.PATIENT.name())
		
		.antMatchers("/appoint/doc-appoint").hasAuthority(UserRoles.DOCTOR.name())
		.antMatchers("slots/doc-slots").hasAuthority(UserRoles.DOCTOR.name())  
		
		.antMatchers("/spec/spec-register","/spec/save").hasAuthority(UserRoles.ADMIN.name())
		.antMatchers("/doctor/register","/doctor/save").hasAuthority(UserRoles.ADMIN.name())		
		.antMatchers("/appoint/register","/appoint/save").hasAuthority(UserRoles.ADMIN.name())
		
		
		.anyRequest().authenticated()
		
		//login details 
		.and()
		.formLogin()
		.loginPage("/user/login")    //show login page
		.loginProcessingUrl("/login")//do login 
		.defaultSuccessUrl("/user/setup", true)
		.failureUrl("/user/login?error=true")
		
		//logout details
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/user/login?logout=true")
		
		//denied details 
		
		;
	}
	
}
