package it.epicode.progetto.puntivendita;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "punti_vendita")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean attivo;
}



