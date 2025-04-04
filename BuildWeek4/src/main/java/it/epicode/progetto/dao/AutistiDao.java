package it.epicode.progetto.dao;

import it.epicode.progetto.entities.Autista;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AutistiDao {
    private EntityManager em;

    public void save(Autista a) {
        em.persist(a);
    }
    public void delete(Autista a) {
        //imposto l'attributo attivo a false
        a.setAttivo(false);
    }
    public Autista findById(Long id) {
        return em.find(Autista.class, id);
    }

    public void findAllAutistiAttivi() {
        em.createQuery("SELECT a FROM Autista a WHERE a.attivo = true", Autista.class).getResultList().forEach(a -> System.out.println(a));
        System.out.println();
        System.out.println("Premi INVIO per tornare al menu di gestione autisti");
    }
    public void findAllAutistiLicenziati() {
        em.createQuery("SELECT a FROM Autista a WHERE a.attivo = false", Autista.class).getResultList().forEach(a -> System.out.println(a));
        System.out.println();
        System.out.println("Premi INVIO per tornare al menu di gestione autisti");
    }

}
