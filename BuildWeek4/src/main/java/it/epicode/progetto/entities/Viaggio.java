package it.epicode.progetto.entities;

import it.epicode.progetto.dao.TrattaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Viaggio {


    public static void selezionaViaggio() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
        emf = Persistence.createEntityManagerFactory("epicode");
        em = emf.createEntityManager();
        TrattaDAO trattaDAO = new TrattaDAO(em);
        System.out.println("Seleziona una delle tratte");
        System.out.println();
        List<Tratta> tutteLeTratte = trattaDAO.findAll();
        int index = 1;
        for (Tratta destinazione : tutteLeTratte) {

            System.out.println(index + ". " + destinazione);
            index++;
        } } catch (Exception e) {
            throw new RuntimeException("Errore nella visualizzazione dell'abbonamento", e);
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }


}
