package net.sabercrafts.coursemgmt.ui.controller.model.request;

import java.io.Serializable;

import lombok.Data;

import net.sabercrafts.coursemgmt.entity.Category;

@Data
public class CourseEditRequestModel implements Serializable{
	
 private String title;
 private String description;
 private Category category;
}
