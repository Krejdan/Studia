package daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.Month;
import util.HibernateUtil;

public class MonthDao {
	
	public void add(Month entity) {
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
	
	public Month get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Month month = null;
    	
    	try {
            transaction = session.beginTransaction();
            month = session.get(Month.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return month;
	}
}
