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
        System.out.println("1. Login");
        System.out.println("2. Registra un nuovo utente");
        System.out.println("3. Acquista un biglietto");
        System.out.println("0. Esci");
        System.out.println();
        int scelta = scanner.nextInt();
        scanner.nextLine();
        if (scelta == 2) {
            GestioneUtenti.crea();
        } else if (scelta == 1) {
            Login login = new Login();
            login.login();
        } else if (scelta == 0) {
            System.out.println("Arrivederci!");
            System.exit(0);
        }
    }
}
