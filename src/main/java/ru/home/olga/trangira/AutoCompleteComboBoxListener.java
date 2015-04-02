package ru.home.olga.trangira;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.IndexRange;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AutoCompleteComboBoxListener implements EventHandler<KeyEvent> {

	private final ComboBox<String> comboBox;
	private StringBuilder sb;
	private int lastLength;

	public AutoCompleteComboBoxListener(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
		sb = new StringBuilder();

		comboBox.setEditable(true);
		comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);

		// add a focus listener such that if not in focus, reset the filtered typed keys
		comboBox.getEditor().focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					// in focus
				} else {
					lastLength = 0;
					sb.delete(0, sb.length());
					selectClosestResultBasedOnTextFieldValue(false, false);
				}
			}
		});

		comboBox.setOnMouseClicked(e -> {
			selectClosestResultBasedOnTextFieldValue(true, true);
		});
	}

	@Override
	public void handle(KeyEvent event) {
		// this variable is used to bypass the auto complete process if the length is the same.
		// this occurs if user types fast, the length of textfield will record after the user
		// has typed after a certain delay.
		if (lastLength != (comboBox.getEditor().getLength() - comboBox.getEditor().getSelectedText().length())) {
			lastLength = comboBox.getEditor().getLength() - comboBox.getEditor().getSelectedText().length();
		}

		if (event.isControlDown() || event.getCode() == KeyCode.BACK_SPACE
				|| event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
				|| event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.HOME
				|| event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
			return;
		}

		IndexRange ir = comboBox.getEditor().getSelection();
		sb.delete(0, sb.length());
		sb.append(comboBox.getEditor().getText());
		// remove selected string index until end so only unselected text will be recorded
		try {
			sb.delete(ir.getStart(), sb.length());
		} catch (Exception e) {
		}

		for (String str : comboBox.getItems()) {
			if (str.toLowerCase().startsWith(comboBox.getEditor().getText().toLowerCase())) {
				try {
					comboBox.getEditor().setText(sb.toString() + str.substring(sb.toString().length()));
				} catch (Exception e) {
					comboBox.getEditor().setText(sb.toString());
				}
				comboBox.getEditor().positionCaret(sb.toString().length());
				comboBox.getEditor().selectEnd();
				break;
			}
		}
	}

	/*
	 * selectClosestResultBasedOnTextFieldValue() - selects the item and scrolls to it when
	 * the popup is shown.
	 * 
	 * parameters:
	 *  affect - true if combobox is clicked to show popup so text and caret position will be readjusted.
	 *  inFocus - true if combobox has focus. If not, programmatically press enter key to add new entry to list.
	 * 
	 */
	private void selectClosestResultBasedOnTextFieldValue(boolean affect, boolean inFocus) {
		ObservableList items = AutoCompleteComboBoxListener.this.comboBox.getItems();
		boolean found = false;
		for (int i = 0; i < items.size(); i++) {
			if (AutoCompleteComboBoxListener.this.comboBox.getEditor().getText().toLowerCase().equals(items.get(i).toString().toLowerCase())) {
				try {
					ListView lv = ((ComboBoxListViewSkin) AutoCompleteComboBoxListener.this.comboBox.getSkin()).getListView();
					lv.getSelectionModel().clearAndSelect(i);
					lv.scrollTo(lv.getSelectionModel().getSelectedIndex());
					found = true;
					break;
				} catch (Exception e) {
				}
			}
		}

		String s = comboBox.getEditor().getText();
		if (!found && affect) {
			comboBox.getSelectionModel().clearSelection();
			comboBox.getEditor().setText(s);
			comboBox.getEditor().end();
		}
		/*
		 if (!inFocus && comboBox.getEditor().getText() != null && comboBox.getEditor().getText().trim().length() > 0) {
		 // press enter key programmatically to have this entry added
		 KeyEvent ke = new KeyEvent(comboBox, comboBox, KeyEvent.KEY_TYPED,
		 comboBox.getEditor().getText(), KeyCode.ENTER.getName(),
		 KeyCode.ENTER, false, false, false, false);
		 comboBox.fireEvent(ke);
		 }
		 */
	}

}
