package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.ExamCategory;
import util.HibernateUtil;

public class ExamCategoryDao implements Dao<ExamCategory> {
	
	@Override
	public void add(ExamCategory entity) {
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
	public void delete(ExamCategory entity) {
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
	public void update(ExamCategory entity) {
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

	public ExamCategory get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	ExamCategory examCategory = null;
    	
    	try {
            transaction = session.beginTransaction();
            examCategory = session.get(ExamCategory.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return examCategory;
	}

	@Override
	public List<ExamCategory> getAll() {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<ExamCategory> examCategories = null;
    	
		try {
			transaction = session.beginTransaction();
			examCategories = session.createQuery("from ExamCategory", ExamCategory.class).list();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return examCategories;
	}
	
	public ExamCategory get(String name) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	ExamCategory examCategory = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ExamCategory where name = :name", ExamCategory.class);
			query.setParameter("name", name);
			examCategory = (ExamCategory)query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return examCategory;
	}
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
