package net.sabercrafts.coursemgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.sabercrafts.coursemgmt.entity.Tag;

public interface TagRepository extends CrudRepository<Tag, Long>{

	@Override
	List<Tag> findAll();
	
	Optional<Tag> findBySlug(String slug);
	Optional<Tag> findByLabel(String label);

}
