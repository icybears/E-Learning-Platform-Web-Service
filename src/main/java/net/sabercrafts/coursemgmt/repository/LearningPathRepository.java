package net.sabercrafts.coursemgmt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.sabercrafts.coursemgmt.entity.LearningPath;


public interface LearningPathRepository  extends CrudRepository<LearningPath, Long>{

}
