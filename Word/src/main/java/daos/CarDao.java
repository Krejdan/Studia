package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.Car;
import tables.ExamCategory;
import tables.QuestionResult;
import util.HibernateUtil;

public class CarDao implements Dao<Car> {
	
	
	@Override
	public void add(Car entity) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
  
    	try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }	
	}

	@Override
	public void delete(Car entity) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
  
    	try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		
	}

	@Override
	public void update(Car entity) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
  
    	try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		
	}

	public Car get(int id) throws Exception{
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Car car = null;
    	
    	try {
            transaction = session.beginTransaction();
            car = session.get(Car.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
        	session.close();
        }
		return car;
	}

	@Override
	public List<Car> getAll() {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<Car> cars = null;
    	
		try {
			transaction = session.beginTransaction();
			cars = session.createQuery("from Car", Car.class).list();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return cars;
	}
	
	public List<Car> getByCategory(ExamCategory category) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<Car> cars = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Car where examCategory = :category", Car.class);
			query.setParameter("category", category);
			cars = castList(Car.class, query.getResultList());
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return cars;
	}
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
