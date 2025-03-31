package it.epicode.progetto;

import it.epicode.progetto.utenti.GestioneUtenti;

import java.util.Scanner;

public class coseACaso {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleziona 1 per creare un utente");
        System.out.println("Seleziona 2 per aggiornare un utente");
        int scelta = scanner.nextInt();
        if (scelta == 1) {
            GestioneUtenti.crea();
        } else if (scelta == 2) {
            GestioneUtenti.aggiorna(null);
        }
    }
}
