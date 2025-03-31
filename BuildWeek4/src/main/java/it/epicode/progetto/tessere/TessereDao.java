package it.epicode.progetto.tessere;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TessereDao {
    private EntityManager em;
    public void insert(Tessera t) {
        em.persist(t);
    }
}
