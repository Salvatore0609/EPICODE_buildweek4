package it.epicode.progetto.biglietteria.abbonamento;

import it.epicode.progetto.biglietteria.ElementoBiglietteria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
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
    @Enumerated
    @Column
    private DurataAbbonamento durataAbbonamento;

    @Column
    private LocalDate scadenzaAbbonamento;
}
