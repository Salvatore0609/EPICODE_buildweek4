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
    public Utente findById(Long id) {
        return em.find(Utente.class, id);
    }
    public Utente findByUsername(String username) {
        return em.createQuery("SELECT u FROM Utente u WHERE u.username = :username", Utente.class)
                .setParameter("username", username)
                .getSingleResult();
    }
    //ricerca per nome e cognome
    public Utente findByNomeAndCognome(String nome, String cognome) {
        return em.createQuery("SELECT u FROM Utente u WHERE u.nome = :nome AND u.cognome = :cognome", Utente.class)
                .setParameter("nome", nome)
                .setParameter("cognome", cognome)
                .getSingleResult();
    }

}
