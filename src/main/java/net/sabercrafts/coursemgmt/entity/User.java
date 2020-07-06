package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id = ?")
public class User implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId
	@Column(unique= true)
	private String username;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(unique = true)
	private String email;

	@Column(name = "encrypted_password")
	private String encryptedPassword;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	private String info;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_created_course", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	@JsonIgnore
	private Set<Course> createdCourses = new HashSet<>();

	@OneToMany(mappedBy = "user",orphanRemoval = true)
	@JsonManagedReference(value="user-enrollments")
	private List<Enrollment> enrollments = new ArrayList<>();
	
	private boolean deleted;
	
	public User() {
		
	}
	
	public User(String username, String firstName, String lastName, String email, String encryptedPassword) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
	}

	@PreRemove
	public void deleteUser() {
	this.deleted = true;
	}
	

	public void enrollToCourse(Course course) {
		Enrollment enrollment = new Enrollment(course, this);

		this.enrollments.add(enrollment);
		course.getEnrollments().add(enrollment);
	}

	public void unenrollFromCourse(Course course) {
		for (Iterator<Enrollment> iterator = enrollments.iterator(); iterator.hasNext();) {
			Enrollment enrollment = iterator.next();

			if (enrollment.getUser().equals(this) && enrollment.getCourse().equals(course)) {
				iterator.remove();
				enrollment.getCourse().getEnrollments().remove(enrollment);
				enrollment.setCourse(null);
				enrollment.setUser(null);
			}
		}
	}

	public void addCreatedCourse(Course course) {
		course.getCreators().add(this);
		this.createdCourses.add(course);
	}

	public void removeCreatedCourse(Course course) {
		course.getCreators().remove(this);
		this.createdCourses.remove(course);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (!(o instanceof User))
			return false;

		User other = (User) o;

		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return 19;
	}

	
	
}
