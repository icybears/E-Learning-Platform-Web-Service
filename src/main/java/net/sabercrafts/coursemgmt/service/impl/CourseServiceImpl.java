package net.sabercrafts.coursemgmt.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.sabercrafts.coursemgmt.entity.Course;
import net.sabercrafts.coursemgmt.entity.Enrollment;
import net.sabercrafts.coursemgmt.entity.EnrollmentId;
import net.sabercrafts.coursemgmt.entity.LearningPath;
import net.sabercrafts.coursemgmt.entity.Module;
import net.sabercrafts.coursemgmt.entity.Tag;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.exception.CourseServiceException;
import net.sabercrafts.coursemgmt.repository.CourseRepository;
import net.sabercrafts.coursemgmt.repository.EnrollmentRepository;
import net.sabercrafts.coursemgmt.repository.LearningPathRepository;
import net.sabercrafts.coursemgmt.repository.ModuleRepository;
import net.sabercrafts.coursemgmt.repository.TagRepository;
import net.sabercrafts.coursemgmt.repository.UserRepository;
import net.sabercrafts.coursemgmt.service.CourseService;
import net.sabercrafts.coursemgmt.ui.controller.model.request.CourseEditRequestModel;
import net.sabercrafts.coursemgmt.utils.SlugGenerator;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private LearningPathRepository learningPathRepository;

	@Override
	public Course create(Course course) {

		course.setSlug(SlugGenerator.toSlug(course.getTitle()));

		Optional<Course> result = courseRepository.findBySlug(course.getSlug());

		if (result.isPresent()) {
			throw new CourseServiceException("Course with slug " + course.getSlug() + " already exists");
		}

		return courseRepository.save(course);
	}

	@Override
	public Course getById(Long id) {

		return fetchEntityById(id);

	}

	@Override
	public Course getBySlug(String slug) {

		Optional<Course> result = courseRepository.findBySlug(slug);

		if (result.isEmpty()) {
			throw new CourseServiceException("Course with slug " + slug + " doesn't exist");

		}

		return result.get();

	}

	@Override
	public List<Course> getAll(int page, int limit) {

		Pageable pageReq = PageRequest.of(page, limit);

		return courseRepository.findAll(pageReq).getContent();
	}

	@Override
	public List<Course> getByCategoryId(Long id, int page, int limit) {
		Pageable pageReq = PageRequest.of(page, limit);
		return courseRepository.findByCategoryId(id, pageReq).getContent();

	}

	@Override
	public List<Course> getByTagId(Long id, int page, int limit) {
		Pageable pageReq = PageRequest.of(page, limit);
		return courseRepository.findByTagsId(id, pageReq).getContent();

	}

	@Override
	public Course edit(Long courseId, CourseEditRequestModel course) {

		mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

		Course entity = fetchEntityById(courseId);

		if (course.getTitle() != null && entity.getTitle() != course.getTitle()) {

			String slug = SlugGenerator.toSlug(course.getTitle());

			if (courseRepository.findBySlug(slug).isPresent()) {
				throw new CourseServiceException("Course with slug " + slug + " already exists.");
			}

			entity.setSlug(slug);
		}

		mapper.map(course, entity);

		return courseRepository.save(entity);
	}

	@Override
	public boolean remove(Long id) {

		courseRepository.delete(fetchEntityById(id));

		return true;

	}

	@Override
	public Course addModule(Long courseId, Module module) {
		Course entity = fetchEntityById(courseId);
		entity.addModule(module);

		return entity;

	}

	@Override
	public Course removeModule(Long courseId, Long moduleId) {
		Course entity = fetchEntityById(courseId);
		Optional<Module> module = moduleRepository.findById(moduleId);
		if (module.isEmpty()) {
			throw new CourseServiceException("Cannot remove module: module with id " + moduleId + " doesn't exist");
		}
		System.out.println("module: " + module.get());
		entity.removeModule(module.get());

		return entity;

	}

	@Override
	public List<Tag> getCourseTags(Long courseId) {
		Course entity = fetchEntityById(courseId);
		return new ArrayList<Tag>(entity.getTags());
	}

	@Override
	public Course addTag(Long courseId, Long tagId) {

		Course entity = fetchEntityById(courseId);

		Optional<Tag> result = tagRepository.findById(tagId);
		if (result.isEmpty()) {
			throw new CourseServiceException("Tag with id " + tagId + " doesn't exist");
		}
		entity.addTag(result.get());

		return entity;
	}

	@Override
	public Course removeTag(Long courseId, Long tagId) {
		Course entity = fetchEntityById(courseId);
		Optional<Tag> result = tagRepository.findById(tagId);
		if (result.isEmpty()) {
			throw new CourseServiceException("Tag with id " + tagId + " doesn't exist");
		}
		entity.removeTag(result.get());

		return entity;
	}

	@Override
	public Course enroll(Long courseId, Long userId) {
		Course course = fetchEntityById(courseId);
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new CourseServiceException("Cannot enroll to course: User with id " + userId + " doesn't exist");
		}

		Optional<Enrollment> enrollmentCheck = enrollmentRepository.findById(new EnrollmentId(courseId, userId));
		if (enrollmentCheck.isPresent()) {
			throw new CourseServiceException("Cannot enroll to course: User with id " + userId
					+ " already enrolled to course with id " + courseId);
		}

		Enrollment enrollment = new Enrollment(course, user.get());

		course.addEnrollment(enrollment);
		return course;
	}

	@Override
	public Course unenroll(Long courseId, Long userId) {

		Course course = fetchEntityById(courseId);

		Optional<User> user = userRepository.findById(userId);

		if (user.isEmpty()) {
			throw new CourseServiceException("Cannot unenroll from course: User with id " + userId + " doesn't exist");
		}

		Optional<Enrollment> enrollment = enrollmentRepository.findById(new EnrollmentId(courseId, userId));
		if (enrollment.isEmpty()) {
			throw new CourseServiceException("Cannot unenroll from course: Enrollment not found for courseId "
					+ courseId + " and userId " + userId);
		}
		course.removeEnrollment(enrollment.get());
		return course;
	}

	@Override
	public List<LearningPath> getLearningPaths(Long courseId) {
		Course course = fetchEntityById(courseId);

		return new ArrayList<LearningPath>(course.getLearningPaths());
	}

	@Override
	public Course addCourseToLearningPath(Long courseId, LearningPath learningPath) {

		Course course = fetchEntityById(courseId);

		Optional<LearningPath> result = learningPathRepository.findById(learningPath.getId());

		if (result.isEmpty()) {
			throw new CourseServiceException("LearningPath with id " + learningPath.getId() + " doesn't exist");
		}

		boolean isNotDuplicate = course.getLearningPaths().add(learningPath);

		if (isNotDuplicate == false) {
			throw new CourseServiceException("Course with id " + courseId
					+ " already belongs to the learning path with id " + learningPath.getId());
		}

		return course;

	}

	@Override
	public Course removeCourseFromLearningPath(Long courseId, LearningPath learningPath) {

		Course course = fetchEntityById(courseId);

		Optional<LearningPath> result = learningPathRepository.findById(learningPath.getId());

		if (result.isEmpty()) {
			throw new CourseServiceException("LearningPath with id " + learningPath.getId() + " doesn't exist");
		}

		boolean exists = course.getLearningPaths().remove(learningPath);

		if (exists == false) {
			throw new CourseServiceException(
					"Course with id " + courseId + " does not belong to learning path with id " + learningPath.getId());
		}

		return course;

	}

	@Override
	public Enrollment completeCourse(Long courseId, Long userId) {
		Course course = fetchEntityById(courseId);
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new CourseServiceException("Cannot complete course: User with id " + userId + " doesn't exist");
		}

		Optional<Enrollment> enrollment = enrollmentRepository.findById(new EnrollmentId(courseId, userId));

		if (enrollment.isEmpty()) {
			throw new CourseServiceException(
					"Cannot complete course: User with id " + userId + " not enrolled in course with id " + courseId);
		}

		Enrollment entity = enrollment.get();

		entity.setCompleted(true);
		entity.setCompletionDate(LocalDateTime.now());

		return entity;
	}

	@Override
	public List<Module> getCourseModules(Long courseId) {

		Course course = fetchEntityById(courseId);

		return new ArrayList<>(course.getModules());
	}

	private Course fetchEntityById(Long id) {

		Optional<Course> result = courseRepository.findById(id);

		if (result.isEmpty()) {
			throw new CourseServiceException("Course with id " + id + " doesn't exist");

		}

		return result.get();
	}

}
