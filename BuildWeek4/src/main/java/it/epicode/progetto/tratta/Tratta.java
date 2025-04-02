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

   /* @Column(nullable = false)*/
    private LocalDateTime tempoPrevistoDiPercorrenza;
   /* @Column(nullable = false)*/
    private LocalDateTime tempoEffettivoDiPercorrenza;
   /* @Column(nullable = true)*/
    private Integer differenzaTempo;

    @OneToMany
    private List<Mezzo> mezzi;

    public Tratta() {
    }

    public Tratta(Long id, String zonaDiPartenza, String capolinea, LocalDateTime tempoPrevistoDiPercorrenza, LocalDateTime tempoEffettivoDiPercorrenza, Integer differenzaTempo, List<Mezzo> mezzi) {
        this.id = id;
        this.zonaDiPartenza = zonaDiPartenza;
        this.capolinea = capolinea;
        this.tempoPrevistoDiPercorrenza = tempoPrevistoDiPercorrenza;
        this.tempoEffettivoDiPercorrenza = tempoEffettivoDiPercorrenza;
        this.differenzaTempo = this.tempoPrevistoDiPercorrenza.getMinute() - this.tempoEffettivoDiPercorrenza.getMinute();
        this.mezzi = mezzi;
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

    public LocalDateTime getTempoPrevistoDiPercorrenza() {
        return tempoPrevistoDiPercorrenza;
    }

    public void setTempoPrevistoDiPercorrenza(LocalDateTime tempoPrevistoDiPercorrenza) {
        this.tempoPrevistoDiPercorrenza = tempoPrevistoDiPercorrenza;
    }

    public LocalDateTime getTempoEffettivoDiPercorrenza() {
        return tempoEffettivoDiPercorrenza;
    }

    public void setTempoEffettivoDiPercorrenza(LocalDateTime tempoEffettivoDiPercorrenza) {
        this.tempoEffettivoDiPercorrenza = tempoEffettivoDiPercorrenza;
    }

    public Integer getDifferenzaTempo() {
        return differenzaTempo;
    }

    public void setDifferenzaTempo(Integer differenzaTempo) {
        this.differenzaTempo = differenzaTempo;
    }

    public List<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }

    @Override
    public String toString() {
        if (differenzaTempo > 0 ) {
            return "La tratta numero " + getId() + " partirà da " + getZonaDiPartenza() + " e arriverà a " + getCapolinea() + " con un anticipo di " + differenzaTempo + " minuti.";
        } else if (differenzaTempo < 0) {
            differenzaTempo = differenzaTempo * -1;
            return "La tratta numero " + getId() + " partirà da " + getZonaDiPartenza() + " e arriverà a " + getCapolinea() + " con un ritardo di " + differenzaTempo + " minuti.";
        } else if  (differenzaTempo == 0) {
            return "La tratta numero " + getId() + " partirà da " + getZonaDiPartenza() + " e arriverà a " + getCapolinea() + " in orario.";
        } else {
            return "Errore con la tratta " + getId();
        }
    }
}
