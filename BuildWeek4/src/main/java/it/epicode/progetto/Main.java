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

        Tratta milanoRoma = new Tratta(null, "Milano", "Roma", LocalDateTime.of(2025,3,31,10,30,00), LocalDateTime.of(2025,3,31,15,30,00), 360);
        Autobus autobus1 = new Autobus(null, milanoRoma, 5, 2);
        Tram tram1 = new Tram(null, milanoRoma, 5, 2);
        PeriodoDiServizio servizioAutobus1 = new PeriodoDiServizio(null, Stato.IN_SERVIZIO, LocalDate.of(2025,3,25), LocalDate.of(2025,3,31), autobus1);
        PeriodoDiServizio servizioTram1 = new PeriodoDiServizio(null, Stato.IN_MANUTENZIONE, LocalDate.of(2025,3,25), LocalDate.of(2025,3,31), tram1);


        trattaDAO.insert(milanoRoma);
        mezzoDAO.insert(autobus1);
        mezzoDAO.insert(tram1);
        periodoDiServizioDAO.insert(servizioAutobus1);
        periodoDiServizioDAO.insert(servizioTram1);


        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
