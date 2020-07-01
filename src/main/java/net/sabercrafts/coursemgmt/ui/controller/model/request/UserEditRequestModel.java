package net.sabercrafts.coursemgmt.ui.controller.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserEditRequestModel implements Serializable{

	private String firstName;
	private String lastName;
	private String info;
	
}
