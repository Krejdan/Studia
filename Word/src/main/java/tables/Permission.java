package tables;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name="permissions")
public class Permission implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 218279343434837703L;

	@Id
	@SequenceGenerator(name="permissionSeq", sequenceName="permission_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permissionSeq")
	@Column(name="permission_id")
	private int permissionId;
	
	@ManyToOne(
			cascade= CascadeType.ALL,
			fetch=FetchType.EAGER
	)
	@JoinColumn(name="position_id")
	private Position position;
	
	@ManyToOne(
			cascade= CascadeType.ALL,
			fetch=FetchType.EAGER
	)
	@JoinColumn(name="resource_id")
	private Resource resource;
	
	@ManyToOne(
			cascade= CascadeType.ALL,
			fetch=FetchType.EAGER
	)
	@JoinColumn(name="operation_id")
	private Operation operation;
	
	public Permission() {
		
	}
	public Permission(Position position, Resource resource, Operation operation) {
		this.position = position;
		this.resource = resource;
		this.operation = operation;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + permissionId;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
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
		Permission other = (Permission) obj;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (permissionId != other.permissionId)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		return true;
	}
	
	
	
	
	
}
