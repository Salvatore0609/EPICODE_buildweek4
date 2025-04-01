package it.epicode.progetto.mezzi;
import it.epicode.progetto.periodo_di_servizio.PeriodoDiServizio;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Tram extends Mezzo{
     private int capienza = 50 ;


     public Tram(int capienza) {
          this.capienza = capienza;
     }

     public Tram() {
     }

     public Tram(Long id, Tratta tratta, int numeroTicketVidimati, int volteTrattaPercorsa, int capienza, PeriodoDiServizio periodoDiServizio) {
          super(id, tratta, numeroTicketVidimati, volteTrattaPercorsa, periodoDiServizio);
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
          return "Tram{" +
                  "capienza=" + capienza +
                  '}';
     }
}
