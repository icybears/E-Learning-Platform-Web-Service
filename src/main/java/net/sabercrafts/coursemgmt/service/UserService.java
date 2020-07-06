package net.sabercrafts.coursemgmt.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.ui.controller.model.request.UserEditRequestModel;
import net.sabercrafts.coursemgmt.ui.controller.model.request.UserRegistrationRequestModel;

public interface UserService extends UserDetailsService{

	User create(UserRegistrationRequestModel user);
	User getById(Long id);
	User getByUsername(String username);
	List<User> getAll();
	boolean remove(User user);
	User edit(Long id, UserEditRequestModel user);
	Course createCourse(Long userId, Course course);
	List<Course> removeCourse(Long courseId, Long userId);

	
	
}
