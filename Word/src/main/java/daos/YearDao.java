package daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.Year;
import util.HibernateUtil;

public class YearDao {
	
	public void add(Year entity) {
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
	
	public Year get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Year year = null;
    	
    	try {
            transaction = session.beginTransaction();
            year = session.get(Year.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return year;
	}
}
