package it.epicode.progetto.tessereutente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tessere")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroTessera;

    @Column(nullable = false)
    private LocalDate dataScadenza;

    public boolean isValid() {
        return LocalDate.now().isBefore(dataScadenza);
    }
}

