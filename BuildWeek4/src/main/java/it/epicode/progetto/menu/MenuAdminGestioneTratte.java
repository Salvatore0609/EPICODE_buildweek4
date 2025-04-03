package it.epicode.progetto.menu;

import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.entities.Mezzo;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.entities.Tratta;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import static it.epicode.progetto.utils.Input.scanner;

public class MenuAdminGestioneTratte {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		// DAO
		TrattaDAO trattaDAO = new TrattaDAO(em);
		MezzoDAO mezzoDAO = new MezzoDAO(em);

		int sceltaPresa;

		do {
			ClearTerminal.clearConsole();
			System.out.println("*********************************");
			System.out.println("***** MENU GESTIONE TRATTE ******");
			System.out.println("*********************************");
			System.out.println();
			System.out.println("1. Crea una tratta");
			System.out.println("2. Modifica una tratta");
			System.out.println("3. Assegna una tratta a un mezzo");
			System.out.println("4. Elimina una tratta");
			System.out.println("0. Esci dal menu della gestione delle tratte");

			sceltaPresa = scanner.nextInt();

			switch (sceltaPresa) {
				case 1 :
					ClearTerminal.clearConsole();
					System.out.println("************************");
					System.out.println("***** CREA TRATTA ******");
					System.out.println("************************");
					System.out.println();
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
					System.out.println();
					System.out.println("Hai creato correttamente la tua tratta!");
					break;
				case 2 :
					ClearTerminal.clearConsole();
					System.out.println("****************************");
					System.out.println("***** MODIFICA TRATTA ******");
					System.out.println("***************************");
					System.out.println();
					System.out.println("Quale tratta vuoi modificare?");
					try {
						List<Tratta> tutteLeTratte = trattaDAO.findAll();
						int index = 1;
						for (Tratta trattaScelta : tutteLeTratte) {

							System.out.println(index + ". " + trattaScelta);
							index++;
						}
						int scelta = scanner.nextInt();
						scanner.nextLine();
						Tratta trattaScelta = tutteLeTratte.get(scelta - 1);
						Tratta trattaModifica = trattaDAO.findById(trattaScelta.getId());
						System.out.println("Cosa vuoi modificare?");
						System.out.println("1. Partenza");
						System.out.println("2. Capolinea");
						Integer sceltaModifica = scanner.nextInt();
						if (sceltaModifica == 1) {
							System.out.println("Inserisci la nuova partenza:");
							String nuovaPartenza = scanner.next();
							trattaModifica.setZonaDiPartenza(nuovaPartenza);
							trattaDAO.update(trattaModifica);
							System.out.println("Hai modificato correttamente la partenza della tratta!");
						} else if (sceltaModifica == 2) {
							System.out.println("Inserisci il nuova capolinea:");
							String nuovaCapolinea = scanner.next();
							trattaModifica.setCapolinea(nuovaCapolinea);
							trattaDAO.update(trattaModifica);
							System.out.println("Hai modificato correttamente il capolinea della tratta!");
						} else {
							System.out.println("Scelta non valida.");
						}
					} catch (Exception e) {
						System.out.println("Errore inaspettato.");
					}
					break;
				case 3 :
					ClearTerminal.clearConsole();
					System.out.println("***************************");
					System.out.println("***** ASSEGNA TRATTA ******");
					System.out.println("**************************");
					System.out.println();
					System.out.println("Quale tratta vuoi assegnare ad un mezzo?");
					try {
						List<Tratta> tutteLeTratte = trattaDAO.findAll();
						int index = 1;
						for (Tratta trattaScelta : tutteLeTratte) {

							System.out.println(index + ". " + trattaScelta);
							index++;
						}
						int scelta = scanner.nextInt();
						scanner.nextLine();
						Tratta trattaScelta = tutteLeTratte.get(scelta - 1);
						ClearTerminal.clearConsole();
						System.out.println("***************************");
						System.out.println("***** ASSEGNA TRATTA ******");
						System.out.println("**************************");
						System.out.println();
						System.out.println("A quale mezzo vuoi assegnare la tratta?");
						List<Mezzo> tuttiIMezzi = mezzoDAO.findAll();
						int index2 = 1;
						for (Mezzo mezzoScelto : tuttiIMezzi) {

							System.out.println(index2 + ". " + mezzoScelto);
							index2++;
						}
						int scelta2 = scanner.nextInt();
						scanner.nextLine();
						Mezzo mezzoScelto = tuttiIMezzi.get(scelta2 - 1);
						mezzoScelto.setTratta(trattaScelta);
						mezzoScelto.setStatoEnum(Stato.IN_SERVIZIO);
						mezzoDAO.update(mezzoScelto);
						System.out.println("Hai assegnato correttamente la tratta al mezzo! Buon viaggio!");
					} catch (Exception e) {
						System.out.println("Errore inaspettato.");
					}
					break;
				case 4 :
					ClearTerminal.clearConsole();
					System.out.println("***************************");
					System.out.println("***** ELIMINA TRATTA ******");
					System.out.println("**************************");
					System.out.println();
					System.out.println("Quale tratta vuoi eliminare?");
					try {
						List<Tratta> tutteLeTratte = trattaDAO.findAll();
						int index = 1;
						for (Tratta trattaScelta : tutteLeTratte) {

							System.out.println(index + ". " + trattaScelta);
							index++;
						}
						int scelta = scanner.nextInt();
						scanner.nextLine();
						Tratta trattaScelta = tutteLeTratte.get(scelta - 1);
						for (Mezzo mezzoRicerca : mezzoDAO.findAll()) {
							if (mezzoRicerca.getTratta() != null
									&& mezzoRicerca.getTratta().getId() == trattaScelta.getId()) {
								System.out.println(
										"Ci sono ancora dei mezzi che stanno percorrendo questa tratta. Vuoi farli prima rientrare?");
								System.out.println(
										"S/N (Attenzione: la tratta verrà eliminata definitivamente se scegli S)");
								String sceltaRientro = scanner.next();
								if (sceltaRientro.equalsIgnoreCase("S")) {
									for (Mezzo mezzo : mezzoDAO.findAll()) {
										if (mezzo.getTratta() != null
												&& mezzo.getTratta().getId() == trattaScelta.getId()) {
											mezzo.setTratta(null);
											mezzo.setStatoEnum(Stato.FERMO);
											mezzoDAO.update(mezzo);
											System.out.println("Tutti i mezzi associati a quella tratta sono stati rimandati al deposito!");
										}
									}

									trattaDAO.delete(trattaScelta.getId());
									System.out.println("Hai eliminato con successo la tratta!");
								} else if (sceltaRientro.equalsIgnoreCase("N")) {
									System.out.println(
											"Hai scelto di non far rientrare i mezzi, quindi la tratta resta attiva.");
								} else {
									System.out.println("Scelta non valida.");
								}
							} else {
								System.out.println("Ciao");
								trattaDAO.delete(trattaScelta.getId());
								System.out.println("Hai eliminato correttamente la tratta!");
							}
						}
					} catch (Exception e) {
						System.out.println("Errore durante l'eliminazione della tratta: " + e.getMessage());
						e.printStackTrace(); // Stampiamo l'errore per avere maggiori dettagli
					}
					break;
				case 0 :
					System.out.println("Arrivederci!");
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
