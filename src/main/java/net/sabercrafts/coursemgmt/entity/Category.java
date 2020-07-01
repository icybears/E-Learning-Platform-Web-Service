package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Category implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId(mutable = true)
	private String slug;
	
	@Column(unique = true)
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	@ToString.Exclude
	List<Course> courses = new ArrayList<>();
	
	public Category() {
		super();
	}

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public void addCourse(Course course) {
		course.setCategory(this);
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (!(o instanceof Category))
	            return false;
	 
	        Category other = (Category) o;
	 
	         return id != null && id.equals(other.getId());
	    }
	 
	    @Override
	    public int hashCode() {
	        return 13;
	    }


		
}
