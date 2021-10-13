package in.nit.hc.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String userName);
}
