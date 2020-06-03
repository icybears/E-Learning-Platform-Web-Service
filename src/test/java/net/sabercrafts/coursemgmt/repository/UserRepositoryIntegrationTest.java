package net.sabercrafts.coursemgmt.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.sabercrafts.coursemgmt.entity.User;

@SpringBootTest
class UserRepositoryIntegrationTest {

	private User savedUser;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	void init() {
		savedUser = userRepository.save(new User("test","test","test@gmail.com","test123"));
	}
	
	@Test
	void saveUser() {
	
		assertTrue(savedUser.getId() > 0);
	}

	@Test
	void findUserById() {
		
		assertEquals(savedUser.getId(),userRepository.findById(savedUser.getId()).get().getId());
	}
	@Test
	void updateUser() {
		
		User user = userRepository.findById(savedUser.getId()).get();
		
		user.setInfo("Test info");
		
		userRepository.save(user);
		
		assertEquals("Test info",userRepository.findById(savedUser.getId()).get().getInfo());
	}
	
	@Test
	void deleteUser() {
		
		userRepository.delete(savedUser);
		
		assertThrows(NoSuchElementException.class,() ->  userRepository.findById(savedUser.getId()).get());
	}

}
