package it.epicode.progetto.login;

import it.epicode.progetto.utenti.GestioneUtenti;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("****************************");
        System.out.println("***** MENU PRINCIPALE ******");
        System.out.println("****************************");
        System.out.println();
        System.out.println("Seleziona 1 per creare un utente");
        System.out.println("Seleziona 2 per fare il login");
        System.out.println("Seleziona 3 per acquistare un biglietto");
        System.out.println("Seleziona 0 per uscire");
        int scelta = scanner.nextInt();
        scanner.nextLine();
        if (scelta == 1) {
            GestioneUtenti.crea();
        } else if (scelta == 2) {
            Login login = new Login();
            login.login();
        } else if (scelta == 0) {
            System.out.println("Arrivederci!");
            System.exit(0);
        }
    }
}
