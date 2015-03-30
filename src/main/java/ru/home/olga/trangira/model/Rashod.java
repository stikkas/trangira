package ru.home.olga.trangira.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 * Таблица с данными
 *
 * @author Благодатских С.
 */
@NamedQueries({
	@NamedQuery(name = "Rashod.findInterval",
			query = "SELECT a.name, SUM(r.summa) AS s FROM Rashod r join r.article "
					+ "a WHERE r.trangDate BETWEEN :start AND :end GROUP BY a.name ORDER BY s"
	),
	@NamedQuery(name = "Rashod.findFromStart",
			query = "SELECT a.name, SUM(r.summa) AS s FROM Rashod r join r.article "
					+ "a WHERE r.trangDate >= :start GROUP BY a.name ORDER BY s"
	),
	@NamedQuery(name = "Rashod.findToEnd",
			query = "SELECT a.name, SUM(r.summa) AS s FROM Rashod r join r.article "
					+ "a WHERE r.trangDate <= :end GROUP BY a.name ORDER BY s"
	),
	@NamedQuery(name = "Rashod.findAll",
			query = "SELECT a.name, SUM(r.summa) AS s FROM Rashod r join r.article "
					+ "a GROUP BY a.name ORDER BY s"
	),
	@NamedQuery(name = "Rashod.findForDate",
			query = "SELECT r FROM Rashod r WHERE r.trangDate = :date"),
	@NamedQuery(name = "Rashod.removeByIds",
			query = "DELETE FROM Rashod r WHERE r.id in :ids")})
@Entity
public class Rashod implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date trangDate;

	@ManyToOne
	private Article article;

	private Double summa;

	public Rashod() {
	}

	public Rashod(Date trangDate, Article article, Double summa) {
		this.trangDate = trangDate;
		this.article = article;
		this.summa = summa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTrangDate() {
		return trangDate;
	}

	public void setTrangDate(Date trangDate) {
		this.trangDate = trangDate;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Double getSumma() {
		return summa;
	}

	public void setSumma(Double summa) {
		this.summa = summa;
	}

}
