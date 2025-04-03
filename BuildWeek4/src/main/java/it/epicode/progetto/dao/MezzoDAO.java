package it.epicode.progetto.dao;
import it.epicode.progetto.entities.Mezzo;
import it.epicode.progetto.enums.Stato;
import it.epicode.progetto.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class MezzoDAO {
	private EntityManager em;

	// PREDEFINITI
	public void insert(Mezzo e) {
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		System.out.println("Il mezzo è stato inserito con successo");
	}
	public Mezzo findById(Long id) {
		return em.find(Mezzo.class, id);
	}
	public void delete(Long id) {
		Mezzo mezzo = findById(id);
		if (mezzo != null) {
			em.getTransaction().begin();
			em.remove(mezzo);
			em.getTransaction().commit();
			System.out.println("Il mezzo è stato eliminato con successo");
		}
	}
	public void update(Mezzo e) {
		em.getTransaction().begin();
		em.merge(e);
		em.getTransaction().commit();
	}

	// INIZIO METODI SPEFICIFI

	// metodo associato alla vidimazione di un biglietto singolo ad ogni accesso a
	// un mezzo da parte dell'utente
	// (i biglietti vidimati di ogni mezzo fanno +1 ogni volta che un utente sale su
	// un mezzo)



	public List<Mezzo> findAll() { // STAMPA TUTTI I MEZZI
		TypedQuery<Mezzo> query = em.createQuery("SELECT m FROM Mezzo m", Mezzo.class);
		return query.getResultList();
	}

	public void updateBigliettiVidimati(Long id, Integer bigliettiVidimati) { // AGGIUNGE BIGLIETTI VIDIMATI
		Mezzo mezzo = findById(id);
		if (mezzo != null) {
			mezzo.setNumeroBigliettiVidimati(bigliettiVidimati);
			em.getTransaction().begin();
			em.merge(mezzo);
			em.getTransaction().commit();
		}
	}

	// stampa tutti i mezzi per una determinata tratta
	public List<Mezzo> findMezzoByTratta(Tratta tratta) {
		String query = "SELECT m FROM Mezzo m WHERE m.tratta = :tratta";
		return em.createQuery(query, Mezzo.class).setParameter("tratta", tratta).getResultList();
	}

	// metodo per stampare mezzi trovati tramite stato passato come enum
	public List<Mezzo> findMezzoByStatoEnum(Stato stato) { // STAMPA TUTTI I MEZZI DELLO STATO AGGIUNTO COME PARAMETRO
		String query = "SELECT m FROM Mezzo m WHERE m.stato = :stato";
		return em.createQuery(query, Mezzo.class).setParameter("stato", stato).getResultList();

	}

	// update di stato

	public void updateStato(Long id, Stato stato) { // CAMBIA LO STATO DEL MEZZO TRAMITE IL SUO ID
		Mezzo mezzo = findById(id);
		if (mezzo != null) {
			mezzo.setStatoEnum(stato);
			em.getTransaction().begin();
			em.merge(mezzo);
			em.getTransaction().commit();
		}
	}

	public void updateTratta(Long id, Tratta tratta) { // CAMBIA LA TRATTA DEL MEZZO TRAMITE IL SUO ID
		Mezzo mezzo = findById(id);
		if (mezzo != null) {
			mezzo.setTratta(tratta);
			em.getTransaction().begin();
			em.merge(mezzo);
			em.getTransaction().commit();
		}
	}

	public void updateCapienza(Long id, Integer capienza) { // CAMBIA LA CAPIENZA DEL MEZZO TRAMITE IL SUO ID
		Mezzo mezzo = findById(id);
		if (mezzo != null) {
			mezzo.setCapienza(capienza);
			em.getTransaction().begin();
			em.merge(mezzo);
			em.getTransaction().commit();
		}
	}

	public void updateVolteTrattaPercorsa(Long id, Integer volteTrattaPercorsa) { // CAMBIA LE VOLTE DELLA TRATTA DEL
																					// MEZZO TRAMITE IL SUO ID
		Mezzo mezzo = findById(id);
		if (mezzo != null) {
			mezzo.setVolteTrattaPercorsa(volteTrattaPercorsa);
			em.getTransaction().begin();
			em.merge(mezzo);
			em.getTransaction().commit();
		}
	}

	public void findAllBigliettiVidimati(LocalDate dataInizio, LocalDate dataFine) { // STAMPA TUTTI I BIGLIETTI VIDIMATI IN UN PERIODO DI TEMPO
		List<Mezzo> mezzi = findAll();
		int bigliettiVidimati = 0;
		for (Mezzo mezzo : mezzi) {
			long bigliettiVidimatiPerMezzo = em.createQuery("select count(b) from Biglietto b where b.mezzo.id = :idMezzo and b.dataVidimato between :dataInizio and :dataFine", Long.class)
					.setParameter("idMezzo", mezzo.getId())
					.setParameter("dataInizio", dataInizio)
					.setParameter("dataFine", dataFine)
					.getSingleResult();
			bigliettiVidimati += (int) bigliettiVidimatiPerMezzo;
		}
		System.out.println("Biglietti vidimati tra " + dataInizio + " e " + dataFine + " : " + bigliettiVidimati);
	}

	// 	Stampa tutti i biglietti vidimati per mezzo
	public void ottieniBigliettiVidimatiPerUnMezzo(long idMezzo) {
		Mezzo mezzo = findById(idMezzo);
		if (mezzo != null) {
			long numeroBiglietti = em.createQuery("select count(b) from Biglietto b where b.mezzo.id = :idMezzo", Long.class)
					.setParameter("idMezzo", idMezzo)
					.getSingleResult();

			System.out.println("Il mezzo " + mezzo.getId() + " ha vidimato " + numeroBiglietti + " biglietti.");
		} else  {
			System.out.println("Mezzo non trovato con id " + idMezzo + ".");
		}

	}
	// Metodo per stampare posti liberi su un mezzo (i posti liberi non sono altro
	// che (capienza - numeroBigliettiVidimati)

	public void findPostiLiberi(Long id) {
		Mezzo mezzo = findById(id);
		if (mezzo != null) {
			int postiLiberi = mezzo.getCapienza() - mezzo.getNumeroBigliettiVidimati();
			System.out.println("Il mezzo " + mezzo.getId() + " ha " + postiLiberi + " posti liberi.");
		}
	}
	// Stampa lista di tutti i mezzi con posti liberi

	public void findAllPostiLiberi() {
		TypedQuery<Mezzo> query = em.createQuery("SELECT m FROM Mezzo m", Mezzo.class);
		query.getResultList().forEach(mezzo -> {
			int postiLiberi = mezzo.getCapienza() - mezzo.getNumeroBigliettiVidimati();
			if (postiLiberi >= 1 && mezzo.getClasse().equals("Autobus")) {
				System.out.println("L'" + mezzo.getClasse().toLowerCase() + " numero " + mezzo.getId() + " ha "
						+ postiLiberi + " posti liberi.");
			} else if (postiLiberi >= 1 && mezzo.getClasse().equals("Tram")) {
				System.out.println("Il " + mezzo.getClasse().toLowerCase() + " numero " + mezzo.getId() + " ha "
						+ postiLiberi + " posti liberi.");
			} ;
		});
	}

}
