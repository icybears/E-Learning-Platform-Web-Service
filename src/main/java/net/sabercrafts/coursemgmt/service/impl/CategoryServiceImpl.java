package net.sabercrafts.coursemgmt.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sabercrafts.coursemgmt.entity.Category;
import net.sabercrafts.coursemgmt.exception.CategoryServiceException;
import net.sabercrafts.coursemgmt.repository.CategoryRepository;
import net.sabercrafts.coursemgmt.service.CategoryService;
import net.sabercrafts.coursemgmt.utils.SlugGenerator;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category create(Category category) {

		category.setSlug(SlugGenerator.toSlug(category.getName()));

		Optional<Category> result = categoryRepository.findBySlug(category.getSlug());

		if (result.isPresent()) {
			throw new CategoryServiceException("Category with slug " + category.getSlug() + " already exists");
		}

		return categoryRepository.save(category);
	}

	@Override
	public Category getById(Long id) {

		Optional<Category> result = categoryRepository.findById(id);

		if (result.isEmpty()) {
			throw new CategoryServiceException("Category with id " + id + " doesn't exist");
		}

		return result.get();

	}

	@Override
	public Category getBySlug(String slug) {

		Optional<Category> result = categoryRepository.findBySlug(slug);

		if (result.isEmpty()) {
			throw new CategoryServiceException("Category with slug " + slug + " doesn't exist");

		}

		return result.get();

	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category edit(Category category) {

		Optional<Category> result = categoryRepository.findById(category.getId());

		if (result.isEmpty()) {
			throw new CategoryServiceException("Category with id " + category.getId() + " doesn't exist");

		}

		if(result.get().getName() != category.getName()) {
			
			category.setSlug(SlugGenerator.toSlug(category.getName()));
			
			if(categoryRepository.findBySlug(category.getSlug()).isPresent()) {
				throw new CategoryServiceException("Category with slug "+ category.getSlug() +" already exists.");
			}
		}
		
		return categoryRepository.save(category);
	}

	@Override
	public boolean remove(Category category) {

		Optional<Category> result = categoryRepository.findById(category.getId());

		if (result.isEmpty()) {
			throw new CategoryServiceException("Category with id " + category.getId() + " doesn't exist");
		}

		if (result.get().getCourses().size() > 0) {			
			throw new CategoryServiceException("Category with id " + category.getId() + " contains courses");
		}
		
		categoryRepository.delete(category);
		
		return true;

	}

}
