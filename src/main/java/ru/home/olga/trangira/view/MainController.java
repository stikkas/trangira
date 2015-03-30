package ru.home.olga.trangira.view;

import java.io.File;
import ru.home.olga.trangira.model.Statistic;
import java.io.IOException;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import ru.home.olga.trangira.MainApp;
import ru.home.olga.utils.DateUtil;

/**
 *
 * @author basa
 */
public class MainController implements Controller {

	private MainApp app;

	@FXML
	private TableView<Statistic> table;

	@FXML
	private DatePicker startDate;

	@FXML
	private DatePicker endDate;

	@FXML
	private TableColumn<Statistic, String> articleColumn;

	@FXML
	private TableColumn<Statistic, Double> summaColumn;

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

	/**
	 * Меняет базу
	 */
	@FXML
	private void chooseDB() {
		DirectoryChooser dc = new DirectoryChooser();
		File dir = dc.showDialog(app.getStage());
		if (dir != null) {
			app.getRd().reset(dir.getAbsolutePath());
			app.fillArticles(null);
			table.getItems().clear();
		}
	}

	/**
	 * Отображает данные за период
	 */
	@FXML
	private void showData() {
		Date start = DateUtil.fromLocalDate(startDate.getValue());
		Date end = DateUtil.fromLocalDate(endDate.getValue());
		ObservableList<Statistic> items = table.getItems();
		items.clear();
		Double total = 0.0;
		for (Object[] r : app.getRd().find(start, end)) {
			Double sum = (Double) r[1];
			items.add(new Statistic((String) r[0], (Double) r[1]));
			total += sum;
		}
		if (total > 0) {
			items.add(new Statistic("", null));
			total *= 100;
			total = (double) total.intValue() / 100;
			items.add(new Statistic("Итого", total));
		}
	}

	public void initialize() {
		table.setPlaceholder(new Label());
		articleColumn.setCellValueFactory(data -> data.getValue().articleProperty());
		summaColumn.setCellValueFactory(data -> data.getValue().summaProperty());
	}

	@Override
	public void setApp(MainApp stage) {
		app = stage;
	}

}
