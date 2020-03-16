package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;


import tables.ExamResult;
import tables.TheoreticalExam;
import tables.User;
import util.HibernateUtil;

public class ExamResultDao implements Dao<ExamResult> {

	@Override
	public void add(ExamResult entity) {
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
	public void delete(ExamResult entity) {
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
	public void update(ExamResult entity) {
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

	public ExamResult get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	ExamResult examResult = null;
    	
    	try {
            transaction = session.beginTransaction();
            examResult = session.get(ExamResult.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return examResult;
	}

	@Override
	public List<ExamResult> getAll() {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<ExamResult> examResults = null;
    	
		try {
			transaction = session.beginTransaction();
			examResults = session.createQuery("from ExamResult", ExamResult.class).list();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return examResults;
	}
	
	public List<ExamResult> get(TheoreticalExam theoreticalExam) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<ExamResult> examResults = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ExamResult where theoreticalExam = :theoreticalExam", ExamResult.class);
			query.setParameter("theoreticalExam", theoreticalExam);
			examResults = castList(ExamResult.class, query.getResultList());
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return examResults;
	}
	
	public List<ExamResult> get(User user) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<ExamResult> examResults = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ExamResult where egzaminowany = :user", ExamResult.class);
			query.setParameter("user", user);
			examResults = castList(ExamResult.class, query.getResultList());
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return examResults;
	}
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
