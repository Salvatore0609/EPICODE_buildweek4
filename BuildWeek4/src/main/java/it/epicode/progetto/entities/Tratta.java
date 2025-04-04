package it.epicode.progetto.entities;
import it.epicode.progetto.utils.Randomizers;
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

	/* @Column(nullable = false) */
	private LocalDateTime tempoPrevistoDiPercorrenza;
	/* @Column(nullable = false) */
	private LocalDateTime tempoEffettivoDiPercorrenza;
	/* @Column(nullable = true) */
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
		this.differenzaTempo = (this.tempoPrevistoDiPercorrenza.getMinute()
				- this.tempoEffettivoDiPercorrenza.getMinute());
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
		final String reset = "\u001B[0m";
		final String rosso = "\u001B[31m";
		final String verde = "\u001B[32m";
		final String azzurro = "\u001B[36m";
		final String giallo = "\u001B[33m";

		if (differenzaTempo > 0) {
			return azzurro + "ATEB " + Randomizers.randomSerie() + reset + " " + getZonaDiPartenza() + " -------> "
					+ getCapolinea() + "\t" + " PARTENZA ORE: "
					+ getTempoPrevistoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
					+ "\t" + " ARRIVO ORE: "
					+ getTempoEffettivoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
					+ "\t" + " (" + verde + "ANTICIPO" + reset + ": " + differenzaTempo + "')";
		} else if (differenzaTempo < 0) {

			return azzurro + "ATEB " + Randomizers.randomSerie() + reset + " " + getZonaDiPartenza() + " -------> "
					+ getCapolinea() + "\t" + " PARTENZA ORE: "
					+ getTempoPrevistoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
					+ "\t" + " ARRIVO ORE: "
					+ getTempoEffettivoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
					+ "\t" + " (" + rosso + "RITARDO" + reset + ": " + (differenzaTempo * -1) + "')";
		} else if (differenzaTempo == 0) {
			return azzurro + "ATEB " + Randomizers.randomSerie() + reset + " " + getZonaDiPartenza() + " -------> "
					+ getCapolinea() + "\t" + " PARTENZA ORE: "
					+ getTempoPrevistoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
					+ "\t" + " ARRIVO ORE: "
					+ getTempoEffettivoDiPercorrenza().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
					+ "\t" + " (" + giallo + "IN ORARIO" + reset + ")";
		} else {
			return "Errore con la tratta " + getId();
		}
	}
}
