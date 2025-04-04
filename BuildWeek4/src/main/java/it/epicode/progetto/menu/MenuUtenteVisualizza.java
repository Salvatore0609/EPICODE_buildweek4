package it.epicode.progetto.menu;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.entities.Mezzo;
import it.epicode.progetto.entities.Tratta;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

import static it.epicode.progetto.utils.Input.scanner;

public class MenuUtenteVisualizza {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
	static EntityManager em = emf.createEntityManager();
	static TrattaDAO trattaDAO = new TrattaDAO(em);
	static MezzoDAO mezzoDAO = new MezzoDAO(em);

	public static void visualizzaTratte() {
		System.out.println("***************************");
		System.out.println("***** MONITOR VIAGGI ******");
		System.out.println("***************************");
		System.out.println();
		List<Tratta> tutteLeTratte = trattaDAO.findAll();
		int index = 1;
		for (Tratta trattaScelta : tutteLeTratte) {
			System.out.println(index + ". " + trattaScelta);
			index++;
		}
		try {
			System.out.println();
			System.out.print("Premi invio per continuare...");
			System.in.read();
			ClearTerminal.clearConsole();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void visualizzaMezziDisponibili() {

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
			ClearTerminal.clearConsole();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void visualizzaTutto() {
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
	}

}
