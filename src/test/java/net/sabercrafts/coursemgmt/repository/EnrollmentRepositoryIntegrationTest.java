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
import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.Enrollment;
import net.sabercrafts.coursemgmt.entity.User;

@SpringBootTest
class EnrollmentRepositoryIntegrationTest {

	private Enrollment savedEnrollment;
	private Course course;
	private Category category;
	private User user;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	void init() {
		category = categoryRepository.save(new Category("Category Test 33","Description of category Test"));
		user = userRepository.save(new User("test","test","test","test@gmail.com","test123"));
		course = courseRepository.save(new Course("Title","Description",category));
		savedEnrollment = enrollmentRepository.save(new Enrollment(course, user));
	}
	
	@Test
	void saveEnrollment() {
	
		assertTrue(savedEnrollment.getId().getCourseId() > 0 && savedEnrollment.getId().getUserId() > 0);
	}

	@Test
	void findEnrollmentById() {
		
		assertEquals(savedEnrollment.getId(),enrollmentRepository.findById(savedEnrollment.getId()).get().getId());
	}
	@Test
	void updateEnrollment() {
		
		Enrollment enrollment = enrollmentRepository.findById(savedEnrollment.getId()).get();
		
		enrollment.setCompleted(true);
		
		enrollmentRepository.save(enrollment);
		
		assertEquals(true,enrollmentRepository.findById(savedEnrollment.getId()).get().isCompleted());
	}
	
	@Test
	void deleteEnrollment() {
		
		enrollmentRepository.delete(savedEnrollment);
		
		assertThrows(NoSuchElementException.class,() ->  enrollmentRepository.findById(savedEnrollment.getId()).get());
	}
	

}
