package it.epicode.progetto.utils;

public class ClearTerminal {
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
