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
import net.sabercrafts.coursemgmt.entity.Module;
import net.sabercrafts.coursemgmt.entity.Tag;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.service.CourseService;
import net.sabercrafts.coursemgmt.ui.controller.model.request.CourseEditRequestModel;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public List<Course> getAllCourses(){
		return courseService.getAll();
	}
	

	@GetMapping(path="/{id}")
	public Course getCourseById(@PathVariable Long id) {
		return courseService.getById(id);
	}
	
	@PostMapping
	public Course createCourse(@RequestBody Course course) {
		return courseService.create(course);
	}
	
	@PutMapping(path="/{id}")
	public Course editCourse(@PathVariable Long id, @RequestBody CourseEditRequestModel course) {
		return courseService.edit(id,course);
	}

	@DeleteMapping(path="/{id}")
	public void deleteCourse(@PathVariable Long id) {

		courseService.remove(id);
	}
	
	@GetMapping(path="/{courseId}/module")
	public List<Module> getCourseModules(@PathVariable Long courseId){
		return courseService.getById(courseId).getModules();
	}
	
	@PostMapping(path="/{courseId}/module")
	public Course createModule(@PathVariable Long courseId,@RequestBody Module module) {
		
		return courseService.addModule(courseId, module);
	}
	
	@DeleteMapping(path="/{courseId}/module/{moduleId}")
	public Course removeModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
		return courseService.removeModule(courseId, moduleId);
	}
	

	@GetMapping(path="/{courseId}/tag")
	public List<Tag> getCourseTags(@PathVariable Long courseId) {
		return courseService.getCourseTags(courseId);
	}
	
	@PutMapping(path="/{courseId}/tag/{tagId}")
	public Course assignTag(@PathVariable Long courseId,@PathVariable Long tagId) {
		return courseService.addTag(courseId, tagId);
	}
	
	@DeleteMapping(path="/{courseId}/tag/{tagId}")
	public Course remove(@PathVariable Long courseId,@PathVariable Long tagId) {
		return courseService.removeTag(courseId, tagId);
	}
	
	@PostMapping(path="/{courseId}/enroll")
	public Course enrollUser(@PathVariable Long courseId, @RequestBody User user) {
		return courseService.enroll(courseId, user.getId());
	}
	
	@PostMapping(path="/{courseId}/unenroll")
	public Course unenrollUser(@PathVariable Long courseId, @RequestBody User user) {
		return courseService.unenroll(courseId, user.getId());
	}
}
