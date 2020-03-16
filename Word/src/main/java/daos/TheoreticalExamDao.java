package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.Day;
import tables.ExamCategory;
import tables.Month;
import tables.Term;
import tables.TheoreticalExam;
import tables.User;
import util.HibernateUtil;

public class TheoreticalExamDao implements Dao<TheoreticalExam> {
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
	
	@Override
	public void add(TheoreticalExam entity) {
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
	public void delete(TheoreticalExam entity) {
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
	public void update(TheoreticalExam entity) {
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

	public TheoreticalExam get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	TheoreticalExam theoreticalExam = null;
    	
    	try {
            transaction = session.beginTransaction();
            theoreticalExam = session.get(TheoreticalExam.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return theoreticalExam;
	}

	@Override
	public List<TheoreticalExam> getAll() {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<TheoreticalExam> theoreticalExams = null;
    	
		try {
			transaction = session.beginTransaction();
			theoreticalExams = session.createQuery("from TheoreticalExam", TheoreticalExam.class).list();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return theoreticalExams;
	}
	
	public TheoreticalExam getByTerm(Term term) throws Exception{
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	TheoreticalExam theoreticalExam = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from TheoreticalExam where term = :term", TheoreticalExam.class);
			query.setParameter("term", term);
			theoreticalExam = (TheoreticalExam) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	throw e;
        } finally {
        	session.close();
        }
		return theoreticalExam;
	}
	
	public TheoreticalExam getByTermAndCategory(Term term, ExamCategory category) throws Exception{
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	TheoreticalExam theoreticalExam = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from TheoreticalExam where term = :term and category = :category", TheoreticalExam.class);
			query.setParameter("term", term);
			query.setParameter("category", category);
			theoreticalExam = (TheoreticalExam) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	throw e;
        } finally {
        	session.close();
        }
		return theoreticalExam;
	}
	
	public List<TheoreticalExam> getByUserAndTerm(User user, Term term) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<TheoreticalExam> theoreticalExams = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from TheoreticalExam where :user in users and term = :term", TheoreticalExam.class);
			query.setParameter("user", user);
			query.setParameter("term", term);
			theoreticalExams = castList(TheoreticalExam.class, query.getResultList());
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return theoreticalExams;
	}
	public TheoreticalExam getOpen() throws NoResultException{
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	TheoreticalExam theoreticalExam = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from TheoreticalExam where isOpen = 1", TheoreticalExam.class);
			theoreticalExam = (TheoreticalExam) query.getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	throw e;
        } finally {
        	session.close();
        }
		return theoreticalExam;
	}
	public List<TheoreticalExam> getNotOpen() throws NoResultException{
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<TheoreticalExam> theoreticalExams = null;
    	
		try {
			transaction = session.beginTransaction();
			theoreticalExams = session.createQuery("from TheoreticalExam where isOpen = 0", TheoreticalExam.class).list();
            transaction.commit();
        } catch (NoResultException e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	throw e;
        } finally {
        	session.close();
        }
		return theoreticalExams;
	}
	
	public List<TheoreticalExam> getNotOpenByDay(Day day, Month month) throws NoResultException{
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<TheoreticalExam> theoreticalExams = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from TheoreticalExam where isOpen = 0 and term.day = :day and term.month = :month", TheoreticalExam.class);
			query.setParameter("day", day);
			query.setParameter("month", month);
			theoreticalExams = castList(TheoreticalExam.class, query.getResultList());
            transaction.commit();
        } catch (NoResultException e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	throw e;
        } finally {
        	session.close();
        }
		return theoreticalExams;
	}
	
	public List<TheoreticalExam> getNotOpenByUser(User user) throws NoResultException{
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<TheoreticalExam> theoreticalExams = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from TheoreticalExam where :user in users and isOpen = 0", TheoreticalExam.class);
			query.setParameter("user", user);
			theoreticalExams = castList(TheoreticalExam.class, query.getResultList());
            transaction.commit();
        } catch (NoResultException e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	throw e;
        } finally {
        	session.close();
        }
		return theoreticalExams;
	}
	
}
