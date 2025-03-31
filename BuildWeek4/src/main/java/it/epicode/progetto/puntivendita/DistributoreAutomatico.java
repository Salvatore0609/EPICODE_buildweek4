package it.epicode.progetto.puntivendita;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DistributoreAutomatico extends PuntoVendita {
    private String posizione;

    private Stato stato;
}
