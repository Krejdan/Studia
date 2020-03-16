package daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.Day;
import util.HibernateUtil;

public class DayDao {
	
	public void add(Day entity) {
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
	
	public Day get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Day day = null;
    	
    	try {
            transaction = session.beginTransaction();
            day = session.get(Day.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return day;
	}
}
