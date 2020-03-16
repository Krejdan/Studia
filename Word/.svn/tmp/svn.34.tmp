package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration()
				   .configure()
				   .addAnnotatedClass(tables.User.class)
				   .addAnnotatedClass(tables.Position.class)
				   //.addAnnotatedClass(tables.UserRole.class)
				   .addAnnotatedClass(tables.Operation.class)
				   .addAnnotatedClass(tables.Resource.class)
				   .addAnnotatedClass(tables.Permission.class)
				   .addAnnotatedClass(tables.ExaminationCard.class)
				   .addAnnotatedClass(tables.ExamCategory.class)
				   .addAnnotatedClass(tables.ExamTask.class)
				   .addAnnotatedClass(tables.CategoryTask.class)
				   .addAnnotatedClass(tables.ExamTaskResult.class)	
				   .addAnnotatedClass(tables.Question.class)	
				   .addAnnotatedClass(tables.CategoryQuestion.class)	
				   .addAnnotatedClass(tables.QuestionResult.class)
				   .addAnnotatedClass(tables.ExamResult.class)
				   .addAnnotatedClass(tables.TheoreticalExam.class)
				   .addAnnotatedClass(tables.Term.class)	
				   .addAnnotatedClass(tables.Day.class)	
				   .addAnnotatedClass(tables.Month.class)	
				   .addAnnotatedClass(tables.Year.class)	
				   .addAnnotatedClass(tables.Hour.class)	
				   .addAnnotatedClass(tables.Car.class)	
				   .addAnnotatedClass(tables.TaskType.class)
				   .buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
