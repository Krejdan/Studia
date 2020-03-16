package test;

import java.util.List;

import daos.ExamCategoryDao;
import daos.ExamTaskDao;
import daos.ResourceDao;
import daos.TaskTypeDao;
import tables.ExamCategory;
import tables.ExamTask;
import tables.Resource;
import util.HibernateUtil;

public class InsertTasks {

	private Resource taskResource;
	private List<ExamCategory> categories;
	
	public InsertTasks() {
		ResourceDao rdao = new ResourceDao();
		this.taskResource = rdao.get(1);
		categories = getCategories();
	}
	
	public void insertTask(String name, int mistakes, String taskType) {
		TaskTypeDao taskTypeDao = new TaskTypeDao();
		ExamTask task = new ExamTask(name);
		task.setCategories(categories);
		task.setPossibleMistakes(mistakes);
		task.setTaskType(taskTypeDao.get(taskType));
		ExamTaskDao etdao = new ExamTaskDao();
		
		etdao.add(task);
			
	}
	
	private List<ExamCategory> getCategories() {
		ExamCategoryDao examCategoryDao = new ExamCategoryDao();
		categories = examCategoryDao.getAll();
		return categories;
	}
	
	public void insertExampleTasks() {
		insertTask("Przygotowanie do jazdy", 0, "Wymagane");
		insertTask("Rozprzeganie przyczepy", 0, "Opcjonalne");
		insertTask("Sprzeganie przyczepy", 0, "Opcjonalne");
		insertTask("Ruszanie z miejsca oraz jazda pasem ruchu do przodu i do tylu", 1, "Wymagane");
		insertTask("Parkowanie skosne", 1, "Parkowanie");
		insertTask("Parkowanie prostopad³e wjazd przodem", 1, "Parkowanie");
		insertTask("Parkowanie prostopad³e wjazd tylem", 1, "Parkowanie");
		insertTask("Parkowanie równoleg³e", 1, "Parkowanie");
		insertTask("Zawracanie", 1, "Wymagane");
		insertTask("Ruszanie z miejsca do przodu na wzniesieniu", 1, "Wymagane");
		insertTask("Wlaczenie sie do ruchu", 1, "Wymagane");
		insertTask("Jazda drogami dwukierunkowymi", 1, "Wymagane");
		insertTask("Jazda drogami dwukierunkowymi", 1, "Wymagane");
		insertTask("Przejazd przez skrzyzowania równorzedne", 1, "Wymagane");
		insertTask("Przejazd przez skrzyzowania oznakowane znakami ustalajacymi pierwszenstwo przejazdu", 1, "Wymagane");
		insertTask("Przejazd przez skrzyzowania o ruchu kierowanym", 1, "Opcjonalne");
		insertTask("Przejazd przez skrzyzowania na których ruch odbywa sie wokól wyspy", 1, "Wymagane");
		insertTask("Przejazd przez skrzyzowania wielopoziomowe", 1, "Opcjonalne");
		insertTask("Zachowanie w rejonach przejsc dla pieszych i wobec pieszych", 1, "Wymagane");
		insertTask("Przejazd przez torowisko", 1, "Opcjonalne");
		insertTask("Przejazd przez tunel", 1, "Opcjonalne");
		insertTask("Przejazd obok przystanków", 1, "Opcjonalne");
		insertTask("Wyprzedzanie", 1, "Opcjonalne");
		insertTask("Omijanie", 1, "Opcjonalne");
		insertTask("Wymijanie", 1, "Opcjonalne");
		insertTask("Zmiana pasa ruchu", 1, "Wymagane");
		insertTask("Zmiana kierunku jazdy w lewo", 1, "Opcjonalne");
		insertTask("Zmiana kierunku jazdy w prawo", 1, "Opcjonalne");
		insertTask("Zawracanie na skrzy¿owaniu", 1, "Opcjonalne");
		insertTask("Hamowanie do zatrzymania w wyznaczonym miejscu", 1, "Wymagane");
		insertTask("Hamowanie awaryjne", 1, "Wymagane");
		insertTask("Zachowanie wobec innych kierujacych", 1, "Wymagane");
		insertTask("Zachowanie w odniesieniu do znaków drogowych pionowych", 1, "Wymagane");
		insertTask("Zachowanie w odniesieniu do znaków drogowych poziomych", 1, "Wymagane");
		insertTask("Zachowanie w odniesieniu do sygnalow swietlnych/polecen kierujacych ruchem", 1, "Wymagane");
		insertTask("Respektowanie zasad techniki kierowania", 1, "Wymagane");
		insertTask("Dynamika jazdy", 1, "Wymagane");
		insertTask("Dostosowanie predkosci do warunkow ruchu", 1, "Wymagane");
		insertTask("Sposob uzywania mechanizmow sterowania pojazdem", 1, "Wymagane");
		insertTask("Inne", 1, "Opcjonalne");

	}

	public static void main(String[] args) {
		InsertTasks testInsert = new InsertTasks();
		testInsert.insertExampleTasks();
		HibernateUtil.shutdown();

	}

}
