package it.epicode.progetto.mezzi;

import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.Entity;

@Entity
public class Autobus extends Mezzo{
    private int capienza;

    public Autobus(int capienza) {
        this.capienza = capienza;
    }

    public Autobus() {
    }

    public Autobus(Long id, Tratta tratta, int numeroTicketVidimati, int volteTrattaPercorsa, int capienza, Stato stato) {
        super(id, tratta, numeroTicketVidimati, volteTrattaPercorsa, stato);
        this.capienza = capienza;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    @Override
    public String toString() {
        return "Autobus con numero identificativo " + getId() + " con capienza di " + getCapienza() + " attualmente " + " ha un numero di ticket vidimati di " + getNumeroTicketVidimati();
    }
}
