package it.epicode.progetto.entities;
import it.epicode.progetto.enums.Stato;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Mezzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Tratta tratta;

	private int numeroBigliettiVidimati;

	@Column(nullable = true)
	private int volteTrattaPercorsa;

	private int capienza;

	@Enumerated(EnumType.STRING)
	private Stato stato;

	@Column(nullable = true)
	private LocalDateTime inizioAttività;

	@Column(nullable = true)
	private LocalDateTime fineAttività;

	@OneToMany(mappedBy = "mezzo")
	private List<Biglietto> biglietti;

	public Stato getStatoEnum() {
		return Stato.valueOf(String.valueOf(stato));
	}

	public void setStatoEnum(Stato statoEnum) {
		this.stato = Stato.valueOf(statoEnum.name());
	}

	public Mezzo() {
	}

	public Mezzo(Long id, Tratta tratta, int numeroBigliettiVidimati, int volteTrattaPercorsa, int capienza,
			Stato stato, LocalDateTime inizioAttività, LocalDateTime fineAttività) {
		this.id = id;
		this.tratta = tratta;
		this.numeroBigliettiVidimati = numeroBigliettiVidimati;
		this.volteTrattaPercorsa = volteTrattaPercorsa;
		this.capienza = capienza;
		this.stato = stato;
		this.inizioAttività = inizioAttività;
		this.fineAttività = fineAttività;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tratta getTratta() {
		return tratta;
	}

	public void setTratta(Tratta tratta) {
		this.tratta = tratta;

	}

	public int getNumeroBigliettiVidimati() {
		return numeroBigliettiVidimati;
	}

	public void setNumeroBigliettiVidimati(int numeroBigliettiVidimati) {
		this.numeroBigliettiVidimati = numeroBigliettiVidimati;
	}

	public int getVolteTrattaPercorsa() {
		return volteTrattaPercorsa;
	}

	public void setVolteTrattaPercorsa(int volteTrattaPercorsa) {
		this.volteTrattaPercorsa = volteTrattaPercorsa;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public LocalDateTime getInizioAttività() {
		return inizioAttività;
	}

	public void setInizioAttività(LocalDateTime inizioAttività) {
		this.inizioAttività = inizioAttività;
	}

	public LocalDateTime getFineAttività() {
		return fineAttività;
	}

	public void setFineAttività(LocalDateTime fineAttività) {
		this.fineAttività = fineAttività;
	}

	public String getClasse() {
		return "";
	}

	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}
}
