package it.epicode.progetto.bigliettoeabbonamento;

import it.epicode.progetto.puntivendita.PuntoVendita;
import it.epicode.progetto.tessereutente.Tessera;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "abbonamenti")
public class Abbonamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, nullable = false)
    private String codice;
    @Column(nullable = false)
    private LocalDate dataInizio;
    @Column(nullable = false)
    private LocalDate dataFine;

    @ManyToOne
    private Tessera tessera;

    @ManyToOne
    private PuntoVendita puntoVendita;

    public boolean isValid() {
        return LocalDate.now().isBefore(dataFine);
    }
}

