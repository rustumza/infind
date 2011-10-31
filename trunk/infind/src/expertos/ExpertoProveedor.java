/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MateriaPrima;
import Entidades.ProductoComponente;
import Entidades.Proveedor;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoProveedor {
    
    public List<MateriaPrima> buscarMateriasPrimas(){
    
        List<MateriaPrima> listaMateriaPrima = null;
        Criteria criterioMatPrim = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterioMatPrim);        
        return listaMateriaPrima;
    
    }

    
    public List<ProductoComponente> buscarProductosComponentes(){
    
        List<ProductoComponente> listaProductoComponentes = null;
        Criteria criterioProdComp = Fachada.getInstancia().crearCriterio(ProductoComponente.class);
        listaProductoComponentes = Fachada.getInstancia().buscar(ProductoComponente.class, criterioProdComp);        
        return listaProductoComponentes;
    
    }

    public void guardar(Proveedor proveedor) {
        Fachada.getInstancia().guardar(proveedor);
        for (MateriaPrima materiaPrima : proveedor.getMateriasPrimas()) {
            Fachada.getInstancia().guardar(materiaPrima);
        }
        for (ProductoComponente prodComp : proveedor.getProductosComponentes()) {
            Fachada.getInstancia().guardar(prodComp);
        }
    }
}
