package ru.home.olga.trangira.model.dao;

import java.util.List;

/**
 *
 * @author Благодатских С.
 */
public class ArticleDao extends AbstractDao {

	public ArticleDao() {
		super(true);
	}


	public List<String> findAll() {
		return em.createNamedQuery("Article.findAll").getResultList();
	}

	public List<String> findStartsWith(String prefix) {
		return em.createNamedQuery("Article.findStartsWith")
				.setParameter("name", prefix + "%")
				.getResultList();
	}
}
