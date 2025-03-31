package it.epicode.progetto.utenti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tessere")
public class Tessera extends Utente {

    @Id
    private Long idTessera;

    @Column(nullable = false)
    private int dataEmissione;

    public Tessera(String username, String nome, String cognome, String password, Ruolo ruolo, Long idTessera, int dataEmissione) {
        super(username, nome, cognome, password, ruolo);
        this.idTessera = idTessera;
        this.dataEmissione = dataEmissione;
    }

}
