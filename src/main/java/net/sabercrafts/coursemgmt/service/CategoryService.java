package net.sabercrafts.coursemgmt.service;

import java.util.List;

import net.sabercrafts.coursemgmt.entity.Category;

public interface CategoryService {

	Category getById(Long id);
	Category getBySlug(String slug);
	List<Category> getAll();
	Category edit(Category category);
	boolean remove(Category category);
}
