package it.epicode.progetto.dao;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class TrattaDAO {

    private EntityManager em;



    public void insert (Tratta e) {
        em.persist(e);
        System.out.println("La tratta é stata inserita con successo");
    }


    public Tratta findById (Long id) {
        return em.find(Tratta.class, id);
    }


    public void delete (Long id) {
        Tratta mezzo = findById(id);
        if(mezzo != null) {
            em.remove(mezzo);
            System.out.println("La tratta é stata eliminata con successo");
        }
    }

    public void update (Tratta e) {
        em.merge(e);
    }




    public List<Tratta> findAll() {                                             //STAMPA TUTTE LE TRATTE
        return em.createQuery("SELECT t FROM Tratta t", Tratta.class).getResultList();
    }
}


