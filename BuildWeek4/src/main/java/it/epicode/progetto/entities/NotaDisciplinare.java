package it.epicode.progetto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class NotaDisciplinare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;

    private LocalDate data;

    @ManyToOne
    private Autista autista;
}
