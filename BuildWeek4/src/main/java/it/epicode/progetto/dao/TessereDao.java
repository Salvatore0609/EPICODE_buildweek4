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
	final static String reset = "\u001B[0m";
	final static String rosso = "\u001B[31m";
	final static String verde = "\u001B[32m";
	final static String azzurro = "\u001B[36m";
	final static String giallo = "\u001B[33m";

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
					""", Tessera.class).setParameter("utente", utente).setParameter("oggi", LocalDate.now())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public String findAbbonamentoByTessera(Long idTessera) {
		try {
			Abbonamento result = em
					.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera.id = :idTessera", Abbonamento.class)
					.setParameter("idTessera", idTessera).getSingleResult();

			if (result.getScadenzaAbbonamento().isBefore(LocalDate.now())) {
				return"Abbonamento " +
						"[Durata: " + result.getDurataAbbonamento().toString().toLowerCase() +
						", " + rosso + "Scaduto il: " + result.getScadenzaAbbonamento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + reset +
						", Tessera ID: " + (result.getTessera() != null ? result.getTessera().getIdTessera() : "N/D") +
						"]";
			} else {
				return "Abbonamento " +
						"[Durata: " + result.getDurataAbbonamento().toString().toLowerCase() +
						", " + verde + "Scadenza: " + result.getScadenzaAbbonamento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + reset +
						", Tessera ID: " + (result.getTessera() != null ? result.getTessera().getIdTessera() : "N/D") +
						"]";
			}
		} catch (NoResultException e) {
			System.out.println("Non esiste un abbonamento valido per la tessera con identificativo " + idTessera + ".");
		}
        return "";
    }

	public boolean isAbbonamentoByTessera(Long idTessera) {

        try {
            Abbonamento result = em.createQuery(
                            "SELECT a FROM Abbonamento a WHERE a.tessera.id = :idTessera AND a.scadenzaAbbonamento >= :oggi", Abbonamento.class)
                    .setParameter("idTessera", idTessera)
                    .setParameter("oggi", LocalDate.now())
                    .getSingleResult();

            System.out.println(giallo +"Esiste già un abbonamento valido per la tessera con identificativo " + result.getTessera().getIdTessera() +
                    ", durata " + result.getDurataAbbonamento().toString().toLowerCase() +
                    " e scadenza il " + result.getScadenzaAbbonamento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "." + reset);

            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

}
