package it.epicode.progetto.utils;

import it.epicode.progetto.dao.UtentiDao;
import it.epicode.progetto.entities.Utente;
import it.epicode.progetto.enums.Ruolo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class CreateAdmin {
	public static void main(String[] args) {
		Map<String, Object> overrideProperties = new HashMap<>();
		overrideProperties.put("hibernate.hbm2ddl.auto", "create");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode", overrideProperties);
		EntityManager em = emf.createEntityManager();
		Utente admin = new Utente("admin", "Admin", "Team6", "admin", Ruolo.ADMIN, true);
		UtentiDao uDAO = new UtentiDao(em);
		em.getTransaction().begin();
		uDAO.insert(admin);
		em.getTransaction().commit();
	}
}
