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
import javafx.stage.Stage;
import server.Request;
import server.RequestType;
import tables.ExaminationCard;
import tables.User;
import util.Connection;

public class BrowsingExamsController implements Initializable {

	@FXML
	private Button theoreticalExamsButton;

	@FXML
	private Button practicalExamsButton;

	private User user;

	public void initUser(User user) {
		this.user = user;
	}

	@FXML
	void theoreticalExamsButtonClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TheoreticalExamsListView.fxml"));
		Parent theoreticalExamsListView;
		try {
			theoreticalExamsListView = loader.load();
			Scene theoreticalExamsListScene = new Scene(theoreticalExamsListView);

			TheoreticalExamsListController theoreticalExamsListController = loader.getController();
			theoreticalExamsListController.initUser(user);

			Stage window = (Stage) practicalExamsButton.getScene().getWindow();
			window.setScene(theoreticalExamsListScene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void practicalExamsButtonClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("PracticalExamsListView.fxml"));
		Parent practicalExamsListView;
		try {
			practicalExamsListView = loader.load();
			Scene practicalExamsListScene = new Scene(practicalExamsListView);

			PracticalExamsListController practicalExamsListController = loader.getController();
			practicalExamsListController.initUser(user);

			Stage window = (Stage) theoreticalExamsButton.getScene().getWindow();
			window.setScene(practicalExamsListScene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
