package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fxclassess.PracticalResultTableCell;
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
import tables.ExaminationCard;
import tables.Term;
import tables.User;
import util.Connection;

public class PracticalExamsListController implements Initializable {
	
	@FXML
	private Button cancelButton;

	@FXML
	private TableView<ExaminationCard> examsTableView;

	@FXML
	private TableColumn<ExaminationCard, Term> termColumn;

	@FXML
	private TableColumn<ExaminationCard, ExamCategory> categoryColumn;

	@FXML
	private TableColumn<ExaminationCard, String> resultColumn;
	
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
		loader.setLocation(getClass().getResource("PracticalExamResultView.fxml"));
		Parent practicalExamResultView = loader.load();
		Scene practicalExamResultScene = new Scene(practicalExamResultView);

		PracticalExamResultController practicalExamResultController = loader.getController();
		ExaminationCard exam = examsTableView.getSelectionModel().getSelectedItem();
		practicalExamResultController.initExam(exam);
		practicalExamResultController.initUser(user);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(practicalExamResultScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		termColumn.setCellValueFactory(new PropertyValueFactory<ExaminationCard, Term>("term"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<ExaminationCard, ExamCategory>("category"));
		resultColumn.setCellValueFactory(new PropertyValueFactory<ExaminationCard, String>("result"));
		resultColumn.setCellFactory(cell -> new PracticalResultTableCell());
		Platform.runLater(() -> {
			try {
				Connection.request = new Request();
				Connection.request.setType(RequestType.GET_EXAMINATION_CARDS);
				//LocalDate localDate = LocalDate.of(2020, 6, 12);
				//Connection.request.setDate(localDate);
				System.out.println(user);
				Connection.request.setUser(user);

				Connection.getOutput().writeObject(Connection.request);
				
				List<ExaminationCard> exams = (List<ExaminationCard>) Connection.getInput().readObject();
				ObservableList<ExaminationCard> observableExams = FXCollections.observableArrayList(exams);
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
