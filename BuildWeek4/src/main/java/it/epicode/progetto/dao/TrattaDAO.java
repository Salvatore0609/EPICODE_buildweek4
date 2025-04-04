package it.epicode.progetto.dao;
import it.epicode.progetto.entities.Tratta;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TrattaDAO {

	private EntityManager em;

	public void insert(Tratta e) {
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}

	public Tratta findById(Long id) {
		return em.find(Tratta.class, id);
	}

	public void delete(Long id) {
		Tratta mezzo = findById(id);
		if (mezzo != null) {
			em.getTransaction().begin();
			em.remove(mezzo);
			em.getTransaction().commit();
		}
	}

	public void update(Tratta e) {
		em.getTransaction().begin();
		em.merge(e);
		em.getTransaction().commit();
	}

	public List<Tratta> findAll() { // STAMPA TUTTE LE TRATTE
		return em.createQuery("SELECT t FROM Tratta t", Tratta.class).getResultList();
	}

}
