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
          return "Tram con numero identificativo " + getId() + " con capienza di " + getCapienza() + " persone," + " attualmente " + getStatoEnum(); // + " ha un numero di ticket vidimati di " + getNumeroBigliettiVidimati() + ". La sua corsa partirà da " + getTratta().getZonaDiPartenza() + " alle " + getTratta().getOrarioDiPartenza().getHour() +":" + getTratta().getOrarioDiPartenza().getMinute() + " e arriverà a " + getTratta().getCapolinea() + " alle " + getTratta().getOrarioDiArrivo().getHour() + ":" + getTratta().getOrarioDiArrivo().getMinute() + ". Vi auguriamo buon viaggio!";
     }

}

