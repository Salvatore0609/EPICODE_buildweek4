package it.epicode.progetto.dao;

import it.epicode.progetto.entities.Autista;
import it.epicode.progetto.entities.Utente;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AutistaDao {
    private EntityManager em;

    public void save(Autista a) {
        em.persist(a);
    }
}
