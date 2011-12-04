/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.Demanda;
import Entidades.ProductoFinal;
import excepciones.ExpertoABMDemandaExcepcion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoABMDemanda extends Experto {

    public List<Demanda> buscarDemandas() throws ExpertoABMDemandaExcepcion {
        List<Demanda> demandaEncontrada = null;
        Criteria criterioDemanda = Fachada.getInstancia().crearCriterioSinEliminado(Demanda.class);
        criterioDemanda.add(Restrictions.eq("eliminado", false));
        demandaEncontrada = Fachada.getInstancia().buscar(Demanda.class, criterioDemanda);
        return demandaEncontrada;
    }

    public void guardarDemanda(Demanda demanda) {
        Fachada.getInstancia().guardar(demanda);
    }

    public List<ProductoFinal> buscarProductos() {
        List<ProductoFinal> productoencontrado = null;
        Criteria criterioProducto = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        criterioProducto.add(Restrictions.eq("eliminado", false));
        productoencontrado = Fachada.getInstancia().buscar(ProductoFinal.class, criterioProducto);
        return productoencontrado;
    }

    public void guardarProductos(ProductoFinal productos) {
        Fachada.getInstancia().guardar(productos);
    }
}
