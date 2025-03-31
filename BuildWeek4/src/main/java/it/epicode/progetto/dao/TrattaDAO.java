package it.epicode.progetto.dao;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class TrattaDAO {

    private EntityManager em;



    public void insert (Tratta e) {
        em.persist(e);
    }


    public Tratta findById (Long id) {
        return em.find(Tratta.class, id);
    }


    public void delete (Long id) {
        Tratta mezzo = findById(id);
        if(mezzo != null) {
            em.remove(mezzo);
        }
    }

    public void update (Tratta e) {
        em.merge(e);
    }
}
