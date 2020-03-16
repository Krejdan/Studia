package fxclassess;

import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tables.ExamResult;

public class ResultTableCell extends TableCell<ExamResult, String> {

	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setGraphic(null);
		} else if (item.equals("POZYTYWNY")) 	{
			Rectangle rect = new Rectangle(12, 12);
			rect.setFill(Color.GREEN);
			rect.setTranslateX(4);
			setGraphic(rect);
		} else if (item.equals("NEGATYWNY")) {
			Rectangle rect = new Rectangle(12, 12);
			rect.setFill(Color.RED);
			rect.setTranslateX(4);
			setGraphic(rect);
		} else {
			setGraphic(null);
		}


	}
	
}
