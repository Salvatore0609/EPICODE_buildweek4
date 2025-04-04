package it.epicode.progetto.dao;

import it.epicode.progetto.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;

import static it.epicode.progetto.utils.Input.scanner;

@AllArgsConstructor
public class UtentiDao {
	private EntityManager em;

	public void insert(Utente u) {
		em.persist(u);
	}
	public void update(Utente u) {
		try {
			em.merge(u);
			System.out.println("Utente aggiornato con successo!");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(Utente u) {
		// imposto l'utente come inattivo
		u.setAttivo(false);
	}
	public Utente findById(Long id) {
		return em.find(Utente.class, id);
	}
	public Utente findByUsername(String username) {
		try {
			return em.createQuery("SELECT u FROM Utente u WHERE u.username = :username", Utente.class)
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	// ricerca per nome e cognome
	public Utente findByNomeAndCognome(String nome, String cognome) {
		return em.createQuery("SELECT u FROM Utente u WHERE u.nome = :nome AND u.cognome = :cognome", Utente.class)
				.setParameter("nome", nome).setParameter("cognome", cognome).getSingleResult();
	}
	// ricerca per username e password
	public Utente findByUsernameAndPassword(String username, String password) {
		try {
			return em.createQuery("""
					    SELECT u FROM Utente u
					    WHERE u.username = :username
					    AND u.password = :password
					""", Utente.class).setParameter("username", username).setParameter("password", password)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	//metodo per visualizzare tutti gli utenti attivi
	public Utente findAllAttivi() {
		em.createQuery("SELECT u FROM Utente u WHERE u.attivo = true", Utente.class).getResultList().forEach(u -> System.out.println(u));
		System.out.println();
		System.out.println("Premi INVIO per tornare al menu di gestione utenti");
		scanner.nextLine();
		return null;
	}
	//metodo per visualizzare tutti gli utenti inattivi
	public Utente findAllInattivi() {
		em.createQuery("SELECT u FROM Utente u WHERE u.attivo = false", Utente.class).getResultList().forEach(u -> System.out.println(u));
		System.out.println();
		System.out.println("Premi INVIO per tornare al menu di gestione utenti");
		scanner.nextLine();
		return null;
	}
}
