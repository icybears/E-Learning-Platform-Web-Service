package net.sabercrafts.coursemgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.UserLearningPathProgress;
import net.sabercrafts.coursemgmt.entity.UserLearningPathProgressId;

public interface UserLearningPathProgressRepository  extends CrudRepository<UserLearningPathProgress, UserLearningPathProgressId>{
	
	@Modifying
	@Transactional
	@Query("DELETE FROM UserLearningPathProgress ulpp WHERE ulpp.id.learningPathId = ?1")
	void deleteAllByLearningPath(Long learningPathId);
	
}
