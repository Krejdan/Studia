package application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fxclassess.QuestionResultTableCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.Request;
import server.RequestType;
import tables.ExamResult;
import tables.QuestionResult;
import tables.User;
import util.Connection;

public class ExamResultController implements Initializable {

	@FXML
	private Label questionText;
	@FXML
	private ImageView questionImage;
	@FXML
	private RadioButton answerButton1;
	@FXML
	private RadioButton answerButton2;
	@FXML
	private Button cancelButton;
	@FXML
	private Button checkButton;
	@FXML
	private TableView<QuestionResult> examsTableView;
	@FXML
	private TableColumn<QuestionResult, Integer> questionResultColumn;

	List<QuestionResult> questionResultList;

	private ExamResult examResult;

	public void initExamResult(ExamResult examResult) {
		this.examResult = examResult;
	}

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
	void checkButtonOnClick(ActionEvent event) {
		QuestionResult questionResult = examsTableView.getSelectionModel().getSelectedItem();
		questionText.setText(questionResult.getQuestion().getQuestion());
		answerButton1.setText(questionResult.getUserAnswer());
		answerButton2.setText(questionResult.getQuestion().getCorrectAnswer());
		byte[] data = questionResult.getQuestion().getImage();
		InputStream targetStream = new ByteArrayInputStream(data);
		questionImage.setImage(new Image(targetStream));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		questionResultColumn.setCellValueFactory(new PropertyValueFactory<QuestionResult, Integer>("result"));
		questionResultColumn.setCellFactory(cell -> new QuestionResultTableCell());
		answerButton1.setDisable(true);
		answerButton2.setDisable(true);
		answerButton1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
		answerButton2.setStyle("-fx-background-color: green; -fx-text-fill: black;");
		answerButton1.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		answerButton2.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		Platform.runLater(() -> {
			try {
				Connection.request = new Request();
				Connection.request.setType(RequestType.GET_QUESTIONS);
				Connection.request.setExamResult(examResult);

				Connection.getOutput().writeObject(Connection.request);

				List<QuestionResult> questions = (List<QuestionResult>) Connection.getInput().readObject();
				ObservableList<QuestionResult> observableExams = FXCollections.observableArrayList(questions);
				examsTableView.getItems().addAll(observableExams);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
	}
}