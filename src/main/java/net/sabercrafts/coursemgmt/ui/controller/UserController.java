package net.sabercrafts.coursemgmt.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.service.UserService;
import net.sabercrafts.coursemgmt.ui.controller.model.request.UserEditRequestModel;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAll();
	}
	
	@GetMapping(path="/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getById(id);
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.create(user);
	}
	
	@PutMapping(path="/{id}")
	public User editUser(@PathVariable Long id, @RequestBody UserEditRequestModel user) {
		return userService.edit(id, user);
	}

	@DeleteMapping(path="/{id}")
	public void deleteUser(@PathVariable Long id) {
		User user = new User();
		user.setId(id);
		userService.remove(user);
		
	}
	
	@PostMapping(path="/{userId}/courses")
	public Course createCourse(@PathVariable Long userId, @RequestBody Course course) {
		return userService.createCourse(userId, course);
	}
	
	@DeleteMapping(path="/{userId}/courses/{courseId}")
	public List<Course> deleteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
		return userService.removeCourse(courseId, userId);
	}
}
