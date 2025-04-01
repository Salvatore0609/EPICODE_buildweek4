package it.epicode.progetto.rivenditori;

import it.epicode.progetto.rivenditori.distributoriautomatici.DistributoriAutomatici;
import it.epicode.progetto.rivenditori.distributoriautomatici.Stato;
import it.epicode.progetto.rivenditori.rivenditoriautorizzati.RivenditoriAutorizzati;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainRivenditore {
    public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw4_t6");
    EntityManager em = emf.createEntityManager();

    RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);

    RivenditoriAutorizzati r1 = RivenditoriAutorizzati.builder()
                    .nome("Rivenditore 1")
                            .build();

    DistributoriAutomatici d1 = DistributoriAutomatici.builder()
            .nome("Distributore 1")
            .stato(Stato.ATTIVO)
            .build();

    DistributoriAutomatici d2 = DistributoriAutomatici.builder()
            .nome("Distributore 2")
            .stato(Stato.FUORI_SERVIZIO)
            .build();



        em.getTransaction().begin();
        rivenditoreDAO.insert(r1);
        rivenditoreDAO.insert(d1);
        rivenditoreDAO.insert(d2);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
