package it.epicode.progetto.tratta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
//@AllArgsConstructor
//@Data
//@NoArgsConstructor
@Table(name = "tratte")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, nullable = false)
    private String zonaDiPartenza;

    @Column(length = 100, nullable = false)
    private String capolinea;


    private LocalDateTime orarioDiPartenza;


    private LocalDateTime orarioDiArrivo;


    private Double tempoEffettivoDiPercorrenza;


    public Tratta(Long id, String zonaDiPartenza, String capolinea, LocalDateTime orarioDiPartenza, LocalDateTime orarioDiArrivo, Double tempoEffettivoDiPercorrenza) {
        this.id = id;
        this.zonaDiPartenza = zonaDiPartenza;
        this.capolinea = capolinea;
        this.orarioDiPartenza = orarioDiPartenza;
        this.orarioDiArrivo = orarioDiArrivo;
        this.tempoEffettivoDiPercorrenza = tempoEffettivoDiPercorrenza;
    }

    public Tratta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZonaDiPartenza() {
        return zonaDiPartenza;
    }

    public void setZonaDiPartenza(String zonaDiPartenza) {
        this.zonaDiPartenza = zonaDiPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public LocalDateTime getOrarioDiPartenza() {
        return orarioDiPartenza;
    }

    public void setOrarioDiPartenza(LocalDateTime orarioDiPartenza) {
        this.orarioDiPartenza = orarioDiPartenza;
    }

    public LocalDateTime getOrarioDiArrivo() {
        return orarioDiArrivo;
    }

    public void setOrarioDiArrivo(LocalDateTime orarioDiArrivo) {
        this.orarioDiArrivo = orarioDiArrivo;
    }

    public Double getTempoEffettivoDiPercorrenza() {
        return tempoEffettivoDiPercorrenza;
    }

    public void setTempoEffettivoDiPercorrenza(Double tempoEffettivoDiPercorrenza) {
        this.tempoEffettivoDiPercorrenza = tempoEffettivoDiPercorrenza;
    }
}
