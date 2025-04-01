package it.epicode.progetto.dao;
import it.epicode.progetto.mezzi.Mezzo;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MezzoDAO {
    private EntityManager em;

    public void insert (Mezzo e) {
        em.persist(e);
        System.out.println("Il mezzo è stato inserito con successo");
    }


    public Mezzo findById (Long id) {
        return em.find(Mezzo.class, id);
    }


    public void delete (Long id) {
        Mezzo mezzo = findById(id);
        if(mezzo != null) {
            em.remove(mezzo);
            System.out.println("Il mezzo è stato eliminato con successo");
        }
    }

    public void update (Mezzo e) {

        em.merge(e);

        }

        //Crea query tramite quale si possono trovare tutti i mezzi con stato in manutenzione e in servizio attraverso una stringa

    public void findByStato(String stato) {
        String query = "SELECT m FROM Mezzo m WHERE m.periodoDiServizio.stato = :stato";
        em.createQuery(query, Mezzo.class)
                .setParameter("stato", stato)
                .getResultList()
                .forEach(System.out::println);}










    }



