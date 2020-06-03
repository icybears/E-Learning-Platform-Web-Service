package net.sabercrafts.coursemgmt.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user_learning_path_progress")
@Data
public class UserLearningPathProgress {

	@EmbeddedId
	private UserLearningPathProgressId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("learningPathId")
	private LearningPath learningPath;
	
	@Column(name="progress_rate")
	private Float progressRate = 0f;

	public UserLearningPathProgress() {
		super();
	}


	public UserLearningPathProgress(User user, LearningPath learningPath, Float progressRate) {
		super();
		this.user = user;
		this.learningPath = learningPath;
		this.id = new UserLearningPathProgressId(user.getId(),learningPath.getId());
		this.progressRate = progressRate;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        UserLearningPathProgress that = (UserLearningPathProgress) o;
        return Objects.equals(user, that.getUser()) &&
               Objects.equals(learningPath, that.getLearningPath());
    }
 
	 @Override
	    public int hashCode() {
	        return Objects.hash(user, learningPath);
	    }
	
}
