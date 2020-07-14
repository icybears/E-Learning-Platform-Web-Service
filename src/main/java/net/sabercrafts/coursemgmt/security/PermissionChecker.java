package net.sabercrafts.coursemgmt.security;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.service.CourseService;
import net.sabercrafts.coursemgmt.service.UserService;

@Component("permissionChecker")
public class PermissionChecker {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
		
	

	public boolean isSameUser(Authentication auth, Long userId) {
		
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		
		return userPrincipal.getUser().getId() == userId;

	}


	public boolean isCourseOwner(Authentication auth, Long courseId) {
		
		Course courseEntity = courseService.getById(courseId);
		
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		
		User entity = userPrincipal.getUser();
		
		return	userService.getUserCreatedCourses(entity.getId()).contains(courseEntity);
		
		

	}
}
