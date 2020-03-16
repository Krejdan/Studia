package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QuestionResultId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="question_id")
	private long questionId;
	
	@Column(name="examresult_id")
	private long examResultId;

	public QuestionResultId() {
	}

	public QuestionResultId(long questionId, long examResultId) {
		this.questionId = questionId;
		this.examResultId = examResultId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(long examResultId) {
		this.examResultId = examResultId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (examResultId ^ (examResultId >>> 32));
		result = prime * result + (int) (questionId ^ (questionId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionResultId other = (QuestionResultId) obj;
		if (examResultId != other.examResultId)
			return false;
		if (questionId != other.questionId)
			return false;
		return true;
	}
	
	

}
