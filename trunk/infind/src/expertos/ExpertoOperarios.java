/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOOperario;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.Operario;
import Entidades.TipoOperario;
import excepciones.ExpertoOperarioException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoOperarios extends Experto {

    public List<Operario> buscarOperarios(DTOOperario dtoOperario) throws ExpertoOperarioException {

        List<Operario> operarioEncontrado = null;
        
        if (dtoOperario == null) {

            operarioEncontrado = Fachada.getInstancia().buscar(Operario.class, null);
        } else {
            Criteria criterioCentro = Fachada.getInstancia().crearCriterio(Operario.class);
            if (dtoOperario.getCodigoOperario() != null) {
                criterioCentro.add(Restrictions.like("codigoOperario", dtoOperario.getCodigoOperario()));
            }

            if (dtoOperario.getNombreOperario() != null) {
                criterioCentro.add(Restrictions.like("nombre", dtoOperario.getNombreOperario()));
            }

            operarioEncontrado = Fachada.getInstancia().buscar(Operario.class, criterioCentro);
        }
        if (operarioEncontrado.isEmpty()) {
            throw new ExpertoOperarioException("No se encontró Operario para los datos ingresados");

        }

        
        return operarioEncontrado;
    }

    public void guardar(Operario operario) throws ExpertoOperarioException {
        if (operarioInvalido(operario)) {
            throw new ExpertoOperarioException("Faltan completar Campos");
        } else {

            try {
                Fachada.getInstancia().guardar(operario);
            } catch (Exception ex) {
                throw new ExpertoOperarioException("Error al guardar el Operario");
            }
        }
    }

    public void eliminar(Operario operario) throws ExpertoOperarioException {

        operario.setEliminado(Boolean.TRUE);

        try {
            Fachada.getInstancia().guardar(operario);

        } catch (Exception ex) {
            throw new ExpertoOperarioException("Error al eliminar el Operario");
        }


    }

    private boolean operarioInvalido(Operario operario) {
        if (operario.getApellido().length() == 0) {
            return true;
        } else if (operario.getCodigoOperario().length() == 0) {
            return true;
        } else if (operario.getCorreoElectronico().length() == 0) {
            return true;
        } else if (operario.getDireccion().length() == 0) {
            return true;
        } else if (operario.getDni() == 0) {
            return true;
        } else if (operario.getNombre().length() == 0) {
            return true;
        } else if (operario.getTelefono() == 0) {
            return true;
        /*} else if (operario.getTipoOperario() == null) {
            return true;*/
        } else {
            return false;
        }
        

    }
    
    
    public List<TipoOperario> buscarTipoOperario(){
        List<TipoOperario> tipoOperarioEncontrada = null;

       tipoOperarioEncontrada = Fachada.getInstancia().buscar(TipoOperario.class, null);

       return tipoOperarioEncontrada;

    }
}
