/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.exception.SQLGrammarException;

/**
 *
 * @author diego
 */
public class IntermediarioGenerico {

    private EntityManager em;

    public IntermediarioGenerico() {
    }

    /**
     * Guarda un objeto en la base de datos:
     * actualiza si el objeto ya tiene oid
     * @param obj
     * @throws Exception 
     */
    public void guardar(ObjetoPersitente obj) throws Exception {
        Conexion.getInstancia().iniciarTX();
        em = Conexion.getInstancia().getEntityManager();
        try {
            if (obj.getId() != null) {
                em.merge(obj);
            } else {
                em.persist(obj);
            }
            Conexion.getInstancia().confirmarTx();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error");
        }
    }

    public List buscar(Class object, Criteria criterio) {
        Conexion.getInstancia().iniciarTX();
        List<ObjetoPersitente> lista = null;
        try {
            lista = criterio.list();
        } catch (SQLGrammarException ex) {
            System.out.println(ex.getMessage());
        }
        Conexion.getInstancia().confirmarTx();
        return lista;
    }
}
