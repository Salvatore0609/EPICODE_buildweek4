package it.epicode.progetto.utils;

import it.epicode.progetto.dao.*;
import it.epicode.progetto.entities.*;
import it.epicode.progetto.enums.DurataAbbonamento;
import it.epicode.progetto.enums.Ruolo;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.enums.StatoDistributori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateDatabase {
    public static void main(String[] args) {
        Map<String, Object> overrideProperties = new HashMap<>();
        overrideProperties.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode", overrideProperties);
        EntityManager em = emf.createEntityManager();

        // DAO
        UtentiDao utentiDao = new UtentiDao(em);
        TrattaDAO trattaDao = new TrattaDAO(em);
        MezzoDAO mezzoDao = new MezzoDAO(em);
        ElementoBiglietteriaDAO elementoBiglietteriaDAO = new ElementoBiglietteriaDAO(em);
        TessereDao tesseraDao = new TessereDao(em);
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);

        // CREA ADMIN
        em.getTransaction().begin();
        Utente admin = utentiDao.findByUsername("admin");
        if (admin == null) {
            Utente newAdmin = new Utente("admin", "Admin", "Team6", "admin", Ruolo.ADMIN, true);
            utentiDao.insert(newAdmin);
            System.out.println("Admin creato.");
        } else {
            System.out.println("Admin già presente.");
            return;
        }

        utentiDao.insert(new Utente("user1", "Mario", "Rossi", "password1", Ruolo.USER, false));
        utentiDao.insert(new Utente("user2", "Giulia", "Bianchi", "password2", Ruolo.USER, false));
        utentiDao.insert(new Utente("user3", "Luca", "Verdi", "password3", Ruolo.USER, false));
        utentiDao.insert(new Utente("user4", "Anna", "Neri", "password4", Ruolo.USER, false));
        utentiDao.insert(new Utente("user5", "Giovanni", "Gialli", "password5", Ruolo.USER, false));
        utentiDao.insert(new Utente("user6", "Sara", "Azzurri", "password6", Ruolo.USER, false));
        utentiDao.insert(new Utente("user7", "Marco", "Arancioni", "password7", Ruolo.USER, false));
        utentiDao.insert(new Utente("user8", "Elena", "Viola", "password8", Ruolo.USER, false));
        utentiDao.insert(new Utente("user9", "Paolo", "Blu", "password9", Ruolo.USER, false));
        utentiDao.insert(new Utente("user10", "Laura", "Rosa", "password10", Ruolo.USER, false));
        System.out.println("Utenti normali creati.");

        em.getTransaction().commit();

        //

        em.getTransaction().begin();
        // CREA RIVENDITORE
        RivenditoriAutorizzati r = RivenditoriAutorizzati.builder()
                .nome("Tabaccheria piazza Roma")
                .build();
        RivenditoriAutorizzati r2 = RivenditoriAutorizzati.builder()
                .nome("Tabaccheria piazza Milano")
                .build();
        RivenditoriAutorizzati r3 = RivenditoriAutorizzati.builder()
                .nome("Tabaccheria piazza Torino")
                .build();
        RivenditoriAutorizzati r4 = RivenditoriAutorizzati.builder()
                .nome("Tabaccheria piazza Firenze")
                .build();
        RivenditoriAutorizzati r5 = RivenditoriAutorizzati.builder()
                .nome("Tabaccheria piazza Bologna")
                .build();
        //
        DistributoriAutomatici d = DistributoriAutomatici.builder()
                .nome("Distributore stazione centrale")
                .stato(StatoDistributori.ATTIVO)
                .build();
        DistributoriAutomatici d2 = DistributoriAutomatici.builder()
                .nome("Distributore stazione nord")
                .stato(StatoDistributori.FUORI_SERVIZIO)
                .build();
        DistributoriAutomatici d3 = DistributoriAutomatici.builder()
                .nome("Distributore stazione sud")
                .stato(StatoDistributori.ATTIVO)
                .build();
        DistributoriAutomatici d4 = DistributoriAutomatici.builder()
                .nome("Distributore stazione est")
                .stato(StatoDistributori.FUORI_SERVIZIO)
                .build();
        DistributoriAutomatici d5 = DistributoriAutomatici.builder()
                .nome("Distributore stazione ovest")
                .stato(StatoDistributori.FUORI_SERVIZIO)
                .build();
        rivenditoreDAO.insert(r);
        rivenditoreDAO.insert(r2);
        rivenditoreDAO.insert(r3);
        rivenditoreDAO.insert(r4);
        rivenditoreDAO.insert(r5);
        rivenditoreDAO.insert(d);
        rivenditoreDAO.insert(d2);
        rivenditoreDAO.insert(d3);
        rivenditoreDAO.insert(d4);
        rivenditoreDAO.insert(d5);
        //
        // CREA UTENTI CON ABBONAMENTO (se non ci sono)
        //utente senza abbonamento
        Utente abbonato = new Utente("userAbbonato0", "Salvatore", "Desole", "password1", Ruolo.USER, false);
        Utente abbonato1 = new Utente("userAbbonato1", "Giovanni", "Bianchi", "password2", Ruolo.USER, false);
        Utente abbonato2 = new Utente("userAbbonato2", "Maria", "Verdi", "password3", Ruolo.USER, false);
        Utente abbonato3 = new Utente("userAbbonato3", "Luigi", "Rossi", "password4", Ruolo.USER, false);
        Utente abbonato4 = new Utente("userAbbonato4", "Anna", "Neri", "password5", Ruolo.USER, false);
        Utente abbonato5 = new Utente("userAbbonato5", "Paolo", "Gialli", "password6", Ruolo.USER, false);
        utentiDao.insert(abbonato);
        utentiDao.insert(abbonato1);
        utentiDao.insert(abbonato2);
        utentiDao.insert(abbonato3);
        utentiDao.insert(abbonato4);
        utentiDao.insert(abbonato5);
        //
        LocalDate dataEmissione = LocalDate.now();
        Tessera tessera = new Tessera(abbonato, dataEmissione);
        Tessera tessera1 = new Tessera(abbonato1, dataEmissione);
        Tessera tessera2 = new Tessera(abbonato2, dataEmissione);
        Tessera tessera3 = new Tessera(abbonato3, dataEmissione);
        Tessera tessera4 = new Tessera(abbonato4, dataEmissione);
        Tessera tessera5 = new Tessera(abbonato5, dataEmissione);
        tesseraDao.insert(tessera);
        tesseraDao.insert(tessera1);
        tesseraDao.insert(tessera2);
        tesseraDao.insert(tessera3);
        tesseraDao.insert(tessera4);
        tesseraDao.insert(tessera5);
        //
        //l'abbonamento per l'utente
        Abbonamento eb = Abbonamento.builder()
                .dataDiEmissione(LocalDate.now())
                .durataAbbonamento(DurataAbbonamento.SETTIMANALE)
                .scadenzaAbbonamento(LocalDate.now().plusWeeks(1))
                .tessera(tessera)
                .rivenditore(r)
                .build();
        Abbonamento eb1 = Abbonamento.builder()
                .dataDiEmissione(LocalDate.now())
                .durataAbbonamento(DurataAbbonamento.SETTIMANALE)
                .scadenzaAbbonamento(LocalDate.now().plusWeeks(1))
                .tessera(tessera1)
                .rivenditore(r2)
                .build();
        Abbonamento eb2 = Abbonamento.builder()
                .dataDiEmissione(LocalDate.now())
                .durataAbbonamento(DurataAbbonamento.MENSILE)
                .scadenzaAbbonamento(LocalDate.now().plusMonths(1))
                .tessera(tessera2)
                .rivenditore(r3)
                .build();
        Abbonamento eb3 = Abbonamento.builder()
                .dataDiEmissione(LocalDate.now())
                .durataAbbonamento(DurataAbbonamento.SETTIMANALE)
                .scadenzaAbbonamento(LocalDate.now().plusWeeks(1))
                .tessera(tessera3)
                .rivenditore(r4)
                .build();
        Abbonamento eb4 = Abbonamento.builder()
                .dataDiEmissione(LocalDate.now())
                .durataAbbonamento(DurataAbbonamento.MENSILE)
                .scadenzaAbbonamento(LocalDate.now().plusMonths(1))
                .tessera(tessera4)
                .rivenditore(r5)
                .build();
        Abbonamento eb5 = Abbonamento.builder()
                .dataDiEmissione(LocalDate.now())
                .durataAbbonamento(DurataAbbonamento.MENSILE)
                .scadenzaAbbonamento(LocalDate.now().plusMonths(1))
                .tessera(tessera5)
                .rivenditore(r)
                .build();
        elementoBiglietteriaDAO.insert(eb);
        elementoBiglietteriaDAO.insert(eb1);
        elementoBiglietteriaDAO.insert(eb2);
        elementoBiglietteriaDAO.insert(eb3);
        elementoBiglietteriaDAO.insert(eb4);
        elementoBiglietteriaDAO.insert(eb5);

        System.out.println("Rivenditori creato.");
        System.out.println("Utenti con abbonamento mensile creato.");
        em.getTransaction().commit();
        //
        // CREA MEZZI DI TRASPORTO
        Autobus autobus = new Autobus(null, null, 10, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        Autobus autobus1 = new Autobus(null, null, 10, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        Autobus autobus2 = new Autobus(null, null, 10, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        Autobus autobus3 = new Autobus(null, null, 10, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        Autobus autobus4 = new Autobus(null, null, 10, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        Tram tram = new Tram(null, null, 43, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        Tram tram1 = new Tram(null, null, 43, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        Tram tram2 = new Tram(null, null, 43, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        Tram tram3 = new Tram(null, null, 43, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
        mezzoDao.insert(autobus);
        mezzoDao.insert(autobus1);
        mezzoDao.insert(autobus2);
        mezzoDao.insert(autobus3);
        mezzoDao.insert(autobus4);
        mezzoDao.insert(tram);
        mezzoDao.insert(tram1);
        mezzoDao.insert(tram2);
        mezzoDao.insert(tram3);
        System.out.println("Mezzi di trasporto creati.");


        // CREA TRATTE
        Tratta tratta1 = new Tratta(null, "Milano", "Roma", LocalDateTime.now().plusMinutes(200), LocalDateTime.now().plusMinutes(250), 50, null);
        Tratta tratta2 = new Tratta(null, "Sassari", "Cagliari", LocalDateTime.now().plusMinutes(250), LocalDateTime.now().plusMinutes(200), 50, null);
        Tratta tratta3 = new Tratta(null, "Roma", "Milano", LocalDateTime.now().plusMinutes(250), LocalDateTime.now().plusMinutes(200), 50, null);
        Tratta tratta4 = new Tratta(null, "Cagliari", "Sassari", LocalDateTime.now().plusMinutes(200), LocalDateTime.now().plusMinutes(250), 50, null);
        Tratta tratta5 = new Tratta(null, "Torino", "Milano", LocalDateTime.now().plusMinutes(200), LocalDateTime.now().plusMinutes(250), 50, null);
        Tratta tratta6 = new Tratta(null, "Milano", "Torino", LocalDateTime.now().plusMinutes(250), LocalDateTime.now().plusMinutes(200), 50, null);
        Tratta tratta7 = new Tratta(null, "Napoli", "Roma", LocalDateTime.now().plusMinutes(250), LocalDateTime.now().plusMinutes(200), 50, null);
        Tratta tratta8 = new Tratta(null, "Roma", "Napoli", LocalDateTime.now().plusMinutes(200), LocalDateTime.now().plusMinutes(250), 50, null);
        Tratta tratta9 = new Tratta(null, "Firenze", "Milano", LocalDateTime.now().plusMinutes(200), LocalDateTime.now().plusMinutes(250), 50, null);
        Tratta tratta10 = new Tratta(null, "Milano", "Firenze", LocalDateTime.now().plusMinutes(250), LocalDateTime.now().plusMinutes(200), 50, null);
        trattaDao.insert(tratta1);
        trattaDao.insert(tratta2);
        trattaDao.insert(tratta3);
        trattaDao.insert(tratta4);
        trattaDao.insert(tratta5);
        trattaDao.insert(tratta6);
        trattaDao.insert(tratta7);
        trattaDao.insert(tratta8);
        trattaDao.insert(tratta9);
        trattaDao.insert(tratta10);
        System.out.println("Tratte create.");
        // Associa mezzi alle tratte
        List<Mezzo> mezzi = mezzoDao.findAll();

        autobus.setTratta(tratta1);
        autobus1.setTratta(tratta2);
        autobus2.setTratta(tratta3);
        autobus3.setTratta(tratta4);
        autobus4.setTratta(tratta5);
        tram.setTratta(tratta6);
        tram1.setTratta(tratta7);
        tram2.setTratta(tratta8);
        tram3.setTratta(tratta9);

        mezzoDao.update(autobus);
        mezzoDao.update(autobus1);
        mezzoDao.update(autobus2);
        mezzoDao.update(autobus3);
        mezzoDao.update(autobus4);
        mezzoDao.update(tram);
        mezzoDao.update(tram1);
        mezzoDao.update(tram2);
        mezzoDao.update(tram3);

        em.close();
        emf.close();

        System.out.println("Database inizializzato con successo!");
    }
}