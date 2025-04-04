package it.epicode.progetto.menu;

import it.epicode.progetto.entities.GestioneTessere;
import it.epicode.progetto.entities.GestioneUtenti;
import it.epicode.progetto.utils.ClearTerminal;

import static it.epicode.progetto.utils.Input.scanner;

public class MenuGestioneUtenti {
	public static void menuUtenti() {
		while (true) {
			System.out.println("*****************************");
			System.out.println("****** GESTIONE UTENTI ******");
			System.out.println("*****************************");
			System.out.println();
			System.out.println("1. Crea un nuovo utente");
			System.out.println("2. Aggiorna un utente");
			System.out.println("3. Elimina un utente");
			System.out.println("4. Crea una tessera");
			System.out.println("5. Visualizza tutti gli utenti attivi");
			System.out.println("6. Visualizza tutti gli utenti inattivi");
			System.out.println("7. Controlla validità abbonamento");
			System.out.println("0. Esci");
			System.out.println();
			System.out.print("Scelta: ");
			int scelta = scanner.nextInt();
			scanner.nextLine();
			if (scelta == 0) {
				ClearTerminal.clearConsole();
				return;
			} else if (scelta == 1) {
				boolean isAdmin = true;
				GestioneUtenti.crea(isAdmin);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ClearTerminal.clearConsole();
			} else if (scelta == 2) {
				GestioneUtenti.aggiorna(null);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ClearTerminal.clearConsole();
			} else if (scelta == 3) {
				GestioneUtenti.elimina(null);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ClearTerminal.clearConsole();
			} else if (scelta == 4) {
				GestioneTessere.crea(null);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ClearTerminal.clearConsole();
			} else if (scelta == 5) {
				GestioneUtenti.visualizzaUtentiAttivi();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ClearTerminal.clearConsole();
			} else if (scelta == 6) {
				GestioneUtenti.visualizzaUtentiInattivi();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ClearTerminal.clearConsole();
			} else if (scelta == 7) {
				GestioneUtenti.controllaValiditaAbbonamento();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ClearTerminal.clearConsole();
			} else {
				System.err.println("Scelta non valida. Riprova.");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ClearTerminal.clearConsole();
			}
		}
	}
}
