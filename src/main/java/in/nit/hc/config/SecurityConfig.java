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
		.antMatchers("/patient/patient-register","/patient/save").permitAll()
		.antMatchers("/appoint/view","/appoint/search").hasAuthority(UserRoles.PATIENT.name())
		
		.anyRequest().authenticated()
		
		//login details 
		
		.and()
		.formLogin()
		.loginPage("/user/login")
		.loginProcessingUrl("/login") 
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
