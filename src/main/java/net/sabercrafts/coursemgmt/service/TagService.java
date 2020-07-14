package net.sabercrafts.coursemgmt.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import net.sabercrafts.coursemgmt.entity.Tag;

public interface TagService {

	@Secured("ROLE_ADMIN")
	Tag create(Tag tag);
	
	Tag getById(Long id);
	
	Tag getBySlug(String slug);
	
	List<Tag> getAll();
	
	@Secured("ROLE_ADMIN")
	Tag edit(Tag tag);
	
	@Secured("ROLE_ADMIN")
	boolean remove(Tag tag);
	
}
