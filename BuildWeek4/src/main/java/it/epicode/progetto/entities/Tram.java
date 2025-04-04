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
		final String reset = "\u001B[0m";
		final String rosso = "\u001B[31m";
		final String verde = "\u001B[32m";
		final String giallo = "\u001B[33m";
		final String azzurro = "\u001B[36m";

		if (getStatoEnum() == Stato.IN_SERVIZIO && getTratta() != null
				&& getNumeroBigliettiVidimati() >= getCapienza()) {
			return "Tram " + azzurro + "ATEB" + reset + " linea " + getId() + " attualmente " + verde
					+ getStatoEnum().toString().replace("_", " ").toLowerCase() + reset + " sulla tratta \t "
					+ getTratta().getZonaDiPartenza() + " -------> " + getTratta().getCapolinea() + "\t" + rosso
					+ "\t PIENO 🚫" + reset;
		} else if (getStatoEnum() == Stato.IN_SERVIZIO && getTratta() != null
				&& getNumeroBigliettiVidimati() < getCapienza()) {
			return "Tram " + azzurro + "ATEB" + reset + " linea " + getId() + " attualmente " + verde
					+ getStatoEnum().toString().replace("_", " ").toLowerCase() + reset + " sulla tratta \t "
					+ getTratta().getZonaDiPartenza() + " -------> " + getTratta().getCapolinea() + "\t" + verde
					+ "\t DISPONIBILE ✅" + reset + "\t Posti liberi: " + (getCapienza() - getNumeroBigliettiVidimati());
		} else if (getStatoEnum() == Stato.FERMO) {
			return "Tram " + azzurro + "ATEB" + reset + " linea " + getId() + " attualmente " + rosso
					+ getStatoEnum().toString().replace("_", " ").toLowerCase() + reset
					+ " al deposito in attesa di ricevere una tratta da percorrere. ";
		} else if (getStatoEnum() == Stato.IN_MANUTENZIONE) {
			return "Tram " + azzurro + "ATEB" + reset + " linea " + getId() + " attualmente " + giallo
					+ getStatoEnum().toString().replace("_", " ").toLowerCase() + reset + " per problemi tecnici dal "
					+ getInizioAttività().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + " al "
					+ getFineAttività().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + ".";
		} else {
			return "Errore con il tram linea " + getId() + ".";
		}
	}

	@Override
	public String getClasse() {
		return "Tram";
	}

}
