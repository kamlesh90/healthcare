package in.nit.hc.service;

import java.util.Optional;

import in.nit.hc.entity.User;

public interface IUserService {
	
	public Long saveUser(User user); 
	
	public Optional<User> findByUsername(String username);
	
	public void updatePassword(String pwd, Long userId);
}
