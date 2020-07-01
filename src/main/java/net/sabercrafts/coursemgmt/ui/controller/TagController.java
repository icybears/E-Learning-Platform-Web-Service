package net.sabercrafts.coursemgmt.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sabercrafts.coursemgmt.entity.Tag;
import net.sabercrafts.coursemgmt.service.TagService;

@RestController
@RequestMapping("api/v1/tag")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@GetMapping
	public List<Tag> getAllCategories(){
		return tagService.getAll();
	}
	
	
	
	@GetMapping(path="/{id}")
	public Tag getTagById(@PathVariable Long id) {
		return tagService.getById(id);
	}
	
	@PostMapping
	public Tag createTag(@RequestBody Tag tag) {
		return tagService.create(tag);
	}
	
	@PutMapping(path="/{id}")
	public Tag editTag(@PathVariable Long id, @RequestBody Tag tag) {
		return tagService.edit(tag);
	}

	@DeleteMapping(path="/{id}")
	public void deleteTag(@PathVariable Long id) {
		Tag tag = new Tag();
		tag.setId(id);
		tagService.remove(tag);
	}
}
