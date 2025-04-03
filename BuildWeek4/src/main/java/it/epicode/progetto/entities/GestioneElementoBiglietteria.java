package it.epicode.progetto.entities;

import it.epicode.progetto.enums.DurataAbbonamento;
import it.epicode.progetto.dao.ElementoBiglietteriaDAO;
import it.epicode.progetto.dao.RivenditoreDAO;
import it.epicode.progetto.dao.TessereDao;
import it.epicode.progetto.dao.UtentiDao;
import it.epicode.progetto.menu.MenuUtente;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

import static it.epicode.progetto.utils.Input.scanner;

public class GestioneElementoBiglietteria {

	public static void visualizzaScadenzaTessera(Long myUser) {
		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();

			TessereDao tDao = new TessereDao(em);
			Utente utente = em.find(Utente.class, myUser);
			Tessera tesseraEsistente = tDao.findByUtente(utente);
			LocalDate scadenza = tesseraEsistente.getDataScadenza();
			System.out.println("La tua tessera scade il giorno: "
					+ scadenza.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			throw new RuntimeException("Errore nella visualizzazione dell'abbonamento", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}

	public static void visualizzaAbbonamento(Long myUser) {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();
			TessereDao tDao = new TessereDao(em);
			Utente utente = em.find(Utente.class, myUser);
			Tessera tesseraEsistente = tDao.findByUtente(utente);
			tDao.findAbbonamentoByTessera(tesseraEsistente.getIdTessera());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			throw new RuntimeException("Errore nella visualizzazione dell'abbonamento", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}

	public static void creaBiglietto(Long myUser) {
		while (true) {
			EntityManagerFactory emf = null;
			EntityManager em = null;
			try {
				emf = Persistence.createEntityManagerFactory("epicode");
				em = emf.createEntityManager();

				RivenditoreDAO rDao = new RivenditoreDAO(em);
				ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);
				UtentiDao uDao = new UtentiDao(em);

				ClearTerminal.clearConsole();
				System.out.println("**********************");
				System.out.println("***** BIGLIETTO ******");
				System.out.println("**********************");
				System.out.println();
				System.out.println("Elenco rivenditori autorizzati e distributori");
				System.out.println("automatici");
				System.out.println();
				List<Rivenditore> rivenditori = rDao.findAll();
				int index = 1;
				for (Rivenditore r : rivenditori) {
					System.out.println(index + ". " + r.getNome());
					index++;
				}
				System.out.println("0. Esci");
				System.out.println();
				System.out.println("Seleziona il rivenditore autorizzato");
				System.out.println("o il distributore automatico per il");
				System.out.println("tuo acquisto");
				System.out.println();
				int scelta = scanner.nextInt();
				scanner.nextLine();
				if (scelta == 0) {
					return;
				}

				if (scelta < 1 || scelta > rivenditori.size()) {
					System.out.println("Scelta non valida. Seleziona un rivenditore o un distributore dalla lista.");
					Thread.sleep(1500);
					continue;
				}

				Utente myObjUser;
				if (myUser != null) {
					myObjUser = uDao.findById(myUser);
				} else {
					myObjUser = null;
				}

				Rivenditore rivenditore = rivenditori.get(scelta - 1);
				Biglietto eb = Biglietto.builder().dataDiEmissione(LocalDate.now()).rivenditore(rivenditore)
						.utente(myObjUser).build();

				em.getTransaction().begin();
				ebDao.insert(eb);
				rDao.aggiornaBigliettiAbbonamentiEmessi();
				em.getTransaction().commit();
				System.out.println("Biglietto acquistato con successo!");
				System.out.println();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				throw new RuntimeException("Errore nella creazione del biglietto", e);
			} finally {
				if (em != null)
					em.close();
				if (emf != null)
					emf.close();
			}
		}
	}

	public static void creaAbbonamento(Long myUser) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();

			UtentiDao udao = new UtentiDao(em);
			TessereDao tDao = new TessereDao(em);
			RivenditoreDAO rDao = new RivenditoreDAO(em);
			ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);

			ClearTerminal.clearConsole();
			System.out.println("************************");
			System.out.println("***** ABBONAMENTO ******");
			System.out.println("************************");
			System.out.println();

			Utente utenteDaCercare = udao.findById(myUser);
			Tessera tesseraDaCercare = tDao.findByUtente(utenteDaCercare);

			boolean abbonamentoEsistente = tDao.isAbbonamentoByTessera(tesseraDaCercare.getIdTessera());

			List<Rivenditore> rivenditori = null;
			if (abbonamentoEsistente) {
				System.out.println();
				System.out.println("0. Esci");
				System.out.println();
				int scelta = scanner.nextInt();
				if (scelta == 0) {
					MenuUtente.menuUtente(myUser);
				}
			} else {
				System.out.println("Elenco rivenditori autorizzati e distributori");
				System.out.println("automatici");
				System.out.println();
				rivenditori = rDao.findAll();
				int index = 1;
				for (Rivenditore r : rivenditori) {
					System.out.println(index + ". " + r.getNome());
					index++;
				}

				System.out.println("0. Esci");
				System.out.println();

				System.out.println("Seleziona il rivenditore autorizzato");
				System.out.println("o il distributore automatico per il");
				System.out.println("tuo acquisto");
				System.out.println();
				int scelta = scanner.nextInt();
				scanner.nextLine();

				if (scelta == 0) {
					MenuUtente.menuUtente(myUser);
				}

				if (scelta < 1 || scelta > rivenditori.size()) {
					System.out.println("Scelta non valida. Seleziona un rivenditore o un distributore dalla lista.");
					Thread.sleep(1500);
				}

				System.out.println("Indica la durata dell'abbonamento: ");
				System.out.println("1. Settimanale");
				System.out.println("2. Mensile");
				int durata = scanner.nextInt();
				scanner.nextLine();
				DurataAbbonamento durataAbbonamento = null;
				LocalDate dataScadenza = null;
				if (durata == 1) {
					durataAbbonamento = DurataAbbonamento.SETTIMANALE;
					dataScadenza = LocalDate.now().plusWeeks(1);
				} else if (durata == 2) {
					durataAbbonamento = DurataAbbonamento.MENSILE;
					dataScadenza = LocalDate.now().plusMonths(1);
				}

				Abbonamento eb = null;
				Rivenditore rivenditore = rivenditori.get(scelta - 1);
				if (utenteDaCercare != null) {

					if (tesseraDaCercare != null) {
						eb = Abbonamento.builder().dataDiEmissione(LocalDate.now()).rivenditore(rivenditore)
								.durataAbbonamento(durataAbbonamento).scadenzaAbbonamento(dataScadenza)
								.tessera(tesseraDaCercare).build();

						em.getTransaction().begin();
						ebDao.insert(eb);
						rDao.aggiornaBigliettiAbbonamentiEmessi();
						em.getTransaction().commit();
						System.out.println("Abbonamento " + durataAbbonamento + " acquistato con successo!");
						System.out.println();
					}
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				GestioneElementoBiglietteria.creaAbbonamento(myUser);
			}

		} catch (Exception e) {
			throw new RuntimeException("Errore nella creazione del biglietto", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}
}
