package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Role implements Serializable {

	private static final long serialVersionUID = 1837037399350644891L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="role_authority", 
	joinColumns = @JoinColumn(name="role_id"),
	inverseJoinColumns = @JoinColumn(name="authority_id"))
	private Set<Authority> authorities;
	
	public Role() {}
	
	public Role(String name) {
		this.name = name;
	}
	
}
