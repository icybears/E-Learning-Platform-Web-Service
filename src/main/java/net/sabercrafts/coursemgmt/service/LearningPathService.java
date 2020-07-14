package net.sabercrafts.coursemgmt.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.LearningPath;

public interface LearningPathService {

	@Secured("ROLE_ADMIN")
	LearningPath create(LearningPath learningPath);
	
	LearningPath getById(Long id);
	
	LearningPath getBySlug(String slug);
	
	List<LearningPath> getAll();
	
	@Secured("ROLE_ADMIN")
	LearningPath edit(LearningPath learningPath);
	
	@Secured("ROLE_ADMIN")
	boolean remove(LearningPath learningPath);
	
	List<Course> getCoursesInLearningPath(Long id);
	
}
