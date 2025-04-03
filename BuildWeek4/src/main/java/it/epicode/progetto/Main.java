package it.epicode.progetto;

import it.epicode.progetto.dao.UtentiDao;
import it.epicode.progetto.entities.GestioneElementoBiglietteria;
import it.epicode.progetto.entities.Utente;
import it.epicode.progetto.entities.Viaggio;
import it.epicode.progetto.enums.Ruolo;
import it.epicode.progetto.utils.CreateAdmin;
import it.epicode.progetto.utils.CreateDatabase;
import it.epicode.progetto.utils.Login;
import it.epicode.progetto.entities.GestioneUtenti;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import static it.epicode.progetto.utils.Input.scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();


        UtentiDao uDao = new UtentiDao(em);
        Utente utente = uDao.findByUsername("admin");
        if (utente == null) {
            CreateDatabase.main(null);
        }
        while (true) {
            try {
                ClearTerminal.clearConsole();
                System.out.println("****************************");
                System.out.println("***** MENU PRINCIPALE ******");
                System.out.println("****************************");
                System.out.println();
                System.out.println("1. Login");
                System.out.println("2. Registra un nuovo utente");
                System.out.println("3. Acquista un biglietto");
                System.out.println("4. Viaggia");
                System.out.println("0. Esci");
                System.out.println();
                System.out.print("Scelta: ");

                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1 -> {
                        Login login = new Login();
                        login.login();
                    }
                    case 2 -> {
                        boolean isAdmin = false;
                        GestioneUtenti.crea(isAdmin);
                    }
                    case 3 -> {
                        Long myUser = null;
                        GestioneElementoBiglietteria.creaBiglietto(myUser);
                    }
                    case 4 -> {
                        Long myUser = null;
                        Viaggio.selezionaViaggio(myUser);
                    }
                    case 0 -> {
                        System.out.println("Arrivederci!");
                        System.exit(0);
                    }
                    default -> System.err.println("Scelta non valida. Riprova.");
                }

                System.out.println("\nPremi INVIO per continuare...");
                scanner.nextLine();

            } catch (InputMismatchException e) {
                System.err.println("Errore: Inserisci un numero valido.");
                scanner.nextLine();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                ClearTerminal.clearConsole();
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
                e.printStackTrace();
                System.out.println("Ritorno al menu principale...");
                scanner.nextLine();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
