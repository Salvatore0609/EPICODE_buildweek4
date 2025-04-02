package it.epicode.progetto.menu;

import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.entities.Mezzo;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import static it.epicode.progetto.utils.Input.scanner;

public class MenuAdminGestioneTratte {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        //DAO
        TrattaDAO trattaDAO = new TrattaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);

        int sceltaPresa;

        do {
            System.out.println("Benvenuto nel menu della gestione delle tratte");
            System.out.println("1. Crea una tratta");
            System.out.println("2. Modifica una tratta");
            System.out.println("3. Assegna una tratta a un mezzo");
            System.out.println("4. Elimina una tratta");
            System.out.println("0. Esci dal menu della gestione delle tratte");

            sceltaPresa = scanner.nextInt();

            switch(sceltaPresa) {
                case 1:
                    System.out.println("Hai scelto di creare una tratta. Inserisci i dettagli:");
                    System.out.println("Inserisci la zona di partenza della tratta:");
                    String partenza = scanner.next();
                    System.out.println("Inserisci il capolinea della tratta:");
                    String capolinea = scanner.next();
                    System.out.println("Inserisci il tempo previsto di percorrenza della tratta:");
                    Integer tempoPrevistoPercorrenza = scanner.nextInt();
                    System.out.println("Inserisci il tempo effettivo di percorrenza della tratta:");
                    Integer tempoEffettivoPercorrenza = scanner.nextInt();
                    Tratta tratta1 = new Tratta(null, partenza, capolinea, LocalDateTime.now().plusMinutes(tempoPrevistoPercorrenza), LocalDateTime.now().plusMinutes(tempoEffettivoPercorrenza), null, null);
                    trattaDAO.insert(tratta1);
                    System.out.println("Hai creato correttamente la tua tratta!");
                    break;
                case 2:
                    System.out.println("Hai scelto di modificare una tratta.");
                    System.out.println("Quale tratta vuoi modificare?");
                    List<Tratta> tutteLeTratte = trattaDAO.findAll();
                    tutteLeTratte.forEach(System.out::println);
                    Long idTrattaModifica = scanner.nextLong();
                    Tratta trattaModifica = trattaDAO.findById(idTrattaModifica);
                    System.out.println("Cosa vuoi modificare?");
                    System.out.println("1. Partenza");
                    System.out.println("2. Capolinea");
                    Integer sceltaModifica = scanner.nextInt();
                    if (sceltaModifica == 1) {
                        System.out.println("Inserisci la nuova partenza:");
                        String nuovaPartenza = scanner.next();
                        trattaModifica.setZonaDiPartenza(nuovaPartenza);
                        trattaDAO.update(trattaModifica);
                        System.out.println("Hai modificato correttamente la partenza della tratta!");
                    } else if (sceltaModifica == 2) {
                        System.out.println("Inserisci il nuova capolinea:");
                        String nuovaCapolinea = scanner.next();
                        trattaModifica.setCapolinea(nuovaCapolinea);
                        trattaDAO.update(trattaModifica);
                        System.out.println("Hai modificato correttamente il capolinea della tratta!");
                    } else {
                        System.out.println("Scelta non valida.");
                    }
                    break;
                case 3:
                    System.out.println("Hai scelto di assegnare una tratta a un mezzo.");
                    System.out.println("Quale tratta vuoi assegnare ad un mezzo?");
                    List<Tratta> tutteLeTrattee = trattaDAO.findAll();
                    tutteLeTrattee.forEach(System.out::println);
                    Long idTratta = scanner.nextLong();
                    Tratta tratta = trattaDAO.findById(idTratta);
                    System.out.println("A quale mezzo vuoi assegnare la tratta?");
                    List<Mezzo> tuttiIMezzii = mezzoDAO.findAll();
                    tuttiIMezzii.forEach(System.out::println);
                    Long idMezzo = scanner.nextLong();
                    Mezzo mezzo = mezzoDAO.findById(idMezzo);
                    mezzo.setTratta(tratta);
                    mezzo.setStatoEnum(Stato.IN_SERVIZIO);
                    mezzoDAO.update(mezzo);
                    System.out.println("Hai assegnato correttamente la tratta al mezzo! Buon viaggio!");
                    break;
                case 4:
                    System.out.println("Hai scelto di eliminare una tratta.");
                    System.out.println("Quale tratta vuoi eliminare?");
                    System.out.println(trattaDAO.findAll());
                    Long idTrattaElimina = scanner.nextLong();
                    trattaDAO.delete(idTrattaElimina);
                    System.out.println("Hai eliminato correttamente la tratta!");
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            } }
while (sceltaPresa != 0);


em.close();
emf.close();



            }

        }




