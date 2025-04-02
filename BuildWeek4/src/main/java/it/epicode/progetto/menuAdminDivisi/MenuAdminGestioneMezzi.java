package it.epicode.progetto.menuAdminDivisi;
import static it.epicode.progetto.utility.Input.scanner;
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

import java.time.LocalDate;
import java.util.List;

public class MenuAdminGestioneMezzi {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        //DAO
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);


        int sceltaPresa;
        do {
            System.out.println("Benvenuto nel menu della gestione dei mezzi");
            System.out.println("Scegli cosa vuoi fare:");
            System.out.println("1. Crea un mezzo");
            System.out.println("2. Ripara un mezzo");
            System.out.println("3. Elimina un mezzo");
            System.out.println("4. Modifica un mezzo");
            System.out.println("0. Esci dal menu della gestione dei mezzi");

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
                        System.out.println("Inserisci l'anno di inizio attività");
                        Integer anno = scanner.nextInt();
                        System.out.println("Inserisci il mese di inizio attività");
                        Integer mese = scanner.nextInt();
                        System.out.println("Inserisci il giorno di inizio attività");
                        Integer giorno = scanner.nextInt();
                        //
                        System.out.println("Inserisci l'anno di fine attività");
                        Integer anno1 = scanner.nextInt();
                        System.out.println("Inserisci il mese di fine attività");
                        Integer mese1 = scanner.nextInt();
                        System.out.println("Inserisci il giorno di fine attività");
                        Integer giorno1 = scanner.nextInt();
                        Mezzo autobus = new Autobus(null, null, personeAboard, 1, 20, Stato.FERMO, LocalDate.of(anno, mese, giorno), LocalDate.of(anno1, mese1, giorno1));
                        mezzoDAO.insert(autobus);
                        System.out.println("Hai creato correttamente il tuo autobus!");
                    } else if (sceltaMezzo == 2) {
                        System.out.println("Hai scelto di creare un tram. Inserisci i dettagli:");
                        System.out.println("Quante persone ci sono a bordo?");
                        Integer personeAboardTram = scanner.nextInt();
                        System.out.println("Inserisci l'anno di inizio attività");
                        Integer anno2 = scanner.nextInt();
                        System.out.println("Inserisci il mese di inizio attività");
                        Integer mese2 = scanner.nextInt();
                        System.out.println("Inserisci il giorno di inizio attività");
                        Integer giorno2 = scanner.nextInt();
                        //
                        System.out.println("Inserisci l'anno di fine attività");
                        Integer anno3 = scanner.nextInt();
                        System.out.println("Inserisci il mese di fine attività");
                        Integer mese3 = scanner.nextInt();
                        System.out.println("Inserisci il giorno di fine attività");
                        Integer giorno3 = scanner.nextInt();
                        Mezzo tram = new Tram(null, null, personeAboardTram, 1, 20, Stato.FERMO, LocalDate.of(anno2, mese2, giorno2), LocalDate.of(anno3, mese3, giorno3));
                        mezzoDAO.insert(tram);
                        System.out.println("Hai creato correttamente il tuo tram!");
                    } else {
                        System.out.println("Scelta non valida.");
                    }
                    break;
                case 2:
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
                case 3:
                    System.out.println("Hai scelto di eliminare un mezzo.");
                    System.out.println("Quale mezzo vuoi eliminare?");
                    List<Mezzo> mezziDisponibili = mezzoDAO.findAll();
                    mezziDisponibili.forEach(System.out::println);
                    Long idMezzoElimina = scanner.nextLong();
                    mezzoDAO.delete(idMezzoElimina);
                    System.out.println("Hai eliminato correttamente il mezzo!");
                    break;
                case 4:
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
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            } } while (sceltaPresa != 0);
            em.close();
            emf.close();
        }
}
