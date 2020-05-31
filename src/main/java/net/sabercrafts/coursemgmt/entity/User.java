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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

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

	@ManyToMany
	@JoinTable(name = "user_created_course", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<Course> createdCourses = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Enrollment> enrollments = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserLearningPathProgress> learningPathsProgress;

	public void updateUserLearningProgress(LearningPath learningPath, Float progressRate) {
		for (Iterator<UserLearningPathProgress> iterator = learningPathsProgress.iterator(); iterator.hasNext();) {
			UserLearningPathProgress progress = iterator.next();

			if (progress.getUser().equals(this) && progress.getLearningPath().equals(learningPath)) {
				progress.setProgressRate(progressRate);
				return;
			}

		}

		UserLearningPathProgress progress = new UserLearningPathProgress(this, learningPath);
		this.learningPathsProgress.add(progress);

	}
	
	public void removeUserLearningProgress(LearningPath learningPath) {
		for (Iterator<UserLearningPathProgress> iterator = learningPathsProgress.iterator(); iterator.hasNext();) {
			UserLearningPathProgress progress = iterator.next();

			if (progress.getUser().equals(this) && progress.getLearningPath().equals(learningPath)) {
				iterator.remove();
				progress.setUser(null);
				progress.setLearningPath(null);
				
			}

		}
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
