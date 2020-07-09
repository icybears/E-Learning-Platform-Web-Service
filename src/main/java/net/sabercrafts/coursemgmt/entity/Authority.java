package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Authority implements Serializable {
	
	private static final long serialVersionUID = 375678051320141321L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	public Authority() {}
	
	public Authority(String name) {
		this.name = name;
	}



}
