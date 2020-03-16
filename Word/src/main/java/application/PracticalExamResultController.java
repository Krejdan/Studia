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

public class PracticalExamResultController implements Initializable {

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
	private Button cancelButton;
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
	
	
	@FXML
	void cancelButtonOnClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ClientMainView.fxml"));
			Parent clientView = loader.load();
			Scene clientScene = new Scene(clientView);

			ClientViewController clientViewController = loader.getController();
			clientViewController.initUser(user);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(clientScene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void examResultChoiceBoxClicked(MouseEvent event) {

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Platform.runLater(() -> {
			
			Connection.request = new Request();
			Connection.request.setType(RequestType.GET_TASK_RESULTS);
			Connection.request.setExaminationCard(exam);
			try {
				Connection.getOutput().writeObject(Connection.request);
				examTaskResultList = (List<ExamTaskResult>) Connection.getInput().readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
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

			firstNameLabel.setText(exam.getExaminer().getName());
			lastNameLabel.setText(exam.getExaminer().getSecondName());
			dateLabel.setText(exam.getTerm().toString());
			categoryLabel.setText(exam.getCategory().toString());
			commentArea.setText(exam.getComments());
			if(exam.getResult().equals("NEGATYWNY"))
				examResultChoiceBox.getSelectionModel().select(0);
			else
				examResultChoiceBox.getSelectionModel().select(1);

		});
		
		List<String> examResultChoices = new ArrayList<String>();
		examResultChoices.add("NEGATYWNY");
		examResultChoices.add("POZYTYWNY");
		examResultChoiceBox.setItems(FXCollections.observableArrayList(examResultChoices));
		examResultChoiceBox.setDisable(true);
		commentArea.setDisable(true);
	}

}
