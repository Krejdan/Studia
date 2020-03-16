package daos;


import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.Hour;
import util.HibernateUtil;

public class HourDao {
	
	public void add(Hour entity) {
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
	
	public Hour get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Hour hour = null;
    	
    	try {
            transaction = session.beginTransaction();
            hour = session.get(Hour.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return hour;
	}
	
	public Hour getByHourAndMinute(int h, int min) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Hour hour = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Hour where hour = :hour and minute = :minute", Hour.class);
			query.setParameter("hour", h);
			query.setParameter("minute", min);
			hour = (Hour) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return hour;
	}
}
