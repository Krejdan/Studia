package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.Request;
import server.RequestType;
import tables.ExamCategory;
import tables.Term;
import tables.User;
import util.Connection;

public class OrderTermController implements Initializable {

	@FXML
	private ChoiceBox<ExamCategory> categoryChoiceBox;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button checkAvailableButton;
	@FXML
	private Button orderTermButton;
	@FXML
	private Button returnButton;
	@FXML
	private ListView<Term> termsList;
	@FXML
	private ChoiceBox<String> examType;
	private User user;
	private boolean canOrderPractical;

	public void initUser(User user) {
		this.user = user;
	}
	
	@FXML
	private void chceckAvailableOnClick(ActionEvent event) throws ClassNotFoundException, IOException {
		orderTermButton.setDisable(true);

		if (datePicker.getValue() == null || categoryChoiceBox.getSelectionModel().getSelectedItem() == null) {
			AlertBox.display("Error", "Prosze wypelnic wszystkie pola");
			return;
		}

		termsList.getItems().removeAll(termsList.getItems());
		LocalDate date = datePicker.getValue();
		
		Connection.request = new Request();
		Connection.request.setType(RequestType.GET_FREE_TERMS);
		Connection.request.setDate(date);
		Connection.request.setUser(user);
		Connection.request.setCategory(categoryChoiceBox.getSelectionModel().getSelectedItem());
		try {
			Connection.getOutput().writeObject(Connection.request);
			Request received = (Request) Connection.getInput().readObject();
			if (!received.getResult())
				AlertBox.display("Error", received.getMessage());
			termsList.getItems().addAll(received.getTermsList());
			canOrderPractical = received.isCanOrderPractical();
			if (canOrderPractical) 
				examType.getSelectionModel().select(1);
			else 
				examType.getSelectionModel().select(0);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		termsList.refresh();

	}

	@FXML
	private void orderOnClick(ActionEvent event) {

		Connection.request = new Request();
		Connection.request.setCategory(categoryChoiceBox.getSelectionModel().getSelectedItem());
		Connection.request.setTerm(termsList.getSelectionModel().getSelectedItem());
		Connection.request.setUser(user);
		if (canOrderPractical) {
			Connection.request.setType(RequestType.ORDER_PRACTICAL);
			try {
				Connection.getOutput().writeObject(Connection.request);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Connection.request.setType(RequestType.ORDER_THEORETICAL);
			try {
				Connection.getOutput().writeObject(Connection.request);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Request received;
		try {
			received = (Request) Connection.getInput().readObject();
			AlertBox.display("Message", received.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (datePicker.getValue() == null || categoryChoiceBox.getSelectionModel().getSelectedItem() == null) {
			AlertBox.display("Error", "Prosze wypelnic wszystkie pola");
			return;
		}

		termsList.getItems().removeAll(termsList.getItems());
		LocalDate date = datePicker.getValue();

		Connection.request = new Request();
		Connection.request.setType(RequestType.GET_FREE_TERMS);
		Connection.request.setDate(date);
		Connection.request.setUser(user);
		Connection.request.setCategory(categoryChoiceBox.getSelectionModel().getSelectedItem());
		try {
			Connection.getOutput().writeObject(Connection.request);
			received = (Request) Connection.getInput().readObject();
			termsList.getItems().addAll(received.getTermsList());
			canOrderPractical = received.isCanOrderPractical();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		termsList.refresh();
	}

	@FXML
	private void returnOnClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ClientMainView.fxml"));
			Parent clientMainView = loader.load();
			Scene clientMainViewScene = new Scene(clientMainView);
			
			ClientViewController clientMainViewController = loader.getController();
			clientMainViewController.initUser(user);	
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(clientMainViewScene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void termsListClicked(MouseEvent event) {
		if (termsList.getSelectionModel().getSelectedItem() != null) 
			this.orderTermButton.setDisable(false);
	}

	@FXML
	void examTypeClicked(MouseEvent event) {

	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Connection.request = new Request();
		Connection.request.setType(RequestType.GET_CATEGORIES);
		try {
			Connection.getOutput().writeObject(Connection.request);

			List<ExamCategory> examCategories = (List<ExamCategory>) Connection.getInput().readObject();
			categoryChoiceBox.getItems().addAll(examCategories);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<String> examTypes = new ArrayList<String>();
		examTypes.add("TEORETYCZNY");
		examTypes.add("PRAKTYCZNY");
		examTypes.add("");
		examType.setItems(FXCollections.observableArrayList(examTypes));
		examType.setDisable(true);
		examType.getSelectionModel().select(3);

	}

}
