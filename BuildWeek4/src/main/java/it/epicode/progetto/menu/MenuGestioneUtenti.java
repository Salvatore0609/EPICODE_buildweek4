package it.epicode.progetto.menu;

import it.epicode.progetto.tessere.GestioneTessere;
import it.epicode.progetto.utenti.GestioneUtenti;

import java.util.Scanner;

public class MenuGestioneUtenti {
    public static void menuUtenti() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***********************");
        System.out.println("***** MENU UTENTI ******");
        System.out.println("***********************");
        System.out.println();
        System.out.println("Seleziona 1 per creare un utente");
        System.out.println("Seleziona 2 per aggiornare un utente");
        System.out.println("Seleziona 3 per eliminare un utente");
        System.out.println("Seleziona 4 per creare una tessera");
        int scelta = scanner.nextInt();
        if (scelta == 1) {
            GestioneUtenti.crea();
        } else if (scelta == 2) {
            GestioneUtenti.aggiorna(null);
        } else if (scelta == 3) {
            GestioneUtenti.elimina(null);
        } else if (scelta == 4) {
            GestioneTessere.crea();
        }
    }
}
