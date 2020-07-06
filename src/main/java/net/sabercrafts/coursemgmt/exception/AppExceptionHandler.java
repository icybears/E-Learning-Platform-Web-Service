package net.sabercrafts.coursemgmt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import net.sabercrafts.coursemgmt.ui.controller.model.response.ErrorResponse;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = {
			CourseServiceException.class, 
			CategoryServiceException.class,
			LearningPathServiceException.class,
			TagServiceException.class,
			UserServiceException.class
			})
	public ResponseEntity<Object> handleCourseServiceException(Exception ex, WebRequest request){
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
