package it.epicode.progetto;

import it.epicode.progetto.login.Login;
import it.epicode.progetto.utenti.GestioneUtenti;
import it.epicode.progetto.utility.ClearTerminal;

import java.util.Scanner;
import static it.epicode.progetto.utility.Input.scanner;

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
            } else if (scelta == 0) {
                System.out.println("Arrivederci!");
                return;
            }
        }

    }
}
