package net.sabercrafts.coursemgmt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.exception.UserServiceException;
import net.sabercrafts.coursemgmt.repository.CourseRepository;
import net.sabercrafts.coursemgmt.repository.UserRepository;
import net.sabercrafts.coursemgmt.service.UserService;
import net.sabercrafts.coursemgmt.ui.controller.model.request.UserEditRequestModel;
import net.sabercrafts.coursemgmt.utils.SlugGenerator;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public User create(User user) {

		if(userRepository.existsByUsername(user.getUsername())) {
			throw new UserServiceException("Username "+user.getUsername()+" already exists");
		}
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new UserServiceException("Email "+user.getEmail()+" already exists");
		}

		return userRepository.save(user);
	}

	@Override
	public User getById(Long id) {

		
		return fetchEntityById(id);

	}

	@Override
	public User getByUsername(String username) {

		Optional<User> result = userRepository.findByUsername(username);

		if (result.isEmpty()) {
			throw new UserServiceException("User with username " + username + " doesn't exist");

		}

		return result.get();

	}
	
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User edit(Long id, UserEditRequestModel user) {
		
		mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

		User entity = fetchEntityById(id);
		
		mapper.map(user, entity);
		
		return userRepository.save(entity);
	}

	@Override
	public boolean remove(User user) {

		User entity = fetchEntityById(user.getId());

		if(entity.isDeleted() == true) {
			throw new UserServiceException("User with id " +user.getId()+ " is already deleted");
		}
		
		userRepository.delete(entity);
		
		return true;

	}

	@Override
	public Course createCourse(Long userId, Course course) {
		
		course.setSlug(SlugGenerator.toSlug(course.getTitle()));
		
		User entity = fetchEntityById(userId);
		
		entity.addCreatedCourse(course);
		
		return entity.getCreatedCourses().stream().filter(c -> c.getTitle() == course.getTitle()).findFirst().get();
	}

	private User fetchEntityById(Long id) {
		Optional<User> result = userRepository.findById(id);

		if (result.isEmpty()) {
			throw new UserServiceException("User with id " + id + " doesn't exist");
		}
		
		return result.get();
	}

	@Override
	public List<Course> removeCourse(Long courseId, Long userId) {
		User userEntity = fetchEntityById(userId);
		
		Optional<Course> result = courseRepository.findById(courseId);

		if (result.isEmpty()) {
			throw new UserServiceException("Cannot remove course: Course with id " + courseId + " doesn't exist");

		}
		
		Course courseEntity = result.get();

		if(!userEntity.getCreatedCourses().remove(courseEntity)) {
			throw new UserServiceException("Cannot remove course: Course with id " + courseId + " doesn't belong to user with id "+userId);
		}
		
		return new ArrayList<>(userEntity.getCreatedCourses());
	}
	
	}
