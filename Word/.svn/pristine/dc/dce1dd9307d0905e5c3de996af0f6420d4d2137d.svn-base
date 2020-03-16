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
@Table(name="userRoles")
public class UserRole implements Serializable{

	@Transient
	private static final long serialVersionUID = -7378552639252485463L;

	@EmbeddedId
	private UserRolesId id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@MapsId("userId")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@MapsId("positionId")
	private Position position;
	
	public UserRole() {
		
	}
	public UserRole(User user, Position position) {
		this.user = user;
		this.position = position;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public UserRolesId getId() {
		return id;
	}
	public void setId(UserRolesId id) {
		this.id = id;
	}
	
}
