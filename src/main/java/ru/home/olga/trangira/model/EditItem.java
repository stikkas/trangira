package ru.home.olga.trangira.model;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Благодатских С.
 */
public class EditItem {

	private final Long id;
	private final Date date;
	private final StringProperty article = new SimpleStringProperty();
	private final ObjectProperty<Double> summa = new SimpleObjectProperty<>();
	private final BooleanProperty delete = new SimpleBooleanProperty(false);

	public EditItem(Rashod rashod) {
//		id.set(rashod.getId());
		id = rashod.getId();
		date = rashod.getTrangDate();
		article.set(rashod.getArticle().getName());
		summa.set(rashod.getSumma());
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setArticle(String article) {
		this.article.set(article);
	}

	public String getArticle() {
		return article.get();
	}

	public StringProperty articleProperty() {
		return article;
	}

	public void setSumma(Double summa) {
		this.summa.set(summa);
	}

	public Double getSumma() {
		return summa.get();
	}

	public ObjectProperty<Double> summaProperty() {
		return summa;
	}

	public void setDelete(boolean delete) {
		this.delete.set(delete);
	}

	public boolean getDelete() {
		return delete.get();
	}

	public BooleanProperty deleteProperty() {
		return delete;
	}
}
