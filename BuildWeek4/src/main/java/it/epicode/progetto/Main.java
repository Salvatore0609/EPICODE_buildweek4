package it.epicode.progetto;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.mezzi.Autobus;
import it.epicode.progetto.mezzi.Mezzo;
import it.epicode.progetto.mezzi.Tram;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        Tratta milanoRoma = new Tratta(null, "Milano", "Roma", LocalDateTime.now(), LocalDateTime.now(), null, 360);
        Mezzo autobus1 = new Autobus(null,"autobus1", milanoRoma, 5, 2, 20, Stato.IN_SERVIZIO);
        Mezzo tram1 = new Tram(null,"tram1", milanoRoma, 5, 2, 50, Stato.IN_MANUTENZIONE);

        em.getTransaction().begin();

        trattaDAO.insert(milanoRoma);
        mezzoDAO.insert(autobus1);
        mezzoDAO.insert(tram1);


        mezzoDAO.updateStato("autobus1", Stato.IN_SERVIZIO);

        System.out.println(autobus1);



        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}


