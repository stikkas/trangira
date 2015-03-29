package ru.home.olga.trangira.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 *
 * @author basa
 */
public class MainController extends Controller {

	@FXML
	private TableView tableView;

	@FXML
	private void openAddDialog() throws IOException {
		app.showAddDialog();
	}

	@FXML
	private void openEditDialog() throws IOException {
		app.showEditDialog();
	}

	@FXML
	private void exitApp() {
		app.getStage().close();
	}

	public void initialize() {
		tableView.setPlaceholder(new Label("Нет данных"));
	}

}
