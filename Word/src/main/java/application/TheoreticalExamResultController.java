package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TheoreticalExamResultController implements Initializable {

	@FXML
	private Button finishButton;

	@FXML
	private Label resultEmote;

	@FXML
	private Label pointsText;

	@FXML
	private Label resultText;

	private int maxPoints;
	private int points;

	public void initMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public void initPoints(int points) {
		this.points = points;
	}

	@FXML
	void finishOnClick(ActionEvent event) {
		Parent loginScreen;
		try {
			loginScreen = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene loginScene = new Scene(loginScreen);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(loginScene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setResult();
	}

	private void setResult() {
		if ((float) points / maxPoints >= 0.5) {
			resultEmote.setText(":)");
			resultEmote.setStyle("-fx-text-fill: #9ae41a;");
			resultText.setText("WYNIK: POZYTYWNY");
			pointsText.setText("PUNKTY: " + points);
		} else {
			resultEmote.setText(":(");
			resultEmote.setStyle("-fx-text-fill: #ec4d15;");
			resultText.setText("WYNIK: NEGATYWNY");
			pointsText.setText("PUNKTY: " + points);

		}
	}

}
