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
            System.out.println("3. Gestione Tratte");
            System.out.println("4. Gestione Rivenditori Autorizzati");
            System.out.println("5. Gestione Distributori Automatici");
            System.out.println("6. Report");
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
                    ClearTerminal.clearConsole();
                    // GestioneTratte.menuTratte();
                    break;
                case 4:
                    ClearTerminal.clearConsole();
                    // GestioneRivenditori.menuRivenditori();
                    break;
                case 5:
                    ClearTerminal.clearConsole();
                    // GestioneDistributori.menuDistributori();
                    break;
                case 6:
                    ClearTerminal.clearConsole();
                    // GestioneReport.menuReport();
                    break;
            }
        }
        }
}
