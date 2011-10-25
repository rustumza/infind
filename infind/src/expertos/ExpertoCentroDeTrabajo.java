/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

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
public class ExpertoCentroDeTrabajo {

    /* public List<Producto> buscarArticulos(DTOArticulo dtoArticulo) throws ExpertoArticuloException {
    
    List<Producto> articulosEncontrados = null;
    
    if (dtoArticulo == null) {
    
    articulosEncontrados = Fachada.getInstancia().buscar(Producto.class, null);
    } else {
    Criteria criterioProducto = Fachada.getInstancia().crearCriterio(Producto.class);
    if (dtoArticulo.getCodigoProducto() != null) {
    criterioProducto.add(Restrictions.like("codigoProducto", dtoArticulo.getCodigoProducto()));
    }
    if (dtoArticulo.getNombreProducto() != null) {
    criterioProducto.add(Restrictions.like("nombreProducto", "%" + dtoArticulo.getNombreProducto() + "%").ignoreCase());
    }
    articulosEncontrados = Fachada.getInstancia().buscar(Producto.class, criterioProducto);
    }
    if (articulosEncontrados.isEmpty()) {
    throw new ExpertoArticuloException("No se encontraron Productos para los datos ingresados");
    
    }
    
    return articulosEncontrados;
    }*/

    /*public List<EstadoProducto> buscarEstados() {
    List<EstadoProducto> estadoEncontrada = null;
    
    estadoEncontrada = Fachada.getInstancia().buscar(EstadoProducto.class, null);
    
    return estadoEncontrada;
    
    }
    
    public List<TipoProducto> buscarTipoProducto() {
    List<TipoProducto> tipoEncontrada = null;
    
    tipoEncontrada = Fachada.getInstancia().buscar(TipoProducto.class, null);
    
    return tipoEncontrada;
    
    }*/
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

    /*public void eliminar(Producto producto) throws ExpertoCentroDeTrabajoException {
        producto.setEliminado(true);

        try {
            Fachada.getInstancia().guardar(producto);

        } catch (Exception ex) {
            throw new ExpertoCentroDeTrabajoException("Error al eliminar el Centro de Trabajo");
        }


    }*/

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
