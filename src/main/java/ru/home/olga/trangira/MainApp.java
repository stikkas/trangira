package ru.home.olga.trangira;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.home.olga.trangira.model.dao.ArticleDao;
import ru.home.olga.trangira.model.dao.RashodDao;
import ru.home.olga.trangira.view.Controller;

public class MainApp extends Application {

	private final AnnotationConfigApplicationContext ctx
			= new AnnotationConfigApplicationContext(SpringConfig.class);
	private Stage addDialog;
	private Stage editDialog;
	private Stage stage;

	private final ArticleDao ad = ctx.getBean(ArticleDao.class);

	private final RashodDao rd = ctx.getBean(RashodDao.class);

	private final ObservableList<String> articles;

	public MainApp() {
		articles = FXCollections.observableArrayList(ad.findAll());
	}

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
			addDialog.initModality(Modality.APPLICATION_MODAL);
			completeStage(addDialog, "AddDialog.fxml", "Добавление данных");
		} else {
			addDialog.show();
		}

	}

	public void showEditDialog() throws IOException {
		if (editDialog == null) {
			editDialog = new Stage();
			editDialog.initModality(Modality.APPLICATION_MODAL);
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

	public void fillArticles(String prefix) {
		articles.clear();
		if (prefix == null) {
			articles.addAll(ad.findAll());
		} else {
			articles.addAll(ad.findStartsWith(prefix));
		}
	}

	public ObservableList<String> getArticles() {
		return articles;
	}

	public ArticleDao getAd() {
		return ad;
	}

	public RashodDao getRd() {
		return rd;
	}

}
