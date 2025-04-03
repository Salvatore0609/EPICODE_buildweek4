package it.epicode.progetto.dao;

import it.epicode.progetto.entities.Abbonamento;
import it.epicode.progetto.entities.Biglietto;
import it.epicode.progetto.entities.ElementoBiglietteria;
import it.epicode.progetto.entities.Tessera;
import it.epicode.progetto.exceptions.BiglietteriaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;

import java.util.List;

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
    public void findScadenzaAbbonamento (Long idTessera) {
        Abbonamento result =  em.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera.id = :idTessera", Abbonamento.class)
                .setParameter("idTessera", idTessera)
                .getSingleResult();
        System.out.println("La scadenza dell'abbonamento è: " +
                "Scadenza: " + result.getScadenzaAbbonamento() +
                ", Tessera ID: " + result.getTessera().getIdTessera());
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

    public Abbonamento findAbbonamentoByTessera(Tessera tessera) {
        try {
            return em.createQuery(
                            "SELECT a FROM Abbonamento a WHERE a.tessera.id = :idTessera", Abbonamento.class)
                    .setParameter("idTessera", tessera.getIdTessera())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //cerco tutti i biglietti in base all'id utente e non vidimato
    public  List<ElementoBiglietteria> findBigliettiByUtente(Long idUtente) {
        try {
            return em.createQuery(
                            "SELECT b FROM ElementoBiglietteria b WHERE b.utente.id = :idUtente AND b.vidimato = false", ElementoBiglietteria.class)
                    .setParameter("idUtente", idUtente)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Tutti i biglietti in base all'utente
    public List<ElementoBiglietteria> findAllBiglietibyUtente(Long idUtente) {
        return em.createQuery(
                        "SELECT b FROM Biglietto b WHERE b.utente.id = :idUtente", ElementoBiglietteria.class)
                .setParameter("idUtente", idUtente)
                .getResultList();
    }

    //Tutti gli abbonamenti in base all'utente
    public List<ElementoBiglietteria> findAllAbbonamentibyUtente(Long idUtente) {
        return em.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.tessera.utente.id = :idUtente", ElementoBiglietteria.class)
                .setParameter("idUtente", idUtente)
                .getResultList();
    }


    //faccio update di vidimato true su biglietto in base a id biglietto
    public void updateVidimato(Long idBiglietto) {
        try {
            Biglietto biglietto = em.find(Biglietto.class, idBiglietto);
            if (biglietto != null) {
                biglietto.setVidimato(true);
                em.getTransaction().begin();
                em.merge(biglietto);
                em.getTransaction().commit();
            } else {
                throw new BiglietteriaException("Biglietto non trovato");
            }
        } catch (PersistenceException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new BiglietteriaException("Errore durante l'aggiornamento del biglietto: " + ex.getMessage());
        }
    }
}
