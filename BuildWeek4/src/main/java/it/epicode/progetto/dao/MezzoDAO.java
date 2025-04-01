package it.epicode.progetto.dao;
import it.epicode.progetto.mezzi.Mezzo;
import it.epicode.progetto.periodo_di_servizio.Stato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class MezzoDAO {
    private EntityManager em;

    public void insert (Mezzo e) {
        em.persist(e);
    }


    public Mezzo findById (Long id) {
        return em.find(Mezzo.class, id);
    }


    public void delete (Long id) {
        Mezzo mezzo = findById(id);
        if(mezzo != null) {
            em.remove(mezzo);
        }
    }

    public void update (Mezzo e) {
        em.merge(e);
        }

    }





