package ru.home.olga.trangira.model.dao;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Благодатских С.
 */
@Component
public class ArticleDao extends AbstractDao {

	public List<String> findAll() {
		return em.createNamedQuery("Article.findAll").getResultList();
	}

	public List<String> findStartsWith(String prefix) {
		return em.createNamedQuery("Article.findStartsWith")
				.setParameter("name", prefix + "%")
				.getResultList();
	}
}
