package it.epicode.progetto.entities;

import it.epicode.progetto.dao.TessereDao;

import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

import static it.epicode.progetto.utils.Input.scanner;
public class GestioneTessere {
	final static String reset = "\u001B[0m";
	final static String rosso = "\u001B[31m";
	final static String verde = "\u001B[32m";
	final static String azzurro = "\u001B[36m";
	final static String giallo = "\u001B[33m";

	public static Tessera crea(Long myUser) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		Tessera nuovaTessera = null;

		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();

			Long id = null;
			if (myUser != null) {
				id = myUser;
			} else {

				System.out.println("Inserisci l'ID dell'utente a cui associare la tessera:");

				id = scanner.nextLong();
				scanner.nextLine();
			}

			Utente utente = em.find(Utente.class, id);
			TessereDao tDao = new TessereDao(em);

			if (utente != null) {
				// Verifica se l'utente ha già una tessera
				Tessera tesseraEsistente = tDao.findByUtente(utente);
				if (tesseraEsistente != null) {
					System.out.println("*************************");
					System.out.println("***** CREA TESSERA ******");
					System.out.println("*************************");
					System.out.println();

					System.out.println(verde + "L'utente ha già una tessera associata attiva con identificativo "
							+ reset + tesseraEsistente.getIdTessera() + "!");
					try {
						System.out.println();
						System.out.print("Premi invio per continuare...");
						System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					}
					ClearTerminal.clearConsole();
					return tesseraEsistente;
				}
				nuovaTessera = new Tessera(utente, LocalDate.now());

				em.getTransaction().begin();
				tDao.insert(nuovaTessera);
				em.getTransaction().commit();

				System.out.println("************************");
				System.out.println("***** CREA TESSERA******");
				System.out.println("************************");
				System.out.println();
				System.out.println(verde + "Tessera creata con successo per l'utente: " + utente.getNome() + " "
						+ utente.getCognome() + reset);
				try {
					System.out.println();
					System.out.print("Premi invio per continuare...");
					System.in.read();
					ClearTerminal.clearConsole();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println(rosso+"Utente non trovato."+reset);
			}
		} catch (Exception e) {
			throw new RuntimeException(rosso+"Errore nella creazione della tessera"+reset, e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}

		return nuovaTessera;
	}
}
