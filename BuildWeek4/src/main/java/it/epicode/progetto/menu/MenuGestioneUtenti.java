package it.epicode.progetto.menu;

import it.epicode.progetto.tessere.GestioneTessere;
import it.epicode.progetto.utenti.GestioneUtenti;
import it.epicode.progetto.utility.ClearTerminal;

import static it.epicode.progetto.utility.Input.scanner;

public class MenuGestioneUtenti {
    public static void menuUtenti() {
        while (true) {
            System.out.println("*************************");
            System.out.println("****** MENU UTENTI ******");
            System.out.println("*************************");
            System.out.println();
            System.out.println("1. Crea un nuovo utente");
            System.out.println("2. Aggiorna un utente");
            System.out.println("3. Elimina un utente");
            System.out.println("4. Crea una tessera");
            System.out.println("0. Esci");
            System.out.println();
            int scelta = scanner.nextInt();
            scanner.nextLine();
            if (scelta == 0) {
                ClearTerminal.clearConsole();
                return;
            } else if (scelta == 1) {
                boolean isAdmin = true;
                GestioneUtenti.crea(isAdmin);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClearTerminal.clearConsole();
            } else if (scelta == 2) {
                GestioneUtenti.aggiorna(null);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClearTerminal.clearConsole();
            } else if (scelta == 3) {
                GestioneUtenti.elimina(null);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClearTerminal.clearConsole();
            } else if (scelta == 4) {
                GestioneTessere.crea();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClearTerminal.clearConsole();
            }
        }
    }
}
