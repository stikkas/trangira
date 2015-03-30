package ru.home.olga.trangira.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Благодатских С.
 */
public class Statistic {

	private final StringProperty article = new SimpleStringProperty();
	private final ObjectProperty<Double> summa = new SimpleObjectProperty<>();

	public Statistic(String article, Double summa) {
		this.article.set(article);
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
