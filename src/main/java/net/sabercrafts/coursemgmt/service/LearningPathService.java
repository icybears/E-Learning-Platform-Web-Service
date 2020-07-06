package net.sabercrafts.coursemgmt.service;

import java.util.List;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.LearningPath;

public interface LearningPathService {

	LearningPath create(LearningPath learningPath);
	LearningPath getById(Long id);
	LearningPath getBySlug(String slug);
	List<LearningPath> getAll();
	LearningPath edit(LearningPath learningPath);
	boolean remove(LearningPath learningPath);
	List<Course> getCoursesInLearningPath(Long id);
	
}
