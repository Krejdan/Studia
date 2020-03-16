package application;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AlertBox {

	public static void display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.UTILITY);
		window.setTitle(title);

		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button("OK");
		closeButton.setOnAction(e -> window.close());

		//label.setLayoutX(14.0);
		//label.setLayoutY(14.0);
		
		//closeButton.setLayoutX(168.0);
		//closeButton.setLayoutY(103.0);
		
		//closeButton.setPadding(new Insets(10, 10, 10, 10));
		
		VBox layout = new VBox(20);
		//layout.setMinSize(250.0, 150.0);
		/*AnchorPane anchorPane1 = new AnchorPane();
		layout.setPrefHeight(150.0);
		layout.setPrefWidth(250.0);
		
		AnchorPane.setTopAnchor(label, 14.0);
		AnchorPane.setLeftAnchor(label, 14.0);
		
		AnchorPane.setTopAnchor(closeButton, 103.0);
		AnchorPane.setLeftAnchor(closeButton, 168.0);
		*/
		

		VBox.setMargin(label, new Insets(10,10,10,10));
		
        HBox hBox=new HBox();
        hBox.getChildren().add(closeButton);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox.setMargin(closeButton, new Insets(10,10,10,10));
        
        layout.getChildren().addAll(label, hBox);
		
		Scene scene = new Scene(layout);
		URL url = AlertBox.class.getResource( "/dark_theme.css" );
		//String styleSheet = fileToStylesheetString(new File("target/classes/dark_theme.css"));
		scene.getStylesheets().add(url.toString());
		window.setScene(scene);
		window.showAndWait();

	}
	
	/*private static String fileToStylesheetString(File stylesheetFile) {
	    try {
	        return stylesheetFile.toURI().toURL().toString();
	    } catch ( MalformedURLException e ) {
	        return null;
	    }
	}*/
}
