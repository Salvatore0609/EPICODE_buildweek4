package it.epicode.progetto.biglietteria.abbonamento;

import it.epicode.progetto.biglietteria.ElementoBiglietteria;
import it.epicode.progetto.tessere.Tessera;
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
}
