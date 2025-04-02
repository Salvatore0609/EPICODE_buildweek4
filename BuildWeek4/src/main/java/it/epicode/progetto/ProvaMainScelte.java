package it.epicode.progetto;
import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.mezzi.Autobus;
import it.epicode.progetto.mezzi.Mezzo;
import it.epicode.progetto.mezzi.Tram;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ProvaMainScelte {
    public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
    EntityManager em = emf.createEntityManager();
    MezzoDAO mezzoDAO = new MezzoDAO(em);
    TrattaDAO trattaDAO = new TrattaDAO(em);

    em.getTransaction().begin();


    //Inizio costruzione di MEZZI/TRATTE lato ADMIN con SCANNER e SOUTS

    Scanner scanner = new Scanner(System.in);
    int sceltaPresa;
    do {
        System.out.println("\nBenvenuti nel parco mezzi del Team 6! Qui hai la possibilità di creare dei nuovi veicoli, tratte e percorsi personalizzati.");
        System.out.println("Potra anche assegnarli ai mezzi destinati! Cominciamo subito allora!");
        System.out.println("Scegli il tipo di operazione da eseguire: ");
        System.out.println("1. Crea un mezzo");
        System.out.println("2. Crea una tratta");
        System.out.println("3. Assegna una tratta a un mezzo");
        System.out.println("4. Ripara un mezzo");
        System.out.println("5. Modifica una tratta");
        System.out.println("6. Elimina un mezzo");
        System.out.println("7. Elimina una tratta");
        System.out.println("8. Visualizza tutti i mezzi");
        System.out.println("9. Visualizza tutte le tratte");
        System.out.println("10. Visualizza tutti i biglietti vidimati nel corso del tempo");
        System.out.println("0. Abbandona il servizio");
        System.out.println("11. Modifica un mezzo");
        System.out.println("12. Visualizza tutti i mezzi con dei posti liberi");


        sceltaPresa = scanner.nextInt();

        switch (sceltaPresa) {
            case 1:
                System.out.println("\nHai scelto di creare un mezzo. Inserisci i dettagli:");
                System.out.println("Che tipo di mezzo vuoi creare?");
                System.out.println("1. Autobus");
                System.out.println("2. Tram");
                Integer sceltaMezzo = scanner.nextInt();
                if (sceltaMezzo == 1) {
                    System.out.println("Hai scelto di creare un autobus. Inserisci i dettagli:");
                    System.out.println("Quante persone ci sono a bordo?");
                    Integer personeAboard = scanner.nextInt();
                    Mezzo autobus = new Autobus(null, null, Stato.FERMO, personeAboard, 1, 20);
                    mezzoDAO.insert(autobus);
                    System.out.println("Hai creato correttamente il tuo autobus!");
                } else if (sceltaMezzo == 2) {
                    System.out.println("Hai scelto di creare un tram. Inserisci i dettagli:");
                    System.out.println("Quante persone ci sono a bordo?");
                    Integer personeAboardTram = scanner.nextInt();
                    Mezzo tram = new Tram(null, null, Stato.FERMO, personeAboardTram, 1, 50);
                    mezzoDAO.insert(tram);
                    System.out.println("Hai creato correttamente il tuo tram!");
                } else {
                    System.out.println("Scelta non valida.");
                }
                break;
            case 2:
                System.out.println("Hai scelto di creare una tratta. Inserisci i dettagli:");
                System.out.println("Inserisci la zona di partenza della tratta:");
                String partenza = scanner.next();
                System.out.println("Inserisci il capolinea della tratta:");
                String capolinea = scanner.next();
                Tratta tratta1 = new Tratta(null, partenza, capolinea, LocalDateTime.now(), LocalDateTime.now(), 1, 1, null);
                trattaDAO.insert(tratta1);
                System.out.println("Hai creato correttamente la tua tratta!");
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
                System.out.println("Hai scelto di riparare un mezzo.");
                System.out.println("Quale mezzo vuoi riparare?");
                List<Mezzo> tuttiIMezziiRiparare = mezzoDAO.findAll();
                tuttiIMezziiRiparare.forEach(System.out::println);
                Long idMezzoModifica = scanner.nextLong();
                Mezzo mezzoRipara = mezzoDAO.findById(idMezzoModifica);
                mezzoRipara.setStatoEnum(Stato.IN_MANUTENZIONE);
                mezzoDAO.update(mezzoRipara);
                System.out.println("Hai riparato correttamente il mezzo! Buon lavoro!");
                break;
            case 5:
                System.out.println("Hai scelto di modificare una tratta.");
                System.out.println("Quale tratta vuoi modificare?");
                System.out.println(trattaDAO.findAll());
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
            case 6:
                System.out.println("Hai scelto di eliminare un mezzo.");
                System.out.println("Quale mezzo vuoi eliminare?");
                System.out.println(mezzoDAO.findAll());
                Long idMezzoElimina = scanner.nextLong();
                mezzoDAO.delete(idMezzoElimina);
                System.out.println("Hai eliminato correttamente il mezzo!");
                break;
            case 7:
                System.out.println("Hai scelto di eliminare una tratta.");
                System.out.println("Quale tratta vuoi eliminare?");
                System.out.println(trattaDAO.findAll());
                Long idTrattaElimina = scanner.nextLong();
                trattaDAO.delete(idTrattaElimina);
                System.out.println("Hai eliminato correttamente la tratta!");
                break;
            case 8:
                System.out.println("Hai scelto di visualizzare tutti i mezzi.");
                List<Mezzo> tuttiIMezzi = mezzoDAO.findAll();
                for (Mezzo cacca : tuttiIMezzi) {
                    System.out.println(cacca);
                }
                break;
            case 9:
                System.out.println("Hai scelto di visualizzare tutte le tratte.");
                List<Tratta> tutteLeTratte = trattaDAO.findAll();
                for (Tratta cacca : tutteLeTratte) {
                    System.out.println(cacca);
                }
                break;
            case 10:
                System.out.println("Hai scelto di visualizzare tutti i biglietti vidimati nel corso del tempo.");
                mezzoDAO.findAllBigliettiVidimati();
                break;
            case 11:
                System.out.println("Hai scelto di modificare un mezzo.");
                System.out.println("Quale mezzo vuoi modificare?");
                List<Mezzo> tuttiIMezziDaModificare = mezzoDAO.findAll();
                tuttiIMezziDaModificare.forEach(System.out::println);
                Long idMezzoDaModificare = scanner.nextLong();
                Mezzo mezzoDaModificare = mezzoDAO.findById(idMezzoDaModificare);
                System.out.println("Cosa vuoi modificare?");
                System.out.println("1. Stato");
                System.out.println("2. Tratta");
                System.out.println("3. Capienza");
                System.out.println("4. Persone a bordo");
                Integer sceltaModificaMezzo = scanner.nextInt();
                if (sceltaModificaMezzo == 1) {
                    System.out.println("Inserisci il nuovo stato:");
                    System.out.println("1. Fermo");
                    System.out.println("2. In servizio");
                    System.out.println("3. In manutenzione");
                    Integer sceltaStato = scanner.nextInt();
                    if (sceltaStato == 1) {
                        mezzoDaModificare.setStatoEnum(Stato.FERMO);
                        mezzoDAO.update(mezzoDaModificare);
                        System.out.println("Hai modificato correttamente lo stato del mezzo!");
                    } else if (sceltaStato == 2) {
                        mezzoDaModificare.setStatoEnum(Stato.IN_SERVIZIO);
                        mezzoDAO.update(mezzoDaModificare);
                        System.out.println("Hai modificato correttamente lo stato del mezzo!");
                    } else if (sceltaStato == 3) {
                        mezzoDaModificare.setStatoEnum(Stato.IN_MANUTENZIONE);
                        mezzoDAO.update(mezzoDaModificare);
                        System.out.println("Hai modificato correttamente lo stato del mezzo!");
                    } else {
                        System.out.println("Scelta non valida.");
                    }
                } else if (sceltaModificaMezzo == 2) {
                    System.out.println("Inserisci la nuova tratta:");
                    List<Tratta> tutteLeTratteDaModificare = trattaDAO.findAll();
                    tutteLeTratteDaModificare.forEach(System.out::println);
                    Long idTrattaDaModificare = scanner.nextLong();
                    Tratta trattaDaModificare = trattaDAO.findById(idTrattaDaModificare);
                    mezzoDaModificare.setTratta(trattaDaModificare);
                    mezzoDaModificare.setStatoEnum(Stato.IN_SERVIZIO);
                    mezzoDAO.update(mezzoDaModificare);
                    System.out.println("Hai modificato correttamente la tratta del mezzo!");
                } else if (sceltaModificaMezzo == 3) {
                    System.out.println("Inserisci la nuova capienza:");
                    Integer nuovaCapienza = scanner.nextInt();
                    mezzoDaModificare.setCapienza(nuovaCapienza);
                    mezzoDAO.update(mezzoDaModificare);
                    System.out.println("Hai modificato correttamente la capienza del mezzo!");
                } else if (sceltaModificaMezzo == 4) {
                    System.out.println("Inserisci il nuovo numero di persone a bordo:");
                    Integer nuovaPersoneAboard = scanner.nextInt();
                    mezzoDaModificare.setNumeroBigliettiVidimati(nuovaPersoneAboard);
                    mezzoDAO.update(mezzoDaModificare);
                    System.out.println("Hai modificato correttamente il numero di persone a bordo del mezzo!");
                } else {
                    System.out.println("Scelta non valida.");
                }
            case 12:
                System.out.println("Hai scelto di visualizzare tutti i mezzi con posti liberi.");
                mezzoDAO.findAllPostiLiberi();
                break;
            case 0:
                System.out.println("Arrivederci!");
                break;
            default:
                System.out.println("Scelta non valida.");
                break;

        }
    } while (sceltaPresa != 0);

    scanner.close();




        em.getTransaction().commit();
        em.close();
        emf.close();
}
}
