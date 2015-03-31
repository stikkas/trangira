package ru.home.olga.trangira.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import ru.home.olga.trangira.MainApp;
import ru.home.olga.trangira.model.Article;
import ru.home.olga.trangira.model.EditItem;
import ru.home.olga.trangira.model.Rashod;
import ru.home.olga.trangira.model.dao.RashodDao;
import ru.home.olga.utils.DateUtil;

/**
 *
 * @author basa
 */
public class EditController implements Controller {

	private MainApp app;

	@FXML
	private DatePicker dateEdit;

	@FXML
	private TableView<EditItem> table;

	@FXML
	private TableColumn<EditItem, String> articleColumn;

	@FXML
	private TableColumn<EditItem, Double> summaColumn;

	@FXML
	private TableColumn<EditItem, Boolean> deleteColumn;

	/**
	 * Отображает данные за выбранную дату
	 */
	@FXML
	private void showItems() {
		ObservableList<EditItem> items = table.getItems();
		items.clear();
		Date d = DateUtil.fromLocalDate(dateEdit.getValue());
		if (d != null) {
			app.getRd().find(d)
					.stream().forEach(r -> {
						items.add(new EditItem(r));
					});
		}
	}

	/**
	 * Записывает изменения в базу
	 */
	@FXML
	private void applyChanges() {
		ObservableList<EditItem> items = table.getItems();
		List<Long> ids = new ArrayList<>();
		RashodDao rd = app.getRd();

		items.stream().forEach(it -> {
			if (it.getDelete()) {
				ids.add(it.getId());
			} else {
				rd.update(new Rashod(it.getId(), it.getDate(),
						new Article(it.getArticle()), it.getSumma()));
			}
		});
		if (!ids.isEmpty()) {
			rd.remove(ids);
		}
		items.clear();

	}

	public void initialize() {
		articleColumn.setCellValueFactory(cellData -> cellData.getValue().articleProperty());
		articleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		summaColumn.setCellValueFactory(cellData -> cellData.getValue().summaProperty());
		summaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {

			@Override
			public String toString(Double t) {
				if (t == null) {
					return "";
				}
				return t.toString();
			}

			@Override
			public Double fromString(String string) {
				try {
					return Double.parseDouble(string);
				} catch (Exception e) {
					return null;
				}
			}
		}));
		deleteColumn.setCellValueFactory(cellData -> cellData.getValue().deleteProperty());
		deleteColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
		table.setPlaceholder(new Label());
	}

	@Override
	public void setApp(MainApp stage) {
		app = stage;
	}
}
