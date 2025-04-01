package it.epicode.progetto;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.PeriodoDiServizioDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.mezzi.Autobus;
import it.epicode.progetto.mezzi.Mezzo;
import it.epicode.progetto.mezzi.Tram;
import it.epicode.progetto.periodo_di_servizio.PeriodoDiServizio;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        MezzoDAO mezzoDAO =  new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        PeriodoDiServizioDAO periodoDiServizioDAO = new PeriodoDiServizioDAO(em);



        PeriodoDiServizio servizioTram1 = new PeriodoDiServizio(null, Stato.IN_MANUTENZIONE, LocalDate.of(2025,3,25), LocalDate.of(2025,3,31), null);
        PeriodoDiServizio servizioAutobus1 = new PeriodoDiServizio(null, Stato.IN_SERVIZIO, LocalDate.of(2025,3,25), LocalDate.of(2025,3,31), null);
        Tratta milanoRoma = new Tratta(null, "Milano", "Roma", LocalDateTime.of(2025,3,31,10,30,00), LocalDateTime.of(2025,3,31,15,30,00), 360);
        Mezzo autobus1 = new Autobus(null, milanoRoma, 5, 2, 20, servizioAutobus1);
        Mezzo tram1 = new Tram(null, milanoRoma, 5, 2, 50, servizioTram1);

        servizioAutobus1.setMezzo(autobus1);
        servizioTram1.setMezzo(tram1);

        trattaDAO.insert(milanoRoma);
        mezzoDAO.insert(autobus1);
        mezzoDAO.insert(tram1);
        periodoDiServizioDAO.insert(servizioAutobus1);
        periodoDiServizioDAO.insert(servizioTram1);

        autobus1.setPeriodoDiServizio(servizioTram1);
        mezzoDAO.update(autobus1);

        em.getTransaction().commit();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Menu Mezzi ---");
            System.out.println("1. Mezzi in servizio");
            System.out.println("2. Mezzi in manutenzione");
            System.out.println("3. Esci");
            System.out.print("Seleziona un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    visualizzaMezzi(Stato.IN_SERVIZIO, periodoDiServizioDAO);
                    System.out.println("Cambia stato");
                    break;
                case 2:
                    visualizzaMezzi(Stato.IN_MANUTENZIONE, periodoDiServizioDAO);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Uscita dal menu.");
                    break;
                default:
                    System.out.println("Opzione non valida, riprova.");
            }
        }
        scanner.close();


        em.close();
        emf.close();
    }

    public static void visualizzaMezzi(Stato stato, PeriodoDiServizioDAO periodoDiServizioDAO) {
        List<Mezzo> mezzi = periodoDiServizioDAO.findMezziByStato(stato);

        if (mezzi.isEmpty()) {
            System.out.println("Nessun mezzo trovato.");
            return;
        }

        System.out.println("\n--- Mezzi " + stato + " ---");
        for (Mezzo mezzo : mezzi) {
            System.out.println("ID: " + mezzo.getId() + " - Tipo: " + mezzo.getClass().getSimpleName());
            System.out.println("Biglietti Vidimati: " + mezzo.getNumeroTicketVidimati());
            System.out.println("Capienza: " + (mezzo instanceof Autobus ? ((Autobus) mezzo).getCapienza() : "N/D"));
            System.out.println("Tratta: " + mezzo.getTratta().getZonaDiPartenza() + " → " + mezzo.getTratta().getCapolinea());

            List<PeriodoDiServizio> periodi = periodoDiServizioDAO.findByMezzo(mezzo);
            for (PeriodoDiServizio periodo : periodi) {
                System.out.println("  - Periodo: " + periodo.getInizioAttivita() + " - " + periodo.getFineAttivita());
            }
            System.out.println("-----------------------------------");
        }
    }
}
