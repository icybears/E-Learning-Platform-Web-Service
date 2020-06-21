package net.sabercrafts.coursemgmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.sabercrafts.coursemgmt.entity.Category;
import net.sabercrafts.coursemgmt.repository.CategoryRepository;
import net.sabercrafts.coursemgmt.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category getById(Long id) {
		categoryRepository.findById(id);
		return null;
	}

	@Override
	public Category getBySlug(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category edit(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

}
