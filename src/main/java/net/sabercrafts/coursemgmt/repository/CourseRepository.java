package net.sabercrafts.coursemgmt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.sabercrafts.coursemgmt.entity.Course;


public interface CourseRepository extends CrudRepository<Course, Long>{

}
