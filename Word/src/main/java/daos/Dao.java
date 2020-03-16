package daos;

import java.util.List;

public interface Dao<T> {
	void add(T entity);
	void delete(T entity);
	void update(T entity);
	List<T> getAll();
	
}
