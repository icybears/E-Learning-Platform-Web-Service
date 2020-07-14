package net.sabercrafts.coursemgmt.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import net.sabercrafts.coursemgmt.entity.Category;

public interface CategoryService {

	@Secured("ROLE_ADMIN")
	Category create(Category category);
	
	Category getById(Long id);
	
	Category getBySlug(String slug);
	
	List<Category> getAll();
	
	@Secured("ROLE_ADMIN")
	Category edit(Category category);
	
	@Secured("ROLE_ADMIN")
	boolean remove(Category category);
	
}
