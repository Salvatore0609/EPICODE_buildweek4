package it.epicode.progetto.entities;
import it.epicode.progetto.enums.Stato;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@Entity
public class Tram extends Mezzo {

	public Tram() {
	}

	public Tram(Long id, Tratta tratta, int numeroBigliettiVidimati, int volteTrattaPercorsa, int capienza, Stato stato,
			LocalDateTime inizioAttività, LocalDateTime fineAttività) {
		super(id, tratta, numeroBigliettiVidimati, volteTrattaPercorsa, capienza, stato, inizioAttività, fineAttività);
	}

	@Override
	public String toString() {
		if (getStatoEnum() == Stato.IN_SERVIZIO && getTratta() != null) {
			return "Tram linea " + getId() + " attualmente " + getStatoEnum().toString().replace("_", " ").toLowerCase() + " sulla tratta " + getTratta();
		} else if (getStatoEnum() == Stato.FERMO) {
			return "Tram linea " + getId() + " attualmente " + getStatoEnum().toString()
					+ " in attesa di ricevere una tratta da percorrere. ";
		} else if (getStatoEnum() == Stato.IN_MANUTENZIONE) {
			return "Tram linea " + getId() + " attualmente " + getStatoEnum().toString().replace("_", " ") + " dal " + getInizioAttività().format(DateTimeFormatter.ofPattern("dd/MM/yy"))
					+ " al " + getFineAttività().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + ".";
		} else {
			return "Errore con il tram linea " + getId() + ".";
		}
	}

	@Override
	public String getClasse() {
		return "Tram";
	}

}
