package it.epicode.progetto.biglietteria;


import it.epicode.progetto.biglietteria.abbonamento.Abbonamento;
import it.epicode.progetto.biglietteria.abbonamento.DurataAbbonamento;
import it.epicode.progetto.biglietteria.biglietto.Biglietto;
import it.epicode.progetto.rivenditori.Rivenditore;
import it.epicode.progetto.rivenditori.RivenditoreDAO;
import it.epicode.progetto.rivenditori.distributoriautomatici.DistributoriAutomatici;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data


public class MainBiglietteria {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw4_t6");
        EntityManager em = emf.createEntityManager();

        ElementoBiglietteriaDAO elementodao = new ElementoBiglietteriaDAO(em);
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);

        List<Rivenditore> rivenditoriAutorizzati = em.createQuery("select r from RivenditoriAutorizzati r", Rivenditore.class).getResultList();
        List<Rivenditore> distributoriAutomatici = em.createQuery("select d from DistributoriAutomatici d", Rivenditore.class).getResultList();


        Biglietto b1 = Biglietto.builder()
                        .dataDiEmissione(LocalDate.now())
                        .rivenditore(rivenditoriAutorizzati.get(0))
                        .build();

        Biglietto b2 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(rivenditoriAutorizzati.get(0))
                .build();

        Biglietto b3 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(rivenditoriAutorizzati.get(0))
                .build();

        Biglietto b4 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(distributoriAutomatici.get(0))
                .build();

        Biglietto b5 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(distributoriAutomatici.get(0))
                .build();

        Abbonamento a1 = Abbonamento.builder()
                        .dataDiEmissione(LocalDate.now())
                        .rivenditore(rivenditoriAutorizzati.get(0))
                .durataAbbonamento(DurataAbbonamento.SETTIMANALE)
                .scadenzaAbbonamento(LocalDate.of(2025, 8 , 4))
                        .build();


        em.getTransaction().begin();
        elementodao.insert(b1);
        elementodao.insert(b2);
        elementodao.insert(b3);
        elementodao.insert(b4);
        elementodao.insert(b5);
        rivenditoreDAO.aggiornaBigliettiAbbonamentiEmessi();
        em.getTransaction().commit();

        rivenditoreDAO.ottieniBigliettiAbbonamentiEmessi(rivenditoriAutorizzati.get(0).getNome(), LocalDate.now(), LocalDate.now());

        em.close();
        emf.close();



    }
}
