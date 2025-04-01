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


        PeriodoDiServizio inManutenzione = new PeriodoDiServizio(null, "In manutenzione", LocalDate.of(2025,3,25), LocalDate.of(2025,3,31), null);
        PeriodoDiServizio inServizio = new PeriodoDiServizio(null, "In servizio", LocalDate.of(2025,3,25), LocalDate.of(2025,3,31), null);
        Tratta milanoRoma = new Tratta(null, "Milano", "Roma", LocalDateTime.of(2025,3,31,10,30,00), LocalDateTime.of(2025,3,31,15,30,00), 360);
        Mezzo autobus1 = new Autobus(null, milanoRoma, 5, 2, 20, inManutenzione);
        Mezzo tram1 = new Tram(null, milanoRoma, 6, 2, 50, inServizio);
//
//
//
//
//        trattaDAO.insert(milanoRoma);
//        periodoDiServizioDAO.insert(inManutenzione);
//        periodoDiServizioDAO.insert(inServizio);
//        mezzoDAO.insert(tram1);
//        mezzoDAO.insert(autobus1);


//        System.out.println("I mezzi attualmente in manutenzione sono: ");
//        mezzoDAO.findByStato("In manutenzione");



        autobus1.setPeriodoDiServizio(inServizio);
        mezzoDAO.update(autobus1);
        System.out.println(autobus1);
        em.getTransaction().commit();





        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Menu Mezzi ---");
            System.out.println("1. Cambia stato autobus");
            System.out.println("2. Mezzi in manutenzione");
            System.out.println("3. Esci");
            System.out.print("Seleziona un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.println("Cambio lo stato di autobus1 da in manutenzione a in servizio.");
                    autobus1.setPeriodoDiServizio(inServizio);
                    mezzoDAO.update(autobus1);
                    System.out.println(autobus1);
                    break;
                case 2:
                    System.out.println("I mezzi attualmente in manutenzione sono: ");
                    mezzoDAO.findByStato("In manutenzione");
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
    } }



