package net.sabercrafts.coursemgmt.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.sabercrafts.coursemgmt.entity.Category;

@SpringBootTest
@Transactional
class CategoryRepositoryIntegrationTest {

	private Category savedCategory;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	void init() {
		savedCategory = categoryRepository.save(new Category("Category Test","Description of category Test"));
	}
	
	@Test
	void saveCategory() {
	
		assertTrue(savedCategory.getId() > 0);
	}

	@Test
	void findCategoryById() {
		
		assertEquals(savedCategory.getId(),categoryRepository.findById(savedCategory.getId()).get().getId());
	}
	@Test
	void updateCategory() {
		
		Category category = categoryRepository.findById(savedCategory.getId()).get();
		
		category.setDescription("Edited description of category Test");
		
		assertEquals("Edited description of category Test",categoryRepository.findById(savedCategory.getId()).get().getDescription());
	}
	
	@Test
	void deleteCategory() {
		
		categoryRepository.delete(savedCategory);
		
		assertThrows(NoSuchElementException.class,() ->  categoryRepository.findById(savedCategory.getId()).get());
	}
	
}
