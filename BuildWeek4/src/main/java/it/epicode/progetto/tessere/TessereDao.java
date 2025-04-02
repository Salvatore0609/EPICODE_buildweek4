package it.epicode.progetto.tessere;

import it.epicode.progetto.biglietteria.abbonamento.Abbonamento;
import it.epicode.progetto.utenti.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class TessereDao {
    private EntityManager em;
    public void insert(Tessera t) {
        em.persist(t);
    }
    public Tessera findByUtente(Utente utente) {
        try {
            return em.createQuery("""
                SELECT t FROM Tessera t
                WHERE t.utente = :utente
                  AND t.dataScadenza > :oggi
                """, Tessera.class)
                    .setParameter("utente", utente)
                    .setParameter("oggi", LocalDate.now())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void findAbbonamentoByTessera (Long idTessera) {

            Abbonamento result =  em.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera.id = :idTessera", Abbonamento.class)
                    .setParameter("idTessera", idTessera)
                    .getSingleResult();
        System.out.println("L'abbonamento valido è: " +
                "Durata: " + result.getDurataAbbonamento() +
                ", Scadenza: " + result.getScadenzaAbbonamento() +
                ", Tessera ID: " + result.getTessera().getIdTessera());
    }

}
