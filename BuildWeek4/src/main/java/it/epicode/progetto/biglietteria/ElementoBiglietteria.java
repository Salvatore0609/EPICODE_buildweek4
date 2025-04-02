package it.epicode.progetto.biglietteria;

import it.epicode.progetto.rivenditori.Rivenditore;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "elementi_biglietteria")
public abstract class ElementoBiglietteria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idBiglietto;

    @Column(nullable = false)
    private LocalDate dataDiEmissione;

    @ManyToOne
    @JoinColumn(name = "rivenditore_id")
    private Rivenditore rivenditore;


}
