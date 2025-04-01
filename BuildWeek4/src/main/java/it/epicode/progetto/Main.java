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
        Mezzo tram1 = new Tram(null, milanoRoma, 5, 2, 50, inServizio);




        trattaDAO.insert(milanoRoma);
        mezzoDAO.insert(autobus1);
        mezzoDAO.insert(tram1);
        periodoDiServizioDAO.insert(inManutenzione);
        periodoDiServizioDAO.insert(inServizio);


        System.out.println("I mezzi attualmente in manutenzione sono: ");
        mezzoDAO.findByStato("In manutenzione");


        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
