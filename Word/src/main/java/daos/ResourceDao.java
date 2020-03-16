package daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tables.Resource;
import util.HibernateUtil;

public class ResourceDao implements Dao<Resource> {
	
	@Override
	public void add(Resource entity) {
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
	public void delete(Resource entity) {
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
	public void update(Resource entity) {
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

	public Resource get(int id) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Resource resource = null;
    	
    	try {
            transaction = session.beginTransaction();
            resource = session.get(Resource.class, id);
            transaction.commit();
            Hibernate.initialize(resource);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	session.close();
        }
		return resource;
	}

	@Override
	public List<Resource> getAll() {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List<Resource> resources = null;
    	
		try {
			transaction = session.beginTransaction();
			resources = session.createQuery("from Resource", Resource.class).list();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return resources;
	}
	
	public Resource getByName(String name) {
		Transaction transaction = null;
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Resource resource = null;
    	
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Resource where name = :name", Resource.class);
			query.setParameter("name", name);
			resource = (Resource)query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
        	e.printStackTrace();
        } finally {
        	session.close();
        }
		return resource;
	}
}
