package it.epicode.progetto.entities;

import it.epicode.progetto.dao.AutistiDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

import static it.epicode.progetto.utils.Input.scanner;

public class GestioneAutisti {

    public static void assumiAutista() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("epicode");
            em = emf.createEntityManager();

            System.out.println("**********************************");
            System.out.println("******* Assumi un autista ********");
            System.out.println("**********************************");
            System.out.println();
            System.out.println("Inserisci il nome dell'autista:");
            String nomeAutista = scanner.nextLine();
            System.out.println("Inserisci il cognome dell'autista:");
            String cognomeAutista = scanner.nextLine();
            System.out.println("Inserisci l'anno di nascita:");
            int annoNascita = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Inserisci il mese di nascita:");
            int meseNascita = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Inserisci il giorno di nascita:");
            int giornoNascita = scanner.nextInt();
            scanner.nextLine();
            LocalDate dataNascita = LocalDate.of(annoNascita, meseNascita, giornoNascita);
            System.out.println("Inserisci la patente categoria dell'autista (A, B, C, D, E):");
            String patenteCategoria = scanner.nextLine();

            Autista autista = new Autista(nomeAutista, cognomeAutista, dataNascita, patenteCategoria, true);

            AutistiDao aDao = new AutistiDao(em);

            em.getTransaction().begin();
            aDao.save(autista);
            em.getTransaction().commit();

            System.out.println("Autista creato con successo!");

        } catch (Exception e) {
            System.err.println("Errore durante l'assunzione dell'autista: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public static void licenziaAutista() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("epicode");
            em = emf.createEntityManager();

            System.out.println("*************************************");
            System.out.println("******** Licenzia un autista ********");
            System.out.println("*************************************");
            System.out.println();
            System.out.println("Inserisci l'ID dell'autista da licenziare:");
            Long idAutista = scanner.nextLong();
            scanner.nextLine();

            AutistiDao autistaDao = new AutistiDao(em);

            //Recupero  dell'autista
            Autista autista = autistaDao.findById(idAutista);
            if (autista == null) {
                System.err.println("Autista non trovato.");
                return;
            }
            // licenzio l'autista

            em.getTransaction().begin();
            autistaDao.delete(autista);
            em.getTransaction().commit();
            System.out.println("Autista licenziato con successo!");


        } catch (Exception e) {
            System.err.println("Errore durante la licenziatura dell'autista: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public static void visualizzaAutistiAttivi() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("epicode");
            em = emf.createEntityManager();

            AutistiDao autistaDao = new AutistiDao(em);

            System.out.println("**********************************");
            System.out.println("******* Autisti Attivi ********");
            System.out.println("**********************************");
            System.out.println();

            autistaDao.findAllAutistiAttivi();

        } catch (Exception e) {
            System.err.println("Errore durante la visualizzazione degli autisti attivi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
    public static void visualizzaAutistiLicenziati() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("epicode");
            em = emf.createEntityManager();

            AutistiDao autistaDao = new AutistiDao(em);

            System.out.println("**********************************");
            System.out.println("******* Autisti Licenziati ********");
            System.out.println("**********************************");
            System.out.println();

            autistaDao.findAllAutistiLicenziati();

        } catch (Exception e) {
            System.err.println("Errore durante la visualizzazione degli autisti licenziati: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}

