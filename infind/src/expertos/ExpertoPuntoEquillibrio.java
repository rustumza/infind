/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.CostoVariable;
import Entidades.MaestroDeArticulo;
import Entidades.ProductoFinal;
import excepciones.ExpertoPuntoEquilibrioException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoPuntoEquillibrio extends Experto{
    

     public List<CostoVariable> buscarCostosVariables(MaestroDeArticulo productoIntermedioBase) throws ExpertoPuntoEquilibrioException{
        
        List<CostoVariable> costosEncontrados = null;
        Criteria criterioCostosVariables = Fachada.getInstancia().crearCriterioSinEliminado(CostoVariable.class);
        criterioCostosVariables.add(Restrictions.eq("articulo", productoIntermedioBase));
        costosEncontrados = Fachada.getInstancia().buscar(CostoVariable.class, criterioCostosVariables);

        return costosEncontrados;
    }
     
     public List<ProductoFinal> buscarProductoFinal(){
        List<ProductoFinal> productoFinalEncontrados = null;
        Criteria criterioFinalIntermedio = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        criterioFinalIntermedio.add(Restrictions.eq("eliminado", false));
        productoFinalEncontrados = Fachada.getInstancia().buscar(ProductoFinal.class, criterioFinalIntermedio);

        return productoFinalEncontrados;
        
        
    }
     
     public List<MaestroDeArticulo> buscarProducto(){
        List<MaestroDeArticulo> productoFinalEncontrados = null;
        Criteria criterioFinalIntermedio = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        criterioFinalIntermedio.add(Restrictions.eq("eliminado", false));
        productoFinalEncontrados = Fachada.getInstancia().buscar(ProductoFinal.class, criterioFinalIntermedio);

        return productoFinalEncontrados;
        
        
    }
     
     public List<CostoVariable> buscarCostosVariablesProdEstandar(MaestroDeArticulo productoIntermedioBase)  throws ExpertoPuntoEquilibrioException{
        List<CostoVariable> costosEncontrados = null;
        Criteria criterioCostosVariables = Fachada.getInstancia().crearCriterioSinEliminado(CostoVariable.class);
        criterioCostosVariables.add(Restrictions.eq("eliminado", false));
        criterioCostosVariables.add(Restrictions.eq("articulo", productoIntermedioBase));
        costosEncontrados = Fachada.getInstancia().buscar(CostoVariable.class, criterioCostosVariables);

        return costosEncontrados;
    
    }
     
     public MaestroDeArticulo buscarProductoEstandar() {
        List<MaestroDeArticulo> productoEstandarEncontrados = null;
        Criteria criterioProductoEstandar = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeArticulo.class);
        criterioProductoEstandar.add(Restrictions.eq("eliminado", false));
        criterioProductoEstandar.add(Restrictions.eq("codigo", "4.1.1"));
        productoEstandarEncontrados = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterioProductoEstandar);

        return productoEstandarEncontrados.get(0);
    }
    
    
}
    

