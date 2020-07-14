package net.sabercrafts.coursemgmt.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.ui.controller.model.request.UserEditRequestModel;
import net.sabercrafts.coursemgmt.ui.controller.model.request.UserRegistrationRequestModel;

public interface UserService extends UserDetailsService{

	User create(UserRegistrationRequestModel user);
	
	User getById(Long id);
	
	User getByUsername(String username);
	
	User getByEmail(String email);
	
	@Secured("ROLE_ADMIN")
	List<User> getAll();
	
	@Secured("ROLE_ADMIN")
	boolean remove(User user);
	
	@PreAuthorize("isAuthenticated() and ( #id == principal.id or hasRole('ADMIN') )")
	User edit(Long id, UserEditRequestModel user);
	
	@PreAuthorize("isAuthenticated() or hasRole('ADMIN')")
	Course createCourse(Long userId, Course course);
	
	@PreAuthorize("isAuthenticated() and ( @permissionChecker.isCourseOwner(authentication, #courseId) or hasRole('ADMIN') )")
	List<Course> removeCourse(Long courseId, Long userId);
	
	List<Course> getUserCreatedCourses(Long userId);

	
	
}
