package tables;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="resources")
public class Resource implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 516570310440626783L;

	@Id
	@SequenceGenerator(name="resourcesSeq", sequenceName="resources_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="resourcesSeq")
	@Column(name="resource_id")
	private int resourceId;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(
			mappedBy="resource",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.EAGER
	)
	private List<Permission> permissions;
	
	public Resource() {
		
	}
	public Resource(String nazwa) {
		this.name = nazwa;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getNazwa() {
		return name;
	}
	public void setNazwa(String nazwa) {
		this.name = nazwa;
	}
	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + resourceId;
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
		Resource other = (Resource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (resourceId != other.resourceId)
			return false;
		return true;
	}
	
	
	
}
