package net.sabercrafts.coursemgmt.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.sabercrafts.coursemgmt.entity.LearningPath;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.entity.UserLearningPathProgress;

@SpringBootTest
class UserLearningPathProgressRepositoryIntegrationTest {

	private UserLearningPathProgress savedUserLearningPathProgress;
	
	private LearningPath learningPath;
	
	private User user;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserLearningPathProgressRepository userLearningPathProgressRepository;
	
	@Autowired
	private LearningPathRepository learningPathRepository;
	
	@BeforeEach
	void init() {
		user = userRepository.save(new User("test","test","test","test@gmail.com","test123"));
		learningPath = learningPathRepository.save(new LearningPath("LearningPath Test","Description of learningPath Test"));
		savedUserLearningPathProgress = userLearningPathProgressRepository.save(new UserLearningPathProgress(user,learningPath,.2f));
	}
	
	@Test
	void saveUserLearningPathProgress() {
	
		assertTrue(savedUserLearningPathProgress.getId().getLearningPathId() > 0 && savedUserLearningPathProgress.getId().getUserId() > 0);
	}

	@Test
	void findUserLearningPathProgressById() {
		
		assertEquals(savedUserLearningPathProgress.getId(),userLearningPathProgressRepository.findById(savedUserLearningPathProgress.getId()).get().getId());
	}
	@Test
	void updateUserLearningPathProgress() {
		
		UserLearningPathProgress userLearningPathProgress = userLearningPathProgressRepository.findById(savedUserLearningPathProgress.getId()).get();
		
		userLearningPathProgress.setProgressRate(.3f);
		
		userLearningPathProgressRepository.save(userLearningPathProgress);
		
		assertEquals(.3f,userLearningPathProgressRepository.findById(savedUserLearningPathProgress.getId()).get().getProgressRate());
	}
	
	@Test
	void deleteUserLearningPathProgress() {
		
		userLearningPathProgressRepository.delete(savedUserLearningPathProgress);
		
		assertThrows(NoSuchElementException.class,() ->  userLearningPathProgressRepository.findById(savedUserLearningPathProgress.getId()).get());
	}
	

}
