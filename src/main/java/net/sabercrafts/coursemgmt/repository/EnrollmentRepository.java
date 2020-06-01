package net.sabercrafts.coursemgmt.repository;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.Enrollment;
import net.sabercrafts.coursemgmt.entity.EnrollmentId;

public interface EnrollmentRepository  extends CrudRepository<Enrollment,EnrollmentId>{

}
