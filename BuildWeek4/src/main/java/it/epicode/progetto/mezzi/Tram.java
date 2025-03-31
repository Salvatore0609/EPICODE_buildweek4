package it.epicode.progetto.mezzi;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tram")

public class Tram extends Mezzo{
     private int capienza = 50 ;

     public Tram(int capienza) {
          this.capienza = capienza;
     }

     public Tram() {
     }

     public Tram(Long id, Tratta tratta, int numeroTicketVidimati, int volteTrattaPercorsa) {
          super(id, tratta, numeroTicketVidimati, volteTrattaPercorsa);
     }

     public Tram(Long id, Tratta tratta, int numeroTicketVidimati, int volteTrattaPercorsa, int capienza) {
          super(id, tratta, numeroTicketVidimati, volteTrattaPercorsa);
          this.capienza = capienza;
     }

     public int getCapienza() {
          return capienza;
     }

     public void setCapienza(int capienza) {
          this.capienza = capienza;
     }
}
