package tables;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="operations")
public class Operation implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 4674393648263716288L;

	@Id
	@SequenceGenerator(name="operationsSeq", sequenceName="operations_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="operationsSeq")
	@Column(name="operation_id")
	private int operationId;
	
	@Column(name="name")
	private String name;

	@Column(name="type")
	private int type;
	
	@OneToMany(
			mappedBy="operation",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE}
	)
	private List<Permission> permissions;
	
	//Operation.add
	
	public Operation() {
		
	}

	public Operation(String name, int type) {
		super();
		this.name = name;
		this.type = type;
	}

	public int getOperationId() {
		return operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Operation [operationId=" + operationId + ", name=" + name + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + operationId;
		result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
		result = prime * result + type;
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
		Operation other = (Operation) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operationId != other.operationId)
			return false;
		if (permissions == null) {
			if (other.permissions != null)
				return false;
		} else if (!permissions.equals(other.permissions))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
	
	
}
