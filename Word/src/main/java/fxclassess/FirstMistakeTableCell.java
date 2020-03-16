package fxclassess;

import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tables.ExamTaskResult;

public class FirstMistakeTableCell extends TableCell<ExamTaskResult, Integer> {

	@Override
	protected void updateItem(Integer item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setGraphic(null);
		} else if ((item & 2) != 0) {
			Rectangle rect = new Rectangle(12, 12);
			rect.setFill(Color.WHITE);
			rect.setTranslateX(4);
			setGraphic(rect);
		} else {
			setGraphic(null);
		}

	}
}
