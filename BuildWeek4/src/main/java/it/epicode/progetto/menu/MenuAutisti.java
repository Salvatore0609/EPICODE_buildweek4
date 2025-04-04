package it.epicode.progetto.menu;
import it.epicode.progetto.entities.GestioneAutisti;
import it.epicode.progetto.entities.GestioneUtenti;
import it.epicode.progetto.utils.ClearTerminal;

import static it.epicode.progetto.utils.Input.scanner;

public class MenuAutisti {
    public static void menuAutisti() {
        while (true) {
            System.out.println("*************************");
            System.out.println("****** MENU AUTISTI ******");
            System.out.println("*************************");
            System.out.println();
            System.out.println("1. Assumi un nuovo autista");
            System.out.println("2. Fai un richiamo ad un autista");
            System.out.println("3. Licenzia un autista");
            System.out.println("4. Visualizza tutti gli autisti attivi");
            System.out.println("5. Visualizza tutti gli autisti inattivi");
            System.out.println("0. Esci");
            System.out.println();
            int scelta = scanner.nextInt();
            scanner.nextLine();
            switch (scelta) {
                case 1:
                    ClearTerminal.clearConsole();
                    GestioneAutisti.assumiAutista();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    ClearTerminal.clearConsole();
                    return;
                default:
                    System.err.println("Scelta non valida. Riprova.");
                    break;
            }

        }
    }
}
