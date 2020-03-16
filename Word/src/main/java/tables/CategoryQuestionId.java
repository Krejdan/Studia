package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoryQuestionId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="question_id")
	private long questionId;
	
	@Column(name="category_id")
	private long categoryId;
	
	public CategoryQuestionId() {
		
	}

	public CategoryQuestionId(long questionId, long categoryId) {
		super();
		this.questionId = questionId;
		this.categoryId = categoryId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
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
		CategoryQuestionId other = (CategoryQuestionId) obj;
		if (categoryId != other.categoryId)
			return false;
		if (questionId != other.questionId)
			return false;
		return true;
	}
	
	

}
