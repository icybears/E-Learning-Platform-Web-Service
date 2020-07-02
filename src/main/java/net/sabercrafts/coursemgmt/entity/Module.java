package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Module implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String content;
	
	@ManyToOne
	@JsonIgnore
	@ToString.Exclude
	private Course course;
	
	public Module() {
		super();
	}

	public Module(String title, Course course) {
		super();
		this.title = title;
		this.course = course;
	}

	
	public void setCourse(Course course) {
		
		if(this.course != null) {
			this.course.getModules().remove(this);
		}
		
		this.course = course;
		
		if(course != null) {
			this.course.getModules().add(this);
		}
		
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (!(o instanceof Module))
	            return false;
	 
	        Module other = (Module) o;
	 
	        return id != null && id.equals(other.getId());
	    }
	 
	    @Override
	    public int hashCode() {
	        return 17;
	    }
	
}
