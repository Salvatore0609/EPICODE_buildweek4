package it.epicode.progetto.mezzi;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Tram extends Mezzo{

     public Tram() {
     }

     public Tram(Long id, Tratta tratta, int numeroBigliettiVidimati, int volteTrattaPercorsa, int capienza, Stato stato, LocalDate inizioAttività, LocalDate fineAttività) {
          super(id, tratta, numeroBigliettiVidimati, volteTrattaPercorsa, capienza, stato, inizioAttività, fineAttività);
     }

     @Override
     public String toString() {
          if( getStatoEnum() == Stato.IN_SERVIZIO) {
               return  "Tram numero " + getId() + " attualmente " + getStatoEnum() + " partito da " + getTratta().getZonaDiPartenza() + " con destinazione " + getTratta().getCapolinea();
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

