package fxclassess;

import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tables.QuestionResult;

public class QuestionResultTableCell extends TableCell<QuestionResult, Integer>{
	@Override
	protected void updateItem(Integer item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setGraphic(null);
		} else if (item == 1) 	{
			Rectangle rect = new Rectangle(12, 12);
			rect.setFill(Color.GREEN);
			rect.setTranslateX(4);
			setGraphic(rect);
		} else if (item == 0) {
			Rectangle rect = new Rectangle(12, 12);
			rect.setFill(Color.RED);
			rect.setTranslateX(4);
			setGraphic(rect);
		} else {
			setGraphic(null);
		}


	}
}
