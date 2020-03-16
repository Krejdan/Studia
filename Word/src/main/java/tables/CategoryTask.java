package tables;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="categoryTask")
public class CategoryTask implements Serializable{

	@Transient
	private static final long serialVersionUID = 6942520859121681118L;

	@EmbeddedId
	private CategoryTaskId id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@MapsId("categoryId")
	private ExamCategory examCategory;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@MapsId("taskId")
	private ExamTask examTask;
	
	public CategoryTask() {
		
	}

	public CategoryTask(ExamCategory examCategory, ExamTask examTask) {
		this.examCategory = examCategory;
		this.examTask = examTask;
	}

	public ExamCategory getExamCategory() {
		return examCategory;
	}

	public void setExamCategory(ExamCategory examCategory) {
		this.examCategory = examCategory;
	}

	public ExamTask getExamTask() {
		return examTask;
	}

	public void setExamTask(ExamTask examTask) {
		this.examTask = examTask;
	}
	
}
