package net.sabercrafts.coursemgmt.service;

import java.util.List;

import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.ui.controller.model.request.UserEditRequestModel;

public interface UserService {

	User create(User user);
	User getById(Long id);
	User getByUsername(String username);
	List<User> getAll();

	boolean remove(User user);

	User edit(Long id, UserEditRequestModel user);
	
	
}
