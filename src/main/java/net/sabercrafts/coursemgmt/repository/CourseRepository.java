package net.sabercrafts.coursemgmt.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.sabercrafts.coursemgmt.entity.Course;


public interface CourseRepository extends PagingAndSortingRepository<Course, Long>{

	Page<Course> findByCategoryId(Long id, Pageable pageable);
	
	Page<Course> findByTagsId(Long id, Pageable pageReq);
	
	Optional<Course> findBySlug(String slug);
	
	boolean existsByTitle(String title);
	
	boolean existsBySlug(String slug);

	
}
