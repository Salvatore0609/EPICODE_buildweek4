package it.epicode.progetto.mezzi;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Autobus extends Mezzo{

    public Autobus() {
    }

    public Autobus(Long id, Tratta tratta, int numeroBigliettiVidimati, int volteTrattaPercorsa, int capienza, Stato stato, LocalDate inizioAttività, LocalDate fineAttività) {
        super(id, tratta, numeroBigliettiVidimati, volteTrattaPercorsa, capienza, stato, inizioAttività, fineAttività);
    }


    @Override
    public String toString() {
        if( getStatoEnum() == Stato.IN_SERVIZIO) {
            return  "Autobus numero " + getId() + " attualmente " + getStatoEnum();
        } else if (getStatoEnum() == Stato.FERMO){
            return "Autobus numero " + getId() + " attualmente " + getStatoEnum() + " in attesa di ricevere una tratta da percorrere ";
        } else if (getStatoEnum() == Stato.IN_MANUTENZIONE) {
            return "Autobus numero " + getId() + " attualmente " + getStatoEnum() + " dal " + getInizioAttività() + " al " + getFineAttività();
        } else {
            return "Errore con l'autobus numero " + getId();
        }
    }

    @Override
    public String getClasse() {
        return "Autobus";
    }


}
