package in.nit.hc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nit.hc.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String userName);
	
	@Modifying
	@Query("UPDATE User set password=:encPwd where id=:userId") //SQL -> UPDATE user_tab SET usr_pwd_col = '$2a$10$6wYmKomgB7MQmMgf2' WHERE usr_id_col = 10;
	void updatePassword(String encPwd, Long userId);					 
}
