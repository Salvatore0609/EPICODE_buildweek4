package it.epicode.progetto.menu;

import it.epicode.progetto.Main;
import it.epicode.progetto.biglietteria.GestioneElementoBiglietteria;
import it.epicode.progetto.utility.ClearTerminal;

import java.util.Scanner;

public class MenuUtente {
    public static void menuUtente(Long myUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*************************");
        System.out.println("****** MENU UTENTE ******");
        System.out.println("*************************");
        System.out.println();
        System.out.println("1. Crea una tessera");
        System.out.println("2. Visualizza la scadenza della tessera");
        System.out.println("3. Acquista un biglietto");
        System.out.println("4. Acquista un abbonamento");
        System.out.println("5. Visualizza gli abbonamenti attivi");
        System.out.println("6. Visualizza lo storico dei tuoi biglietti");
        System.out.println("7. Visualizza lo storico degli abbonamenti");
        System.out.println("0. Esci");
        System.out.println();
        int scelta = scanner.nextInt();
        switch (scelta) {
            case 0:
                ClearTerminal.clearConsole();
                Main.main(null);
                break;
            case 1:

                break;
            case 2:
                break;
            case 3:
                ClearTerminal.clearConsole();
                GestioneElementoBiglietteria.creaBiglietto(myUser);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }
}
