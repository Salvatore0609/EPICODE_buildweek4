package it.epicode.progetto.dao;
import it.epicode.progetto.periodo_di_servizio.PeriodoDiServizio;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PeriodoDiServizioDAO {
    private EntityManager em;


    public void insert (PeriodoDiServizio e) {
        em.persist(e);
    }


    public PeriodoDiServizio findById (Long id) {
        return em.find(PeriodoDiServizio.class, id);
    }


    public void delete (Long id) {
        PeriodoDiServizio mezzo = findById(id);
        if(mezzo != null) {
            em.remove(mezzo);
        }
    }

    public void update (PeriodoDiServizio e) {
        em.merge(e);
    }
}
