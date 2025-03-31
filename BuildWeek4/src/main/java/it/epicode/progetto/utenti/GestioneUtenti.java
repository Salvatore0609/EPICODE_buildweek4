package it.epicode.progetto.utenti;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class GestioneUtenti {

    public static Utente crea() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Utente nuovoUtente = null;

        try (Scanner scanner = new Scanner(System.in)) {
            emf = Persistence.createEntityManagerFactory("epicode");
            em = emf.createEntityManager();

            System.out.println("Inserisci il nome dell'utente:");
            String nomeUtente = scanner.nextLine();

            System.out.println("Inserisci il cognome dell'utente:");
            String cognomeUtente = scanner.nextLine();

            System.out.println("Inserisci uno username:");
            String username = scanner.nextLine();

            System.out.println("Inserisci una password:");
            String passwordUtente = scanner.nextLine();

            System.out.println("Inserisci il ruolo dell'utente:");
            System.out.println("1. ADMIN");
            System.out.println("2. USER");
            int ruoloUtente = scanner.nextInt();
            scanner.nextLine();

            Ruolo ruolo;
            if (ruoloUtente == 1) {
                ruolo = Ruolo.ADMIN;
            } else {
                ruolo = Ruolo.USER;
            }

            nuovoUtente = new Utente();
            nuovoUtente.setNome(nomeUtente);
            nuovoUtente.setCognome(cognomeUtente);
            nuovoUtente.setUsername(username);
            nuovoUtente.setPassword(passwordUtente);
            nuovoUtente.setRuolo(ruolo);

            UtentiDao uDao = new UtentiDao(em);
            em.getTransaction().begin();
            uDao.insert(nuovoUtente);
            em.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException("Errore nella creazione dell'utente", e);
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }

        return nuovoUtente;
    }
}
