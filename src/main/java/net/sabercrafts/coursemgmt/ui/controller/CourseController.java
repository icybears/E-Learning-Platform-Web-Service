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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.Enrollment;
import net.sabercrafts.coursemgmt.entity.LearningPath;
import net.sabercrafts.coursemgmt.entity.Module;
import net.sabercrafts.coursemgmt.entity.Tag;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.service.CourseService;
import net.sabercrafts.coursemgmt.ui.controller.model.request.CourseEditRequestModel;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public List<Course> getAllCourses(@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit",defaultValue="20") int limit){
		return courseService.getAll(page,limit);
	}
	
	@GetMapping(path="/category/{id}")
	public List<Course> getCoursesByCategoryId(@PathVariable Long id,@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit",defaultValue="20") int limit) {
		return courseService.getByCategoryId(id, page, limit);
	}
	
	@GetMapping(path="/tag/{id}")
	public List<Course> getCoursesByTagId(@PathVariable Long id,@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit",defaultValue="20") int limit) {
		return courseService.getByTagId(id, page, limit);
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
	
	@GetMapping(path="/{courseId}/modules")
	public List<Module> getCourseModules(@PathVariable Long courseId){
		return courseService.getCourseModules(courseId);
	}
	
	@PostMapping(path="/{courseId}/modules")
	public Course createModule(@PathVariable Long courseId,@RequestBody Module module) {
		
		return courseService.addModule(courseId, module);
	}
	
	@DeleteMapping(path="/{courseId}/modules/{moduleId}")
	public Course removeModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
		return courseService.removeModule(courseId, moduleId);
	}
	

	@GetMapping(path="/{courseId}/tags")
	public List<Tag> getCourseTags(@PathVariable Long courseId) {
		return courseService.getCourseTags(courseId);
	}
	
	@PutMapping(path="/{courseId}/tags/{tagId}")
	public Course assignTag(@PathVariable Long courseId,@PathVariable Long tagId) {
		return courseService.addTag(courseId, tagId);
	}
	
	@DeleteMapping(path="/{courseId}/tags/{tagId}")
	public Course remove(@PathVariable Long courseId,@PathVariable Long tagId) {
		return courseService.removeTag(courseId, tagId);
	}
	
	@PostMapping(path="/{courseId}/enrollments")
	public Course enrollUser(@PathVariable Long courseId, @RequestBody User user) {
		return courseService.enroll(courseId, user.getId());
	}
	
	@DeleteMapping(path="/{courseId}/enrollments")
	public Course unenrollUser(@PathVariable Long courseId, @RequestBody User user) {
		return courseService.unenroll(courseId, user.getId());
	}
	
	@GetMapping(path="/{courseId}/learning-paths")
	public List<LearningPath> getCourseLearningPaths(@PathVariable Long courseId) {
		return courseService.getLearningPaths(courseId);
	}
	
	@PostMapping(path="/{courseId}/learning-paths")
	public Course addCourseToLearningPath(@PathVariable Long courseId, @RequestBody LearningPath learningPath) {
		return courseService.addCourseToLearningPath(courseId, learningPath);
	}
	
	@DeleteMapping(path="/{courseId}/learning-paths")
	public Course removeCourseFromLearningPath(@PathVariable Long courseId, @RequestBody LearningPath learningPath) {
		return courseService.removeCourseFromLearningPath(courseId, learningPath);
	}
	
	@PostMapping(path="/{courseId}/completion")
	public Enrollment completeCourse(@PathVariable Long courseId, @RequestBody User user) {
		return courseService.completeCourse(courseId, user.getId());
	}
}
