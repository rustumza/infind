/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MateriaPrima;
import Entidades.ProductoComponente;
import Entidades.ProductoIntermedio;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoEstructuraDeProducto {

    public List buscarMateriasPrimas() {
        List listaMateriaPrima = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterioNumerador);
        return listaMateriaPrima;
    }

    public List buscarProductoComponente() {
        List listaProductoComponente = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterio(ProductoComponente.class);
        listaProductoComponente = Fachada.getInstancia().buscar(ProductoComponente.class, criterioNumerador);
        return listaProductoComponente;
    }

    public List buscarProductoIntermedio() {
        List listaIntermedio = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        listaIntermedio = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioNumerador);
        return listaIntermedio;
    }
    
}
