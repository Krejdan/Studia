package test;

import java.util.Date;

import daos.*;
import tables.*;

public class InitialTest {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		TermDAO terminDao = new TermDAO();
		QuestionDao questionDao = new QuestionDao();
		ExaminationCardDao examinationCardDao = new ExaminationCardDao();
		TheoreticalExamDao theoreticalExamDao = new TheoreticalExamDao();
		PositionDao positionDao = new PositionDao();
		PermissionDAO permissionDao = new PermissionDAO();
		ResourceDao resourceDao = new ResourceDao();
		HourDao hourDao = new HourDao();
		YearDao yearDao = new YearDao();
		MonthDao monthDao = new MonthDao();
		DayDao dayDao = new DayDao();

		//Resource resource = new Resource("ExamCard");
		//Operation operation = new Operation("Kopiowanie", 1);
		//Position position = new Position("Egzaminowany", null);
		//positionDao.add(position);
		//Permission permission = new Permission(position, resource, operation);
		//permissionDao.savePermission(permission);
		//Term term = new Term(new Year(2020), new Month(6), new Day(12), new Hour(12, 30));
		//terminDao.add(term);
		//position = new Position("Egzaminator", null);
		//User user = new User("Ramesh", "Fadatare", new Date(), "terh@javaguides.com", "12345");
		//User user1 = new User("Emil", "Emil", new Date(), "test@test.test", "54321");
		//user.addPosition(positionDao.getByName("Egzaminator"));
		//user1.addPosition(positionDao.getByName("Klient"));
		//userDao.add(user1);
		//userDao.add(user);
		
		//ExaminationCard examinationCard = new ExaminationCard("B", 0, "Brak", "Pozytywny", terminDao.get(yearDao.get(2020), monthDao.get(6), dayDao.get(12), hourDao.getByHourAndMinute(12, 30)), resourceDao.getByName("ExaminationCard"), userDao.get(1), userDao.get(2));
		//ExaminationCard examinationCard = examinationCardDao.getByTermAndCar(terminDao.get(2020, 6, 12, hourDao.getByHourAndMinute(12, 30)), 0);
		//examinationCardDao.add(examinationCard);
		//Term term = new Term(yearDao.get(2020), monthDao.get(6), dayDao.get(12), hourDao.getByHourAndMinute(12, 30));
		//terminDao.update(term);
		//TheoreticalExam theoreticalExam = new TheoreticalExam("B", 10, term, resourceDao.getByName("TheoreticalExam"), userDao.get(1));
		//theoreticalExam.addUser(userDao.get(2));
		//theoreticalExamDao.add(theoreticalExam);
		//ExaminationCard examinationCard = examinationCardDao.getByTermAndCar(terminDao.get(6), 0);
		
		/*
		 * Session session = HibernateUtil.getSessionFactory().openSession();
		 * session.beginTransaction(); ExaminationCard newExamCard =
		 * session.get(ExaminationCard.class, examinationCard.getExamCardId());
		 * System.out.println(newExamCard.getEgzaminowany());
		 * session.getTransaction().commit();
		 */
		//List<ExaminationCard> examinationCards = examinationCardDao.getAll();
		//user = usersDao.get(1);
		//System.out.println(examinationCard);
		/*
		 * for(ExaminationCard e : examinationCards) {
		 * System.out.println(e.getExaminer().toString()); } for(Question q1 :
		 * questions) { System.out.println(q1.toString()); }
		 */

	}

}
