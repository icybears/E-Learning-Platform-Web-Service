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

import net.sabercrafts.coursemgmt.entity.LearningPath;
import net.sabercrafts.coursemgmt.service.LearningPathService;

@RestController
@RequestMapping("api/v1/learning-path")
public class LearningPathController {
	
	@Autowired
	private LearningPathService learningPathService;
	
	@GetMapping
	public List<LearningPath> getAllLearningPaths(){
		return learningPathService.getAll();
	}
	
	@GetMapping(path="/{id}")
	public LearningPath getLearningPathById(@PathVariable Long id) {
		return learningPathService.getById(id);
	}
	
	@PostMapping
	public LearningPath createLearningPath(@RequestBody LearningPath learningPath) {
		return learningPathService.create(learningPath);
	}
	
	@PutMapping(path="/{id}")
	public LearningPath editLearningPath(@PathVariable Long id, @RequestBody LearningPath learningPath) {
		return learningPathService.edit(learningPath);
	}

	@DeleteMapping(path="/{id}")
	public void deleteLearningPath(@PathVariable Long id) {
		LearningPath learningPath = new LearningPath();
		learningPath.setId(id);
		learningPathService.remove(learningPath);
	}
}
