package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Tag implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String label;
	
	@ManyToMany(mappedBy="tags")
	private Set<Course> courses;
	
	public Tag() {
		
	}
	
	public Tag(String label) {
		this.label = label;
	}
	

}
