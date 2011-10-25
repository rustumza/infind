/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOCentro;
import Entidades.MaestroDeCentroDeTrabajo;
import excepciones.ExpertoCentroDeTrabajoException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoCentroDeTrabajo extends Experto {

    public List<MaestroDeCentroDeTrabajo> buscarCentrosDeTrabajo(MaestroDeCentroDeTrabajo centro) throws ExpertoCentroDeTrabajoException {

        List<MaestroDeCentroDeTrabajo> centrosEncontrados = null;

        if (centro == null) {

            centrosEncontrados = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, null);
        } else {
            Criteria criterioCentro = Fachada.getInstancia().crearCriterio(MaestroDeCentroDeTrabajo.class);
            if (centro.getCodigo() != null) {
                criterioCentro.add(Restrictions.like("codigo", centro.getCodigo()));
            }

            if (centro.getNombreCentro() != null) {
                criterioCentro.add(Restrictions.like("nombreCentro", centro.getNombreCentro()));
            }

            centrosEncontrados = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, criterioCentro);
        }
        /*if (centrosEncontrados.isEmpty()) {
        throw new ExpertoCentroDeTrabajoException("No se encontraron Productos para los datos ingresados");
        
        }*/

        return centrosEncontrados;
    }

    //busca el centro desde la pantalla editar centros
    public MaestroDeCentroDeTrabajo buscarCentros(DTOCentro dtoCentro) throws ExpertoCentroDeTrabajoException {

        List<MaestroDeCentroDeTrabajo> centroEncontrado = null;
        MaestroDeCentroDeTrabajo centroDevuelto = null;

        if (dtoCentro == null) {

            centroEncontrado = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, null);
        } else {
            Criteria criterioCentro = Fachada.getInstancia().crearCriterio(MaestroDeCentroDeTrabajo.class);
            if (dtoCentro.getCodigoCentro() != null) {
                criterioCentro.add(Restrictions.like("codigo", dtoCentro.getCodigoCentro()));
            }

            if (dtoCentro.getNombreCentro() != null) {
                criterioCentro.add(Restrictions.like("nombreCentro", dtoCentro.getNombreCentro()));
            }

            centroEncontrado = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, criterioCentro);
        }
        if (centroEncontrado.isEmpty()) {
            throw new ExpertoCentroDeTrabajoException("No se encontró Centro de Trabajo para los datos ingresados");

        }

        centroDevuelto = centroEncontrado.get(0);
        return centroDevuelto;
    }

    public void guardar(MaestroDeCentroDeTrabajo centroDeTrabajo) throws ExpertoCentroDeTrabajoException {
        if (centroInvalido(centroDeTrabajo)) {
            throw new ExpertoCentroDeTrabajoException("Faltan completar Campos");
        } else {

            try {
                Fachada.getInstancia().guardar(centroDeTrabajo);
            } catch (Exception ex) {
                throw new ExpertoCentroDeTrabajoException("Error al guardar el Centro de Trabajo");
            }
        }
    }

    public void eliminar(MaestroDeCentroDeTrabajo centro) throws ExpertoCentroDeTrabajoException {
        
        centro.setEliminado(true);

        try {
            Fachada.getInstancia().guardar(centro);

        } catch (Exception ex) {
            throw new ExpertoCentroDeTrabajoException("Error al eliminar el Centro de Trabajo");
        }


    }

    private boolean centroInvalido(MaestroDeCentroDeTrabajo centro) {
        if (centro.getCodigo().length() == 0) {
            return true;
        } else if (centro.getDescripcion().length() == 0) {
            return true;
        } else {
            return false;
        }

    }
}
