package it.epicode.progetto.menu;

import it.epicode.progetto.Main;
import it.epicode.progetto.utils.ClearTerminal;

import java.util.InputMismatchException;

import static it.epicode.progetto.utils.Input.scanner;

public class MenuAdmin {
	public static void menuAdmin() {
		while (true) {
			try {
				System.out.println("***********************");
				System.out.println("***** MENU ADMIN ******");
				System.out.println("***********************");
				System.out.println();
				System.out.println("1. Gestione Utenti");
				System.out.println("2. Gestione Mezzi");
				System.out.println("3. Gestione Tratte");
				System.out.println("4. Gestione Rivenditori Autorizzati e Distributori Automatici");
				System.out.println("5. Report");
				System.out.println("6. Gestione Autisti");
				System.out.println("0. Esci");
				System.out.println();
				System.out.print("Scelta: ");
				int scelta = scanner.nextInt();
				switch (scelta) {
					case 0:
						ClearTerminal.clearConsole();
						Main.main(null);
						return;
					case 1:
						ClearTerminal.clearConsole();
						MenuGestioneUtenti.menuUtenti();
						break;
					case 2:
						ClearTerminal.clearConsole();
						MenuAdminGestioneMezzi.main(null);
						break;
					case 3:
						ClearTerminal.clearConsole();
						MenuAdminGestioneTratte.main(null);
						break;
					case 4:
						ClearTerminal.clearConsole();
						MenuRivenditore.main(null);
						break;
					case 5:
						ClearTerminal.clearConsole();
						MenuAdminVisualizzaTutto.main(null);
						break;
					case 6:
						ClearTerminal.clearConsole();
						MenuAutisti.menuAutisti();
						break;
					default:
						System.err.println("Scelta non valida. Riprova.");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ClearTerminal.clearConsole();
				}
			} catch (InputMismatchException e) {
				System.err.println("Errore: Inserisci un numero valido.");
				scanner.nextLine();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				ClearTerminal.clearConsole();
			} catch (Exception e) {
				System.err.println("Errore: " + e.getMessage());
				e.printStackTrace();
				System.out.println("Ritorno al menu principale...");
				scanner.nextLine();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				ClearTerminal.clearConsole();
			}
		}
	}
}
