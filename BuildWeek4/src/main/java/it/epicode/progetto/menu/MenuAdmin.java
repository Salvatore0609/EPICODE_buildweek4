package it.epicode.progetto.menu;

import it.epicode.progetto.Main;
import it.epicode.progetto.ProvaMainScelte;
import it.epicode.progetto.utility.ClearTerminal;

import static it.epicode.progetto.utility.Input.scanner;

public class MenuAdmin {
    public static void menuAdmin() {
        while (true) {
            System.out.println("***********************");
            System.out.println("***** MENU ADMIN ******");
            System.out.println("***********************");
            System.out.println();
            System.out.println("1. Gestione Utenti");
            System.out.println("2. Gestione Mezzi");
            System.out.println("3. Report");
            System.out.println("0. Esci");
            System.out.println();
            int scelta = scanner.nextInt();
            switch (scelta) {
                case 0:
                    ClearTerminal.clearConsole();
                    Main.main(null);
                    return;
                case 1:
                    ClearTerminal.clearConsole();
                    MenuGestioneUtenti.menuUtenti();
                    break;
                case 2:
                    ClearTerminal.clearConsole();
                    ProvaMainScelte.main(null);
                    break;
                case 3:
                    break;
            }
        }
        }
}
