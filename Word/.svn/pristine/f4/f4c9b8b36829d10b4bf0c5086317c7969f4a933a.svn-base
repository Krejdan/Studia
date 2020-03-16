package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExamTaskResultId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="task_id")
	private long taskId;
	
	@Column(name="examcard_id")
	private long examCardId;
	
	public ExamTaskResultId() {
		
	}

	public ExamTaskResultId(long taskId, long examCardId) {
		super();
		this.taskId = taskId;
		this.examCardId = examCardId;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public long getExamCardId() {
		return examCardId;
	}

	public void setExamCardId(long examCardId) {
		this.examCardId = examCardId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (examCardId ^ (examCardId >>> 32));
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
		ExamTaskResultId other = (ExamTaskResultId) obj;
		if (examCardId != other.examCardId)
			return false;
		if (taskId != other.taskId)
			return false;
		return true;
	}
	
	
}
