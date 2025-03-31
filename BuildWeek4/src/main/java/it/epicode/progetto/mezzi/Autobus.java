package it.epicode.progetto.mezzi;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Autobus extends Mezzo{
    private int capienza = 20;

    public Autobus(int capienza) {
        this.capienza = capienza;
    }

    public Autobus() {
    }

    public Autobus(Long id, Tratta tratta, Stato stato, int numeroTicketVidimati, int volteTrattaPercorsa, int capienza) {
        super(id, tratta, stato, numeroTicketVidimati, volteTrattaPercorsa);
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
        return "Autobus{" +
                "capienza=" + capienza +
                '}';
    }
}
