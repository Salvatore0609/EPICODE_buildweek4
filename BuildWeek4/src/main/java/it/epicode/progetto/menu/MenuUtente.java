package it.epicode.progetto.menu;

import it.epicode.progetto.Main;
import it.epicode.progetto.entities.GestioneElementoBiglietteria;
import it.epicode.progetto.entities.GestioneTessere;
import it.epicode.progetto.entities.Viaggio;
import it.epicode.progetto.utils.ClearTerminal;

import java.util.Scanner;

public class MenuUtente {
    public static void menuUtente(Long myUser) {
        while (true) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*************************");
        System.out.println("****** MENU UTENTE ******");
        System.out.println("*************************");
        System.out.println();
        System.out.println("1. Crea una tessera");
        System.out.println("2. Visualizza la scadenza della tessera");
        System.out.println("3. Acquista un biglietto");
        System.out.println("4. Acquista un abbonamento");
        System.out.println("5. Viaggia");
        System.out.println("6. Controlla se hai un abbonamento");
        System.out.println("7. Visualizza lo storico dei tuoi biglietti");
        System.out.println("8. Visualizza lo storico degli abbonamenti");
        System.out.println("0. Esci");
        System.out.println();
        int scelta = scanner.nextInt();


        switch (scelta) {
            case 0:
                ClearTerminal.clearConsole();
                Main.main(null);
                break;
            case 1:
                ClearTerminal.clearConsole();
                GestioneTessere.crea(myUser);
                break;
            case 2:
                ClearTerminal.clearConsole();
                GestioneElementoBiglietteria.visualizzaScadenzaTessera(myUser);
                break;
            case 3:
                ClearTerminal.clearConsole();
                GestioneElementoBiglietteria.creaBiglietto(myUser);
                break;
            case 4:
                ClearTerminal.clearConsole();
                GestioneElementoBiglietteria.creaAbbonamento(myUser);
                break;
            case 5:
                ClearTerminal.clearConsole();
                Viaggio.selezionaViaggio(myUser);
                break;
            case 6:
                ClearTerminal.clearConsole();
                GestioneElementoBiglietteria.visualizzaAbbonamento(myUser);
                break;
            case 7:
                break;
            case 8:
                break;
        }
    } }
}
