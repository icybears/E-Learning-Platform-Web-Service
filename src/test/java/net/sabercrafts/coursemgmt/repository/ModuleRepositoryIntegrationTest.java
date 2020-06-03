package net.sabercrafts.coursemgmt.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.sabercrafts.coursemgmt.entity.Category;
import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.Module;

@SpringBootTest
@Transactional
class ModuleRepositoryIntegrationTest {

	private Module savedModule;
	private Course course;
	private Category category;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	void init() {
		
		category = categoryRepository.save(new Category("Category Test","Description of category Test"));
		course = courseRepository.save(new Course("Title","Description",category));
		
		savedModule = moduleRepository.save(new Module("Module Test", course));
	}
	
	@Test
	void saveModule() {
	
		assertTrue(savedModule.getId() > 0);
	}

	@Test
	void findModuleById() {
		
		assertEquals(savedModule.getId(),moduleRepository.findById(savedModule.getId()).get().getId());
	}
	@Test
	void updateModule() {
		
		Module module = moduleRepository.findById(savedModule.getId()).get();
		
		module.setTitle("Edited title of module Test");
		
		moduleRepository.save(module);
		
		assertEquals("Edited title of module Test",moduleRepository.findById(savedModule.getId()).get().getTitle());
	}
	
	@Test
	void deleteModule() {
		
		moduleRepository.delete(savedModule);
		
		assertThrows(NoSuchElementException.class,() ->  moduleRepository.findById(savedModule.getId()).get());
	}
}
