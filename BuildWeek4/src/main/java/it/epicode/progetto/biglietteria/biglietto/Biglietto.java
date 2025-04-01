package it.epicode.progetto.biglietteria.biglietto;

import it.epicode.progetto.biglietteria.ElementoBiglietteria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
