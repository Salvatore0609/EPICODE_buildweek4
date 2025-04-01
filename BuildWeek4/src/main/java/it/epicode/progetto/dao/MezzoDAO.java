package it.epicode.progetto.dao;
import it.epicode.progetto.mezzi.Mezzo;
import it.epicode.progetto.periodo_di_servizio.Stato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

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

    //Crea query tramite quale si possono trovare tutti i mezzi con stato in manutenzione e in servizio attraverso una stringa

    public void findMezzoByStato(String stato) {
        String query = "SELECT m FROM Mezzo m WHERE m.stato = :stato";
        em.createQuery(query, Mezzo.class)
                .setParameter("stato", stato)
                .getResultList()
                .forEach(System.out::println);
    }

    //crea query tramite la quale posso cambiare numero di biglietti vidimati di un mezzo

    public void updateBigliettiVidimati(Long id, Integer bigliettiVidimati) {
        Mezzo mezzo = findById(id);
        if (mezzo != null) {
            mezzo.setNumeroTicketVidimati(bigliettiVidimati);
            em.merge(mezzo);
        }
    }


    //creami un metodo per risalire all'id dell'oggetto creato

    public Long findIdByMezzo(Mezzo mezzo) {
        String query = "SELECT m.id FROM Mezzo m WHERE m = :mezzo";
        return em.createQuery(query, Long.class)
                .setParameter("mezzo", mezzo)
                .getSingleResult();
    }


    // metodo per stampare mezzi trovati tramite stato passato come enum
    public void findMezzoByStatoEnum(Stato stato) {
        String query = "SELECT m FROM Mezzo m WHERE m.stato = :stato";
        em.createQuery(query, Mezzo.class)
                .setParameter("stato", stato)
                .getResultList()
                .forEach(System.out::println);
    }

    //update di stato

    public void updateStato(Long id, Stato stato) {
        Mezzo mezzo = findById(id);
        if (mezzo != null) {
            mezzo.setStatoEnum(stato);
            em.merge(mezzo);
        }
    }

}



