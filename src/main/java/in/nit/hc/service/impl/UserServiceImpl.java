package in.nit.hc.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.hc.entity.User;
import in.nit.hc.repository.UserRepository;
import in.nit.hc.service.IUserService;
import in.nit.hc.util.EmailUtil;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailUtil mailUtil;
	
	@Override
	public Long saveUser(User user) {
		
		// read gen pwd
		String pwd = user.getPassword();
		
		// encode pwd
		String encPwd = passwordEncoder.encode(pwd);
		
		//set back to user 
		user.setPassword(encPwd); 
		
		user  = userRepository.save(user);
		
		return user.getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		Optional<User> opt = userRepository.findByUserName(username);
		
		return opt;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> opt = findByUsername(username);
		
		if(!opt.isPresent()) {
			throw new UsernameNotFoundException(username);
		}else {
			User userhc = opt.get();
			
		return new org.springframework.security.core.userdetails.User(
					userhc.getUserName(),
					userhc.getPassword(), 
					Collections.singletonList(new SimpleGrantedAuthority(userhc.getRole()))
				);
		}
		
	}

	@Transactional
	public void updatePassword(String pwd, Long userId) {
		String encPwd = passwordEncoder.encode(pwd);
		userRepository.updatePassword(encPwd, userId);
		
	}

}

























