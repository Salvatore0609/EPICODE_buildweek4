package it.epicode.progetto.utility;

public class ClearTerminal {
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
