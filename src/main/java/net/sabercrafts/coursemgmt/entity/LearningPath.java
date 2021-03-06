package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "learning_path")
public class LearningPath implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;
	
	@NaturalId(mutable = true)
	private String slug;

	@ManyToMany(mappedBy = "learningPaths")
	@JsonIgnore
	private Set<Course> courses = new HashSet<>();

	public LearningPath() {
		super();
	}
	
	public LearningPath(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}


	public void addCourse(Course course) {
		this.courses.add(course);
		course.getLearningPaths().add(this);
	}

	public void removeCourse(Course course) {
		this.courses.remove(course);
		course.getLearningPaths().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (!(o instanceof LearningPath))
			return false;

		LearningPath other = (LearningPath) o;

		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return 15;
	}

}
