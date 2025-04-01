package it.epicode.progetto.dao;
import it.epicode.progetto.mezzi.Mezzo;
import it.epicode.progetto.periodo_di_servizio.PeriodoDiServizio;
import it.epicode.progetto.periodo_di_servizio.Stato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PeriodoDiServizioDAO {
    private EntityManager em;


    public void insert (PeriodoDiServizio e) {
        em.persist(e);
        System.out.println("Il periodo di servizio é stato inserito con successo");
    }


    public PeriodoDiServizio findById (Long id) {
        return em.find(PeriodoDiServizio.class, id);
    }


    public void delete (Long id) {
        PeriodoDiServizio mezzo = findById(id);
        if(mezzo != null) {
            em.remove(mezzo);
            System.out.println("Il periodo di servizio é stato eliminato con successo");
        }
    }

    public void update (PeriodoDiServizio e) {
        em.merge(e);
    }


    public List<Mezzo> findMezziByStato(Stato stato) {
        TypedQuery<Mezzo> query = em.createQuery(
                "SELECT DISTINCT p.mezzo FROM PeriodoDiServizio p WHERE p.stato = :stato", Mezzo.class
        );
        query.setParameter("stato", stato);
        return query.getResultList();
    }

    public List<PeriodoDiServizio> findByMezzo(Mezzo mezzo) {
        TypedQuery<PeriodoDiServizio> query = em.createQuery(
                "SELECT p FROM PeriodoDiServizio p WHERE p.mezzo = :mezzo", PeriodoDiServizio.class
        );
        query.setParameter("mezzo", mezzo);
        return query.getResultList();
    }
}

