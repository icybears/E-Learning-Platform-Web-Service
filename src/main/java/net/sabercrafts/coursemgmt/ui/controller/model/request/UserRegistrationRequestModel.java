package net.sabercrafts.coursemgmt.ui.controller.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestModel {

	
	private String username;

	private String firstName;

	private String lastName;

	private String email;

	private String password;
}
