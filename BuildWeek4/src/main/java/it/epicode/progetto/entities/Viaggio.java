package it.epicode.progetto.entities;

import it.epicode.progetto.dao.*;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import static it.epicode.progetto.utils.Input.scanner;

public class Viaggio {

	public static void selezionaViaggio(Long myUser) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("epicode");
			em = emf.createEntityManager();
			TrattaDAO trattaDAO = new TrattaDAO(em);
			System.out.println("***************************");
			System.out.println("***** MONITOR VIAGGI ******");
			System.out.println("***************************");
			System.out.println();
			System.out.println("Elenco delle tratte disponibili:");
			System.out.println();
			List<Tratta> tutteLeTratte = trattaDAO.findAll();
			int index = 1;
			for (Tratta destinazione : tutteLeTratte) {

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
			Tratta trattaScelta = tutteLeTratte.get(scelta - 1);

			MezzoDAO mezzoDAO = new MezzoDAO(em);
			System.out.println();
			System.out.println("Elenco dei mezzi disponibili:");
			System.out.println();
			// stampo tutti i mezzi disponibili in base alla tratta scelta
			List<Mezzo> mezziDisponibili = mezzoDAO.findMezzoByTratta(trattaScelta);
			if (mezziDisponibili.isEmpty()) {
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
			int indexMezzi = 1;
			for (Mezzo mezzo : mezziDisponibili) {
				System.out.println(indexMezzi + ". " + mezzo);
				indexMezzi++;
			}
			System.out.println();
			System.out.println("Seleziona il mezzo desiderato:");
			System.out.println();
			System.out.print("Scelta: ");
			int sceltaMezzo = scanner.nextInt();
			scanner.nextLine();
			Mezzo mezzoScelto = mezziDisponibili.get(sceltaMezzo - 1);
			System.out.println("Hai scelto il mezzo: " + mezzoScelto);
			System.out.println();
			try {
				if (myUser != null) {
					// controllo se l'utente ha un abbonamento valido
					UtentiDao uDao = new UtentiDao(em);
					Utente utente = uDao.findById(myUser);

					TessereDao tDao = new TessereDao(em);
					Tessera tesseraExist = tDao.findByUtente(utente);
					boolean ebAbbonamento = false;

					if (tesseraExist != null) {
						ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);
						Abbonamento abbonamento = ebDao.findAbbonamentoByTessera(tesseraExist);
						if (abbonamento != null) {
							System.out.println("Il tuo abbonamento è valido. Puoi salire sul mezzo.");
							System.out.println("BUON VIAGGIO!");
							System.out.println();
							ebAbbonamento = true;
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}

					if (!ebAbbonamento) {
						ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);
						List<ElementoBiglietteria> biglietti = ebDao.findBigliettiByUtente(utente.getIdUtente());

						if (biglietti.isEmpty()) {
							System.out.println(
									"Non hai abbonamenti o biglietti disponibili. Acquista un abbonamento o un biglietto prima di viaggiare.");
							try {
								Thread.sleep(2000); // tempo per leggere il messaggio
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							ClearTerminal.clearConsole(); // se vuoi riportare al menu
							return;
						}

						int indexBiglietti = 1;
						for (ElementoBiglietteria biglietto : biglietti) {
							System.out.println(indexBiglietti + ". " + biglietto);
							indexBiglietti++;
						}

						System.out.println();
						System.out.println("Seleziona il biglietto desiderato (1-" + biglietti.size() + "):");
						int sceltaBiglietto = scanner.nextInt();
						scanner.nextLine();

						if (sceltaBiglietto < 1 || sceltaBiglietto > biglietti.size()) {
							System.out.println("Scelta non valida. Riprova.");
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							ClearTerminal.clearConsole();
							return;
						}

						Biglietto bigliettoScelto = (Biglietto) biglietti.get(sceltaBiglietto - 1);
						System.out.println("Hai scelto il biglietto: " + bigliettoScelto);
						System.out.println();
						System.out.println("Salire sul mezzo? (S/N)");
						String conferma = scanner.nextLine();
						if (conferma.equalsIgnoreCase("S")) {
							System.out.println("BUON VIAGGIO!");
							System.out.println();
							ebDao.updateVidimato(bigliettoScelto.getIdBiglietto(), mezzoScelto);
							mezzoScelto.setNumeroBigliettiVidimati(mezzoScelto.getNumeroBigliettiVidimati() + 1);
							mezzoDAO.updateBigliettiVidimati(mezzoScelto.getId(),
									mezzoScelto.getNumeroBigliettiVidimati());
						}
					}
				} else {
					System.out.println("Inserisci l'identificativo del biglietto per viaggiare");
					System.out.println();
					Long idBiglietto = scanner.nextLong();
					scanner.nextLine();
					// controllo se il biglietto è stato già vidimato
					ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);
					boolean bigliettoVidimato = ebDao.isVidimato(idBiglietto);
					if (bigliettoVidimato) {
						System.out.println(
								"Biglietto già vidimato. Non puoi viaggiare. Acquista uno nuovo o un abbonamento!");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ClearTerminal.clearConsole();
						return;
					}
					ElementoBiglietteria bigliettoGuest = ebDao.findById(idBiglietto);
					if (bigliettoGuest != null) {
						System.out.println("Salire sul mezzo? (S/N)");
						String conferma = scanner.nextLine();
						if (conferma.equalsIgnoreCase("S")) {
							System.out.println("BUON VIAGGIO!");
							System.out.println();
							ebDao.updateVidimato(idBiglietto, mezzoScelto);
							mezzoScelto.setNumeroBigliettiVidimati(mezzoScelto.getNumeroBigliettiVidimati() + 1);
							mezzoDAO.updateBigliettiVidimati(mezzoScelto.getId(),
									mezzoScelto.getNumeroBigliettiVidimati());
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}

			} catch (Exception e) {
				throw new RuntimeException("Errore nella visualizzazione dell'abbonamento", e);
			} finally {
				if (em != null)
					em.close();
				if (emf != null)
					emf.close();
			}
		} catch (Exception e) {
			System.out.println("Errore inaspettato.");
		}
	}

}
