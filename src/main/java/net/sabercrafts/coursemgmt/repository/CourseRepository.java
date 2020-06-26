package net.sabercrafts.coursemgmt.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import net.sabercrafts.coursemgmt.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Long>{

	@Override
	List<Course> findAll();
	
	Optional<Course> findBySlug(String slug);
	
	boolean existsByTitle(String title);
	
	boolean existsBySlug(String slug);
	
}
