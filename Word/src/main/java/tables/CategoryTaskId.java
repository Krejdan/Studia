package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoryTaskId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="category_id")
	private long categoryId;
	
	@Column(name="task_id")
	private long taskId;
	
	public CategoryTaskId() {
		
	}

	public CategoryTaskId(long categoryId, long taskId) {
		this.categoryId = categoryId;
		this.taskId = taskId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
		result = prime * result + (int) (taskId ^ (taskId >>> 32));
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
		CategoryTaskId other = (CategoryTaskId) obj;
		if (categoryId != other.categoryId)
			return false;
		if (taskId != other.taskId)
			return false;
		return true;
	}
	
	

}
