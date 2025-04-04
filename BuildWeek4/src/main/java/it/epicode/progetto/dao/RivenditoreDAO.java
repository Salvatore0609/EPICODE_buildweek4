package it.epicode.progetto.dao;

import it.epicode.progetto.entities.Rivenditore;
import it.epicode.progetto.entities.DistributoriAutomatici;
import it.epicode.progetto.enums.StatoDistributori;
import it.epicode.progetto.exceptions.RivenditoreException;
import it.epicode.progetto.entities.RivenditoriAutorizzati;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.time.LocalDate;
import java.util.List;

public class RivenditoreDAO {
	private EntityManager em;

	public RivenditoreDAO(EntityManager em) {
		this.em = em;
	}

	public void insert(Rivenditore r) {
		try {
			em.persist(r);
		} catch (PersistenceException ex) {
			throw new RivenditoreException("Errore durante l'aggiunta del rivenditore" + ex);
		}
	}

	public Rivenditore delete(Long id) {
		try {
			Rivenditore rivenditore = em.find(Rivenditore.class, id);
			if (rivenditore != null) {
				em.remove(rivenditore);
			} else {
				throw new RivenditoreException("Rivenditore non trovato");
			}
			return rivenditore;
		} catch (PersistenceException ex) {
			throw new RivenditoreException("Errore durante la rimozione del rivenditore" + ex);
		}
	}

	public Rivenditore findById(Long id) {
		try {
			if (id != null) {
				return em.find(Rivenditore.class, id);
			} else {
				throw new RivenditoreException("Rivenditore non trovato");
			}
		} catch (PersistenceException ex) {
			throw new RivenditoreException("Errore durante la ricerca del rivenditore" + ex);
		}
	}

	public Rivenditore update(Rivenditore r) {
		try {
			if (r != null) {
				return em.merge(r);
			} else {
				throw new RivenditoreException("Rivenditore non trovato");
			}
		} catch (PersistenceException ex) {
			throw new RivenditoreException("Errore durante l'aggiornamento del rivenditore" + ex);
		}
	}

	public List<Rivenditore> findAll() {
		return em.createQuery(
				"select r from Rivenditore r where type(r) = RivenditoriAutorizzati or (type(r) = DistributoriAutomatici and r.stato = :attivo)",
				Rivenditore.class).setParameter("attivo", StatoDistributori.ATTIVO).getResultList();
	}

	public List<Rivenditore> findDistributoriFuoriServizio() {
		return em.createQuery(
				"select r from Rivenditore r where type(r) = DistributoriAutomatici and r.stato = :fuoriServizio", Rivenditore.class)
				.setParameter("fuoriServizio", StatoDistributori.FUORI_SERVIZIO)
				.getResultList();
	}

	public void aggiornaBigliettiAbbonamentiEmessi() {
		em.getTransaction().begin();
		List<Rivenditore> rivenditori = em.createQuery("select r from Rivenditore r", Rivenditore.class)
				.getResultList();

		for (Rivenditore rivenditore : rivenditori) {
			long numeroBiglietti = em
					.createQuery("select count(b) from Biglietto b where b.rivenditore.idRivenditore = :idRivenditore",
							Long.class)
					.setParameter("idRivenditore", rivenditore.getIdRivenditore()).getSingleResult();

			long numeroAbbonamenti = em.createQuery(
					"select count(b) from Abbonamento b where b.rivenditore.idRivenditore = :idRivenditore", Long.class)
					.setParameter("idRivenditore", rivenditore.getIdRivenditore()).getSingleResult();

			if (rivenditore instanceof RivenditoriAutorizzati) {
				em.createQuery(
						"update RivenditoriAutorizzati r set r.bigliettiEmessi = :bigliettiEmessi, r.abbonamentiEmessi = :abbonamentiEmessi where r.idRivenditore = :idRivenditore")
						.setParameter("bigliettiEmessi", numeroBiglietti)
						.setParameter("abbonamentiEmessi", numeroAbbonamenti)
						.setParameter("idRivenditore", rivenditore.getIdRivenditore()).executeUpdate();

			} else if (rivenditore instanceof DistributoriAutomatici) {
				em.createQuery(
						"update DistributoriAutomatici d set d.bigliettiEmessi = :bigliettiEmessi,d.abbonamentiEmessi = :abbonamentiEmessi where d.idRivenditore = :idRivenditore")
						.setParameter("bigliettiEmessi", numeroBiglietti)
						.setParameter("abbonamentiEmessi", numeroAbbonamenti)
						.setParameter("idRivenditore", rivenditore.getIdRivenditore()).executeUpdate();
			}
		}
		em.getTransaction().commit();
	}

	public void ottieniBigliettiAbbonamentiEmessi(long idRivenditore, LocalDate dataInizio, LocalDate dataFine) {

		Rivenditore rivenditore = em.createQuery("select r from Rivenditore r where r.idRivenditore = :idRivenditore", Rivenditore.class)
				.setParameter("idRivenditore", idRivenditore).getSingleResult();

		long numeroBiglietti = em.createQuery(
				"select count(b) from Biglietto b where b.rivenditore.idRivenditore = :idRivenditore and b.dataDiEmissione between :dataInizio and :dataFine",
				Long.class).setParameter("idRivenditore", idRivenditore).setParameter("dataInizio", dataInizio)
				.setParameter("dataFine", dataFine).getSingleResult();

		long numeroAbbonamenti = em.createQuery(
				"select count(b) from Abbonamento b where b.rivenditore.idRivenditore = :idRivenditore and b.dataDiEmissione between :dataInizio and :dataFine",
				Long.class).setParameter("idRivenditore", idRivenditore).setParameter("dataInizio", dataInizio)
				.setParameter("dataFine", dataFine).getSingleResult();

		if (rivenditore instanceof RivenditoriAutorizzati) {
			System.out.println("Il numero di biglietti emessi per " + rivenditore.getNome() + " nel periodo "
					+ dataInizio + " e " + dataFine + " è: " + numeroBiglietti);
			System.out.println("Il numero di abbonamenti emessi per " + rivenditore.getNome() + " nel periodo "
					+ dataInizio + " e " + dataFine + " è: " + numeroAbbonamenti);
		} else if (rivenditore instanceof DistributoriAutomatici) {
			System.out.println("Il numero di biglietti emessi per " + rivenditore.getNome() + " nel periodo "
					+ dataInizio + " e " + dataFine + " è: " + numeroBiglietti);
			System.out.println("Il numero di abbonamenti emessi per " + rivenditore.getNome() + " nel periodo "
					+ dataInizio + " e " + dataFine + " è: " + numeroAbbonamenti);
		}
		try {
			System.out.println();
			System.out.print("Premi invio per continuare...");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
