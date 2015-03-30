package ru.home.olga.trangira.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Наименования статей расхода
 *
 * @author Благодатских С.
 */
@NamedQueries({
	@NamedQuery(name = "Article.findAll",
			query = "SELECT a.name FROM Article a ORDER BY a.name"),
	@NamedQuery(name = "Article.findStartsWith",
			query = "SELECT a.name FROM Article a WHERE a.name like :name ORDER BY a.name"),
	@NamedQuery(name = "Article.findByName",
			query = "SELECT a FROM Article a WHERE a.name = :name")
})

@Entity
public class Article implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", unique = true)
	private String name;

	public Article() {
	}

	public Article(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
