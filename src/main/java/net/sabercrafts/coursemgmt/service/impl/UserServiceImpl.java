package net.sabercrafts.coursemgmt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.exception.UserServiceException;
import net.sabercrafts.coursemgmt.repository.UserRepository;
import net.sabercrafts.coursemgmt.service.UserService;
import net.sabercrafts.coursemgmt.ui.controller.model.request.UserEditRequestModel;
import net.sabercrafts.coursemgmt.utils.SlugGenerator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

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

		Optional<User> result = userRepository.findById(id);

		if (result.isEmpty()) {
			throw new UserServiceException("User with id " + id + " doesn't exist");
		}

		return result.get();

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
		

		Optional<User> result = userRepository.findById(id);

		if (result.isEmpty()) {
			throw new UserServiceException("User with id " + id + " doesn't exist");

		}

		User entity = result.get();
		
		BeanUtils.copyProperties(user, entity);
		
		return userRepository.save(entity);
	}

	@Override
	public boolean remove(User user) {

		Optional<User> result = userRepository.findById(user.getId());

		if (result.isEmpty()) {
			throw new UserServiceException("User with id " + user.getId() + " doesn't exist");
		}

		if(user.isDeleted() == true) {
			throw new UserServiceException("User with id " +user.getId()+ " is already deleted");
		}
		
		userRepository.delete(user);
		
		return true;

	}

}
