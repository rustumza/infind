/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;

/**
 *
 * @author diego
 */
public class Conexion {

    private static Conexion instancia;
    private EntityManagerFactory emFactory;
    private EntityManager entityManager;

    public Conexion() {
        emFactory = Persistence.createEntityManagerFactory("infindPU");
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public void iniciarTX() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = emFactory.createEntityManager();
        }
        entityManager.getTransaction().begin();
    }

    public void confirmarTx() {
        if (entityManager.isOpen() && entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public void deshacerTx() {
        if (entityManager.isOpen()) {
            entityManager.getTransaction().rollback();
        }
    }

    public Session getSession() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = emFactory.createEntityManager();
        }
        return (Session) entityManager.getDelegate();
    }

    EntityManager getEntityManager() {
        if (entityManager == null) {
            emFactory.createEntityManager();
        }
        return entityManager;
    }
}
