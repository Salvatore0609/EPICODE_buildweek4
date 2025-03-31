package it.epicode.progetto.utenti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tessere")
public class Tessera extends Utente {

    @Column(nullable = false)
    private LocalDate dataEmissione;

    @Column
    private LocalDate dataScadenza;

    public Tessera(String username, String nome, String cognome, String password, Ruolo ruolo, LocalDate dataEmissione) {
        super(username, nome, cognome, password, ruolo);
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataEmissione.plusYears(1);
    }

}
