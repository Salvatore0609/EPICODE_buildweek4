package it.epicode.progetto.utils;

import it.epicode.progetto.dao.UtentiDao;
import it.epicode.progetto.entities.Utente;
import it.epicode.progetto.enums.Ruolo;
import it.epicode.progetto.menu.MenuAdmin;
import it.epicode.progetto.menu.MenuUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;

import java.util.Scanner;

@NoArgsConstructor
public class Login {
    public void login() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        Scanner scanner = new Scanner(System.in);
            emf = jakarta.persistence.Persistence.createEntityManagerFactory("epicode");
            em = emf.createEntityManager();

            System.out.println("Username: ");
            String username = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();

            UtentiDao uDao = new UtentiDao(em);
            Utente utente = uDao.findByUsername(username);
            Utente utentepwd = uDao.findByUsernameAndPassword(username, password);

            if (utente == null) {
                System.out.println("Utente non trovato. Crea un nuovo utente!");
            } else if (utentepwd == null) {
                System.out.println("Password errata.");
            } else {
                ClearTerminal.clearConsole();
                System.out.println("Login effettuato con successo");
                System.out.println();
                System.out.println("******************************");
                System.out.println();
                System.out.println("Benvenuto " + utente.getNome() + " " + utente.getCognome() + " (" + utente.getRuolo() + ")");
                System.out.println();
                //se il ruolo è ADMIN visualizzo il menu di gestione
                if (utente.getRuolo() == Ruolo.ADMIN) {
                    MenuAdmin.menuAdmin();
                } else {
                    MenuUtente.menuUtente(utente.getIdUtente());
                }
            }
    }
}
