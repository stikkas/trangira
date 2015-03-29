package ru.home.olga.trangira;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.home.olga.trangira.view.Controller;

public class MainApp extends Application {

	private Stage addDialog;
	private Stage editDialog;
	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage stage) throws Exception {
		completeStage(stage, "Trangira.fxml", "Трать не жалея!");
		this.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showAddDialog() throws IOException {
		if (addDialog == null) {
			addDialog = new Stage();
			System.out.println("Add dialog create");
			completeStage(addDialog, "AddDialog.fxml", "Добавление данных");
		} else {
			addDialog.show();
		}
	}

	public void showEditDialog() throws IOException {
		if (editDialog == null) {
			System.out.println("Edit dialog create");
			editDialog = new Stage();
			completeStage(editDialog, "EditDialog.fxml", "Удаление / изменение данных");
		} else {
			editDialog.show();
		}
	}

	private void completeStage(Stage stage, String fxmlFileName, String title) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFileName));

		Parent root = loader.load();
		((Controller) loader.getController()).setApp(this);

		Scene scene = new Scene(root);

		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
		stage.setMinWidth(stage.getWidth());
		stage.setMinHeight(stage.getHeight());
	}
}
