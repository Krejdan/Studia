package tables;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.Request;
import server.RequestType;
import util.Connection;

@Entity
@Table(name="ExamResults")
public class ExamResult implements Serializable{
	

	@Transient
	private static final long serialVersionUID = 7643988335028212255L;

	@Id
	@SequenceGenerator(name="examResultSeq", sequenceName="examResult_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="examResultSeq")
	@Column(name="examResult_id")
	private int examResultId;
	
	@Column(name="wynik")
	private String wynik;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="theoreticalexam_id")
	private TheoreticalExam theoreticalExam;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="resources_id")
	private Resource resource;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id_egzaminowany")
	private User egzaminowany;
	
	@OneToMany(
			mappedBy="examResult",
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	private List<QuestionResult> questions = new ArrayList<>();
	
	public ExamResult() {
		
	}

	public ExamResult(String wynik, TheoreticalExam theoreticalExam, Resource resource, User egzaminowany) {
		this.wynik = wynik;
		this.theoreticalExam = theoreticalExam;
		this.resource = resource;
		this.egzaminowany = egzaminowany;
	}
	
	public void addQuestion(Question question, int result, String userAnswer) {
		QuestionResult questionResult = new QuestionResult(question, this, result, userAnswer);
		questions.add(questionResult);
	}
	
	public void removeQuestion(Question question) {
		for (Iterator<QuestionResult> iterator = questions.iterator(); iterator.hasNext(); ) {
			QuestionResult questionResult = iterator.next();
			
			if(questionResult.getQuestion().equals(question) && questionResult.getExamResult().equals(this)) {
				iterator.remove();
				questionResult.getQuestion().getExamResults().remove(questionResult);
				questionResult.setExamResult(null);
				questionResult.setQuestion(null);
			}
		}
	}
	
	public int getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(int examResultId) {
		this.examResultId = examResultId;
	}

	public String getWynik() {
		return wynik;
	}

	public void setWynik(String wynik) {
		this.wynik = wynik;
	}

	public TheoreticalExam getTheoreticalExam() {
		return theoreticalExam;
	}

	public void setTheoreticalExam(TheoreticalExam theoreticalExam) {
		this.theoreticalExam = theoreticalExam;
	}

	public Resource getResources() {
		return resource;
	}

	public void setResources(Resource resource) {
		this.resource = resource;
	}

	public User getEgzaminowany() {
		return egzaminowany;
	}

	public void setEgzaminowany(User egzaminowany) {
		this.egzaminowany = egzaminowany;
	}

	public List<QuestionResult> getQuestions() throws IOException, ClassNotFoundException {
		Connection.request = new Request();
		Connection.request.setType(RequestType.GET_QUESTIONS);
		Connection.request.setExamResult(this);

		Connection.getOutput().writeObject(Connection.request);
		
		List<QuestionResult> questions = (List<QuestionResult>) Connection.getInput().readObject();

		return questions;
	}

	public void setQuestions(List<QuestionResult> questions) {
		this.questions = questions;
	}
	
	public Term getTerm() {
		return this.theoreticalExam.getTerm();
	}

	public ExamCategory getCategory() {
		return this.theoreticalExam.getCategory();
	}
	
	public Integer getPoints() {
		Integer points = 0;
		try {
			Connection.request = new Request();
			Connection.request.setType(RequestType.GET_QUESTIONS);
			Connection.request.setExamResult(this);

			Connection.getOutput().writeObject(Connection.request);
			
			List<QuestionResult> questions = (List<QuestionResult>) Connection.getInput().readObject();
			for (QuestionResult questionResult : questions) {
				points += questionResult.getResult();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(points);
		return points;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((egzaminowany == null) ? 0 : egzaminowany.hashCode());
		result = prime * result + examResultId;
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((theoreticalExam == null) ? 0 : theoreticalExam.hashCode());
		result = prime * result + ((wynik == null) ? 0 : wynik.hashCode());
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
		ExamResult other = (ExamResult) obj;
		if (egzaminowany == null) {
			if (other.egzaminowany != null)
				return false;
		} else if (!egzaminowany.equals(other.egzaminowany))
			return false;
		if (examResultId != other.examResultId)
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (theoreticalExam == null) {
			if (other.theoreticalExam != null)
				return false;
		} else if (!theoreticalExam.equals(other.theoreticalExam))
			return false;
		if (wynik == null) {
			if (other.wynik != null)
				return false;
		} else if (!wynik.equals(other.wynik))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExamResult [examResultId=" + examResultId + ", wynik=" + wynik + ", theoreticalExam=" + theoreticalExam
				+ ", resource=" + resource + ", egzaminowany=" + egzaminowany + "]";
	}

	
	
	
}
