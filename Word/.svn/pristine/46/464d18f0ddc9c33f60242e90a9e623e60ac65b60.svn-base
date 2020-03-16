package tables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
 
@Entity
@Table(name="ExamCategories")
public class ExamCategory implements Serializable{
	
	@Transient
	private static final long serialVersionUID = -463411235245114614L;

	@Id
	@SequenceGenerator(name="examCategoriesSeq", sequenceName="exam_categories_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="examCategoriesSeq")
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy="categories")
	private List<ExamTask> tasks = new ArrayList<>();
	
	public ExamCategory() {
		
	}
	public ExamCategory(String name) {
		this.name = name;
	}
    
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<ExamTask> getTasks() {
		return tasks;
	}
	public void setTasks(List<ExamTask> tasks) {
		this.tasks = tasks;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
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
		ExamCategory other = (ExamCategory) obj;
		if (categoryId != other.categoryId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return name;
	}
	
	
	
	
}
