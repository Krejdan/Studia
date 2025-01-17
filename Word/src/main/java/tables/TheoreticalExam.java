package tables;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TheoreticalExams")
public class TheoreticalExam implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1489057063553003558L;

	@Id
	@SequenceGenerator(name="theoriticalExamSeq", sequenceName="theoritical_exam_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="theoriticalExamSeq")
	@Column(name="exam_id")
	private int examId;
	
	@Column(name="is_open")
	private short isOpen;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private ExamCategory category;
	
	@ManyToOne
	@JoinColumn(name="term_id") 
	private Term term;
	
	@ManyToOne
	@JoinColumn(name="resources_id")
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name="user_id_examiner")
	private User examiner;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="TheoreticalExamUser",
			joinColumns= {@JoinColumn(name="theoreticalExam") },
			inverseJoinColumns= {@JoinColumn(name="user_id_examined")}
	)
	private List<User> users = new ArrayList<>();
	
	public TheoreticalExam() {
		
	}

	public TheoreticalExam(ExamCategory category, Term term, Resource resource, User examiner) {
		super();
		this.category = category;
		this.term = term;
		this.resource = resource;
		this.examiner = examiner;
	}
	
	public void addUser(User user) {
		users.add(user);
		user.getTheoreticalExams().add(this);
	}
	
	public void removeUser(User user) {
		System.out.println("WYKONUJE");
		users.remove(user);
		user.getTheoreticalExams().remove(this);
	}
	
	
	public short getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(short isOpen) {
		this.isOpen = isOpen;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public ExamCategory getCategory() {
		return category;
	}

	public void setCategory(ExamCategory category) {
		this.category = category;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public int getExamineeNumber() {
		return users.size();
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
		TheoreticalExam other = (TheoreticalExam) obj;
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

	@Override
	public String toString() {
		return "TheoreticalExam [examId=" + examId + ", isOpen=" + isOpen + ", category=" + category + ", term=" + term
				+ ", resource=" + resource + ", examiner=" + examiner + ", users=" + users + "]";
	}
	
	
	
	
	
	
	
	
}
