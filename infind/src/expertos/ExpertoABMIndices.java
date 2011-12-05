/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.CostosFijos;
import Entidades.indices.Indices;
import Entidades.indices.NivelAusentismo;
import Entidades.indices.NivelDeRechazos;
import Entidades.indices.RotacionExterna;
import Entidades.indices.RotacionInterna;
import Entidades.indices.SeguimientoPlanSugerencias;
import Entidades.indices.UsoDeHP;
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

    public void guardarNivelRechazo(NivelDeRechazos nuevoNivelRechazos) {
        Fachada.getInstancia().guardar(nuevoNivelRechazos);
    }

    public void guardarUsoHP(UsoDeHP nuevoUsoHP) {
        Fachada.getInstancia().guardar(nuevoUsoHP);
    }

    public void guardarRotacionExterna(RotacionExterna nuevaRotacionExterna) {
        Fachada.getInstancia().guardar(nuevaRotacionExterna);
    }

    public void guardarRotacionInterna(RotacionInterna nuevaRotacionInterna) {
        Fachada.getInstancia().guardar(nuevaRotacionInterna);
    }

    public void guardarNivelAusentismo(NivelAusentismo nuevoNivelAusentismo) {
        Fachada.getInstancia().guardar(nuevoNivelAusentismo);
    }

    public void guardarSeguimientPlan(SeguimientoPlanSugerencias nuevoSeguimientoPlan) {
        Fachada.getInstancia().guardar(nuevoSeguimientoPlan);
    }
    
    
}
