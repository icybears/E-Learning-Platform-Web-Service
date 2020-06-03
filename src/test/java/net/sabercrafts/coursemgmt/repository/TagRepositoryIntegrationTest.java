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

import net.sabercrafts.coursemgmt.entity.Tag;

@SpringBootTest
public class TagRepositoryIntegrationTest {
	
	private Tag savedTag;
	
	@Autowired
	private TagRepository tagRepository;
	
	@BeforeEach
	void init() {
		savedTag = tagRepository.save(new Tag("Tag Test"));
	}
	
	@Test
	void saveTag() {
	
		assertTrue(savedTag.getId() > 0);
	}

	@Test
	void findTagById() {
		
		assertEquals(savedTag.getId(),tagRepository.findById(savedTag.getId()).get().getId());
	}
	@Test
	void updateTag() {
		
		Tag tag = tagRepository.findById(savedTag.getId()).get();
		
		tag.setLabel("Edited tag Test");
		
		tagRepository.save(tag);
		
		assertEquals("Edited tag Test",tagRepository.findById(savedTag.getId()).get().getLabel());
	}
	
	@Test
	void deleteTag() {
		
		tagRepository.delete(savedTag);
		
		assertThrows(NoSuchElementException.class,() ->  tagRepository.findById(savedTag.getId()).get());
	}
}
