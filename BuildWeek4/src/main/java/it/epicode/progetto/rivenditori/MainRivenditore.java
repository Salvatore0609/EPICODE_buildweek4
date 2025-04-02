package it.epicode.progetto.rivenditori;

import it.epicode.progetto.Main;
import it.epicode.progetto.dao.RivenditoreDAO;
import it.epicode.progetto.rivenditori.distributoriautomatici.DistributoriAutomatici;
import it.epicode.progetto.rivenditori.distributoriautomatici.Stato;
import it.epicode.progetto.rivenditori.rivenditoriautorizzati.RivenditoriAutorizzati;
import it.epicode.progetto.utility.ClearTerminal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class MainRivenditore {
    public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
    EntityManager em = emf.createEntityManager();

    RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);

        em.getTransaction().begin();

    Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto nel sistema di gestione dei rivenditori!");
        System.out.println("Seleziona una delle seguenti opzioni:");
        System.out.println("1. Inserisci un nuovo rivenditore");
        System.out.println("2. Elimina un rivenditore");
        System.out.println("3. Modifica un rivenditore");
        System.out.println("4. Visualizza tutti i rivenditori");
        System.out.println("5. Visualizza i biglietti e abbonamenti emessi da un rivenditore in un periodo di tempo");
        System.out.println("0. Esci");

        int scelta = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                ClearTerminal.clearConsole();
                System.out.println("Inserisci il nome del rivenditore:");
                String nomeRivenditore = scanner.nextLine();
                System.out.println("Inserisci il tipo di rivenditore (1 per rivenditori autorizzati, 2 per distributori automatici):");
                int tipoRivenditore = scanner.nextInt();
                scanner.nextLine();
                if (tipoRivenditore == 1) {
                    RivenditoriAutorizzati r = RivenditoriAutorizzati.builder()
                            .nome(nomeRivenditore)
                            .build();
                    rivenditoreDAO.insert(r);
                    System.out.println("Rivenditore inserito con successo!");
                } else if (tipoRivenditore == 2) {
                    System.out.println("Inserisci lo stato del distributore automatico (1 per attivo, 2 per fuori servizio):");
                    int statoDistributore = scanner.nextInt();
                    scanner.nextLine();
                    Stato stato = statoDistributore == 1 ? Stato.ATTIVO : Stato.FUORI_SERVIZIO;
                    DistributoriAutomatici d = DistributoriAutomatici.builder()
                            .nome(nomeRivenditore)
                            .stato(stato)
                            .build();
                    rivenditoreDAO.insert(d);
                    System.out.println("Distributore automatico inserito con successo!");
                }
                break;
            case 2:
                ClearTerminal.clearConsole();
                System.out.println("Inserisci l'ID del rivenditore da eliminare:");
                Long idRivenditore = scanner.nextLong();
                scanner.nextLine();
                rivenditoreDAO.delete(idRivenditore);
                System.out.println("Il rivenditore è stato eliminato con successo");
                break;
            case 3:
                ClearTerminal.clearConsole();
                System.out.println("Inserisci l'ID del rivenditore da modificare:");
                Long idRivenditore2 = scanner.nextLong();
                scanner.nextLine();
                Rivenditore rivenditore = rivenditoreDAO.findById(idRivenditore2);
                if(rivenditore != null) {
                    if(rivenditore instanceof RivenditoriAutorizzati) {
                        System.out.println("Inserisci il nuovo nome del rivenditore:");
                        String nuovoNome = scanner.nextLine();
                        rivenditore.setNome(nuovoNome);

                        rivenditoreDAO.update(rivenditore);
                        System.out.println("Rivenditore aggiornato con successo.");
                    } else if(rivenditore instanceof DistributoriAutomatici) {
                        System.out.println("Inserisci il nuovo nome del distributore:");
                        String nuovoNome = scanner.nextLine();
                        rivenditore.setNome(nuovoNome);
                        System.out.println("Inserisci il nuovo stato del distributore:");
                        Stato nuovoStato = Stato.valueOf(scanner.nextLine());
                        ((DistributoriAutomatici) rivenditore).setStato(nuovoStato);

                        rivenditoreDAO.update(rivenditore);
                        System.out.println("Distributore aggiornato con successo.");
                    }
                } else {
                    System.out.println("Rivenditore non trovato con l'ID specificato");
                }
                break;
            case 4:
                ClearTerminal.clearConsole();
                System.out.println("Elenco dei rivenditori:");
                for (Rivenditore r : rivenditoreDAO.findAll()) {

                    if(r instanceof RivenditoriAutorizzati) {
                        System.out.println("Tipo: Rivenditori Autorizzati" + "Nome: " + r.getNome() + " - Tipo: Rivenditori Autorizzati");
                    } else if(r instanceof DistributoriAutomatici) {
                        System.out.println("Nome: " + r.getNome() + "Stato: " + ((DistributoriAutomatici) r).getStato() + " - Tipo: Distributori Automatici" );
                    }
                }
                break;
            case 5:
                ClearTerminal.clearConsole();
                System.out.println("Inserisci il nome del rivenditore:");
                String nomeRivenditore2 = scanner.nextLine();
                System.out.println("Inserisci la data di inizio:");
                LocalDate dataInizio = LocalDate.parse(scanner.nextLine());
                System.out.println("Inserisci la data di fine:");
                LocalDate dataFine = LocalDate.parse(scanner.nextLine());
                rivenditoreDAO.ottieniBigliettiAbbonamentiEmessi(nomeRivenditore2, dataInizio, dataFine);
                break;
            case 0:
                ClearTerminal.clearConsole();
                Main.main(null);
                break;
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
