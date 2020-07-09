package net.sabercrafts.coursemgmt.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.Role;

public interface RoleRepository  extends CrudRepository<Role, Long>{
	
	Optional<Role> findByName(String name);

}
