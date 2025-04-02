package it.epicode.progetto;

import it.epicode.progetto.dao.UtentiDao;
import it.epicode.progetto.entities.GestioneElementoBiglietteria;
import it.epicode.progetto.entities.Utente;
import it.epicode.progetto.entities.Viaggio;
import it.epicode.progetto.enums.Ruolo;
import it.epicode.progetto.utils.CreateAdmin;
import it.epicode.progetto.utils.Login;
import it.epicode.progetto.entities.GestioneUtenti;
import it.epicode.progetto.utils.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

import static it.epicode.progetto.utils.Input.scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
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
            int scelta = scanner.nextInt();
            scanner.nextLine();
            if (scelta == 2) {
                boolean isAdmin = false;
                GestioneUtenti.crea(isAdmin);
            } else if (scelta == 1) {
                Login login = new Login();
                login.login();
            }else if (scelta == 3) {
                Long myUser = null;
                GestioneElementoBiglietteria.creaBiglietto(myUser);
            } else if (scelta == 4) {
                Long myUser = null;
                Viaggio.selezionaViaggio(myUser);
            } else if (scelta == 0) {
                System.out.println("Arrivederci!");
                return;
            }
        }

    }
}
