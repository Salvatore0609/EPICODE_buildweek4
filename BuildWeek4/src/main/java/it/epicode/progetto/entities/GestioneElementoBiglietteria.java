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
	final static String reset = "\u001B[0m";
	final static String rosso = "\u001B[31m";
	final static String verde = "\u001B[32m";
	final static String azzurro = "\u001B[36m";
	final static String giallo = "\u001B[33m";

	public static void visualizzaBigliettiUtente(Long myUser) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();
			ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);
			System.out.println("*************************");
			System.out.println("*** STORICO BIGLIETTI ***");
			System.out.println("*************************");
			System.out.println();
			List<ElementoBiglietteria> biglietti = ebDao.findAllBiglietibyUtente(myUser);
			if (biglietti.isEmpty()) {
				System.out.println("Non hai ancora acquistato nessun biglietto");
				try {
					System.out.println();
					System.out.print("Premi invio per continuare...");
					System.in.read();
					ClearTerminal.clearConsole();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				for (ElementoBiglietteria biglietto : biglietti) {
					System.out.println("🎫 " + biglietto);
				}
				try {
					System.out.println();
					System.out.print("Premi invio per continuare...");
					System.in.read();
					ClearTerminal.clearConsole();
				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					if (em != null)
						em.close();
					if (emf != null)
						emf.close();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Errore nella visualizzazione dei biglietti", e);
		}
	}

	public static void visualizzaAbbonamentiUtente(Long myUser) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();
			ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);
			System.out.println("***************************");
			System.out.println("*** STORICO ABBONAMENTI ***");
			System.out.println("***************************");
			System.out.println();
			List<ElementoBiglietteria> abbonamenti = ebDao.findAllAbbonamentibyUtente(myUser);
			if (abbonamenti.isEmpty()) {
				System.out.println("Non hai ancora acquistato nessun abbonamento");
				try {
					System.out.println();
					System.out.print("Premi invio per continuare...");
					System.in.read();
					ClearTerminal.clearConsole();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				for (ElementoBiglietteria abbonamento : abbonamenti) {
					System.out.println("🎟️ " + abbonamento);
				}
				try {
					System.out.println();
					System.out.print("Premi invio per continuare...");
					System.in.read();
					ClearTerminal.clearConsole();
				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					if (em != null)
						em.close();
					if (emf != null)
						emf.close();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Errore nella visualizzazione dell'abbonamento", e);
		}
	}

	public static void visualizzaScadenzaTessera(Long myUser) {
		final String giallo = "\u001B[33m";
		final String reset = "\u001B[0m";
		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();

			TessereDao tDao = new TessereDao(em);
			Utente utente = em.find(Utente.class, myUser);
			Tessera tesseraEsistente = tDao.findByUtente(utente);
			LocalDate scadenza = tesseraEsistente.getDataScadenza();
			System.out.println("*****************************");
			System.out.println("***** SCADENZA TESSERA ******");
			System.out.println("*****************************");
			System.out.println();
			if (tesseraEsistente == null) {
				System.out.println( giallo +"Non hai ancora acquistato nessuna tessera" + reset);
				try {
					System.out.println();
					System.out.print("Premi invio per continuare...");
					System.in.read();
					ClearTerminal.clearConsole();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("La tua tessera scade il giorno: " + giallo
					+ scadenza.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + reset);
			try {
				System.out.println();
				System.out.print("Premi invio per continuare...");
				System.in.read();
				ClearTerminal.clearConsole();
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				if (em != null)
					em.close();
				if (emf != null)
					emf.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Errore nella visualizzazione della scadenza della tessera", e);
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
			System.out.println("**********************************");
			System.out.println("***** CONTROLLA ABBONAMENTO ******");
			System.out.println("**********************************");
			System.out.println();
			String abbonamento = tDao.findAbbonamentoByTessera(tesseraEsistente.getIdTessera());
			System.out.println(abbonamento);
			try {
				System.out.println();
				System.out.print("Premi invio per continuare...");
				System.in.read();
				ClearTerminal.clearConsole();
			} catch (Exception e) {
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
		boolean biglietto = true;
		while (biglietto) {
			EntityManagerFactory emf = null;
			EntityManager em = null;
			try {
				emf = Persistence.createEntityManagerFactory("epicode");
				em = emf.createEntityManager();

				RivenditoreDAO rDao = new RivenditoreDAO(em);
				ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);
				UtentiDao uDao = new UtentiDao(em);

				System.out.println("**********************");
				System.out.println("***** BIGLIETTO ******");
				System.out.println("**********************");
				System.out.println();
				System.out.println("Seleziona un rivenditore per acquistare un biglietto");
				System.out.println();
				List<Rivenditore> rivenditori = rDao.findAll();
				int index = 1;
				for (Rivenditore r : rivenditori) {
					System.out.println(index + ". " + r.getNome());
					index++;
				}
				System.out.println("0. Esci");
				System.out.println();
				System.out.print("Scelta: ");
				int scelta = scanner.nextInt();
				scanner.nextLine();
				if (scelta == 0) {
					ClearTerminal.clearConsole();
					return;
				}

				if (scelta < 1 || scelta > rivenditori.size()) {
					System.out.println(rosso
							+ "Scelta non valida. Seleziona un rivenditore o un distributore dalla lista." + reset);
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
				System.out.println();
				System.out.println(
						verde + "Biglietto acquistato con successo con identificativo " + eb.getIdBiglietto() + reset);
				try {
					System.out.println();
					System.out.print("Premi invio per continuare...");
					System.in.read();
					ClearTerminal.clearConsole();
				} catch (Exception e) {
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
			biglietto = false;
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
				System.out.print("Premi invio per continuare...");
				System.in.read();

				{
					ClearTerminal.clearConsole();
					MenuUtente.menuUtente(myUser);
				}
			} else {
				System.out.println("Seleziona un rivenditore per acquistare un biglietto");
				System.out.println();
				rivenditori = rDao.findAll();
				int index = 1;
				for (Rivenditore r : rivenditori) {
					System.out.println(index + ". " + r.getNome());
					index++;
				}

				System.out.println("0. Esci");
				System.out.println();
				System.out.print("Scelta: ");
				int scelta = scanner.nextInt();
				scanner.nextLine();

				if (scelta == 0) {
					ClearTerminal.clearConsole();
					MenuUtente.menuUtente(myUser);
				}

				if (scelta < 1 || scelta > rivenditori.size()) {
					System.out.println("Scelta non valida. Seleziona un rivenditore o un distributore dalla lista.");
					Thread.sleep(1500);
				}

				System.out.println();
				System.out.println("Indica la durata dell'abbonamento: ");
				System.out.println();
				System.out.println("1. Settimanale");
				System.out.println("2. Mensile");
				System.out.println();
				System.out.print("Scelta: ");
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
						System.out.println();
						System.out.println(
								verde + "Abbonamento " + durataAbbonamento + " acquistato con successo!" + reset);
					}
				}
				try {
					System.out.println();
					System.out.print("Premi invio per continuare...");
					System.in.read();
					ClearTerminal.clearConsole();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// GestioneElementoBiglietteria.creaAbbonamento(myUser);
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
