package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import fxclassess.PassedTableCell;
import fxclassess.ResultTableCell;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.Request;
import server.RequestType;
import tables.ExamCategory;
import tables.ExamResult;
import tables.ExamTaskResult;
import tables.Term;
import tables.TheoreticalExam;
import tables.User;
import util.Connection;

public class TheoreticalExamsListController implements Initializable {
	
	@FXML
	private Button cancelButton;

	@FXML
	private TableView<ExamResult> examsTableView;

	@FXML
	private TableColumn<ExamResult, Term> termColumn;

	@FXML
	private TableColumn<ExamResult, ExamCategory> categoryColumn;

	@FXML
	private TableColumn<ExamResult, String> resultColumn;

	@FXML
	private TableColumn<ExamResult, Integer> pointsColumn;
	
	@FXML
	private Button previewExamButton;
	
	private User user;
	
	public void initUser(User user) {
		this.user = user;
	}
	
	@FXML
	void cancelButtonOnClick(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ClientMainView.fxml"));
		Parent clientView = loader.load();
		Scene clientScene = new Scene(clientView);

		ClientViewController clientViewController = loader.getController();
		clientViewController.initUser(user);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(clientScene);
		window.show();
	}

	@FXML
	void previewExamButtonOnClick(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ExamResultView.fxml"));
		Parent examResultView = loader.load();
		Scene examResultScene = new Scene(examResultView);

		ExamResultController examResultController = loader.getController();
		ExamResult examResult = examsTableView.getSelectionModel().getSelectedItem();
		examResultController.initExamResult(examResult);
		examResultController.initUser(user);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(examResultScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		termColumn.setCellValueFactory(new PropertyValueFactory<ExamResult, Term>("term"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<ExamResult, ExamCategory>("category"));
		resultColumn.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("wynik"));
		pointsColumn.setCellValueFactory(new PropertyValueFactory<ExamResult, Integer>("points"));
		resultColumn.setCellFactory(cell -> new ResultTableCell());
		Platform.runLater(() -> {
			try {
				Connection.request = new Request();
				Connection.request.setType(RequestType.GET_THEORETICAL_EXAMS_FOR_USER);
				//LocalDate localDate = LocalDate.of(2020, 6, 12);
				//Connection.request.setDate(localDate);
				System.out.println(user);
				Connection.request.setUser(user);

				Connection.getOutput().writeObject(Connection.request);
				
				List<ExamResult> exams = (List<ExamResult>) Connection.getInput().readObject();
				ObservableList<ExamResult> observableExams = FXCollections.observableArrayList(exams);
				examsTableView.getItems().addAll(observableExams);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
	
}
