package it.epicode.progetto.dao;
import it.epicode.progetto.mezzi.Mezzo;
import it.epicode.progetto.periodo_di_servizio.Stato;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import javax.naming.Name;
import java.util.List;


@AllArgsConstructor
public class MezzoDAO {
    private EntityManager em;

    public void insert(Mezzo e) {
        em.persist(e);
        System.out.println("Il mezzo è stato inserito con successo");
    }

    public Mezzo findById(Long id) {
        return em.find(Mezzo.class, id);
    }

    public void delete(Long id) {
        Mezzo mezzo = findById(id);
        if (mezzo != null) {
            em.remove(mezzo);
            System.out.println("Il mezzo è stato eliminato con successo");
        }
    }

    public void update(Mezzo e) {
        em.merge(e);
    }

    public List<Mezzo> findMezziByNome(String nome) {
        return em.createQuery("SELECT m FROM Mezzo m WHERE m.nome = :nome", Mezzo.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public void updateStato(String nome, Stato stato) {
        List<Mezzo> mezzi = findMezziByNome(nome);
        if (!mezzi.isEmpty()) {
            mezzi.forEach(m -> m.setStatoEnum(stato));
        }
    }

    public List<Mezzo> findMezzoByStato(Stato stato) {
        return em.createQuery("SELECT m FROM Mezzo m WHERE m.stato = :stato", Mezzo.class)
                .setParameter("stato", stato)
                .getResultList();
    }

}



