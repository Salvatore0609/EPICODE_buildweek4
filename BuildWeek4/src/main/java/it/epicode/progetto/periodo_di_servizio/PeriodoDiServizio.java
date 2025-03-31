package it.epicode.progetto.periodo_di_servizio;
import it.epicode.progetto.mezzi.Mezzo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "periodi_di_servizio")
public class PeriodoDiServizio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Stato stato;


    @Column(nullable = false)
    private LocalDate inizioAttivita;

    @Column(nullable = false)
    private LocalDate fineAttivita;

    @ManyToOne
    private Mezzo mezzo;


}
