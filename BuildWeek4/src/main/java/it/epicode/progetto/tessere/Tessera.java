package it.epicode.progetto.tessere;

import it.epicode.progetto.utenti.Utente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tessere")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTessera;

    @Column(nullable = false)
    private LocalDate dataEmissione;

    @Column
    private LocalDate dataScadenza;

    @ManyToOne
    @JoinColumn(name = "idutente", nullable = false)
    private Utente utente;

    public Tessera(Utente utente, LocalDate dataEmissione) {
        this.utente = utente;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataEmissione.plusYears(1);
    }
}

