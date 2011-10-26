/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author diego
 */
public class Fachada {

    private static Fachada instancia;

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public Criteria crearCriterio(Class tipoObjeto) {
        Session s = null;
        s = Conexion.getInstancia().getSession();
        Criteria criteria = s.createCriteria(tipoObjeto);
        criteria.add(Restrictions.eq("eliminado", false));
        return criteria;
    }
    
    public Criteria crearCriterioSinEliminado(Class tipoObjeto) {
        Session s = null;
        s = Conexion.getInstancia().getSession();
        Criteria criteria = s.createCriteria(tipoObjeto);
        return criteria;
    }
    

    public List buscar(Class tipoObjeto, Criteria criterio) {
        if (criterio == null) {
            criterio = crearCriterio(tipoObjeto);
        }
        return (new IntermediarioGenerico()).buscar(tipoObjeto, criterio);
    }

    public void guardar(ObjetoPersitente obj) {
        try {
            (new IntermediarioGenerico()).guardar(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
