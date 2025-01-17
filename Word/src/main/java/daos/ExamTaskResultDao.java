package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.ExamTaskResult;
import tables.ExamTaskResultId;
import tables.ExaminationCard;
import util.HibernateUtil;

public class ExamTaskResultDao implements Dao<ExamTaskResult> {
	@Override
	public void add(ExamTaskResult entity) {
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
	public void delete(ExamTaskResult entity) {
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
	public void update(ExamTaskResult entity) {
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

	public ExamTaskResult get(ExamTaskResultId id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	ExamTaskResult examTaskResult = null;
    	
    	try {
            transaction = session.beginTransaction();
            examTaskResult = session.get(ExamTaskResult.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return examTaskResult;
	}

	@Override
	public List<ExamTaskResult> getAll() {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<ExamTaskResult> examTaskResults = null;
    	
		try {
			transaction = session.beginTransaction();
			examTaskResults = session.createQuery("from ExamTaskResult", ExamTaskResult.class).list();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return examTaskResults;
	}
	
	public List<ExamTaskResult> get(String taskType) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<ExamTaskResult> examTaskResults = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ExamTaskResult where taskType = :taskType", ExamTaskResult.class);
			query.setParameter("taskType", taskType);
			examTaskResults = castList(ExamTaskResult.class, query.getResultList());
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return examTaskResults;
	}
	
	public List<ExamTaskResult> getByExam(ExaminationCard exam) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<ExamTaskResult> examTaskResults = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ExamTaskResult where examCard = :exam", ExamTaskResult.class);
			query.setParameter("exam", exam);
			examTaskResults = castList(ExamTaskResult.class, query.getResultList());
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return examTaskResults;
	}
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
