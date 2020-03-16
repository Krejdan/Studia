package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Day")
public class Day implements Serializable{

	@Transient
	private static final long serialVersionUID = -4780437172181239361L;
	
	@Id
	@Column(name="day_number")
	private int dayNumber;

	public Day() {
	}
	
	public Day(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dayNumber;
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
		Day other = (Day) obj;
		if (dayNumber != other.dayNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(dayNumber);
	}
	
}
