package it.epicode.progetto.entities;

import it.epicode.progetto.dao.AutistaDao;
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
            System.out.println("Inserisci la data di nascita dell'autista (formato: yyyy-MM-dd):");
            LocalDate dataNascita = LocalDate.parse(scanner.nextLine());
            System.out.println("Inserisci la patente categoria dell'autista:");
            String patenteCategoria = scanner.nextLine();

            Autista autista = new Autista(nomeAutista, cognomeAutista, dataNascita, patenteCategoria);

            AutistaDao aDao = new AutistaDao(em);

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
}

