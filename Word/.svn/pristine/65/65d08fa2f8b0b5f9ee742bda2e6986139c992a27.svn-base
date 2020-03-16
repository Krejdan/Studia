package tables;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="examTaskResult")
public class ExamTaskResult implements Serializable{

	@Transient
	private static final long serialVersionUID = -1234665765639130025L;

	@Id
	@SequenceGenerator(name="examTaskResultSeq", sequenceName="exam_task_result_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="examTaskResultSeq")
	@Column(name="examTaskResult_id")
	private int id;
	
	@Column(name="result")
	private int result;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="task_id")
	private ExamTask examTask;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="examcard_id")
	private ExaminationCard examCard;
	
	public ExamTaskResult() {
		
	}

	public ExamTaskResult(int result, ExamTask examTask, ExaminationCard examCard) {
		this.result = result;
		this.examTask = examTask;
		this.examCard = examCard;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public ExamTask getExamTask() {
		return examTask;
	}

	public void setExamTask(ExamTask examTask) {
		this.examTask = examTask;
	}

	public ExaminationCard getExamCard() {
		return examCard;
	}

	public void setExamCard(ExaminationCard examCard) {
		this.examCard = examCard;
	}
	
}
