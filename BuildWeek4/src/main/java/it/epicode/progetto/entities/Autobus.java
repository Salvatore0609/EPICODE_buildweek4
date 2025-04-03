package it.epicode.progetto.entities;
import it.epicode.progetto.enums.Stato;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Autobus extends Mezzo {

	public Autobus() {
	}

	public Autobus(Long id, Tratta tratta, int numeroBigliettiVidimati, int volteTrattaPercorsa, int capienza,
			Stato stato, LocalDateTime inizioAttività, LocalDateTime fineAttività) {
		super(id, tratta, numeroBigliettiVidimati, volteTrattaPercorsa, capienza, stato, inizioAttività, fineAttività);
	}

	@Override
	public String toString() {
		if (getStatoEnum() == Stato.IN_SERVIZIO && getTratta() != null) {
			return "Autobus linea " + getId() + " attualmente " + getStatoEnum().toString().replace("_", " ").toLowerCase() + " sulla tratta " + getTratta();
		} else if (getStatoEnum() == Stato.FERMO) {
			return "Autobus linea " + getId() + " attualmente " + getStatoEnum().toString()
					+ " in attesa di ricevere una tratta da percorrere.";
		} else if (getStatoEnum() == Stato.IN_MANUTENZIONE) {
			return "Autobus linea " + getId() + " attualmente " + getStatoEnum().toString().replace("_", " ") + " dal " + getInizioAttività().format(DateTimeFormatter.ofPattern("dd/MM/yy"))
					+ " al " + getFineAttività().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + ".";
		} else {
			return "Errore con l'autobus linea " + getId();
		}
	}

	@Override
	public String getClasse() {
		return "Autobus";
	}

}
