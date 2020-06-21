package net.sabercrafts.coursemgmt.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sabercrafts.coursemgmt.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Long>{

	Optional<Course> findBySlug(String slug);
	
	boolean existsByTitle(String title);
	
	boolean existsBySlug(String slug);
	
}
