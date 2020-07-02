package net.sabercrafts.coursemgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.LearningPath;


public interface LearningPathRepository  extends CrudRepository<LearningPath, Long>{

	@Override
	List<LearningPath> findAll();
	
	Optional<LearningPath> findBySlug(String slug);
	
	boolean existsByTitle(String title);
	
	boolean existsBySlug(String slug);
	
	
}
