package it.epicode.progetto.bigliettoeabbonamento;

import it.epicode.progetto.puntivendita.PuntoVendita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "biglietti")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codice;

    private boolean vidimato;

    @ManyToOne
    private PuntoVendita puntoVendita;

    public void vidima() {
        this.vidimato = true;
    }
}