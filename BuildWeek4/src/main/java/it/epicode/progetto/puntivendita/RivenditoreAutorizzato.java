package it.epicode.progetto.puntivendita;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RivenditoreAutorizzato extends PuntoVendita {
    private String nome;
    private String indirizzo;
}
