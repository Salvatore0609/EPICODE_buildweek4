package it.epicode.progetto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private String fasciaOraria;

    @ManyToOne
    private Autista autista;

    public LocalDateTime getData() {
        return data.atStartOfDay();
    }
}

