package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.TheoriticalExam;
import util.HibernateUtil;

public class TheoriticalExamDao implements Dao<TheoriticalExam> {
	@Override
	public void add(TheoriticalExam entity) {
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
	public void delete(TheoriticalExam entity) {
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
	public void update(TheoriticalExam entity) {
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

	public TheoriticalExam get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	TheoriticalExam theoriticalExam = null;
    	
    	try {
            transaction = session.beginTransaction();
            theoriticalExam = session.get(TheoriticalExam.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return theoriticalExam;
	}

	@Override
	public List<TheoriticalExam> getAll() {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<TheoriticalExam> theoriticalExams = null;
    	
		try {
			transaction = session.beginTransaction();
			theoriticalExams = session.createQuery("from TheoriticalExam", TheoriticalExam.class).list();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return theoriticalExams;
	}
}
