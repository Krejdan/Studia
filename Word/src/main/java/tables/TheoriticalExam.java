package tables;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TheoriticalExam")
public class TheoriticalExam {
	
	@Id
	@SequenceGenerator(name="theoriticalExamSeq", sequenceName="theoritical_exam_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="theoriticalExamSeq")
	@Column(name="exam_id")
	private int examId;
	
	@Column(name="category")
	private String category;
	
	@Column(name="room_number")
	private String roomNumber;
	
	@ManyToOne
	@JoinColumn(name="term_id")
	private Term term;
	
	@ManyToOne
	@JoinColumn(name="resources_id")
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name="user_id_examiner")
	private User examiner;
	
	public TheoriticalExam() {
		
	}

	public TheoriticalExam(String category, String roomNumber, Term term, Resource resource, User examiner) {
		super();
		this.category = category;
		this.roomNumber = roomNumber;
		this.term = term;
		this.resource = resource;
		this.examiner = examiner;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Resource getResources() {
		return resource;
	}

	public void setResources(Resource resource) {
		this.resource = resource;
	}

	public User getExaminer() {
		return examiner;
	}

	public void setExaminer(User examiner) {
		this.examiner = examiner;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examiner == null) ? 0 : examiner.hashCode());
		result = prime * result + examId;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
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
		TheoriticalExam other = (TheoriticalExam) obj;
		if (examiner == null) {
			if (other.examiner != null)
				return false;
		} else if (!examiner.equals(other.examiner))
			return false;
		if (examId != other.examId)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}
	
	
	
	
}
