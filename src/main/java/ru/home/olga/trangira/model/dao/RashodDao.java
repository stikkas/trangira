package ru.home.olga.trangira.model.dao;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.home.olga.trangira.model.Article;
import ru.home.olga.trangira.model.Rashod;

/**
 *
 * @author Благодатских С.
 */
@Component
public class RashodDao extends AbstractDao {

	public List<Object[]> find(Date start, Date end) {
		if (start != null && end != null) {
			return em.createNamedQuery("Rashod.findInterval")
					.setParameter("start", start)
					.setParameter("end", end)
					.getResultList();

		} else if (start == null && end == null) {
			return em.createNamedQuery("Rashod.findAll")
					.getResultList();
		} else if (start == null) {
			return em.createNamedQuery("Rashod.findToEnd")
					.setParameter("end", end)
					.getResultList();
		}
		return em.createNamedQuery("Rashod.findFromStart")
				.setParameter("start", start)
				.getResultList();
	}

	public List<Rashod> find(Date date) {
		return em.createNamedQuery("Rashod.findForDate")
				.setParameter("date", date)
				.getResultList();
	}

	public void create(Rashod entity) {
		etx.begin();
		setArticle(entity);
		em.persist(entity);
		etx.commit();
	}

	public void update(Rashod entity) {
		etx.begin();
		setArticle(entity);
		em.merge(entity);
		etx.commit();
	}

	public void remove(List<Long> ids) {
		etx.begin();
		em.createNamedQuery("Rashod.removeByIds")
				.setParameter("ids", ids)
				.executeUpdate();
		etx.commit();
	}

	public void remove(Rashod entity) {
		etx.begin();
		em.remove(entity);
		etx.commit();
	}

	private void setArticle(Rashod entity) {
		List<Article> articles = em.createNamedQuery("Article.findByName")
				.setParameter("name", entity.getArticle().getName())
				.getResultList();
		if (articles.isEmpty()) {
			em.persist(entity.getArticle());
		} else {
			entity.setArticle(articles.get(0));
		}
	}
}
