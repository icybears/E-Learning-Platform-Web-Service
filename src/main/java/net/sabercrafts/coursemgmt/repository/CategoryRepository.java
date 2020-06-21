package net.sabercrafts.coursemgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.Category;


public interface CategoryRepository extends CrudRepository<Category, Long>{

	@Override
	List<Category> findAll();
	
	Optional<Category> findBySlug(String slug);
	
	boolean existsByName(String name);
	
}
