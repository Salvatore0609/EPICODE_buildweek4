package it.epicode.progetto.utenti;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UtentiDao {
    private EntityManager em;

    public void insert(Utente u) {
        em.persist(u);
    }
    public Utente update(Utente u) {
        return em.merge(u);
    }
    public void delete(Utente u) {
        em.remove(u);
    }
}
