package net.sabercrafts.coursemgmt.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.Enrollment;
import net.sabercrafts.coursemgmt.entity.LearningPath;
import net.sabercrafts.coursemgmt.entity.Module;
import net.sabercrafts.coursemgmt.entity.Tag;
import net.sabercrafts.coursemgmt.ui.controller.model.request.CourseEditRequestModel;

public interface CourseService {

	
	@PreAuthorize("isAuthenticated() or hasRole('ADMIN')")
	Course create(Course course);
	
	Course getById(Long id);
	
	Course getBySlug(String slug);
	
	List<Course> getAll(int page, int limit);
	
	List<Course> getByCategoryId(Long id, int page, int limit);
	
	List<Course> getByTagId(Long id, int page, int limit);
	
	@PreAuthorize("isAuthenticated() and ( @permissionChecker.isCourseOwner(authentication, #courseId) or hasRole('ADMIN') )")
	Course edit(Long courseId, CourseEditRequestModel course);
	
	@PreAuthorize("isAuthenticated() and ( @permissionChecker.isCourseOwner(authentication, #courseId) or hasRole('ADMIN') )")
	boolean remove(Long id);
	
	@PreAuthorize("isAuthenticated() and ( @permissionChecker.isCourseOwner(authentication, #courseId) or hasRole('ADMIN') )")
	Course addModule(Long courseId, Module module);
	
	@PreAuthorize("isAuthenticated() and ( @permissionChecker.isCourseOwner(authentication, #courseId) or hasRole('ADMIN') )")
	Course removeModule(Long courseId, Long moduleId);
	
	List<Module> getCourseModules(Long courseId);
	
	@PreAuthorize("isAuthenticated() and ( @permissionChecker.isCourseOwner(authentication, #courseId) or hasRole('ADMIN') )")
	Course addTag(Long courseId, Long tagId);
	
	@PreAuthorize("isAuthenticated() and ( @permissionChecker.isCourseOwner(authentication, #courseId) or hasRole('ADMIN') )")
	Course removeTag(Long courseId, Long tagId);
	
	@PreAuthorize("isAuthenticated()")
	Course enroll(Long courseId, Long userId);
	
	@PreAuthorize("isAuthenticated()")
	Course unenroll(Long courseId, Long userId);
	
	List<Tag> getCourseTags(Long courseId);
	
	List<LearningPath> getLearningPaths(Long courseId);
	
	@Secured("ROLE_ADMIN")
	Course addCourseToLearningPath(Long courseId, LearningPath learningPath);
	
	@Secured("ROLE_ADMIN")
	Course removeCourseFromLearningPath(Long courseId, LearningPath learningPath);
	
	@PreAuthorize("isAuthenticated()")
	Enrollment completeCourse(Long courseId, Long userId);

	
}
