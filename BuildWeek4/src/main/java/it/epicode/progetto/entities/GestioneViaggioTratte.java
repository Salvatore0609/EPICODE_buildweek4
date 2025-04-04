package it.epicode.progetto.entities;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.dao.ViaggioTrattaDao;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.menu.MenuAdmin;
import it.epicode.progetto.menu.MenuAdminGestioneMezzi;
import it.epicode.progetto.menu.MenuAdminGestioneTratte;
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
		System.out.println("*************************");
		System.out.println("*** SELEZIONA TRATTA ****");
		System.out.println("*************************");
		System.out.println();
		List<Tratta> tutteLeTratte = trattaDAO.findAll();
		int index = 1;
		for (Tratta trattaScelta : tutteLeTratte) {

			System.out.println(index + ". " + trattaScelta);
			index++;
		}
		System.out.println();
		System.out.println("Seleziona la tratta: ");
		int scelta = scanner.nextInt();
		scanner.nextLine();
		Tratta trattaScelta = tutteLeTratte.get(scelta - 1);
		Long numeroTratta = trattaScelta.getId();
		Tratta numeroTrattaScanner = trattaDAO.findById(numeroTratta);

		List<Mezzo> mezziPresenti = mezzoDAO.findMezzoByTratta(numeroTrattaScanner);
		if (mezziPresenti.isEmpty()) {
			System.out.println("Non ci sono mezzi presenti associati a questa tratta");
			System.out.println("Vuoi assegnare un mezzo a questa tratta?(S/N)");
			String sceltaAsssegna = scanner.nextLine();
			if (sceltaAsssegna.equalsIgnoreCase("S")) {
				MenuAdminGestioneTratte.main(null);
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
		} else {
			System.out.println("Inserisci il tempo previsto di percorrenza della tratta:");
			int tempoPrevisto = scanner.nextInt();
			scanner.nextLine();

			System.out.println("Inserisci il tempo effettivo di percorrenza della tratta:");
			int tempoEffettivo = scanner.nextInt();
			scanner.nextLine();

			LocalDateTime tempoPrevistoOr = LocalDateTime.now().plusMinutes(tempoPrevisto);
			LocalDateTime tempoEffettivoOr = LocalDateTime.now().plusMinutes(tempoEffettivo);

			Duration duration = Duration.between(tempoEffettivoOr, tempoPrevistoOr);
			long roundedSeconds = (long) Math.ceil(duration.toMillis() / 1000.0);
			long differenzaTempo = roundedSeconds / 60;

			int numeroViaggioTratte = viaggioTrattaDAO.ritornaUltimoViaggio();

			ViaggioTratte viaggioTratte = new ViaggioTratte(null, numeroTrattaScanner,  tempoPrevistoOr, tempoEffettivoOr,
					differenzaTempo, numeroViaggioTratte + 1);

			List<Mezzo> mezziPresenti2 = mezzoDAO.findMezzoByTratta(numeroTrattaScanner);
			int indexMezzi2 = 1;
			for (Mezzo mezzo2 : mezziPresenti2) {
				if (mezzo2.getStatoEnum() == Stato.IN_SERVIZIO) {
					System.out.println(indexMezzi2 + ". " + mezzo2);
					indexMezzi2++;
				}
			}
			System.out.println("Scegli il mezzo da far partire sulla tratta");
			int sceltaMezzo = scanner.nextInt();
			Long mezzoScelto = mezziPresenti.get(sceltaMezzo - 1).getId();

			viaggioTrattaDAO.insert(viaggioTratte);
			System.out.println("Viaggio inserito con successo");
			mezzoDAO.updateVolteTrattaPercorsa(mezzoScelto, numeroViaggioTratte + 1);


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