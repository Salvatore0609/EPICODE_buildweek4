package it.epicode.progetto.biglietteria.biglietto;
import it.epicode.progetto.utility.ClearTerminal;

import static it.epicode.progetto.utility.Input.scanner;

public class GestioneElementoBiglietteria {
    public static void creaBiglietto() {
        while (true) {
            ClearTerminal.clearConsole();
            System.out.println("**********************");
            System.out.println("***** BIGLIETTO ******");
            System.out.println("**********************");
            System.out.println();
            System.out.println("1. Seleziona il rivenditore autorizzato");
            System.out.println("   o il distributore automatico per il");
            System.out.println("   tuo acquisto");
            System.out.println("0. Esci");
            System.out.println();
            int scelta = scanner.nextInt();
            scanner.nextLine();
            if (scelta == 1) {

            } else if (scelta == 0) {
                return;
            }
        }
    }
}
