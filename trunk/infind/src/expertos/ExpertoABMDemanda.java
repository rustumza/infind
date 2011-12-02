/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.Demanda;
import excepciones.ExpertoCostosFijosException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoABMDemanda extends Experto {

    public List<Demanda> buscarDemandas() throws ExpertoCostosFijosException {

        List<Demanda> demandaEncontrada = null;

        Criteria criterioDemanda = Fachada.getInstancia().crearCriterioSinEliminado(Demanda.class);
        criterioDemanda.add(Restrictions.eq("eliminado", false));
        demandaEncontrada = Fachada.getInstancia().buscar(Demanda.class, criterioDemanda);
        return demandaEncontrada;
    }

    public void guardarDemanda(Demanda demanda) {
        Fachada.getInstancia().guardar(demanda);
    }
}
