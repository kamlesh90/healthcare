package in.nit.hc.user.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.user.entity.User;
import in.nit.hc.user.repository.UserRepository;
import in.nit.hc.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Long saveUser(User user) {
		user  = userRepository.save(user);
		
		return user.getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		Optional<User> opt = userRepository.findByUserName(username);
		
		return opt;
	}

}
