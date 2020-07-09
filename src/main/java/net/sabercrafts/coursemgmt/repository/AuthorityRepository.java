package net.sabercrafts.coursemgmt.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.Authority;

public interface AuthorityRepository  extends CrudRepository<Authority, Long>{

	Optional<Authority> findByName(String name);
}
