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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Request;
import server.RequestType;
import tables.Position;
import tables.User;
import util.Connection;

public class LoginController implements Initializable {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button helpButton;

	private User user;

	@FXML
	void loginButtonClick(ActionEvent event) throws IOException {
		if (verifyLogin()) {
			if (user.getPositions().contains(new Position("Klient", "Klient"))) {		
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("ClientMainView.fxml"));
				Parent clientMainView = loader.load();
				Scene clientMainViewScene = new Scene(clientMainView);
				
				ClientViewController clientMainViewController = loader.getController();
				clientMainViewController.initUser(user);	
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(clientMainViewScene);
				window.show();
			}
			if (user.getPositions().contains(new Position("Egzaminator", "Egzaminator"))) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("ExaminerMainView.fxml"));
				Parent examinerView = loader.load();
				Scene examinerMainScene = new Scene(examinerView);

				ExaminerViewController examinerViewController = loader.getController();
				examinerViewController.initUser(user);

				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(examinerMainScene);
				window.show();
			}
			if (user.getPositions().contains(new Position("Pracownik administracyjny", "Pracownik administracyjny"))) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("AdminView.fxml"));
				Parent adminView = loader.load();
				Scene adminMainScene = new Scene(adminView);

				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(adminMainScene);
				window.show();
			}
		}
	}

	@FXML
	void helpButtonOnClick(ActionEvent event) {
		AlertBox.display("Pomoc", "Konto egzaminatora: \nmail: test@test.com haslo: 12345 \nKonto klient�w:\nmail: test@test.test haslo: 54321\nmail: bartek.bartek@gmail.com haslo: lazania\nKonto administratora:\nmail: administrator@admin.com haslo: admin");
	}

	boolean verifyLogin() {
		Request received = new Request();
		try {
			String username = usernameField.getText();
			String password = passwordField.getText();
			Connection.request = new Request();
			Connection.request.setType(RequestType.LOGIN);
			Connection.request.setMail(username);
			Connection.request.setPassword(password);

			Connection.getOutput().writeObject(Connection.request);

			received = (Request) Connection.getInput().readObject();
			if (received.getResult()) {
				user = received.getUser();
			} else {
				AlertBox.display("Error", received.getMessage());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (received.getResult());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

}
