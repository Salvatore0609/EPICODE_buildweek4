package it.epicode.progetto.rivenditori;

import it.epicode.progetto.biglietteria.ElementoBiglietteria;
import it.epicode.progetto.biglietteria.abbonamento.Abbonamento;
import it.epicode.progetto.biglietteria.biglietto.Biglietto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Rivenditore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idRivenditore;

    @Column(length = 50, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "rivenditore")
    private List<ElementoBiglietteria> elementiEmessi;
}
