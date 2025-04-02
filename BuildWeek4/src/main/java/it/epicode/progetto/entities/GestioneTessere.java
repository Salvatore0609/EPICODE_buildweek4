package it.epicode.progetto.entities;

import it.epicode.progetto.dao.TessereDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

import static it.epicode.progetto.utils.Input.scanner;
public class GestioneTessere {
    public static Tessera crea(Long myUser) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Tessera nuovaTessera = null;


        try  {
            emf = Persistence.createEntityManagerFactory("epicode");
            em = emf.createEntityManager();

            Long id = null;
            if (myUser != null) {
                id = myUser;
            } else {

            System.out.println("Inserisci l'ID dell'utente a cui associare la tessera:");

            id = scanner.nextLong();
            scanner.nextLine(); }

            Utente utente = em.find(Utente.class, id);
            TessereDao tDao = new TessereDao(em);

            if (utente != null) {
                // Verifica se l'utente ha già una tessera
                Tessera tesseraEsistente = tDao.findByUtente(utente);
                if (tesseraEsistente != null) {
                    System.out.println("L'utente ha già una tessera associata attiva.");
                    return tesseraEsistente;
                }
                nuovaTessera = new Tessera(utente, LocalDate.now());

                em.getTransaction().begin();
                tDao.insert(nuovaTessera);
                em.getTransaction().commit();

                System.out.println("Tessera creata con successo per l'utente: " + utente.getNome() + " " + utente.getCognome());
            } else {
                System.out.println("Utente non trovato.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Errore nella creazione della tessera", e);
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }

        return nuovaTessera;
    }
}

