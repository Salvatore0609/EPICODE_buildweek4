package it.epicode.progetto.dao;

import it.epicode.progetto.entities.Abbonamento;
import it.epicode.progetto.entities.Tessera;
import it.epicode.progetto.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

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
        try {

			Abbonamento result = em.createQuery(
					"SELECT a FROM Abbonamento a WHERE a.tessera.id = :idTessera", Abbonamento.class)
                    .setParameter("idTessera", idTessera)
                    .getSingleResult();
        System.out.println("Il tuo abbonamento è valido con durata " +
                 result.getDurataAbbonamento().toString().toLowerCase() +
                " e scade il " + result.getScadenzaAbbonamento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", abbinato alla tessera con identificativo: " + result.getTessera().getIdTessera() + ".");
    } catch (NoResultException e) {
            System.out.println("Non esiste un abbonamento valido per la tessera con identificativo " + idTessera + ".");
        } }




    public boolean isAbbonamentoByTessera(Long idTessera) {
        try {
            Abbonamento result = em.createQuery(
                            "SELECT a FROM Abbonamento a WHERE a.tessera.id = :idTessera", Abbonamento.class)
                    .setParameter("idTessera", idTessera)
                    .getSingleResult();

            System.out.println("Esiste già un abbonamento valido per la tessera con identificativo " + result.getTessera().getIdTessera() +
                    ", durata " + result.getDurataAbbonamento().toString().toLowerCase() +
                    " e scadenza il " + result.getScadenzaAbbonamento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ".");


            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

}
