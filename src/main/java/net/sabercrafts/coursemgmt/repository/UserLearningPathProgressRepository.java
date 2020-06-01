package net.sabercrafts.coursemgmt.repository;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.UserLearningPathProgress;
import net.sabercrafts.coursemgmt.entity.UserLearningPathProgressId;

public interface UserLearningPathProgressRepository  extends CrudRepository<UserLearningPathProgress, UserLearningPathProgressId>{

}
