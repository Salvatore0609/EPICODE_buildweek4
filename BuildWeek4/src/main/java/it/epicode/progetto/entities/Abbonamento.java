package it.epicode.progetto.entities;

import it.epicode.progetto.enums.DurataAbbonamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Entity
@Table(name = "abbonamenti")
public class Abbonamento extends ElementoBiglietteria {
    @Enumerated(EnumType.STRING)
    @Column
    private DurataAbbonamento durataAbbonamento;

    @Column
    private LocalDate scadenzaAbbonamento;

    @ManyToOne
    @JoinColumn(name = "idTessera", nullable = false)
    private Tessera tessera;

    @Override
    public String toString() {
        final String reset = "\u001B[0m";
        final String rosso = "\u001B[31m";
        final String verde = "\u001B[32m";

        if (scadenzaAbbonamento.isBefore(LocalDate.now())) {
            return"Abbonamento " +
                    "[Durata: " + durataAbbonamento.toString().toLowerCase() +
                    ", " + rosso + "Scaduto il: " + scadenzaAbbonamento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + reset +
                    ", Tessera ID: " + (tessera != null ? tessera.getIdTessera() : "N/D") +
                    "]";
        } else {
            return "Abbonamento " +
                    "[Durata: " + durataAbbonamento.toString().toLowerCase() +
                    ", " + verde + "Scadenza: " + scadenzaAbbonamento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + reset +
                    ", Tessera ID: " + (tessera != null ? tessera.getIdTessera() : "N/D") +
                    "]";
        } }
}


