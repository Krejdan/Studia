package daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.Question;
import util.HibernateUtil;

public class QuestionDao implements Dao<Question> {
	
	@Override
	public void add(Question entity) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
  
    	try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
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
	public void delete(Question entity) {
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
	public void update(Question entity) {
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

	public Question get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Question question = null;
    	
    	try {
            transaction = session.beginTransaction();
            question = session.get(Question.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return question;
	}

	@Override
	public List<Question> getAll() {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<Question> questions = null;
    	
		try {
			transaction = session.beginTransaction();
			questions = session.createQuery("from Question", Question.class).list();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return questions;
	}

	public List<Question> getRandomQuestions (int number) {
		Session session = null;
		List<Question> questions = new ArrayList<>();;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder qb = session.getCriteriaBuilder();
		    CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		    cq.select(qb.count(cq.from(Question.class)));
			questions = session.createQuery("FROM Question ORDER BY constVal", Question.class).setMaxResults(number).list();
			System.out.println(questions.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
		}

		return questions;
	}
	
	
}
