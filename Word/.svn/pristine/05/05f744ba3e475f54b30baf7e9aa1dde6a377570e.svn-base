package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fxclassess.FirstMistakeTableCell;
import fxclassess.PassedTableCell;
import fxclassess.SecondMistakeTableCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.Request;
import server.RequestType;
import tables.ExamTask;
import tables.ExamTaskResult;
import tables.ExaminationCard;
import tables.User;
import util.Connection;

public class PracticalExamController implements Initializable {

	@FXML
	private TableView<ExamTaskResult> tasksTable;
	@FXML
	private TableColumn<ExamTaskResult, ExamTask> tasksColumn;
	@FXML
	private TableColumn<ExamTaskResult, Integer> passedColumn;
	@FXML
	private TableColumn<ExamTaskResult, Integer> mistake1Column;
	@FXML
	private TableColumn<ExamTaskResult, Integer> mistake2Column;
	@FXML
	private Button scoreButton;
	@FXML
	private Button firstMistakeButton;
	@FXML
	private Button secondMistakeButton;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label categoryLabel;
	@FXML
	private TextArea commentArea;
	@FXML
	private Button endExamButton;
	@FXML
	private ChoiceBox<String> examResultChoiceBox;

	List<ExamTaskResult> examTaskResultList;

	private User user;
	private ExaminationCard exam;

	public void initUser(User user) {
		this.user = user;
	}

	public void initExam(ExaminationCard exam) {
		this.exam = exam;
	}
	
	public void checkCurrentExamResult() {
		int parkingsDone = 0;
		for(ExamTaskResult examTaskResult : examTaskResultList) {
			boolean taskPass = ((examTaskResult.getResult() & 1) == 1);
			boolean taskFirstMistakeMade = ((examTaskResult.getResult() & (1 << 1)) == (1 << 1));
			boolean taskSecondMistakeMade = ((examTaskResult.getResult() & (1 << 2)) == (1 << 2)); 
			int mistakesAllowed = examTaskResult.getExamTask().getPossibleMistakes();
			String examType = examTaskResult.getExamTask().getTaskType().getName();
			if(examType.equals("Wymagane")) {
				if(!taskPass) {
					examResultChoiceBox.getSelectionModel().select(0);
					return;
				}
			}
			else if(examType.equals("Opcjonalne")) {
				if ((mistakesAllowed == 0 && taskFirstMistakeMade) || (mistakesAllowed ==  1 && taskSecondMistakeMade)) {
					examResultChoiceBox.getSelectionModel().select(0);
					return;
				}
			}
			else if(examType.equals("Parkowanie")) {
				if(taskSecondMistakeMade) {
					examResultChoiceBox.getSelectionModel().select(0);	
					return;
				}
				else if(taskPass) {
					parkingsDone++;
				}
			}
		}
		if(parkingsDone == 0) {
			examResultChoiceBox.getSelectionModel().select(0);
			return;
		}
		examResultChoiceBox.getSelectionModel().select(1);
	}
	@FXML
	void endExamOnClick(ActionEvent event) {
		
		
		try {
			exam.setExamTaskResults(examTaskResultList);
			exam.setComments(commentArea.getText());
			exam.setResult(examResultChoiceBox.getSelectionModel().getSelectedItem());
			
			Connection.request = new Request();
			Connection.request.setType(RequestType.SEND_EXAMINATION_CARD);
			Connection.request.setExaminationCard(exam);
			Connection.request.setMessage(exam.getResult());
			Connection.request.setMail(exam.getComments());
			Connection.request.setExamTaskResults(examTaskResultList);
			Connection.getOutput().writeObject(Connection.request);
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ExaminerMainView.fxml"));
			Parent examinerView;
			examinerView = loader.load();
			Scene examinerMainScene = new Scene(examinerView);

			ExaminerViewController examinerViewController = loader.getController();
			examinerViewController.initUser(this.user);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(examinerMainScene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void firstMistakeOnClick(ActionEvent event) {
		ExamTaskResult taskResult = tasksTable.getSelectionModel().getSelectedItem();
		if (taskResult != null) {
			if (taskResult.getExamTask().getPossibleMistakes() == 0)
				taskResult.setResult(taskResult.getResult() & ~1);
			taskResult.setResult(taskResult.getResult() ^ 2);
		}
		refreshTable();
	}

	@FXML
	void scoreOnClick(ActionEvent event) {
		ExamTaskResult taskResult = tasksTable.getSelectionModel().getSelectedItem();
		if (taskResult != null) {
			if (taskResult.getExamTask().getPossibleMistakes() == 0)
				taskResult.setResult(taskResult.getResult() & ~2);
			else if (taskResult.getExamTask().getPossibleMistakes() == 1)
				taskResult.setResult(taskResult.getResult() & ~4);
			taskResult.setResult(taskResult.getResult() ^ 1);
		}
		refreshTable();

	}

	@FXML
	void secondMistakeOnClick(ActionEvent event) {
		ExamTaskResult taskResult = tasksTable.getSelectionModel().getSelectedItem();
		if (taskResult != null) {
			if (taskResult.getExamTask().getPossibleMistakes() == 0)
				AlertBox.display("Error", "Dla tego zadania mozliwy jest jeden blad");
			else {
				taskResult.setResult(taskResult.getResult() & ~1);
				taskResult.setResult(taskResult.getResult() ^ 4);
			}
		}
		refreshTable();
	}

	private void refreshTable() {
		checkCurrentExamResult();
		if (tasksTable.getColumns().get(0) != null) {
			tasksTable.getColumns().get(0).setVisible(false);
			tasksTable.getColumns().get(0).setVisible(true);
		}
	}

	@FXML
	void examResultChoiceBoxClicked(MouseEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Platform.runLater(() -> {
			Connection.request = new Request();
			Connection.request.setType(RequestType.GET_TASKS);
			Connection.request.setExaminationCard(exam);
			try {
				Connection.getOutput().writeObject(Connection.request);
				List<ExamTask> examTaskList = (List<ExamTask>) Connection.getInput().readObject();
				examTaskResultList = new ArrayList<ExamTaskResult>();
				for (ExamTask task : examTaskList) {
					examTaskResultList.add(new ExamTaskResult(0, task, exam));
				}

				ObservableList<ExamTaskResult> taskResults = FXCollections.observableArrayList(examTaskResultList);

				tasksColumn.setCellValueFactory(new PropertyValueFactory<ExamTaskResult, ExamTask>("examTask"));
				passedColumn.setCellValueFactory(new PropertyValueFactory<ExamTaskResult, Integer>("result"));
				mistake1Column.setCellValueFactory(new PropertyValueFactory<ExamTaskResult, Integer>("result"));
				mistake2Column.setCellValueFactory(new PropertyValueFactory<ExamTaskResult, Integer>("result"));

				passedColumn.setCellFactory(cell -> new PassedTableCell());
				mistake1Column.setCellFactory(cell -> new FirstMistakeTableCell());
				mistake2Column.setCellFactory(cell -> new SecondMistakeTableCell());

				tasksTable.setItems(taskResults);

				firstNameLabel.setText(exam.getExamined().getName());
				lastNameLabel.setText(exam.getExamined().getSecondName());
				dateLabel.setText(exam.getTerm().toString());
				categoryLabel.setText(exam.getCategory().toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		});
		
		List<String> examResultChoices = new ArrayList<String>();
		examResultChoices.add("NEGATYWNY");
		examResultChoices.add("POZYTYWNY");
		examResultChoiceBox.setItems(FXCollections.observableArrayList(examResultChoices));
		endExamButton.disableProperty().bind(examResultChoiceBox.valueProperty().isNull());
		examResultChoiceBox.setDisable(true);
	}

}
