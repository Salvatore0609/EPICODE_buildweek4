package it.epicode.progetto.entities;
import it.epicode.progetto.enums.Stato;
import jakarta.persistence.Entity;

import java.time.LocalDate;


@Entity
public class Tram extends Mezzo {

     public Tram() {
     }

     public Tram(Long id, Tratta tratta, int numeroBigliettiVidimati, int volteTrattaPercorsa, int capienza, Stato stato, LocalDate inizioAttività, LocalDate fineAttività) {
          super(id, tratta, numeroBigliettiVidimati, volteTrattaPercorsa, capienza, stato, inizioAttività, fineAttività);
     }

     @Override
     public String toString() {
          if( getStatoEnum() == Stato.IN_SERVIZIO) {
               return  "Tram numero " + getId() + " attualmente " + getStatoEnum();
          } else if (getStatoEnum() == Stato.FERMO){
               return "Tram numero " + getId() + " attualmente " + getStatoEnum() + " in attesa di ricevere una tratta da percorrere ";
          } else if (getStatoEnum() == Stato.IN_MANUTENZIONE) {
               return "Tram numero " + getId() + " attualmente " + getStatoEnum() + " dal " + getInizioAttività() + " al " + getFineAttività();
          } else {
               return "Errore con il tram numero " + getId();
          }
     }

     @Override
     public String getClasse() {
          return "Tram";
     }

}

