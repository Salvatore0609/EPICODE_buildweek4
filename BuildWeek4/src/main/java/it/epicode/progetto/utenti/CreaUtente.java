package it.epicode.progetto.utenti;

import java.util.Scanner;

public class CreaUtente {

    public static Utente crea() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Inserisci il nome dell'utente:");
            String nomeUtente = scanner.nextLine();
            System.out.println("Inserisci il cognome dell'utente:");
            String cognomeUtente = scanner.nextLine();
            System.out.println("Inserisci uno username:");
            String username = scanner.nextLine();
            System.out.println("Inserisci una password:");
            String passwordUtente = scanner.nextLine();
            System.out.println("Inserisci il ruolo dell'utente:");
            System.out.println("1. ADMIN");
            System.out.println("2. USER");
            int ruoloUtente = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            Ruolo ruoloEnum = (ruoloUtente == 1) ? Ruolo.ADMIN : Ruolo.USER;
        } catch (Exception e) {
            throw new RuntimeException("Errore nella creazione dell'utente", e);
        }
        return null;
    }
}