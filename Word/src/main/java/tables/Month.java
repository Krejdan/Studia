package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="month")
public class Month implements Serializable{

	@Transient
	private static final long serialVersionUID = 8218099636048748905L;
	
	@Id
	@Column(name="month_number")
	private int monthNumber;

	public Month() {
	}

	public Month(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + monthNumber;
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
		Month other = (Month) obj;
		if (monthNumber != other.monthNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(monthNumber);
	}
	

	
}
