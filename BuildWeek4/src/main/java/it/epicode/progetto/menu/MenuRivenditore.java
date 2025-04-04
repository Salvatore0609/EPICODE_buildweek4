package it.epicode.progetto.menu;

import it.epicode.progetto.dao.RivenditoreDAO;
import it.epicode.progetto.entities.Rivenditore;
import it.epicode.progetto.entities.DistributoriAutomatici;
import it.epicode.progetto.enums.StatoDistributori;
import it.epicode.progetto.entities.RivenditoriAutorizzati;
import it.epicode.progetto.exceptions.RivenditoreException;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

import static it.epicode.progetto.utils.Input.scanner;

public class MenuRivenditore {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();

		RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);

		int scelta;
		do {
			System.out.println("*********************************");
			System.out.println("***** GESTIONE RIVENDITORI ******");
			System.out.println("*********************************");
			System.out.println();
			System.out.println("1. Inserisci un nuovo rivenditore");
			System.out.println("2. Elimina un rivenditore");
			System.out.println("3. Modifica un rivenditore");
			System.out.println("4. Visualizza tutti i rivenditori");
			System.out
					.println("5. Visualizza i biglietti e abbonamenti emessi da un rivenditore in un periodo di tempo");
			System.out.println("0. Esci");
			System.out.println();
			System.out.print("Scelta: ");

			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
				case 1 :
					ClearTerminal.clearConsole();
					System.out.println("Inserisci il nome del rivenditore:");
					String nomeRivenditore = scanner.nextLine();
					System.out.println(
							"Inserisci il tipo di rivenditore (1 per rivenditori autorizzati, 2 per distributori automatici):");
					int tipoRivenditore = scanner.nextInt();
					scanner.nextLine();

					em.getTransaction().begin();
					if (tipoRivenditore == 1) {
						RivenditoriAutorizzati r = RivenditoriAutorizzati.builder().nome(nomeRivenditore).build();
						rivenditoreDAO.insert(r);
						System.out.println("Rivenditore inserito con successo!");
					} else if (tipoRivenditore == 2) {
						System.out.println(
								"Inserisci lo stato del distributore automatico (1 per attivo, 2 per fuori servizio):");
						int statoDistributore = scanner.nextInt();
						scanner.nextLine();
						StatoDistributori stato = statoDistributore == 1
								? StatoDistributori.ATTIVO
								: StatoDistributori.FUORI_SERVIZIO;
						DistributoriAutomatici d = DistributoriAutomatici.builder().nome(nomeRivenditore).stato(stato)
								.build();
						rivenditoreDAO.insert(d);
						System.out.println("Distributore automatico inserito con successo!");
					}
					em.getTransaction().commit();
					break;
				case 2 :
					ClearTerminal.clearConsole();
					System.out.println("Inserisci l'ID del rivenditore da eliminare:");
					Long idRivenditore = scanner.nextLong();
					scanner.nextLine();

					em.getTransaction().begin();
					rivenditoreDAO.delete(idRivenditore);
					em.getTransaction().commit();

					System.out.println("Il rivenditore è stato eliminato con successo");
					break;
				case 3 :
					ClearTerminal.clearConsole();
					System.out.println("Inserisci l'ID del rivenditore da modificare:");
					Long idRivenditore2 = scanner.nextLong();
					scanner.nextLine();
					Rivenditore rivenditore = rivenditoreDAO.findById(idRivenditore2);
					if (rivenditore != null) {
						em.getTransaction().begin();
						boolean modificato = false;

						if (rivenditore instanceof RivenditoriAutorizzati) {
							System.out.println("Vuoi cambiare il nome del rivenditore? (S/N)");
							String risposta = scanner.nextLine();
							if (risposta.equalsIgnoreCase("S")) {
								System.out.println("Inserisci il nuovo nome del rivenditore:");
								String nuovoNome = scanner.nextLine();
								rivenditore.setNome(nuovoNome);
								modificato = true;
							}

						} else if (rivenditore instanceof DistributoriAutomatici) {
							System.out.println("Vuoi cambiare il nome del distributore? (S/N)");
							String risposta = scanner.nextLine();
							if (risposta.equalsIgnoreCase("S")) {
								System.out.println("Inserisci il nuovo nome del distributore:");
								String nuovoNome = scanner.nextLine();
								rivenditore.setNome(nuovoNome);
								modificato = true;
							}
							System.out.println("Vuoi cambiare lo stato del distributore? (S/N)");
							String risposta2 = scanner.nextLine();
							if (risposta2.equalsIgnoreCase("S")) {
								System.out.println(
										"Inserisci il nuovo stato del distributore (1 per attivo, 2 per fuori servizio:");
								int sceltaStato;
								try {
									sceltaStato = Integer.parseInt(scanner.nextLine());
									StatoDistributori nuovoStato = null;

									if (sceltaStato == 1) {
										nuovoStato = StatoDistributori.ATTIVO;
									} else if (sceltaStato == 2) {
										nuovoStato = StatoDistributori.FUORI_SERVIZIO;
									} else {
										System.out.println("Scelta non valida. Lo stato non è stato modificato.");
									}
									((DistributoriAutomatici) rivenditore).setStato(nuovoStato);
									modificato = true;
								} catch (NumberFormatException e) {
									System.out.println("Errore : devi inserire un numero valido 1 o 2.");
									return;
								}
							}
						}
						if (modificato) {
							rivenditoreDAO.update(rivenditore);
							em.getTransaction().commit();
							System.out.println("Rivenditore aggiornato con successo.");
						} else {
							em.getTransaction().rollback();
							System.out.println("Nessuna modifica effettuata.");
						}

					} else {
						System.out.println("Rivenditore non trovato con l'ID specificato");
					}
					break;
				case 4 :
					ClearTerminal.clearConsole();
					System.out.println("Elenco dei rivenditori:");
					for (Rivenditore r : rivenditoreDAO.findAll()) {

						if (r instanceof RivenditoriAutorizzati) {
							System.out.println("Rivenditore Autorizzato" + "{ Nome: " + r.getNome() + "}");
						} else if (r instanceof DistributoriAutomatici) {
							System.out.println("Distributore Automatico" + "{ Nome: " + r.getNome()
									+ " StatoDistributori: " + ((DistributoriAutomatici) r).getStato() + "}");
						}
					}
					try {
						System.out.println();
						System.out.print("Premi invio per continuare...");
						System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					}
					ClearTerminal.clearConsole();
					break;
				case 5 :
					ClearTerminal.clearConsole();
					System.out.println("Inserisci il nome del rivenditore:");
					String nomeRivenditore2 = scanner.nextLine();
					System.out.println("Inserisci la data di inizio:");
					LocalDate dataInizio = LocalDate.parse(scanner.nextLine());
					System.out.println("Inserisci la data di fine:");
					LocalDate dataFine = LocalDate.parse(scanner.nextLine());
					rivenditoreDAO.ottieniBigliettiAbbonamentiEmessi(nomeRivenditore2, dataInizio, dataFine);
					break;
				case 0 :
					ClearTerminal.clearConsole();
					System.out.println("Programma Terminato.");
					break;
			}
		} while (scelta != 0);

		em.close();
		emf.close();
	}

}
