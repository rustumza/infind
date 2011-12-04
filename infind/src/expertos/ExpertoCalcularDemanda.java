/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.Demanda;
import Entidades.Parametros;
import excepciones.ExpertoCalcularDemandaException;
import excepciones.ExpertoCostosFijosException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoCalcularDemanda extends Experto {

    public List<Parametros> buscarParametros() throws ExpertoCalcularDemandaException {
        List<Parametros> parametrosEncontrados = null;
        Criteria criterioParametros = Fachada.getInstancia().crearCriterioSinEliminado(Parametros.class);
        parametrosEncontrados = Fachada.getInstancia().buscar(Parametros.class, criterioParametros);
        return parametrosEncontrados;
    }

    public void guardarDemanda(Demanda demanda) {
        Fachada.getInstancia().guardar(demanda);
    }
}
