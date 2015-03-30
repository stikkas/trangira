package ru.home.olga.trangira.model.dao;

import java.nio.file.Paths;
import java.util.Properties;
import java.util.prefs.Preferences;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import ru.home.olga.trangira.MainApp;

/**
 *
 * @author Благодатских С.
 */
public class AbstractDao {

	protected static EntityManager em;
	protected static EntityTransaction etx;

	public AbstractDao() {
		reset();
	}

	public void reset() {
		String baseDir = Preferences.userNodeForPackage(MainApp.class).get("dbPath", null);
		if (baseDir == null) {
			baseDir = System.getenv("HOME");
		}
		reset(baseDir);
	}

	public void reset(String newPath) {
		if (!newPath.endsWith("Trangira")) {
			newPath = Paths.get(newPath, "Trangira").toString();
		}
		Properties props = new Properties();
		props.setProperty("javax.persistence.jdbc.url", "jdbc:derby:" + newPath + ";create=true");
		em = Persistence.createEntityManagerFactory("PU", props).createEntityManager();
		etx = em.getTransaction();
		Preferences.userNodeForPackage(MainApp.class).put("dbPath", newPath);
	}
}
