package it.epicode.progetto.menu;

import java.util.Scanner;

public class MenuUtente {
    public static void menuUtente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Crea una tessera");
        System.out.println("2. Visualizza la scadenza della tessera");
        System.out.println("3. Acquista un biglietto");
        System.out.println("4. Acquista un abbonamento");
        System.out.println("5. Visualizza gli abbonamenti attivi");
        System.out.println("6. Visualizza lo storico dei tuoi biglietti");
        System.out.println("7. Visualizza lo storico degli abbonamenti");
        int scelta = scanner.nextInt();
        switch (scelta) {
            case 1:

                break;
            case 2:
                break;
            case 3:
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
