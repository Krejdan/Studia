package tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRolesId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private long userId;
	
	@Column(name="position_id")
	private long positionId;
	
	public UserRolesId() {
		
	}

	public UserRolesId(long userId, long positionId) {
		super();
		this.userId = userId;
		this.positionId = positionId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPositionId() {
		return positionId;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (positionId ^ (positionId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		UserRolesId other = (UserRolesId) obj;
		if (positionId != other.positionId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	 
	
}
