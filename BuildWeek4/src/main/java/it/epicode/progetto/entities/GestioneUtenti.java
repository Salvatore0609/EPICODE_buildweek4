package it.epicode.progetto.entities;

import it.epicode.progetto.dao.UtentiDao;
import it.epicode.progetto.enums.Ruolo;
import it.epicode.progetto.dao.TessereDao;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;
import static it.epicode.progetto.utils.Input.scanner;

public class GestioneUtenti {

	public static Utente crea(boolean isAdmin) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		Utente nuovoUtente = null;

		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();


			System.out.println("Inserisci il nome dell'utente:");
			String nomeUtente = scanner.nextLine();

			System.out.println("Inserisci il cognome dell'utente:");
			String cognomeUtente = scanner.nextLine();

			System.out.println("Inserisci uno username:");
			String username = scanner.nextLine();

			System.out.println("Inserisci una password:");
			String passwordUtente = scanner.nextLine();

			// controllo se l'utente loggato ha il ruolo di admin
			Ruolo ruolo;
			if (isAdmin) {
				System.out.println("Inserisci il ruolo dell'utente:");
				System.out.println("1. ADMIN");
				System.out.println("2. USER");
				int ruoloUtente = scanner.nextInt();
				scanner.nextLine();
				ruolo = (ruoloUtente == 1) ? Ruolo.ADMIN : Ruolo.USER;
			} else {
				ruolo = Ruolo.USER;
			}

			// Creazione utente
			UtentiDao uDao = new UtentiDao(em);
			nuovoUtente = new Utente(username, nomeUtente, cognomeUtente, passwordUtente, ruolo, true);

			System.out.println("Vuoi creare una tessera per questo utente? (S/N)");
			String risposta = scanner.nextLine();

			em.getTransaction().begin();

			// Salvataggio dell'utente
			uDao.insert(nuovoUtente);

			// Se richiesto, creazione e associazione tessera
			if (risposta.equalsIgnoreCase("S")) {
				Tessera nuovaTessera = new Tessera(nuovoUtente, LocalDate.now());

				TessereDao tDao = new TessereDao(em);
				tDao.insert(nuovaTessera);
				System.out.println("Tessera creata con successo!");
			}

			em.getTransaction().commit();
			System.out.println("Utente creato con successo!");

		} catch (Exception e) {
			throw new RuntimeException("Errore nella creazione dell'utente", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
		return nuovoUtente;
	}
	public static Utente aggiorna(Utente u) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		Utente utenteDaAggiornare = null;

		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();

			System.out.println("Inserisci il tipo di ricerca dell'utente:");
			System.out.println("1. Per username");
			System.out.println("2. Per ID");
			System.out.println("3. Per nome e cognome");
			int tipoRicerca = scanner.nextInt();
			scanner.nextLine();
			UtentiDao uDao = new UtentiDao(em);
			switch (tipoRicerca) {
				case 1 :
					System.out.println("Inserisci l'username dell'utente:");
					String username = scanner.nextLine();
					utenteDaAggiornare = uDao.findByUsername(username);
					if (utenteDaAggiornare != null) {
						System.out.println("Username attuale: " + utenteDaAggiornare.getUsername());
						System.out.println("Vuoi cambiare l'username? (S/N)");
						String risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo username:");
							String nuovoUsername = scanner.nextLine();
							utenteDaAggiornare.setUsername(nuovoUsername);
						}
						System.out.println("Nome attuale: " + utenteDaAggiornare.getNome());
						System.out.println("Vuoi cambiare il nome? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo nome:");
							String nuovoNome = scanner.nextLine();
							utenteDaAggiornare.setNome(nuovoNome);
						}
						System.out.println("Cognome attuale: " + utenteDaAggiornare.getCognome());
						System.out.println("Vuoi cambiare il cognome? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo cognome:");
							String nuovoCognome = scanner.nextLine();
							utenteDaAggiornare.setCognome(nuovoCognome);
						}
						System.out.println("Password attuale: " + utenteDaAggiornare.getPassword());
						System.out.println("Vuoi cambiare la password? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci la nuova password:");
							String nuovaPassword = scanner.nextLine();
							utenteDaAggiornare.setPassword(nuovaPassword);
						}
						System.out.println("Ruolo attuale: " + utenteDaAggiornare.getRuolo());
						System.out.println("Vuoi cambiare il ruolo? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo ruolo (1 per ADMIN, 2 per USER): ");
							int nuovoRuolo = scanner.nextInt();
							scanner.nextLine();
							if (nuovoRuolo == 1) {
								utenteDaAggiornare.setRuolo(Ruolo.ADMIN);
							} else if (nuovoRuolo == 2) {
								utenteDaAggiornare.setRuolo(Ruolo.USER);
							} else {
								System.out.println("Ruolo non valido. Nessuna modifica apportata.");
							}
						}
						em.getTransaction().begin();
						uDao.update(utenteDaAggiornare);
						em.getTransaction().commit();
					}
					break;
				case 2 :
					System.out.println("Inserisci l'ID dell'utente:");
					Long id = scanner.nextLong();
					scanner.nextLine();
					utenteDaAggiornare = uDao.findById(id);
					if (utenteDaAggiornare != null) {
						System.out.println("Username attuale: " + utenteDaAggiornare.getUsername());
						System.out.println("Vuoi cambiare l'username? (S/N)");
						String risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo username:");
							String nuovoUsername = scanner.nextLine();
							utenteDaAggiornare.setUsername(nuovoUsername);
						}
						System.out.println("Nome attuale: " + utenteDaAggiornare.getNome());
						System.out.println("Vuoi cambiare il nome? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo nome:");
							String nuovoNome = scanner.nextLine();
							utenteDaAggiornare.setNome(nuovoNome);
						}
						System.out.println("Cognome attuale: " + utenteDaAggiornare.getCognome());
						System.out.println("Vuoi cambiare il cognome? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo cognome:");
							String nuovoCognome = scanner.nextLine();
							utenteDaAggiornare.setCognome(nuovoCognome);
						}
						System.out.println("Password attuale: " + utenteDaAggiornare.getPassword());
						System.out.println("Vuoi cambiare la password? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci la nuova password:");
							String nuovaPassword = scanner.nextLine();
							utenteDaAggiornare.setPassword(nuovaPassword);
						}
						System.out.println("Ruolo attuale: " + utenteDaAggiornare.getRuolo());
						System.out.println("Vuoi cambiare il ruolo? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo ruolo (1 per ADMIN, 2 per USER): ");
							int nuovoRuolo = scanner.nextInt();
							scanner.nextLine();
							if (nuovoRuolo == 1) {
								utenteDaAggiornare.setRuolo(Ruolo.ADMIN);
							} else if (nuovoRuolo == 2) {
								utenteDaAggiornare.setRuolo(Ruolo.USER);
							} else {
								System.out.println("Ruolo non valido. Nessuna modifica apportata.");
							}
						}
						em.getTransaction().begin();
						uDao.update(utenteDaAggiornare);
						em.getTransaction().commit();
					}
					break;
				case 3 :
					System.out.println("Inserisci il nome dell'utente:");
					String nome = scanner.nextLine();
					System.out.println("Inserisci il cognome dell'utente:");
					String cognome = scanner.nextLine();
					utenteDaAggiornare = uDao.findByNomeAndCognome(nome, cognome);
					if (utenteDaAggiornare != null) {
						System.out.println("Username attuale: " + utenteDaAggiornare.getUsername());
						System.out.println("Vuoi cambiare l'username? (S/N)");
						String risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo username:");
							String nuovoUsername = scanner.nextLine();
							utenteDaAggiornare.setUsername(nuovoUsername);
						}
						System.out.println("Nome attuale: " + utenteDaAggiornare.getNome());
						System.out.println("Vuoi cambiare il nome? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo nome:");
							String nuovoNome = scanner.nextLine();
							utenteDaAggiornare.setNome(nuovoNome);
						}
						System.out.println("Cognome attuale: " + utenteDaAggiornare.getCognome());
						System.out.println("Vuoi cambiare il cognome? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo cognome:");
							String nuovoCognome = scanner.nextLine();
							utenteDaAggiornare.setCognome(nuovoCognome);
						}
						System.out.println("Password attuale: " + utenteDaAggiornare.getPassword());
						System.out.println("Vuoi cambiare la password? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci la nuova password:");
							String nuovaPassword = scanner.nextLine();
							utenteDaAggiornare.setPassword(nuovaPassword);
						}
						System.out.println("Ruolo attuale: " + utenteDaAggiornare.getRuolo());
						System.out.println("Vuoi cambiare il ruolo? (S/N)");
						risposta = scanner.nextLine();
						if (risposta.equalsIgnoreCase("S")) {
							System.out.println("Inserisci il nuovo ruolo (1 per ADMIN, 2 per USER): ");
							int nuovoRuolo = scanner.nextInt();
							scanner.nextLine();
							if (nuovoRuolo == 1) {
								utenteDaAggiornare.setRuolo(Ruolo.ADMIN);
							} else if (nuovoRuolo == 2) {
								utenteDaAggiornare.setRuolo(Ruolo.USER);
							} else {
								System.out.println("Ruolo non valido. Nessuna modifica apportata.");
							}
						}
						em.getTransaction().begin();
						uDao.update(utenteDaAggiornare);
						em.getTransaction().commit();
						System.out.println("Utente aggiornato con successo!");
					}
					break;
				default :
					System.out.println("Scelta non valida.");
					break;
			}
		} catch (Exception e) {
			throw new RuntimeException("Errore nell'aggiornamento dell'utente", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
		return utenteDaAggiornare;
	}

	public static void elimina(Utente utente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		UtentiDao uDao = new UtentiDao(em);
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Inserisci l'ID dell'utente da eliminare:");
			Long id = scanner.nextLong();
			scanner.nextLine();
			utente = uDao.findById(id);
			if (utente != null) {
				em.getTransaction().begin();
				uDao.delete(utente);
				em.getTransaction().commit();
				System.out.println("Utente eliminato con successo!");
			} else {
				System.out.println("Utente non trovato.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Errore nell'eliminazione dell'utente", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}
	public static void visualizzaUtentiAttivi() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		UtentiDao uDao = new UtentiDao(em);
		try {
			uDao.findAllAttivi();
		} catch (Exception e) {
			throw new RuntimeException("Errore nella visualizzazione degli utenti", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}
	public static void visualizzaUtentiInattivi() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		UtentiDao uDao = new UtentiDao(em);
		try {
			uDao.findAllInattivi();
		} catch (Exception e) {
			throw new RuntimeException("Errore nella visualizzazione degli utenti", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}

	public static <Int> void controllaValiditaAbbonamento() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		UtentiDao uDao = new UtentiDao(em);
		TessereDao tDao = new TessereDao(em);

		System.out.println("Inserisci il numero di tessera");
		Long numeroTessera = scanner.nextLong();
		try {
			String abbonamento = tDao.findAbbonamentoByTessera(numeroTessera);
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
			throw new RuntimeException("Errore nella verifica dell'abbonamento", e);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}
}
