package it.epicode.progetto.tratta;
import it.epicode.progetto.mezzi.Mezzo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
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

    private int tempoPrevistoDiPercorrenza;

    private int tempoEffettivoDiPercorrenza;

    @OneToMany
    private List<Mezzo> mezzi;

    public Tratta(Long id, String zonaDiPartenza, String capolinea, LocalDateTime orarioDiPartenza, LocalDateTime orarioDiArrivo, int tempoPrevistoDiPercorrenza, int tempoEffettivoDiPercorrenza, List<Mezzo> mezzi) {
        this.id = id;
        this.zonaDiPartenza = zonaDiPartenza;
        this.capolinea = capolinea;
        this.orarioDiPartenza = orarioDiPartenza;
        this.orarioDiArrivo = orarioDiArrivo;
        this.tempoPrevistoDiPercorrenza = tempoPrevistoDiPercorrenza;
        this.tempoEffettivoDiPercorrenza = tempoEffettivoDiPercorrenza;
        this.mezzi = mezzi;
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

    public int getTempoPrevistoDiPercorrenza() {
        return tempoPrevistoDiPercorrenza;
    }

    public void setTempoPrevistoDiPercorrenza(int tempoPrevistoDiPercorrenza) {
        this.tempoPrevistoDiPercorrenza = tempoPrevistoDiPercorrenza;
    }

    public int getTempoEffettivoDiPercorrenza() {
        return tempoEffettivoDiPercorrenza;
    }

    public void setTempoEffettivoDiPercorrenza(int tempoEffettivoDiPercorrenza) {
        this.tempoEffettivoDiPercorrenza = tempoEffettivoDiPercorrenza;
    }

    public List<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }

    @Override
    public String toString() {
        return "La tratta con id " + getId() + " parte da " + zonaDiPartenza + " e arriva a " + capolinea;
    }
}
