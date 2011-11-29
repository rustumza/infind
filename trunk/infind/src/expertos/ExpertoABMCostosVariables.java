/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.CostosFijos;
import Entidades.MaestroDeArticulo;
import excepciones.ExpertoCostosVariablesException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author edu
 */
public class ExpertoABMCostosVariables extends Experto {

    public List<MaestroDeArticulo> buscarMaestroArticulo() throws ExpertoCostosVariablesException{

        List<MaestroDeArticulo> maestroArticuloEncontrados = null;

        Criteria criterioMaestroArticulo = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeArticulo.class);
        criterioMaestroArticulo.add(Restrictions.eq("eliminado", false));
        maestroArticuloEncontrados = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterioMaestroArticulo);

        return maestroArticuloEncontrados;
    }
    
}
