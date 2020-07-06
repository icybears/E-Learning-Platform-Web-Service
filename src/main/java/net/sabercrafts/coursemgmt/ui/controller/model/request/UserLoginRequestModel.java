package net.sabercrafts.coursemgmt.ui.controller.model.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestModel implements Serializable{

	private static final long serialVersionUID = 8820115381713144790L;
	private String email;
	private String password;
	
}
