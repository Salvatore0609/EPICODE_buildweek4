package it.epicode.progetto.biglietteria;
import it.epicode.progetto.biglietteria.biglietto.Biglietto;
import it.epicode.progetto.dao.ElementoBiglietteriaDAO;
import it.epicode.progetto.dao.RivenditoreDAO;
import it.epicode.progetto.dao.UtentiDao;
import it.epicode.progetto.rivenditori.Rivenditore;
import it.epicode.progetto.utenti.Utente;
import it.epicode.progetto.utility.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

import static it.epicode.progetto.utility.Input.scanner;

public class GestioneElementoBiglietteria {
    public static void creaBiglietto(Long myUser) {

        while (true) {
            EntityManagerFactory emf = null;
            EntityManager em = null;
            try {
                emf = Persistence.createEntityManagerFactory("epicode");
                em = emf.createEntityManager();

                RivenditoreDAO rDao = new RivenditoreDAO(em);
                ElementoBiglietteriaDAO ebDao = new ElementoBiglietteriaDAO(em);
                UtentiDao uDao = new UtentiDao(em);

                ClearTerminal.clearConsole();
                System.out.println("**********************");
                System.out.println("***** BIGLIETTO ******");
                System.out.println("**********************");
                System.out.println();
                System.out.println("Elenco rivenditori autorizzati e distributori");
                System.out.println("automatici");
                System.out.println();
                List<Rivenditore> rivenditori = rDao.findAll();
                int index = 1;
                for (Rivenditore r : rivenditori) {
                    System.out.println(index + ". " + r.getNome());
                    index++;
                }
                System.out.println("0. Esci");
                System.out.println();
                System.out.println("Seleziona il rivenditore autorizzato");
                System.out.println("o il distributore automatico per il");
                System.out.println("tuo acquisto");
                System.out.println();
                int scelta = scanner.nextInt();
                scanner.nextLine();
                if (scelta == 0) {
                    return;
                }

                if (scelta < 1 || scelta > rivenditori.size()) {
                    System.out.println("Scelta non valida. Seleziona un rivenditore o un distributore dalla lista.");
                    Thread.sleep(1500);
                    continue;
                }

                Utente myObjUser;
                if (myUser != null) {
                    myObjUser = uDao.findById(myUser);
                } else {
                    myObjUser = null;
                }


                Rivenditore rivenditore = rivenditori.get(scelta - 1);
                Biglietto eb = Biglietto.builder()
                        .dataDiEmissione(LocalDate.now())
                        .rivenditore(rivenditore)
                        .utente(myObjUser)
                        .build();

                em.getTransaction().begin();
                ebDao.insert(eb);
                rDao.aggiornaBigliettiAbbonamentiEmessi();
                em.getTransaction().commit();
                System.out.println("Biglietto acquistato con successo!");
                System.out.println();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                throw new RuntimeException("Errore nella creazione del biglietto", e);
            } finally {
                if (em != null) em.close();
                if (emf != null) emf.close();
            }
        }
    }
}
