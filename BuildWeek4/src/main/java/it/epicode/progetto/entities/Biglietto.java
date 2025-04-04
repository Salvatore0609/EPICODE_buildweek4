package it.epicode.progetto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "biglietti")
public class Biglietto extends ElementoBiglietteria {

    @Column
    private boolean vidimato = false;

    @Column
    private LocalDate dataVidimato;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = true)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = true)
    private Mezzo mezzo;

    @Override
    public String toString() {
        final String reset = "\u001B[0m";
        final String rosso = "\u001B[31m";
        final String verde = "\u001B[32m";

        if (this.isVidimato()) {
            return "Biglietto con identificativo: " + this.getIdBiglietto() +
                    ", vidimato: " + verde + this.isVidimato() + reset;
        } else {
            return "Biglietto con identificativo: " + this.getIdBiglietto() +
                    ", vidimato: " + rosso + this.isVidimato() + reset;
        }

    }
}
