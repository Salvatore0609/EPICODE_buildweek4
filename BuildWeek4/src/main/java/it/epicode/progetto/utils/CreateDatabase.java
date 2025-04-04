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

        Utente utente1 = new Utente("user1", "Mario", "Rossi", "password1", Ruolo.USER, true);
        utentiDao.insert(utente1);
        Utente utente2 = new Utente("user2", "Giulia", "Bianchi", "password2", Ruolo.USER, true);
        utentiDao.insert(utente2);
        Utente utente3 = new Utente("user3", "Luca", "Verdi", "password3", Ruolo.USER, true);
        utentiDao.insert(utente3);
        Utente utente4 = new Utente("user4", "Anna", "Neri", "password4", Ruolo.USER, true);
        utentiDao.insert(utente4);
        Utente utente5 = new Utente("user5", "Giovanni", "Gialli", "password5", Ruolo.USER, true);
        utentiDao.insert(utente5);
        Utente utente6 = new Utente("user6", "Sara", "Blu", "password6", Ruolo.USER, true);
        utentiDao.insert(utente6);
        Utente utente7 = new Utente("user7", "Marco", "Arancioni", "password7", Ruolo.USER, true);
        utentiDao.insert(utente7);
        Utente utente8 = new Utente("user8", "Elena", "Viola", "password8", Ruolo.USER, true);
        utentiDao.insert(utente8);
        Utente utente9 = new Utente("user9", "Roberto", "Marroni", "password9", Ruolo.USER, true);
        utentiDao.insert(utente9);
        Utente utente10 = new Utente("user10", "Laura", "Rosso", "password10", Ruolo.USER, true);
        utentiDao.insert(utente10);
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
        Utente abbonato = new Utente("userAbbonato0", "Salvatore", "Desole", "password1", Ruolo.USER, true);
        Utente abbonato1 = new Utente("userAbbonato1", "Giovanni", "Bianchi", "password2", Ruolo.USER, true);
        Utente abbonato2 = new Utente("userAbbonato2", "Maria", "Verdi", "password3", Ruolo.USER, true);
        Utente abbonato3 = new Utente("userAbbonato3", "Luigi", "Rossi", "password4", Ruolo.USER, true);
        Utente abbonato4 = new Utente("userAbbonato4", "Anna", "Neri", "password5", Ruolo.USER, false);
        Utente abbonato5 = new Utente("userAbbonato5", "Paolo", "Gialli", "password6", Ruolo.USER, true);
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
        Mezzo autobus = new Autobus(null, null, 0, 0, 20, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo autobus1 = new Autobus(null, null, 0, 0, 20, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo autobus2 = new Autobus(null, null, 0, 0, 20, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo autobus3 = new Autobus(null, null, 0, 0, 20, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo autobus4 = new Autobus(null, null, 0, 0, 20, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo tram = new Tram(null, null, 0, 0, 50, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo tram1 = new Tram(null, null, 0, 0, 50, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo tram2 = new Tram(null, null, 0, 0, 50, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo tram3 = new Tram(null, null, 0, 0, 50, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Mezzo tram4 = new Tram(null, null, 0, 0, 50, Stato.FERMO, LocalDateTime.now(), LocalDateTime.now().plusDays(7));


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
        Tratta tratta1 = new Tratta(null, "Milano", "Roma", LocalDateTime.now().plusMinutes(200), LocalDateTime.now().plusMinutes(250), null, null);
        Tratta tratta2 = new Tratta(null, "Sassari", "Cagliari", LocalDateTime.now().plusMinutes(400), LocalDateTime.now().plusMinutes(300), null, null);
        Tratta tratta3 = new Tratta(null, "Roma", "Milano", LocalDateTime.now().plusMinutes(300), LocalDateTime.now().plusMinutes(300), null, null);
        Tratta tratta4 = new Tratta(null, "Cagliari", "Sassari", LocalDateTime.now().plusMinutes(220), LocalDateTime.now().plusMinutes(280), null, null);
        Tratta tratta5 = new Tratta(null, "Torino", "Milano", LocalDateTime.now().plusMinutes(420), LocalDateTime.now().plusMinutes(380), null, null);
        Tratta tratta6 = new Tratta(null, "Milano", "Torino", LocalDateTime.now().plusMinutes(120), LocalDateTime.now().plusMinutes(120), null, null);
        Tratta tratta7 = new Tratta(null, "Napoli", "Roma", LocalDateTime.now().plusMinutes(550), LocalDateTime.now().plusMinutes(480), null, null);
        Tratta tratta8 = new Tratta(null, "Roma", "Napoli", LocalDateTime.now().plusMinutes(200), LocalDateTime.now().plusMinutes(250), null, null);
        Tratta tratta9 = new Tratta(null, "Firenze", "Milano", LocalDateTime.now().plusMinutes(180), LocalDateTime.now().plusMinutes(250), null, null);
        Tratta tratta10 = new Tratta(null, "Milano", "Firenze", LocalDateTime.now().plusMinutes(250), LocalDateTime.now().plusMinutes(250), null, null);
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
        autobus.setTratta(tratta1);
        autobus1.setTratta(tratta2);
        autobus2.setTratta(tratta3);
        autobus3.setTratta(tratta4);
        autobus4.setTratta(tratta5);
        tram.setTratta(tratta6);
        tram1.setTratta(tratta7);
        tram2.setTratta(tratta8);
        tram3.setTratta(tratta9);
        tram4.setTratta(tratta10);


        autobus.setStatoEnum(Stato.IN_SERVIZIO);
        autobus1.setStatoEnum(Stato.IN_SERVIZIO);
        autobus2.setStatoEnum(Stato.IN_SERVIZIO);
        autobus3.setStatoEnum(Stato.IN_SERVIZIO);
        autobus4.setStatoEnum(Stato.IN_SERVIZIO);
        tram.setStatoEnum(Stato.IN_SERVIZIO);
        tram1.setStatoEnum(Stato.IN_SERVIZIO);
        tram2.setStatoEnum(Stato.IN_SERVIZIO);
        tram3.setStatoEnum(Stato.IN_SERVIZIO);
        tram4.setStatoEnum(Stato.IN_SERVIZIO);


        mezzoDao.update(autobus);
        mezzoDao.update(autobus1);
        mezzoDao.update(autobus2);
        mezzoDao.update(autobus3);
        mezzoDao.update(autobus4);
        mezzoDao.update(tram);
        mezzoDao.update(tram1);
        mezzoDao.update(tram2);
        mezzoDao.update(tram3);
        System.out.println("Tratte aggiunte ai mezzi");

        em.getTransaction().begin();
        //CREA BIGLIETTI
        Biglietto b = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r)
                .vidimato(true)
                .dataVidimato(LocalDate.of(2023, 12, 15))
                .utente(utente1)
                .mezzo(autobus1)
                .build();
        Biglietto b1 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r2)
                .vidimato(true)
                .dataVidimato(LocalDate.of(2024, 1, 12))
                .utente(utente2)
                .mezzo(autobus2)
                .build();
        Biglietto b2 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r3)
                .vidimato(true)
                .dataVidimato(LocalDate.of(2024, 6, 8))
                .utente(utente3)
                .mezzo(autobus3)
                .build();
        Biglietto b3 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r4)
                .vidimato(false)
                .utente(utente4)
                .mezzo(autobus4)
                .build();
        Biglietto b4 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r5)
                .vidimato(false)
                .utente(utente5)
                .mezzo(tram1)
                .build();
        Biglietto b5 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r)
                .vidimato(false)
                .utente(utente6)
                .mezzo(tram2)
                .build();
        Biglietto b6 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r2)
                .vidimato(false)
                .utente(utente7)
                .mezzo(tram3)
                .build();
        Biglietto b7 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r3)
                .vidimato(false)
                .utente(utente8)
                .mezzo(tram)
                .build();
        Biglietto b8 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r4)
                .vidimato(false)
                .utente(utente9)
                .mezzo(tram1)
                .build();
        Biglietto b9 = Biglietto.builder()
                .dataDiEmissione(LocalDate.now())
                .rivenditore(r5)
                .vidimato(false)
                .utente(utente10)
                .mezzo(tram2)
                .build();
        elementoBiglietteriaDAO.insert(b);
        elementoBiglietteriaDAO.insert(b1);
        elementoBiglietteriaDAO.insert(b2);
        elementoBiglietteriaDAO.insert(b3);
        elementoBiglietteriaDAO.insert(b4);
        elementoBiglietteriaDAO.insert(b5);
        elementoBiglietteriaDAO.insert(b6);
        elementoBiglietteriaDAO.insert(b7);
        elementoBiglietteriaDAO.insert(b8);
        elementoBiglietteriaDAO.insert(b9);
        em.getTransaction().commit();
        rivenditoreDAO.aggiornaBigliettiAbbonamentiEmessi();
        System.out.println("Biglietti creati.");

        autobus.setStatoEnum(Stato.IN_SERVIZIO);
        autobus1.setStatoEnum(Stato.IN_SERVIZIO);
        autobus2.setStatoEnum(Stato.IN_SERVIZIO);
        autobus3.setStatoEnum(Stato.IN_SERVIZIO);
        autobus4.setStatoEnum(Stato.IN_SERVIZIO);
        tram.setStatoEnum(Stato.IN_SERVIZIO);
        tram1.setStatoEnum(Stato.IN_SERVIZIO);
        tram2.setStatoEnum(Stato.IN_SERVIZIO);
        tram3.setStatoEnum(Stato.IN_SERVIZIO);
        tram4.setStatoEnum(Stato.IN_SERVIZIO);

        em.close();
        emf.close();

        System.out.println("Database inizializzato con successo!");
    }
}