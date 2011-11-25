/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MaestroDeArticulo;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoOrdenDeFabricacion extends Experto{

    public MaestroDeArticulo buscarProductoFinal(String codigo) {
        List<ProductoFinal> listaFinal = null;
        Criteria criterioProdFinal = Fachada.getInstancia().crearCriterio(ProductoFinal.class);
        criterioProdFinal.add(Restrictions.eq("codigo", codigo));
        listaFinal = Fachada.getInstancia().buscar(ProductoFinal.class, criterioProdFinal);
        return listaFinal.get(0);
    }

    public MaestroDeArticulo buscarProductoIntermedio(String codigo) {
        List<ProductoIntermedio> listaIntermedio = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        criterioProdInt.add(Restrictions.eq("codigo", codigo));
        listaIntermedio = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioProdInt);
        return listaIntermedio.get(0);
    }
    
    
    
}
