package net.sabercrafts.coursemgmt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter@Setter
public class UserLearningPathProgressId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private Long userId;
	
	@Column(name="learning_path_id")
	private Long learningPathId;

	public UserLearningPathProgressId() {
		super();
	}

	public UserLearningPathProgressId(Long userId, Long learningPathId) {
		super();
		this.userId = userId;
		this.learningPathId = learningPathId;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        UserLearningPathProgressId that = (UserLearningPathProgressId) o;
        return Objects.equals(userId, that.getUserId()) &&
               Objects.equals(learningPathId, that.getLearningPathId());
    }
 
	 @Override
	    public int hashCode() {
	        return Objects.hash(userId, learningPathId);
	    }
}
