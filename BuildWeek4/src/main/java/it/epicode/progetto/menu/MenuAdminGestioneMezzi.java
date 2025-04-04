package it.epicode.progetto.menu;
import static it.epicode.progetto.utils.Input.scanner;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.entities.Autobus;
import it.epicode.progetto.entities.Mezzo;
import it.epicode.progetto.entities.Tram;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.entities.Tratta;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;

public class MenuAdminGestioneMezzi {
	public static void main(String[] args) throws InputMismatchException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		// DAO
		MezzoDAO mezzoDAO = new MezzoDAO(em);
		TrattaDAO trattaDAO = new TrattaDAO(em);
		final String reset = "\033[0m";
		final String rosso = "\033[0;91m";
		final String verde = "\033[0;92m";
		final String giallo = "\033[0;93m";

		int sceltaPresa;

		do {
			List<Mezzo> allMezzi = mezzoDAO.findAll();
			List<Mezzo> allMezziStatoIS = mezzoDAO.findMezzoByStatoEnum(Stato.IN_SERVIZIO);
			List<Mezzo> allMezziStatoIM = mezzoDAO.findMezzoByStatoEnum(Stato.IN_MANUTENZIONE);
			List<Mezzo> allMezziStatoF = mezzoDAO.findMezzoByStatoEnum(Stato.FERMO);
			List<Mezzo> allTramIS = allMezziStatoIS.stream().filter(mezzo -> mezzo instanceof Tram).toList();
			List<Mezzo> allTramIM = allMezziStatoIM.stream().filter(mezzo -> mezzo instanceof Tram).toList();
			List<Mezzo> allTramF = allMezziStatoF.stream().filter(mezzo -> mezzo instanceof Tram).toList();
			List<Mezzo> allAutobusIS = allMezziStatoIS.stream().filter(mezzo -> mezzo instanceof Autobus).toList();
			List<Mezzo> allAutobusIM = allMezziStatoIM.stream().filter(mezzo -> mezzo instanceof Autobus).toList();
			List<Mezzo> allAutobusF = allMezziStatoF.stream().filter(mezzo -> mezzo instanceof Autobus).toList();
			ClearTerminal.clearConsole();
			System.out.println("********************************");
			System.out.println("***** MENU GESTIONE MEZZI ******");
			System.out.println("********************************");
			System.out.println();
			if (allMezzi.isEmpty()) {
				System.out.println();
			} else {
				System.out.println("🚋 \t" + " " + verde + allTramIS.size() + " In servizio" + giallo + " "
						+ allTramIM.size() + " In manutenzione" + " " + rosso + allTramF.size() + " Fermi" + reset);
				System.out.println("🚌 \t" + " " + verde + allAutobusIS.size() + " In servizio" + giallo + " "
						+ allAutobusIM.size() + " In manutenzione" + " " + rosso + allAutobusF.size() + " Fermi"
						+ reset);
			}
			System.out.println();
			System.out.println("1. Crea un mezzo");
			System.out.println("2. Ripara un mezzo");
			System.out.println("3. Elimina un mezzo");
			System.out.println("4. Modifica un mezzo");
			System.out.println("0. Esci dal menu della gestione dei mezzi");

			sceltaPresa = scanner.nextInt();

			switch (sceltaPresa) {
				case 1 :
					ClearTerminal.clearConsole();
					System.out.println("***********************");
					System.out.println("***** CREA MEZZO ******");
					System.out.println("***********************");
					System.out.println();
					System.out.println("1. Autobus (Capienza massima 20 persone)");
					System.out.println("2. Tram (Capienza massima 50 persone)");
					Integer sceltaMezzo = scanner.nextInt();
					if (sceltaMezzo == 1) {
						Mezzo autobus = new Autobus(null, null, 0, 0, 20, Stato.FERMO, null, null);
						mezzoDAO.insert(autobus);
						System.out.println("Hai creato correttamente l'autobus linea " + autobus.getId() + "! 🚌");
					} else if (sceltaMezzo == 2) {
						Mezzo tram = new Tram(null, null, 0, 0, 20, Stato.FERMO, null, null);
						mezzoDAO.insert(tram);
						System.out.println();
						System.out.println("Hai creato correttamente il tram linea " + tram.getId() + "! 🚋");
					} else {
						System.out.println("Scelta non valida.");
					}
					break;
				case 2 :
					ClearTerminal.clearConsole();
					System.out.println("*************************");
					System.out.println("***** RIPARA MEZZO ******");
					System.out.println("*************************");
					System.out.println();
					try {
						List<Mezzo> tuttiIMezziiRiparare = mezzoDAO.findAll();
						int index = 1;
						for (Mezzo mezzoScelto : tuttiIMezziiRiparare) {

							System.out.println(index + ". " + mezzoScelto);
							index++;
						}
						int scelta = scanner.nextInt();
						scanner.nextLine();
						Mezzo mezzoScelto = tuttiIMezziiRiparare.get(scelta - 1);
						Mezzo mezzoRipara = mezzoDAO.findById(mezzoScelto.getId());

						mezzoRipara.setInizioAttività(LocalDateTime.now());
						mezzoRipara.setFineAttività(LocalDateTime.now().plusDays(7));
						mezzoRipara.setStatoEnum(Stato.IN_MANUTENZIONE);
						mezzoDAO.update(mezzoRipara);
						System.out.println("Hai mandato correttamente il mezzo in riparazione!");
					} catch (Exception e) {
						System.out.println("Hai scelto di riparare un mezzo che non esiste.");
					}
					break;
				case 3 :
					ClearTerminal.clearConsole();
					System.out.println("**************************");
					System.out.println("***** ELIMINA MEZZO ******");
					System.out.println("**************************");
					System.out.println();
					System.out.println("Quale mezzo vuoi eliminare?");
					try {
						List<Mezzo> tuttiIMezzi = mezzoDAO.findAll();
						int index = 1;
						for (Mezzo mezzoScelto : tuttiIMezzi) {

							System.out.println(index + ". " + mezzoScelto);
							index++;
						}
						int scelta = scanner.nextInt();
						scanner.nextLine();
						Mezzo mezzoScelto = tuttiIMezzi.get(scelta - 1);
						Mezzo mezzoElimina = mezzoDAO.findById(mezzoScelto.getId());
						mezzoDAO.delete(mezzoElimina.getId());
						System.out.println("Hai eliminato correttamente il mezzo!");
					} catch (Exception e) {
						System.out.println("Errore con l'eliminazione del mezzo.");
					}
					break;
				case 4 :
					ClearTerminal.clearConsole();
					System.out.println("***************************");
					System.out.println("***** MODIFICA MEZZO ******");
					System.out.println("***************************");
					System.out.println();
					System.out.println("Quale mezzo vuoi modificare?");
					try {
						List<Mezzo> tuttiIMezzi = mezzoDAO.findAll();
						int index = 1;
						for (Mezzo mezzoScelto : tuttiIMezzi) {

							System.out.println(index + ". " + mezzoScelto);
							index++;
						}
						int scelta = scanner.nextInt();
						scanner.nextLine();
						Mezzo mezzoScelto = tuttiIMezzi.get(scelta - 1);
						System.out.println("Cosa vuoi modificare?");
						System.out.println("1. Stato");
						System.out.println("2. Tratta");
						System.out.println("3. Capienza");
						System.out.println("4. Persone a bordo");
						Integer sceltaModificaMezzo = scanner.nextInt();
						if (sceltaModificaMezzo == 1) {
							System.out.println("Inserisci il nuovo stato:");
							System.out.println("1. Fermo");
							System.out.println("2. In servizio");
							System.out.println("3. In manutenzione");
							Integer sceltaStato = scanner.nextInt();
							if (sceltaStato == 1) {
								mezzoScelto.setStatoEnum(Stato.FERMO);
								mezzoDAO.update(mezzoScelto);
								System.out.println("Hai mandato correttamente il mezzo al deposito!");
							} else if (sceltaStato == 2) {
								if (mezzoScelto.getStatoEnum() == Stato.IN_MANUTENZIONE
										|| mezzoScelto.getStatoEnum() == Stato.FERMO) {
									System.out.println("Hai scelto di mettere il mezzo in servizio.");
									if (trattaDAO.findAll().isEmpty()) {
										System.out.println("Non ci sono tratte disponibili, devi crearne una.");
										System.out.println("Inserisci la zona di partenza della tratta:");
										String partenza = scanner.next();
										System.out.println("Inserisci il capolinea della tratta:");
										String capolinea = scanner.next();
										System.out.println("Inserisci il tempo previsto di percorrenza della tratta:");
										Integer tempoPrevistoPercorrenza = scanner.nextInt();
										System.out.println("Inserisci il tempo effettivo di percorrenza della tratta:");
										Integer tempoEffettivoPercorrenza = scanner.nextInt();
										Tratta tratta1 = new Tratta(null, partenza, capolinea,
												LocalDateTime.now().plusMinutes(tempoPrevistoPercorrenza),
												LocalDateTime.now().plusMinutes(tempoEffettivoPercorrenza), null, null);
										trattaDAO.insert(tratta1);
										System.out.println("Hai creato correttamente la tua tratta!");
										mezzoScelto.setStatoEnum(Stato.IN_SERVIZIO);
										mezzoScelto.setTratta(tratta1);
										mezzoDAO.update(mezzoScelto);
										System.out.println("Hai messo correttamente in servizio il mezzo!");
										System.out.println(mezzoScelto);
									} else {
										System.out.println("Devi scegliere una tratta da assegnare al mezzo.");
										List<Tratta> tutteLeTratte = trattaDAO.findAll();
										int index2 = 1;
										for (Tratta destinazione : tutteLeTratte) {

											System.out.println(index2 + ". " + destinazione);
											index2++;
										}
										int scelta2 = scanner.nextInt();
										scanner.nextLine();
										Tratta trattaScelta = tutteLeTratte.get(scelta2 - 1);
										mezzoScelto.setTratta(trattaScelta);
										mezzoScelto.setStatoEnum(Stato.IN_SERVIZIO);
										mezzoDAO.update(mezzoScelto);
										System.out.println("Hai mandato correttamente il mezzo in servizio!");
									}

								} else if (mezzoScelto.getStatoEnum() == Stato.IN_SERVIZIO) {
									System.out.println("Il mezzo è già in servizio! Vuoi cambiare la sua tratta?");
									System.out.println("1. Si");
									System.out.println("2. No");
									Integer sceltaCambioTratta = scanner.nextInt();
									if (sceltaCambioTratta == 1) {
										System.out.println("Quale tratta vuoi assegnare al mezzo?");
										List<Tratta> tutteLeTratte = trattaDAO.findAll();
										int index3 = 1;
										for (Tratta destinazione : tutteLeTratte) {

											System.out.println(index3 + ". " + destinazione);
											index3++;
										}
										int scelta3 = scanner.nextInt();
										scanner.nextLine();
										Tratta trattaScelta = tutteLeTratte.get(scelta3 - 1);
										mezzoScelto.setTratta(trattaScelta);
										mezzoDAO.update(mezzoScelto);
										System.out.println("Hai cambiato correttamente la tratta del mezzo!");
									} else if (sceltaCambioTratta == 2) {
										System.out.println("Hai scelto di non cambiare la tratta del mezzo.");
									} else {
										System.out.println("Scelta non valida.");
									}

								}
							} else if (sceltaStato == 3) {
								mezzoScelto.setStatoEnum(Stato.IN_MANUTENZIONE);
								mezzoDAO.update(mezzoScelto);
								System.out.println("Hai modificato correttamente lo stato del mezzo!");
							} else {
								System.out.println("Scelta non valida.");
							}
						} else if (sceltaModificaMezzo == 2) {
							System.out.println("Inserisci la nuova tratta:");
							List<Tratta> tutteLeTratteDaModificare = trattaDAO.findAll();
							tutteLeTratteDaModificare.forEach(System.out::println);
							Long idTrattaDaModificare = scanner.nextLong();
							Tratta trattaDaModificare = trattaDAO.findById(idTrattaDaModificare);
							mezzoScelto.setTratta(trattaDaModificare);
							mezzoScelto.setStatoEnum(Stato.IN_SERVIZIO);
							mezzoDAO.update(mezzoScelto);
							System.out.println("Hai modificato correttamente la tratta del mezzo!");
						} else if (sceltaModificaMezzo == 3) {
							System.out.println("Inserisci la nuova capienza:");
							Integer nuovaCapienza = scanner.nextInt();
							mezzoScelto.setCapienza(nuovaCapienza);
							mezzoDAO.update(mezzoScelto);
							System.out.println("Hai modificato correttamente la capienza del mezzo!");
						} else if (sceltaModificaMezzo == 4) {
							System.out.println("Inserisci il nuovo numero di persone a bordo:");
							Integer nuovaPersoneAboard = scanner.nextInt();
							mezzoScelto.setNumeroBigliettiVidimati(nuovaPersoneAboard);
							mezzoDAO.update(mezzoScelto);
							System.out.println("Hai modificato correttamente il numero di persone a bordo del mezzo!");
						} else {
							System.out.println("Scelta non valida.");
						}
					} catch (Exception e) {
						System.out.println("Errore inaspettato.");
					}
					break;
				case 0 :
					ClearTerminal.clearConsole();
					System.out.println("Arrivederci!");
					break;
				default :
					System.out.println("Scelta non valida.");
			}
		} while (sceltaPresa != 0);
		em.close();
		emf.close();
	}
}
