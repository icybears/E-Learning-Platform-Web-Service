package net.sabercrafts.coursemgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.Category;
import net.sabercrafts.coursemgmt.entity.User;

public interface UserRepository  extends CrudRepository<User, Long>{

	@Override
	List<User> findAll();
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);

}
