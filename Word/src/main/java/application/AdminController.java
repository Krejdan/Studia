package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.binding.Bindings;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.Request;
import server.RequestType;
import tables.ExamCategory;
import tables.ExaminationCard;
import tables.Position;
import tables.Term;
import tables.User;
import util.Connection;

public class AdminController implements Initializable {

	@FXML
	private TextField emailField;
	@FXML
	private PasswordField passwordField;
	@FXML 
	private TextField firstNameField;
	@FXML
	private TextField secondNameField;
	@FXML 
	private DatePicker datePicker;
	@FXML
	private ChoiceBox<Position> roleChoiceBox;
	@FXML
	private Button registerButton;
	@FXML
	private Button returnButton;
	@FXML
	private TableView<User> tableView;
	@FXML
	private TableColumn<User, String> firstNameColumn;
	@FXML
	private TableColumn<User, String> secondNameColumn;
	@FXML
	private TableColumn<User, String> mailColumn;
	
	private boolean validateEmail() {
		String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(emailField.getText());
		if(!matcher.matches()) {
			AlertBox.display("Error", "Podano niew≥aúciwy E-mail");
			return false;
		}
		return true;
	}
	
	private boolean validatePassword() {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(passwordField.getText());
		if(!matcher.matches()) {
			AlertBox.display("Error", "Has≥o powinno sk≥adaÊ siÍ z przynajmniej jednej ma≥ej litery i cyfry");
			return false;
		}
		return true;
	}
	
	private boolean validateFirstName() {
		String regex = "[A-Za-zøüÊÒÛ≥ÍπúØè∆•å £”—]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(firstNameField.getText());
		if(!matcher.matches()) {
			AlertBox.display("Error", "Podano niew≥aúciwe imiÍ");
			return false;
		}
		return true;
	}
	
	private boolean validateSecondName() {
		String regex = "[A-Za-zøüÊÒÛ≥ÍπúØè∆•å £”—]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(secondNameField.getText());
		if(!matcher.matches()) {
			AlertBox.display("Error", "Podano niew≥aúciwe nazwisko");
			return false;
		}
		return true;
	}
	
	private boolean validateBirthDate() {
		Period period = Period.between(datePicker.getValue(), LocalDate.now());
		if(period.getYears() < 18) {
			AlertBox.display("Error", "Uøytownik musi mieÊ skoÒczone 18 lat");
			return false;
		}
		return true;
	}
	
	private void getUsers() {
		List<User> users = null;
		Connection.request = new Request();
		Connection.request.setType(RequestType.GET_USERS);
		try {
			Connection.getOutput().writeObject(Connection.request);
			users = (List<User>) Connection.getInput().readObject();
			ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
			tableView.getItems().clear();
			tableView.getItems().addAll(observableUsers);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace(); 
		}
	}
	
	@FXML
	void registerButtonOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
		if(validateEmail() && validatePassword() && validateFirstName() && validateSecondName() && validateBirthDate()) {
			User user = new User();
			user.setEmail(emailField.getText());
			user.setBirthDate(Date.valueOf(datePicker.getValue()));
			user.setPassword(passwordField.getText());
			user.setName(firstNameField.getText());
			user.setSecondName(secondNameField.getText());
			user.addPosition(roleChoiceBox.getValue());
			Connection.request = new Request();
			Connection.request.setType(RequestType.ADD_USER);
			Connection.request.setUser(user);
			Connection.getOutput().writeObject(Connection.request);
			Request received = (Request) Connection.getInput().readObject();
			if(!received.getResult())
				AlertBox.display("Error", received.getMessage());
			else {
				getUsers();
				AlertBox.display("Success", "Uøytkownik pomyúlnie dodany do systemu");
			}
		}
	}
	@FXML
	void returnButtonOnClick(ActionEvent event) {
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
	public void initialize(URL location, ResourceBundle resources) {
		Connection.request = new Request();
		Connection.request.setType(RequestType.GET_POSITIONS);
		List<Position> positions = null;
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		secondNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("secondName"));
		mailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		try {
			Connection.getOutput().writeObject(Connection.request);
			positions = (List<Position>) Connection.getInput().readObject();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		getUsers();
		roleChoiceBox.setItems(FXCollections.observableArrayList(positions));
		roleChoiceBox.getSelectionModel().select(0);
		registerButton.disableProperty().bind(Bindings.isEmpty(emailField.textProperty())
										.or(Bindings.isEmpty(passwordField.textProperty()))
										.or(Bindings.isEmpty(firstNameField.textProperty()))
										.or(Bindings.isEmpty(secondNameField.textProperty()))
										.or(datePicker.valueProperty().isNull()));
		
	}

}
