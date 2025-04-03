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
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		if (getStatoEnum() == Stato.IN_SERVIZIO && getTratta() != null && getNumeroBigliettiVidimati() >= getCapienza()) {
			return "Autobus linea " + getId() + " attualmente " + getStatoEnum().toString().replace("_", " ").toLowerCase()
					+ " sulla tratta " + getTratta() + ANSI_RED + " PIENO🚫" + ANSI_RESET;
		}
		else if (getStatoEnum() == Stato.IN_SERVIZIO && getTratta() != null && getNumeroBigliettiVidimati() < getCapienza()) {
			return "Autobus linea " + getId() + " attualmente " + getStatoEnum().toString().replace("_", " ").toLowerCase()
					+ " sulla tratta " + getTratta() + ANSI_GREEN + " DISPONIBILE✅" + ANSI_RESET + " Posti liberi: " + (getCapienza()  - getNumeroBigliettiVidimati());
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
