package net.sabercrafts.coursemgmt.repository;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.User;

public interface UserRepository  extends CrudRepository<User, Long>{

}
