package tables;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Resource.class)
public class Resource_ {
	public static volatile SingularAttribute<Resource, Integer> resourceId;
    public static volatile SingularAttribute<Resource, String> name;
    public static volatile ListAttribute<Resource, Permission> permissions;
}
