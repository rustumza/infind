/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.CostosFijos;
import Entidades.Indices;
import excepciones.ExpertoCostosFijosException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author edu
 */
public class ExpertoABMIndices extends Experto {

    public void guardarIndices(Indices indice) {
        Fachada.getInstancia().guardar(indice);
    }

    public Indices buscarIndices() throws ExpertoCostosFijosException {

        List<Indices> indicesEncontrados = null;

        Criteria criterioIndices = Fachada.getInstancia().crearCriterioSinEliminado(Indices.class);
        criterioIndices.add(Restrictions.eq("eliminado", false));
        indicesEncontrados = Fachada.getInstancia().buscar(Indices.class, criterioIndices);

        return indicesEncontrados.get(0);
    }
}
