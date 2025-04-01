package it.epicode.progetto.menu;

import java.util.Scanner;

public class MenuAdmin {
    public static void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***********************");
        System.out.println("***** MENU ADMIN ******");
        System.out.println("***********************");
        System.out.println();
        System.out.println("1. Gestione Utenti");
        System.out.println("2. Gestione Mezzi");
        System.out.println("3. Report");
        int scelta = scanner.nextInt();
        switch (scelta) {
            case 1:
                MenuGestioneUtenti.menuUtenti();
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
