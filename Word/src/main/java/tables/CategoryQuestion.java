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
@Table(name="categoryQuestion")
public class CategoryQuestion implements Serializable{
	
	@Transient
	private static final long serialVersionUID = -6479665564021731001L;

	@EmbeddedId
	private CategoryQuestionId id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@MapsId("questionId")
	private Question question;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@MapsId("categoryId")
	private ExamCategory examCategory;
	
	public CategoryQuestion() {
		
	}

	public CategoryQuestion(Question question, ExamCategory examCategory) {
		this.question = question;
		this.examCategory = examCategory;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public ExamCategory getExamCategory() {
		return examCategory;
	}

	public void setExamCategory(ExamCategory examCategory) {
		this.examCategory = examCategory;
	}
	
}
