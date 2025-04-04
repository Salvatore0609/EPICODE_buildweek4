package it.epicode.progetto.menu;
import static it.epicode.progetto.utils.Input.scanner;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.dao.ViaggioTrattaDao;
import it.epicode.progetto.entities.Mezzo;
import it.epicode.progetto.entities.Tratta;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class MenuAdminVisualizzaTutto {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();

		// COLORI

		final String reset = "\u001B[0m";
		final String azzurro = "\u001B[36m";

		// DAO
		MezzoDAO mezzoDAO = new MezzoDAO(em);
		TrattaDAO trattaDAO = new TrattaDAO(em);

		int sceltaPresa;

		do {
			ClearTerminal.clearConsole();
			System.out.println("***************************");
			System.out.println("***** MONITOR REPORT ******");
			System.out.println("***************************");
			System.out.println();
			System.out.println("1. Visualizza tutti i mezzi");
			System.out.println("2. Visualizza tutte le tratte");
			System.out.println("3. Visualizza tutti i mezzi con dei posti liberi");
			System.out.println("4. Visualizza tutti i biglietti vidimati nel corso del tempo");
			System.out.println("5. Visualizza tempo medio di percorrenza di una tratta");
			System.out.println("6. Visualizza giri mezzi");
			System.out.println("0. Esci");
			System.out.println();
			System.out.print("Scelta: ");

			sceltaPresa = scanner.nextInt();
			switch (sceltaPresa) {
				case 1 :
					ClearTerminal.clearConsole();
					System.out.println("**************************");
					System.out.println("***** MONITOR MEZZI ******");
					System.out.println("**************************");
					System.out.println();
					List<Mezzo> mezziMonitor = mezzoDAO.findAll();
					for (Mezzo mezzoMonitor : mezziMonitor) {
						if (mezzoMonitor instanceof it.epicode.progetto.entities.Autobus) {
							System.out.println("🚌 " + mezzoMonitor);
						} else if (mezzoMonitor instanceof it.epicode.progetto.entities.Tram) {
							System.out.println("🚋 " + mezzoMonitor);
						}
					}
					try {
						System.out.println();
						System.out.print("Premi invio per continuare...");
						System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 2 :
					ClearTerminal.clearConsole();
					System.out.println("***************************");
					System.out.println("***** MONITOR VIAGGI ******");
					System.out.println("***************************");
					System.out.println();
					List<Tratta> tutteLeTratte = trattaDAO.findAll();
					for (Tratta trattaScelta : tutteLeTratte) {
						System.out.println("- " + trattaScelta);
					}
					try {
						System.out.println();
						System.out.print("Premi invio per continuare...");
						System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 3 :
					ClearTerminal.clearConsole();
					System.out.println("***********************************");
					System.out.println("***** MONITOR DISPONIBILITA' ******");
					System.out.println("***********************************");
					System.out.println();
					// stampa solo i mezzi con dei posti liberi
					List<Mezzo> mezziDisponibili = mezzoDAO.findAll();
					for (Mezzo mezzoMonitor : mezziDisponibili) {
						if ((mezzoMonitor.getCapienza() - mezzoMonitor.getNumeroBigliettiVidimati()) > 0
								&& mezzoMonitor.getStatoEnum() != Stato.FERMO
								&& mezzoMonitor.getStatoEnum() != Stato.IN_MANUTENZIONE) {
							if (mezzoMonitor instanceof it.epicode.progetto.entities.Autobus) {
								System.out.println("🚌 " + mezzoMonitor);
							} else if (mezzoMonitor instanceof it.epicode.progetto.entities.Tram) {
								System.out.println("🚋 " + mezzoMonitor);
							}
						}
					}
					try {
						System.out.println();
						System.out.print("Premi invio per continuare...");
						System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 4 :
					ClearTerminal.clearConsole();
					System.out.println(
							"Vuoi visualizzare i biglietti di un particolare mezzo o tutti i biglietti vidimati in un periodo di tempo?");
					System.out.println("1. Visualizza i biglietti di un particolare mezzo.");
					System.out.println("2. Visualizza tutti i biglietti vidimati in un periodo di tempo.");
					int sceltaPresa2 = scanner.nextInt();
					if (sceltaPresa2 == 1) {
						System.out.println("Inserisci l'id del mezzo:");
						long idMezzo = scanner.nextLong();
						mezzoDAO.ottieniBigliettiVidimatiPerUnMezzo(idMezzo);
					} else if (sceltaPresa2 == 2) {
						System.out.println("Inserisci la data di inizio:");
						LocalDate dataInizio = LocalDate.parse(scanner.next());
						System.out.println("Inserisci la data di fine:");
						LocalDate dataFine = LocalDate.parse(scanner.next());
						mezzoDAO.findAllBigliettiVidimati(dataInizio, dataFine);
					}
					break;
				case 5 :
					ClearTerminal.clearConsole();
					System.out.println("***************************************");
					System.out.println("***** MONITOR TEMPO MEDIO TRATTE ******");
					System.out.println("***************************************");
					System.out.println();
					List<Tratta> tutteLeTratte2 = trattaDAO.findAll();
					int index = 1;
					for (Tratta destinazione : tutteLeTratte2) {

						System.out.println(index + ". " + destinazione);
						index++;
					}
					System.out.println("0. Esci");
					System.out.println();
					System.out.print("Seleziona la tratta desiderata:");
					System.out.println();
					System.out.print("Scelta: ");
					int scelta = scanner.nextInt();
					scanner.nextLine();
					if (scelta == 0) {
						ClearTerminal.clearConsole();
						return;
					}
					Tratta trattaScelta = tutteLeTratte2.get(scelta - 1);
					System.out.println();
					System.out.println("Elenco dei mezzi disponibili:");
					System.out.println();
					List<Mezzo> mezziDisponibili2 = mezzoDAO.findMezzoByTratta(trattaScelta);
					if (mezziDisponibili2.isEmpty()) {
						System.out.println("Nessun mezzo disponibile per la tratta selezionata.");
						System.out.println("Attendi un mezzo disponibile o scegli un'altra tratta.");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ClearTerminal.clearConsole();
						return;
					}
					for (Mezzo mezzo : mezziDisponibili2) {
						if (mezzo instanceof it.epicode.progetto.entities.Autobus) {
							int numeroGiri = mezzo.getVolteTrattaPercorsa();
							ViaggioTrattaDao viaggioTrattaDao = new ViaggioTrattaDao(em);
							Long totaleTempo = viaggioTrattaDao.totaleTempoEffettivoViaggiByTratta(trattaScelta.getId());
							Long totaleTempoMedio = totaleTempo / numeroGiri;
							System.out.println("🚌 " + azzurro + "ATEB" + reset + " linea " + mezzo.getId() + " ha un tempo medio di percorrenza con " + (totaleTempoMedio / 60) + " minuti sulla tratta" );
						} else if (mezzo instanceof it.epicode.progetto.entities.Tram) {
							int numeroGiri = mezzo.getVolteTrattaPercorsa();
							ViaggioTrattaDao viaggioTrattaDao = new ViaggioTrattaDao(em);
							Long totaleTempo = viaggioTrattaDao.totaleTempoEffettivoViaggiByTratta(trattaScelta.getId());
							Long totaleTempoMedio = totaleTempo / numeroGiri;
							System.out.println("🚋 " + azzurro + "ATEB" + reset + " linea " + mezzo.getId() + " ha un tempo medio di percorrenza con " + (totaleTempoMedio / 60) + " minuti sulla tratta" );
						}
					}
					try {
						System.out.println();
						System.out.print("Premi invio per continuare...");
						System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 6 :
					ClearTerminal.clearConsole();
					System.out.println("*******************************");
					System.out.println("***** MONITOR GIRI MEZZI ******");
					System.out.println("*******************************");
					System.out.println();
					List<Mezzo> mezziMonitor2 = mezzoDAO.findAll();
					for (Mezzo mezzoMonitor2 : mezziMonitor2) {
						if (mezzoMonitor2 instanceof it.epicode.progetto.entities.Autobus) {
							System.out.println("🚌 " + azzurro + "ATEB" + reset + " linea " + mezzoMonitor2.getId()
									+ " ha eseguito " + mezzoMonitor2.getVolteTrattaPercorsa() + " viaggi sulla tratta "
									+ mezzoMonitor2.getTratta().getZonaDiPartenza() + " -------> "
									+ mezzoMonitor2.getTratta().getCapolinea());
						} else if (mezzoMonitor2 instanceof it.epicode.progetto.entities.Tram) {
							System.out.println("🚋 " + azzurro + "ATEB" + reset + " linea " + mezzoMonitor2.getId()
									+ " ha eseguito " + mezzoMonitor2.getVolteTrattaPercorsa() + " viaggi sulla tratta "
									+ mezzoMonitor2.getTratta().getZonaDiPartenza() + " -------> "
									+ mezzoMonitor2.getTratta().getCapolinea());
						}
					}
					try {
						System.out.println();
						System.out.print("Premi invio per continuare...");
						System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 0 :
					ClearTerminal.clearConsole();
					System.out.println("************************");
					System.out.println("***** ARRIVEDERCI ******");
					System.out.println("************************");
					System.out.println();
					break;

				default :
					System.out.println("Scelta non valida.");
					break;

			}
		} while (sceltaPresa != 0);

		em.close();
		emf.close();

	}

}
