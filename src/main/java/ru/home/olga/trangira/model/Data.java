package ru.home.olga.trangira.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Благодатских С.
 */
public class Data {

	private final StringProperty article = new SimpleStringProperty();
	private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
	private final ObjectProperty<Double> summa = new SimpleObjectProperty<>();

	public Data(String article, LocalDate date, Double summa) {
		this.article.set(article);
		this.date.set(date);
		this.summa.set(summa);
	}

	public String getArticle() {
		return article.get();
	}

	public void setArticle(String article) {
		this.article.set(article);
	}

	public StringProperty articleProperty() {
		return article;
	}

	public LocalDate getDate() {
		return date.get();
	}

	public void setDate(LocalDate date) {
		this.date.set(date);
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return date;
	}

	public Double getSumma() {
		return summa.get();
	}

	public void setSumma(Double summa) {
		this.summa.set(summa);
	}

	public ObjectProperty<Double> summaProperty() {
		return summa;
	}
}
