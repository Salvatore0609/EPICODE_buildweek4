package it.epicode.progetto.mezzi;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.Entity;


@Entity
public class Tram extends Mezzo{

     public Tram(Long id, Tratta tratta, Stato stato, int numeroBigliettiVidimati, int volteTrattaPercorsa, int capienza) {
          super(stato, capienza, volteTrattaPercorsa, numeroBigliettiVidimati, tratta, id);
          setCapienza(50);
     }

     public Tram() {
     }


     @Override
     public String toString() {
          return "Tram numero " + getId() + " attualmente " + getStatoEnum();
     }

     @Override
     public String getClasse() {
          return "Tram";
     }

}

