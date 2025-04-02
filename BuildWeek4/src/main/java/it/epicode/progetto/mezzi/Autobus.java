package it.epicode.progetto.mezzi;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.Entity;


@Entity
public class Autobus extends Mezzo{

    public Autobus(Long id, Tratta tratta, Stato stato, int numeroBigliettiVidimati, int volteTrattaPercorsa, int capienza) {
        super(stato, capienza, volteTrattaPercorsa, numeroBigliettiVidimati, tratta, id);
        setCapienza(20);
    }

    public Autobus() {
    }

    @Override
    public String toString() {
        return "Autobus numero " + getId() + " attualmente " + getStatoEnum() + " partito da " + getTratta().getZonaDiPartenza() + " con destinazione " + getTratta().getCapolinea();
    }

    @Override
    public String getClasse() {
        return "Autobus";
    }


}
