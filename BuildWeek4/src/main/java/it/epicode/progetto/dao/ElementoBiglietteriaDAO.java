package it.epicode.progetto.dao;

import it.epicode.progetto.biglietteria.ElementoBiglietteria;
import it.epicode.progetto.biglietteria.biglietteriaexceptions.BiglietteriaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class ElementoBiglietteriaDAO {
    private EntityManager em;

    public ElementoBiglietteriaDAO(EntityManager em) {
        this.em = em;
    }

    public void insert(ElementoBiglietteria e) {
        try {
            em.persist(e);
        } catch (PersistenceException ex) {
            throw new BiglietteriaException("Errore durante l'aggiunta dell'elemento" + ex);
        }
    }

    public ElementoBiglietteria delete(Long id) {
        try {
            ElementoBiglietteria elemento = em.find(ElementoBiglietteria.class, id);
            if (elemento != null){
                em.remove(elemento);
            }else {
                throw new BiglietteriaException("Elemento non trovato");
            }
            return elemento;
        } catch (PersistenceException ex) {
            throw new BiglietteriaException("Errore durante la rimozione dell'elemento" + ex);
        }
    }

    public ElementoBiglietteria findById(Long id) {
        try {
            if (id != null) {
                return em.find(ElementoBiglietteria.class, id);
            } else {
                throw new BiglietteriaException("Elemento non trovato");
            }
        } catch (PersistenceException ex) {
            throw new BiglietteriaException("Errore durante la ricerca dell'elemento" + ex);

        }
    }
}
