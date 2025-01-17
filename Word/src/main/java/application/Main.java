package application;

import java.io.IOException;
import java.net.ConnectException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Connection;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				try {
					Connection.disconnect();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			});
			primaryStage.show();
			Connection.connect("127.0.0.1", 25565);
		} catch (ConnectException e) {
			AlertBox.display("Error", "Brak polaczenia z serwerem!");
			Platform.exit();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}
