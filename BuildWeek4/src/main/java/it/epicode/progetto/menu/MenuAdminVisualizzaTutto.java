package it.epicode.progetto.menu;
import static it.epicode.progetto.utils.Input.scanner;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.entities.Mezzo;
import it.epicode.progetto.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MenuAdminVisualizzaTutto {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		// DAO
		MezzoDAO mezzoDAO = new MezzoDAO(em);
		TrattaDAO trattaDAO = new TrattaDAO(em);

		int sceltaPresa;

		do {

			System.out.println("Benvenuto nel menu della visualizzazione:");
			System.out.println("Scegli cosa vuoi visualizzare:");
			System.out.println("1. Visualizza tutti i mezzi");
			System.out.println("2. Visualizza tutte le tratte");
			System.out.println("3. Visualizza tutti i biglietti vidimati nel corso del tempo");
			System.out.println("4. Visualizza tutti i mezzi con dei posti liberi");
			System.out.println("0. Esci dal menu della visualizzazione");

			sceltaPresa = scanner.nextInt();
			switch (sceltaPresa) {
				case 1 :
					System.out.println("Hai scelto di visualizzare tutti i mezzi.");
					List<Mezzo> tuttiIMezzi = mezzoDAO.findAll();
					for (Mezzo cacca : tuttiIMezzi) {
						System.out.println(cacca);
					}
					break;
				case 2 :
					System.out.println("Hai scelto di visualizzare tutte le tratte.");
					List<Tratta> tutteLeTratte = trattaDAO.findAll();
					for (Tratta cacca : tutteLeTratte) {
						System.out.println(cacca);
					}
					break;
				case 3 :
					System.out.println("Hai scelto di visualizzare tutti i biglietti vidimati nel corso del tempo.");
					mezzoDAO.findAllBigliettiVidimati();
					break;
				case 4 :
					System.out.println("Hai scelto di visualizzare tutti i mezzi con posti liberi.");
					mezzoDAO.findAllPostiLiberi();
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
