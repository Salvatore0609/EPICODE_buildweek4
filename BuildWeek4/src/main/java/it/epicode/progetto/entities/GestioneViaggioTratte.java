package it.epicode.progetto.entities;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.dao.ViaggioTrattaDao;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.menu.MenuAdmin;
import it.epicode.progetto.menu.MenuAdminGestioneMezzi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static it.epicode.progetto.utils.Input.scanner;

public class GestioneViaggioTratte {
	public static void eseguiViaggioTratta() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		ViaggioTrattaDao viaggioTrattaDAO = new ViaggioTrattaDao(em);
		TrattaDAO trattaDAO = new TrattaDAO(em);
		MezzoDAO mezzoDAO = new MezzoDAO(em);

		System.out.println("Inserisci il numero di tratta da far partire.");
		Long numeroTratta = scanner.nextLong();

		System.out.println("Inserisci il tempo previsto di percorrenza della tratta:");
		int tempoPrevisto = scanner.nextInt();

		System.out.println("Inserisci il tempo effettivo di percorrenza della tratta:");
		int tempoEffettivo = scanner.nextInt();

		Tratta numeroTrattaScanner = trattaDAO.findById(numeroTratta);

		LocalDateTime tempoPrevistoOr = LocalDateTime.now().plusMinutes(tempoPrevisto);
		LocalDateTime tempoEffettivoOr = LocalDateTime.now().plusMinutes(tempoEffettivo);

		Long differenzaTempo = (Duration.between(tempoPrevistoOr, tempoEffettivoOr)).toMinutes();

		int numeroViaggioTratte = viaggioTrattaDAO.ritornaUltimoViaggio();

		ViaggioTratte viaggioTratte = new ViaggioTratte(null, numeroTrattaScanner, tempoPrevistoOr, tempoEffettivoOr,
				differenzaTempo, numeroViaggioTratte + 1);

		viaggioTrattaDAO.insert(viaggioTratte);
		System.out.println("Viaggio inserito con successo");


		List<Mezzo> mezziPresenti = mezzoDAO.findMezzoByTratta(numeroTrattaScanner);
		if (mezziPresenti.isEmpty()) {
			System.out.println("Non ci sono mezzi presenti associati a questa tratta");
			System.out.println("Vuoi assegnare un mezzo a questa tratta?(S/N)");
			String scelta = scanner.nextLine();
			if (scelta.equalsIgnoreCase("S")) {
				MenuAdminGestioneMezzi.main(null);
			} else {
				try {
					System.out.println();
					System.out.print("Premi invio per tornare al menu principale");
					System.in.read();
					MenuAdmin.menuAdmin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		int indexMezzi = 1;
		for (Mezzo mezzo : mezziPresenti) {
			if (mezzo.getStatoEnum() == Stato.IN_SERVIZIO) {
			System.out.println(indexMezzi + ". " + mezzo);
			indexMezzi++;
		}
			System.out.println("Scegli il mezzo da far partire sulla tratta");
			int sceltaMezzo = scanner.nextInt();
			Long mezzoScelto = mezziPresenti.get(sceltaMezzo - 1).getId();
			mezzoDAO.updateVolteTrattaPercorsa(mezzoScelto, numeroViaggioTratte);



			numeroTrattaScanner.setTempoPrevistoDiPercorrenza(tempoPrevistoOr);
			numeroTrattaScanner.setTempoEffettivoDiPercorrenza(tempoEffettivoOr);
			numeroTrattaScanner.setDifferenzaTempo(differenzaTempo);

			trattaDAO.update(numeroTrattaScanner);

			try {
				System.out.println("Aggiornato con successo!");
				System.out.println();
				System.out.print("Premi invio per continuare...");
				System.in.read();
			} catch (Exception e) {
				e.printStackTrace();
			}















			}
		}


	}

