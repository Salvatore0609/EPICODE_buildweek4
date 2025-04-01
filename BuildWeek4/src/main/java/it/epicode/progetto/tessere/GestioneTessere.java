package it.epicode.progetto.tessere;

import it.epicode.progetto.utenti.Utente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class GestioneTessere {
    public static Tessera crea() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Tessera nuovaTessera = null;

        try (Scanner scanner = new Scanner(System.in)) {
            emf = Persistence.createEntityManagerFactory("epicode");
            em = emf.createEntityManager();

            System.out.println("Inserisci l'ID dell'utente a cui associare la tessera:");
            Long id = scanner.nextLong();
            scanner.nextLine();

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

