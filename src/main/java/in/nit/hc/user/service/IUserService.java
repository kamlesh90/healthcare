package in.nit.hc.user.service;

import java.util.Optional;

import in.nit.hc.user.entity.User;

public interface IUserService {
	
	public Long saveUser(User user); 
	
	public Optional<User> findByUsername(String username);
}
