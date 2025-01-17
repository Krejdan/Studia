package application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import server.Request;
import server.RequestType;
import tables.Question;
import tables.TheoreticalExam;
import tables.User;
import util.Connection;

public class TheoreticalExamController implements Initializable {

	@FXML
	private Label questionText;
	@FXML
	private Label questionNumber;
	@FXML
	private ImageView questionImage;
	@FXML
	private RadioButton answerButton1;
	@FXML
	private ToggleGroup answerButtonsGroup;
	@FXML
	private RadioButton answerButton2;
	@FXML
	private RadioButton answerButton3;
	@FXML
	private RadioButton answerButton4;
	@FXML
	private Button answerQuestionButton;
	@FXML
	private Label timeLeftTimer;
	@FXML
	private Button finishExamButton;

	private final static int questionTime = 10;
	private Timeline examTimer;
	private int questionSecondsLeft;
	private int currentQuestion;
	private int numberOfQuestions;
	private List<Question> questions = new ArrayList<>();
	private int points;
	private TheoreticalExam theoreticalExam = new TheoreticalExam();
	private Request send = new Request();
	private Request received = new Request();
	private User user;

	public void initUser(User user) {
		this.user = user;
	}

	public void initExam(TheoreticalExam theoreticalExam) {
		this.theoreticalExam = theoreticalExam;
	}

	public void initQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@FXML
	void answerQuestionClicked(ActionEvent event) {

		checkAnswerIfCorrect();
		if (currentQuestion == numberOfQuestions)
			endExam();
		else {

			changeQuestion();
		}

	}

	@FXML
	void finishClicked(ActionEvent event) {
		checkAnswerIfCorrect();
		endExam();
	}

	private void endExam() {
		examTimer.stop();
		send.setType(RequestType.END_THEORETICAL_EXAM);
		send.setUser(user);
		send.setTheoreticalExam(theoreticalExam);
		try {
			Connection.getOutput().writeObject(send);
			received = (Request) Connection.getInput().readObject();
			points = received.getPoints();
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("TheoreticalExamResultView.fxml"));
			Parent examResultView = loader.load();
			Scene examResultScene = new Scene(examResultView);

			TheoreticalExamResultController examResultController = loader.getController();
			examResultController.initMaxPoints(numberOfQuestions);
			examResultController.initPoints(points);
			examResultController.initialize(null, null);

			Stage window = (Stage) questionText.getScene().getWindow();
			window.setScene(examResultScene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void changeQuestion() {
		if (currentQuestion == numberOfQuestions)
			endExam();
		else {
			startNewQuestionTimer();

			answerButtonsGroup.selectToggle(null);
			questionNumber.setText(String.format("%d/%d", currentQuestion + 1, numberOfQuestions));
			questionText.setText(questions.get(currentQuestion).getQuestion());

			setUpAnswerButtons();

			byte[] data = questions.get(currentQuestion).getImage();
			InputStream targetStream = new ByteArrayInputStream(data);
			questionImage.setImage(new Image(targetStream));

			currentQuestion++;
		}
	}

	private void setUpAnswerButtons() {
		Question question = questions.get(currentQuestion);
		List<String> possibleAnswers = new ArrayList<String>();
		if (question.getAnswer1() != null)
			possibleAnswers.add(question.getAnswer1());
		if (question.getAnswer2() != null)
			possibleAnswers.add(question.getAnswer2());
		if (question.getAnswer3() != null)
			possibleAnswers.add(question.getAnswer3());
		possibleAnswers.add(question.getCorrectAnswer());
		Collections.shuffle(possibleAnswers);

		int numberOfAnswers = possibleAnswers.size();

		switch (numberOfAnswers) {
		case 4:
			answerButton1.setDisable(false);
			answerButton2.setDisable(false);
			answerButton3.setDisable(false);
			answerButton4.setDisable(false);
			answerButton1.setText(possibleAnswers.get(0));
			answerButton1.setUserData(possibleAnswers.get(0));
			answerButton2.setText(possibleAnswers.get(1));
			answerButton2.setUserData(possibleAnswers.get(1));
			answerButton3.setText(possibleAnswers.get(2));
			answerButton3.setUserData(possibleAnswers.get(2));
			answerButton4.setText(possibleAnswers.get(3));
			answerButton4.setUserData(possibleAnswers.get(3));
			break;
		case 3:
			answerButton1.setDisable(false);
			answerButton2.setDisable(false);
			answerButton3.setDisable(false);
			answerButton4.setDisable(true);
			answerButton1.setText(possibleAnswers.get(0));
			answerButton1.setUserData(possibleAnswers.get(0));
			answerButton2.setText(possibleAnswers.get(1));
			answerButton2.setUserData(possibleAnswers.get(1));
			answerButton3.setText(possibleAnswers.get(2));
			answerButton3.setUserData(possibleAnswers.get(2));
			answerButton4.setText("");
			break;
		case 2:
			answerButton1.setDisable(false);
			answerButton2.setDisable(false);
			answerButton3.setDisable(true);
			answerButton4.setDisable(true);
			answerButton1.setText(possibleAnswers.get(0));
			answerButton1.setUserData(possibleAnswers.get(0));
			answerButton2.setText(possibleAnswers.get(1));
			answerButton2.setUserData(possibleAnswers.get(1));
			answerButton3.setText("");
			answerButton4.setText("");
			break;
		default:
			break;
		}
	}

	private void checkAnswerIfCorrect() {
		if (answerButtonsGroup.getSelectedToggle() != null)
			send.getAnswers().add((String) answerButtonsGroup.getSelectedToggle().getUserData());
		else
			send.getAnswers().add("");
		send.getQuestions().add(questions.get(currentQuestion - 1));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		questionSecondsLeft = questionTime;
		currentQuestion = 0;
		numberOfQuestions = 10;
		points = 0;

		// Server connection

		Platform.runLater(() -> {
			if(questions.isEmpty())
				return;
			changeQuestion();

			examTimer = new Timeline(new KeyFrame(Duration.seconds(0), event -> {
				questionSecondsLeft--;

				System.out.println(points);
				if (currentQuestion == numberOfQuestions + 1)
					examTimer.stop(); 
				if (questionSecondsLeft < 0) {
					checkAnswerIfCorrect();
					changeQuestion();
				}
				timeLeftTimer.setText(String.format("%d:%02d", questionSecondsLeft / 60, questionSecondsLeft % 60));

			}), new KeyFrame(Duration.seconds(1)));
			examTimer.setCycleCount(questionTime * numberOfQuestions + 1);
			examTimer.setOnFinished(e -> Platform.runLater(() -> {
				endExam();
			}));
			examTimer.play();
		});
	}

	private void startNewQuestionTimer() {
		questionSecondsLeft = questionTime;
		timeLeftTimer.setText(String.format("%d:%02d", questionSecondsLeft / 60, questionSecondsLeft % 60));
	}

}
