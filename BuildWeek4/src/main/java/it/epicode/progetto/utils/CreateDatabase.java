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

        // CREA UTENTI NORMALI
        List<Utente> user = utentiDao.findAll();
        List<Utente> utentiNonAdmin = user.stream()
                .filter(u -> !u.getUsername().equals("admin"))
                .collect(Collectors.toList());
        if (utentiNonAdmin.isEmpty()) {
            utentiDao.insert(new Utente("user1", "Mario", "Rossi", "password1", Ruolo.USER, false));
            utentiDao.insert(new Utente("user2", "Giulia", "Bianchi", "password2", Ruolo.USER, false));
            System.out.println("Utenti normali creati.");
        }
        em.getTransaction().commit();


        // CREA UTENTI CON ABBONAMENTO (se non ci sono)
        em.getTransaction().begin();
        if (utentiDao.findAll().isEmpty()) {
            // CREA RIVENDITORE

            RivenditoriAutorizzati r = RivenditoriAutorizzati.builder()
                    .nome("Tabaccheria piazza Roma")
                    .build();
            DistributoriAutomatici d = DistributoriAutomatici.builder()
                    .nome("Distributore stazione centrale")
                    .stato(StatoDistributori.ATTIVO)
                    .build();
            rivenditoreDAO.insert(r);
            rivenditoreDAO.insert(d);
            System.out.println("Rivenditore creato.");

            //utente senza abbonamento
            Utente abbonato = new Utente("user3", "Mario", "Rossi", "password1", Ruolo.USER, false);

            utentiDao.insert(abbonato);

            LocalDate dataEmissione = LocalDate.now();
            Tessera tessera = new Tessera(abbonato, dataEmissione);
            tesseraDao.insert(tessera);

            //l'abbonamento per l'utente
            LocalDate dataEmissioneAbbonamento = LocalDate.now();
            LocalDate scadenza = LocalDate.now().plusMonths(1);
            Abbonamento eb = Abbonamento.builder()
                    .dataDiEmissione(dataEmissioneAbbonamento)
                    .durataAbbonamento(DurataAbbonamento.MENSILE)
                    .scadenzaAbbonamento(scadenza)
                    .tessera(tessera)
                    .rivenditore(r)
                    .build();

            elementoBiglietteriaDAO.insert(eb);

            System.out.println("Utente con abbonamento mensile creato.");
        }
        em.getTransaction().commit();

        // CREA MEZZI DI TRASPORTO
        if (mezzoDao.findAll().isEmpty()) {
            Autobus autobus = new Autobus(null, null, 10, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));
            Tram tram = new Tram(null, null, 43, 1, 20, Stato.FERMO, LocalDate.of(2025, 04, 03), LocalDate.of(2025, 04, 03));

            mezzoDao.insert(autobus);
            mezzoDao.insert(tram);
            System.out.println("Mezzi di trasporto creati.");
        }

        // CREA TRATTE
        if (trattaDao.findAll().isEmpty()) {
            // Crea le tratte
            Tratta tratta1 = new Tratta(null, "Milano", "Roma", LocalDateTime.now().plusMinutes(200), LocalDateTime.now().plusMinutes(250), 50, null);
            Tratta tratta2 = new Tratta(null, "Sassari", "Cagliari", LocalDateTime.now().plusMinutes(250), LocalDateTime.now().plusMinutes(200), 50, null);

            trattaDao.insert(tratta1);
            trattaDao.insert(tratta2);
            System.out.println("Tratte create.");

            // Associa mezzi alle tratte
            List<Mezzo> mezzi = mezzoDao.findAll();
            if (mezzi.size() >= 2) {
                List<Mezzo> mezziTratta1 = new ArrayList<>();
                mezziTratta1.add(mezzi.get(0));
                tratta1.setMezzi(mezziTratta1);

                List<Mezzo> mezziTratta2 = new ArrayList<>();
                mezziTratta2.add(mezzi.get(1));
                tratta2.setMezzi(mezziTratta2);

                trattaDao.update(tratta1);
                trattaDao.update(tratta2);
                System.out.println("Mezzi associati alle tratte.");
            }
        }


        em.close();
        emf.close();

        System.out.println("Database inizializzato con successo!");
    }
}