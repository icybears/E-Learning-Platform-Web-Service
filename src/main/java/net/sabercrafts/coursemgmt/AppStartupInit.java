package net.sabercrafts.coursemgmt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.sabercrafts.coursemgmt.entity.Authority;
import net.sabercrafts.coursemgmt.entity.Role;
import net.sabercrafts.coursemgmt.entity.User;
import net.sabercrafts.coursemgmt.repository.AuthorityRepository;
import net.sabercrafts.coursemgmt.repository.RoleRepository;
import net.sabercrafts.coursemgmt.repository.UserRepository;

@Component
public class AppStartupInit {

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	

	
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void initializeDB() {
		System.out.println("APPLICATION READY EVENT FIRED");
		
		Authority read = createAuthority("READ");
		Authority write = createAuthority("WRITE");
		Authority delete = createAuthority("DELETE");
		
		Role roleUser = createRole("ROLE_USER",new HashSet<>(Arrays.asList(read,write)));
		
		Role roleAdmin = createRole("ROLE_ADMIN",new HashSet<>(Arrays.asList(read,write,delete)));
		
		if(!userRepository.existsByEmail("admin@gmail.com")) {
			User adminUser = new User("admin","admin","admin","admin@gmail.com",bcrypt.encode("secret"));
			adminUser.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
			
			userRepository.save(adminUser);
		}
	}
	
	public Authority createAuthority(String name) {
		
		Optional<Authority> result = authorityRepository.findByName(name);
		
		if(result.isEmpty()) {
			return authorityRepository.save(new Authority(name));
		}
		
		return result.get();
	}
	
	public Role createRole(String name, Set<Authority> authorities) {
		
		Optional<Role> result = roleRepository.findByName(name);
		
		if(result.isEmpty()) {
			Role role = new Role(name);
			role.setAuthorities(authorities);
			return roleRepository.save(role);
		}
		
		return result.get();
	}
}
