package util;

import java.util.Date;

import daos.*;
import tables.*;
import test.InsertQuestions;
import test.InsertTasks;

public class InitializeDatabase {

	public static void initializeTerms() {
		
		HourDao hourDao = new HourDao();
		YearDao yearDao = new YearDao();
		MonthDao monthDao = new MonthDao();
		DayDao dayDao = new DayDao();
		
		Hour hour = new Hour();
		
		for(int i=8; i<18; i++)
		{
			hour = new Hour(i, 0);
			hourDao.add(hour);
			hour = new Hour(i, 30);
			hourDao.add(hour);
			
		}
		
		Day day = new Day();
		
		for(int i=1; i<32; i++)
		{
			day = new Day(i);
			dayDao.add(day);
		}
		
		Month month = new Month();
		
		for(int i=1; i<13; i++)
		{
			month = new Month(i);
			monthDao.add(month);
		}
		
		Year year = new Year();
		
		for(int i=2019; i<2101; i++)
		{
			year = new Year(i);
			yearDao.add(year);
		}
	}
	
	public static void initializeResources() {
		ResourceDao resourceDao = new ResourceDao();
		
		Resource resource = new Resource("ExaminationCard");
		resourceDao.add(resource);
		resource = new Resource("Question");
		resourceDao.add(resource);
		resource = new Resource("ExamResult");
		resourceDao.add(resource);
		resource = new Resource("TheoreticalExam");
		resourceDao.add(resource); 
	}
	
	public static void initializePositions() {
		PositionDao positionDao = new PositionDao();
		
		Position position = new Position("Gosc", "gosc");
		positionDao.add(position);
		
		position = new Position("Egzaminator", "egzaminator");
		positionDao.add(position);
		
		position = new Position("Klient", "klient");
		positionDao.add(position);
		
		position = new Position("Pracownik administracyjny", "Pracownik administracyjny");
		positionDao.add(position);
	}
	
	public static void initializeCategories() {
		ExamCategoryDao examCategoryDao = new ExamCategoryDao(); 
		ExamCategory examCategory = new ExamCategory("A");
		examCategoryDao.add(examCategory);
		examCategory = new ExamCategory("B");
		examCategoryDao.add(examCategory);
		examCategory = new ExamCategory("C");
		examCategoryDao.add(examCategory);
		examCategory = new ExamCategory("D");
		examCategoryDao.add(examCategory);
		examCategory = new ExamCategory("A1");
		examCategoryDao.add(examCategory);
		examCategory = new ExamCategory("E");
		examCategoryDao.add(examCategory);
		examCategory = new ExamCategory("F");
		examCategoryDao.add(examCategory);
		examCategory = new ExamCategory("G");
		examCategoryDao.add(examCategory);
		examCategory = new ExamCategory("B1");
		examCategoryDao.add(examCategory);
	}
	
	public static void initializeCars() {
		CarDao carDao = new CarDao();
		ExamCategoryDao categoryDao = new ExamCategoryDao();
		
		Car car = new Car();
		for(int i = 0; i < 1; i++) {
			car.setCarNumber(i);
			car.setExamCategory(categoryDao.get("B"));
			carDao.add(car);
		}
	}
	
	public static void initializeTaskTypes() {
		TaskTypeDao taskTypeDao = new TaskTypeDao();
		TaskType taskType = new TaskType();
		taskType.setName("Wymagane");
		taskTypeDao.add(taskType);
		taskType = new TaskType();
		taskType.setName("Opcjonalne");
		taskTypeDao.add(taskType);
		taskType = new TaskType();
		taskType.setName("Parkowanie");
		taskTypeDao.add(taskType);
		
	}
	
	public static void initializeUsers() {
		PositionDao positionDao = new PositionDao();
		UserDao userDao = new UserDao();
		
		User user = new User("Ramesh", "Fadatare", new Date(), "test@test.com", "12345");
		user.addPosition(positionDao.getByName("Egzaminator"));
		userDao.add(user);
		user = new User("Emil", "Emil", new Date(), "test@test.test", "54321");
		user.addPosition(positionDao.getByName("Klient"));
		userDao.add(user);
		user = new User("Bartek", "Bartek", new Date(), "bartek.bartek@gmail.com", "lazania");
		user.addPosition(positionDao.getByName("Klient"));
		userDao.add(user);
		user = new User("Jan", "Kowalski", new Date(), "administrator@admin.com", "admin");
		user.addPosition(positionDao.getByName("Pracownik administracyjny"));
		userDao.add(user);
	}
	
	public static void initializeQuestions() {
		InsertQuestions insertQuestions = new InsertQuestions();
		insertQuestions.insertExampleQuestions();
	}
	
	public static void initializeTasks() {
		InsertTasks insertTasks = new InsertTasks();
		insertTasks.insertExampleTasks();
	}
	
	public static void main(String[] args) {	
		initializeTerms();
		initializePositions();
		initializeResources();
		initializeCategories();
		initializeCars();
		initializeTaskTypes();
		initializeUsers();
		initializeQuestions();
		initializeTasks();
		
		HibernateUtil.shutdown();
				
		
	}
	
}
