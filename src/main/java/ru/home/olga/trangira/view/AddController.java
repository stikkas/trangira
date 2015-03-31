package ru.home.olga.trangira.view;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ru.home.olga.trangira.MainApp;
import ru.home.olga.trangira.model.Article;
import ru.home.olga.trangira.model.Data;
import ru.home.olga.trangira.model.Rashod;
import ru.home.olga.utils.DateUtil;

/**
 *
 * @author basa
 */
public class AddController implements Controller {

	private MainApp app;

	@FXML
	private ComboBox<String> articleBox;

	@FXML
	private TextField sumEdit;

	@FXML
	private TableColumn<Data, LocalDate> dateColumn;

	@FXML
	private TableColumn<Data, String> articleColumn;

	@FXML
	private TableColumn<Data, Double> summaColumn;

	@FXML
	private DatePicker dateEdit;

	@FXML
	private TableView<Data> table;

	/**
	 * Заносит данные в таблицу
	 */
	@FXML
	private void addRashod() {
		String article = articleBox.getValue();
		LocalDate date = dateEdit.getValue();
		Double summa;
		try {
			summa = Double.parseDouble(sumEdit.getText());
		} catch (Exception ex) {
			return;
		}
		if (article != null && !article.trim().isEmpty() && date != null) {
			table.getItems().add(new Data(article.trim(), date, summa));
		}
	}

	/**
	 * Записывает данные в базу
	 */
	@FXML
	private void dumpData() {
		table.getItems().stream().forEach((data) -> {
			app.getRd().create(new Rashod(DateUtil.fromLocalDate(data.getDate()),
					new Article(data.getArticle()),
					data.getSumma()));
		});
		table.getItems().clear();
		articleBox.setItems(getArticles(null));
	}

	/**
	 * Получает список статей расхода из базы
	 *
	 * @param prefix начало имени статьи, если null - то выбираем все значения
	 * @return список статей
	 */
	private ObservableList<String> getArticles(String prefix) {
		if (prefix == null) {
			return FXCollections.observableArrayList(app.getAd().findAll());
		} else {
			return FXCollections.observableArrayList(app.getAd().findStartsWith(prefix));
		}
	}

	public void initialize() {
		/*
		 articleBox.getEditor().textProperty().addListener(newValue -> {
		 app.fillArticles(((StringProperty) newValue).get());
		 articleBox.setItems(app.getArticles());
		 });
		 */
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		articleColumn.setCellValueFactory(cellData -> cellData.getValue().articleProperty());
		summaColumn.setCellValueFactory(cellData -> cellData.getValue().summaProperty());
		table.setPlaceholder(new Label());
	}

	@Override
	public void setApp(MainApp stage) {
		app = stage;
		articleBox.setItems(getArticles(null));
	}
}
