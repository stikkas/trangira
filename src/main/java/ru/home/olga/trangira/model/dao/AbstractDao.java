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

	public AbstractDao(boolean res) {
		if (res) {
			reset();
		}
	}

	public void reset() {
		String dbFileName = Preferences.userNodeForPackage(MainApp.class).get("dbPath", null);
		if (dbFileName == null) {
			dbFileName = Paths.get(System.getenv("HOME"), "trangira.sqlite").toString();
		}
		reset(dbFileName);
	}

	public void reset(String newPath) {
		/*
		 if (!newPath.endsWith("Trangira")) {
		 newPath = Paths.get(newPath, "Trangira").toString();
		 }
		 */
		Properties props = new Properties();
//		props.setProperty("javax.persistence.jdbc.url", "jdbc:derby:" + newPath + ";create=true");
		props.setProperty("javax.persistence.jdbc.url", "jdbc:sqlite:" + newPath);
		em = Persistence.createEntityManagerFactory("PU", props).createEntityManager();
		etx = em.getTransaction();
		Preferences.userNodeForPackage(MainApp.class).put("dbPath", newPath);
	}

	@Override
	protected void finalize() throws Throwable {
		if (em != null && em.isOpen()) {
			em.close();
		}
		super.finalize();
	}

}
