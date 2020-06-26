package net.sabercrafts.coursemgmt.service;

import java.util.List;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.Module;
import net.sabercrafts.coursemgmt.entity.Tag;

public interface CourseService {

	Course create(Course course);
	Course getById(Long id);
	Course getBySlug(String slug);
	List<Course> getAll();
	Course edit(Course course);
	boolean remove(Long id);
	Course addModule(Long courseId, Module module);
	Course removeModule(Long courseId, Long moduleId);
	Course addTag(Long courseId, Tag tag);
	Course removeTag(Long courseId, Tag tag);
	Course enroll(Long courseId, Long userId);
	Course unenroll(Long courseId, Long userId);
	
	
}
