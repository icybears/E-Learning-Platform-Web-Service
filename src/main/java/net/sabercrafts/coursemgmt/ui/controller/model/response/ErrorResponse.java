package net.sabercrafts.coursemgmt.ui.controller.model.response;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private Date timestamp;
	private String message;
	
	public ErrorResponse() {
		timestamp = new Date();
	}
	public ErrorResponse(String message) {
		this();
		this.message = message;
	}

}
