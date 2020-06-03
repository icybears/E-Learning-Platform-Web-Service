package net.sabercrafts.coursemgmt.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.sabercrafts.coursemgmt.entity.Category;
import net.sabercrafts.coursemgmt.entity.Course;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class CourseRepositoryIntegrationTest {

	private Course savedCourse;
	
	private Category category;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@BeforeAll
	void pre() {
		category = categoryRepository.save(new Category("Category Test","Description of category Test"));
	}
	
	@BeforeEach
	void init() {
		savedCourse = courseRepository.save(new Course("Title","Description",category));
	}
	
	@Test
	void saveCourse() {
	
		assertTrue(savedCourse.getId() > 0);
	}

	@Test
	void findCourseById() {
		
		assertEquals(savedCourse.getId(), courseRepository.findById(savedCourse.getId()).get().getId());
	}
	@Test
	void updateCourse() {
		
		Course course = courseRepository.findById(savedCourse.getId()).get();
		
		course.setDescription("Edited description of course Test");
		
		courseRepository.save(course);
		
		assertEquals("Edited description of course Test",courseRepository.findById(savedCourse.getId()).get().getDescription());
	}
	
	@Test
	void deleteCourse() {
		
		courseRepository.delete(savedCourse);
		
		assertThrows(NoSuchElementException.class, () ->  courseRepository.findById(savedCourse.getId()).get());
	}
	

}
