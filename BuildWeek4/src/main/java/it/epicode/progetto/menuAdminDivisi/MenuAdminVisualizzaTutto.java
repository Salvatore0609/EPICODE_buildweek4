package it.epicode.progetto.menuAdminDivisi;

import it.epicode.progetto.dao.MezzoDAO;
import it.epicode.progetto.dao.TrattaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MenuAdminVisualizzaTutto {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        //DAO
        MezzoDAO mezziDAO = new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);


    }
}
