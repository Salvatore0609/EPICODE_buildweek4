package it.epicode.progetto.entities;
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
    @Column(name = "mezzi")
    private List<Mezzo> mezzi;

    public Tratta() {
    }

	public Tratta(Long id, String zonaDiPartenza, String capolinea, LocalDateTime tempoPrevistoDiPercorrenza,
			LocalDateTime tempoEffettivoDiPercorrenza, Integer differenzaTempo, List<Mezzo> mezzi) {
        this.id = id;
        this.zonaDiPartenza = zonaDiPartenza;
        this.capolinea = capolinea;
        this.tempoPrevistoDiPercorrenza = tempoPrevistoDiPercorrenza;
        this.tempoEffettivoDiPercorrenza = tempoEffettivoDiPercorrenza;
        this.differenzaTempo = (this.tempoPrevistoDiPercorrenza.getMinute() - this.tempoEffettivoDiPercorrenza.getMinute());
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
         final String ANSI_RESET = "\u001B[0m";
         final String ANSI_RED = "\u001B[31m";
         final String ANSI_GREEN = "\u001B[32m";
         final String ANSI_YELLOW = "\u001B[33m";

        if (differenzaTempo > 0 ) {
            return getZonaDiPartenza() + " -------> " + getCapolinea() +" in arrivo alle ore " + getTempoPrevistoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")) + " (" + ANSI_GREEN + "ANTICIPO" + ANSI_RESET + ": " + differenzaTempo + " minuti).";
        } else if (differenzaTempo < 0) {

            return getZonaDiPartenza() + " -------> " + getCapolinea() +" in arrivo alle ore " + getTempoPrevistoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")) + " (" + ANSI_RED + "RITARDO" + ANSI_RESET + ": " + (differenzaTempo * -1) + " minuti).";
        } else if  (differenzaTempo == 0) {
            return getZonaDiPartenza() + " -------> " + getCapolinea() +" in arrivo alle ore " + getTempoPrevistoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")) + " (" + ANSI_YELLOW + "IN ORARIO" + ANSI_RESET + ").";
        } else {
            return "Errore con la tratta " + getId();
        }
    }
}
