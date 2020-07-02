package net.sabercrafts.coursemgmt.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sabercrafts.coursemgmt.entity.Category;
import net.sabercrafts.coursemgmt.service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> getAllCategories(){
		return categoryService.getAll();
	}
	
	@GetMapping(path="/{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return categoryService.getById(id);
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return categoryService.create(category);
	}
	
	@PutMapping(path="/{id}")
	public Category editCategory(@PathVariable Long id, @RequestBody Category category) {
		return categoryService.edit(category);
	}

	@DeleteMapping(path="/{id}")
	public void deleteCategory(@PathVariable Long id) {
		Category category = new Category();
		category.setId(id);
		categoryService.remove(category);
	}
}
