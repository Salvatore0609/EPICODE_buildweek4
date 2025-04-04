package it.epicode.progetto.entities;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Entity
@Table(name = "viaggio_tratte")
@Data
@NoArgsConstructor
public class ViaggioTratte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tratta tratta;

    private LocalDateTime tempoPrevistoDiPercorrenza;

    private LocalDateTime tempoEffettivoDiPercorrenza;

    private Long tempoEffettivoDiPercorrenzaLong;

    private Long differenzaTempo;

    private int numeroDiVoltePercorso;

    public ViaggioTratte(Long id, Tratta tratta, LocalDateTime tempoPrevistoDiPercorrenza, LocalDateTime tempoEffettivoDiPercorrenza, Long differenzaTempo, int numeroDiVoltePercorso) {
        this.id = id;
        this.tratta = tratta;
        this.tempoPrevistoDiPercorrenza = tempoPrevistoDiPercorrenza;
        this.tempoEffettivoDiPercorrenza = tempoEffettivoDiPercorrenza;
        this.differenzaTempo = differenzaTempo;
        this.numeroDiVoltePercorso = numeroDiVoltePercorso;
        this.tempoEffettivoDiPercorrenzaLong = tempoPrevistoDiPercorrenza.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    @Override
    public String toString() {
        return "ViaggioTratteProvaString";
    }
}
