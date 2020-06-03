package net.sabercrafts.coursemgmt.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.sabercrafts.coursemgmt.entity.LearningPath;

@SpringBootTest
@Transactional
class LearningPathRepositoryIntegrationTest {

	private LearningPath savedLearningPath;
	
	@Autowired
	private LearningPathRepository learningPathRepository;
	
	@BeforeEach
	void init() {
		savedLearningPath = learningPathRepository.save(new LearningPath("LearningPath Test","Description of learningPath Test"));
	}
	
	@Test
	void saveLearningPath() {
	
		assertTrue(savedLearningPath.getId() > 0);
	}

	@Test
	void findLearningPathById() {
		
		assertEquals(savedLearningPath.getId(),learningPathRepository.findById(savedLearningPath.getId()).get().getId());
	}
	@Test
	void updateLearningPath() {
		
		LearningPath learningPath = learningPathRepository.findById(savedLearningPath.getId()).get();
		
		learningPath.setDescription("Edited description of learningPath Test");
		
		assertEquals("Edited description of learningPath Test",learningPathRepository.findById(savedLearningPath.getId()).get().getDescription());
	}
	
	@Test
	void deleteLearningPath() {
		
		learningPathRepository.delete(savedLearningPath);
		
		assertThrows(NoSuchElementException.class,() ->  learningPathRepository.findById(savedLearningPath.getId()).get());
	}
	

}
