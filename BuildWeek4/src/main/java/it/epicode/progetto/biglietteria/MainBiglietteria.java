package it.epicode.progetto.biglietteria;


import it.epicode.progetto.biglietteria.biglietto.Biglietto;
import it.epicode.progetto.dao.ElementoBiglietteriaDAO;
import it.epicode.progetto.rivenditori.Rivenditore;
import it.epicode.progetto.rivenditori.RivenditoreDAO;
import it.epicode.progetto.dao.TessereDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data


public class MainBiglietteria {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ElementoBiglietteriaDAO elementodao = new ElementoBiglietteriaDAO(em);
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);
        TessereDao tdao = new TessereDao(em);


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

        /*long utenteBiglietteria = 1L;
        UtentiDao udao = new UtentiDao(em);
        Utente utenteDaCercare = udao.findById(utenteBiglietteria);
        Abbonamento a1 = null;

        if (utenteDaCercare != null) {
            tdao = new TessereDao(em);
            Tessera tesseraDaCercare = tdao.findByUtente(utenteDaCercare);
            if (tesseraDaCercare != null) {
                a1 = Abbonamento.builder()
                        .dataDiEmissione(LocalDate.now())
                        .rivenditore(rivenditoriAutorizzati.get(0))
                        .durataAbbonamento(DurataAbbonamento.SETTIMANALE)
                        .scadenzaAbbonamento(LocalDate.of(2025, 8, 4))
                        .tessera(tesseraDaCercare)
                        .build();
            }
        }*/


        em.getTransaction().begin();
        elementodao.insert(b1);
        elementodao.insert(b2);
        elementodao.insert(b3);
        elementodao.insert(b4);
        elementodao.insert(b5);
        //elementodao.insert(a1);
        rivenditoreDAO.aggiornaBigliettiAbbonamentiEmessi();
        tdao.findAbbonamentoByTessera(1L);
        em.getTransaction().commit();

        rivenditoreDAO.ottieniBigliettiAbbonamentiEmessi(rivenditoriAutorizzati.get(0).getNome(), LocalDate.now(), LocalDate.now());

        em.close();
        emf.close();


    }
}
