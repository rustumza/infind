/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.CostosFijos;
import excepciones.ExpertoCostosFijosException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoABMCostosFijos extends Experto{
    
     public List<CostosFijos> buscarCostosFijos() throws ExpertoCostosFijosException{

        List<CostosFijos> costosFijosEncontrados = null;

        Criteria criterioCostosFijos = Fachada.getInstancia().crearCriterioSinEliminado(CostosFijos.class);
        criterioCostosFijos.add(Restrictions.eq("eliminado", false));
        costosFijosEncontrados = Fachada.getInstancia().buscar(CostosFijos.class, criterioCostosFijos);

        /*if (herramientaEncontrados.isEmpty()) {
            throw new ExpertoCentroDeTrabajoException("No se encontraron Herramientas para los datos ingresados");

        }*/

        return costosFijosEncontrados;
    }

    public void guardarNuevoCosto(CostosFijos nuevoCosto) {
       Fachada.getInstancia().guardar(nuevoCosto);
    }

    
    
}
