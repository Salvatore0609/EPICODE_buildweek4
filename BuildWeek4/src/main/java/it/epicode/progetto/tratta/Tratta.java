package it.epicode.progetto.tratta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "tratte")
public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, nullable = false)
    private String zonaDiPartenza;

    @Column(length = 100, nullable = false)
    private String capolinea;

    @Column(nullable = false)
    private LocalDateTime orarioDiPartenza;

    @Column(nullable = false)
    private LocalDateTime orarioDiArrivo;

    private int tempoEffettivoDiPercorrenza;
}
