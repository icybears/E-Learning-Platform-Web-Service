package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;


@Embeddable
@Getter@Setter
public class EnrollmentId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="course_id")
	private Long courseId;
	
	@Column(name="user_id")
	private Long userId;
	
	public EnrollmentId() {
		
	}
	
	public EnrollmentId(Long courseId, Long userId) {
		this.courseId = courseId;
		this.userId = userId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(courseId, that.getCourseId()) &&
               Objects.equals(userId, that.getUserId());
    }
 
	 @Override
	    public int hashCode() {
	        return Objects.hash(courseId, userId);
	    }
}
