/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.DetalleEstructuraDeProducto;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeEstructuraDeProducto;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;
import Entidades.ProductosFabricables;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoOrdenDeFabricacion extends Experto{
    
    MaestroDeArticulo articulo;

    public MaestroDeArticulo buscarProductoFinal(String codigo) {
        List<ProductoFinal> listaFinal = null;
        Criteria criterioProdFinal = Fachada.getInstancia().crearCriterio(ProductoFinal.class);
        criterioProdFinal.add(Restrictions.eq("codigo", codigo));
        listaFinal = Fachada.getInstancia().buscar(ProductoFinal.class, criterioProdFinal);
        articulo = listaFinal.get(0);
        return articulo;
    }

    public MaestroDeArticulo buscarProductoIntermedio(String codigo) {
        List<ProductoIntermedio> listaIntermedio = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        criterioProdInt.add(Restrictions.eq("codigo", codigo));
        listaIntermedio = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioProdInt);
        articulo = listaIntermedio.get(0);
        return articulo;
    }
    
    
    public void probarGeneracionDeOrdenDeFabricacion(Date fecha){
        

        ProductoTipoIQE iqe = ((ProductosFabricables)articulo).getProductoTipoIQE();        
        MaestroDeEstructuraDeProducto estructuraIQE = iqe.getMaestroEstructuraDeProducto();                
        MaestroDeEstructuraDeProducto estructura = null;
        if(articulo.getClass().equals(ProductoFinal.class)){
            estructura = ((ProductoFinal)articulo).getMaestroEstructuraDeProducto();
        }else{
            estructura = ((ProductoIntermedio)articulo).getMaestroEstructuraDeProducto();        
        }
        
        
        ExpertoStock expertoStock = new ExpertoStock();
        for (DetalleEstructuraDeProducto  detalle : estructura.getDetalleEstructuraProductoList()) {
            boolean hayStockSuficioente = expertoStock.getDisponiblilidadDeStockParaFechaDeterminada(detalle.getMaestroArticulo(), detalle.getCantidad(), fecha);
            if(!hayStockSuficioente){
                //TODO generar orden
            }
        }
        
        
        
        
    
    }
    
}
