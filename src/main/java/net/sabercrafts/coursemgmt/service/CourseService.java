package net.sabercrafts.coursemgmt.service;

import java.util.List;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.Enrollment;
import net.sabercrafts.coursemgmt.entity.LearningPath;
import net.sabercrafts.coursemgmt.entity.Module;
import net.sabercrafts.coursemgmt.entity.Tag;
import net.sabercrafts.coursemgmt.ui.controller.model.request.CourseEditRequestModel;

public interface CourseService {

	Course create(Course course);
	Course getById(Long id);
	Course getBySlug(String slug);
	List<Course> getAll(int page, int limit);
	List<Course> getByCategoryId(Long id, int page, int limit);
	List<Course> getByTagId(Long id, int page, int limit);
	Course edit(Long courseId, CourseEditRequestModel course);
	boolean remove(Long id);
	Course addModule(Long courseId, Module module);
	Course removeModule(Long courseId, Long moduleId);
	Course addTag(Long courseId, Long tagId);
	Course removeTag(Long courseId, Long tagId);
	Course enroll(Long courseId, Long userId);
	Course unenroll(Long courseId, Long userId);
	List<Tag> getCourseTags(Long courseId);
	List<LearningPath> getLearningPaths(Long courseId);
	Course addCourseToLearningPath(Long courseId, LearningPath learningPath);
	Course removeCourseFromLearningPath(Long courseId, LearningPath learningPath);
	Enrollment completeCourse(Long courseId, Long userId);

	
}
