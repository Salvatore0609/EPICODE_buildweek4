package it.epicode.progetto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


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

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = true)
    private Utente utente;

    @Override
    public String toString() {
        return "Biglietto con identificativo: " + this.getIdBiglietto() +
                ", vidimato: " + this.isVidimato();
    }
}
