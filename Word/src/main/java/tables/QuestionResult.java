package tables;


import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="QuestionResult")
public class QuestionResult implements Serializable{

	@Transient
	private static final long serialVersionUID = -2586535965168350721L;

	@Id
	@SequenceGenerator(name="questionResultSeq", sequenceName="question_result_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="questionResultSeq")
	@Column(name="question_result_id")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="question_id")
	private Question question;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="examresult_id")
	private ExamResult examResult;
	
	@Column(name="user_answer")
	private String userAnswer;
	
	@Column(name="result")
	private int result;
	
	public QuestionResult() {
	}

	public QuestionResult(Question question, ExamResult examResult, int result, String userAnswer) {
		this.question = question;
		this.examResult = examResult;
		this.result = result;
		this.userAnswer = userAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public ExamResult getExamResult() {
		return examResult;
	}

	public void setExamResult(ExamResult examResult) {
		this.examResult = examResult;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	public Integer getQuestionNumber() throws ClassNotFoundException, IOException {
		return this.examResult.getQuestions().indexOf(this);
	}
	
	
}
