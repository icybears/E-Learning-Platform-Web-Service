package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime udpatedAt;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "course",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Module> modules = new ArrayList<>();

	
	@ManyToMany
	@JoinTable(name="course_learning_path",
	joinColumns = @JoinColumn(name="course_id"),
	inverseJoinColumns = @JoinColumn(name="learning_path_id"))
	private Set<LearningPath> learningPaths = new HashSet<>();
	
	@ManyToMany(mappedBy="createdCourses")
	private Set<User> creators = new HashSet<>();
	
	@OneToMany(mappedBy="course",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Enrollment> enrollments = new ArrayList<>();
	
	
	public void setCategory(Category category) {
		System.out.println("custom set category on course entity called");
		if(this.category != null) {
			this.category.getCourses().remove(this);
		}
		this.category = category;
		
		if(category != null) {
			category.getCourses().add(this);
		}
	}
	
	public void addModule(Module module) {
		module.setCourse(this);
	}
	
	public void addToLearningPath(LearningPath learningPath) {
		learningPath.getCourses().add(this);
		this.learningPaths.add(learningPath);
	}
	
	public void removeFromLearningPath(LearningPath learningPath) {
		learningPath.getCourses().remove(this);
		this.learningPaths.remove(learningPath);
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (!(o instanceof Course))
	            return false;
	 
	        Course other = (Course) o;
	 
	        return id != null && id.equals(other.getId());
	    }
	 
	    @Override
	    public int hashCode() {
	        return 14;
	    }
}
