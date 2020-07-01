package net.sabercrafts.coursemgmt.service;

import java.util.List;

import net.sabercrafts.coursemgmt.entity.Tag;

public interface TagService {

	Tag create(Tag tag);
	Tag getById(Long id);
	Tag getBySlug(String slug);
	List<Tag> getAll();
	Tag edit(Tag tag);
	boolean remove(Tag tag);
	
}
