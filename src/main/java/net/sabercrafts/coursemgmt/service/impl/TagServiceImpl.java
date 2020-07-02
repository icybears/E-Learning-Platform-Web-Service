package net.sabercrafts.coursemgmt.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sabercrafts.coursemgmt.entity.Tag;
import net.sabercrafts.coursemgmt.exception.TagServiceException;
import net.sabercrafts.coursemgmt.repository.TagRepository;
import net.sabercrafts.coursemgmt.service.TagService;
import net.sabercrafts.coursemgmt.utils.SlugGenerator;

@Service
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public Tag create(Tag tag) {

		tag.setSlug(SlugGenerator.toSlug(tag.getLabel()));

		Optional<Tag> result = tagRepository.findBySlug(tag.getSlug());

		if (result.isPresent()) {
			throw new TagServiceException("Tag with slug " + tag.getSlug() + " already exists");
		}

		return tagRepository.save(tag);
	}

	@Override
	public Tag getById(Long id) {

		Optional<Tag> result = tagRepository.findById(id);

		if (result.isEmpty()) {
			throw new TagServiceException("Tag with id " + id + " doesn't exist");
		}

		return result.get();

	}

	@Override
	public Tag getBySlug(String slug) {

		Optional<Tag> result = tagRepository.findBySlug(slug);

		if (result.isEmpty()) {
			throw new TagServiceException("Tag with slug " + slug + " doesn't exist");

		}

		return result.get();

	}

	@Override
	public List<Tag> getAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag edit(Tag tag) {

		Optional<Tag> result = tagRepository.findById(tag.getId());

		if (result.isEmpty()) {
			throw new TagServiceException("Tag with id " + tag.getId() + " doesn't exist");

		}

		if(result.get().getLabel() != tag.getLabel()) {
			
			tag.setSlug(SlugGenerator.toSlug(tag.getLabel()));
			
			if(tagRepository.findBySlug(tag.getSlug()).isPresent()) {
				throw new TagServiceException("Tag with slug "+ tag.getSlug() +" already exists.");
			}
		}
		
		return tagRepository.save(tag);
	}

	@Override
	public boolean remove(Tag tag) {

		Optional<Tag> result = tagRepository.findById(tag.getId());

		if (result.isEmpty()) {
			throw new TagServiceException("Tag with id " + tag.getId() + " doesn't exist");
		}

		
		
		tagRepository.delete(tag);
		
		return true;

	}

}
