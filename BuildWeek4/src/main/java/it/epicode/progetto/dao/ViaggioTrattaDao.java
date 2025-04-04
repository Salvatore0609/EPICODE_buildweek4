package it.epicode.progetto.dao;

import it.epicode.progetto.entities.ViaggioTratte;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ViaggioTrattaDao {
    private EntityManager em;


    public void insert(ViaggioTratte v) {
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
    }

    public int ritornaUltimoViaggio() {
        // Implementa la logica per recuperare l'ultimo viaggio dal database
        // Utilizza l'EntityManager per eseguire la query
        // Restituisci l'oggetto ViaggioTratte corrispondente
        // Se non ci sono viaggi, restituisci null o gestisci l'errore come preferisci

        // Esempio di query per recuperare l'ultimo viaggio
        String jpql = "SELECT v FROM ViaggioTratte v ORDER BY v.id DESC";
        try {
            int ultimoViaggio = em.createQuery(jpql, ViaggioTratte.class)
                    .setMaxResults(1)
                    .getSingleResult().getNumeroDiVoltePercorso();

            return ultimoViaggio;
        } catch (Exception e) {
            System.out.println("Nessun viaggio trovato.");
            return 0; // Restituisci 0 se non ci sono viaggi
        }
    }
}
