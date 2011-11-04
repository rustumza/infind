/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.ProductoComponente;
import Entidades.Numerador;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoProductoComponente extends Experto{

    ProductoComponente productoComponenteExperto;
    
    public void guardar(ProductoComponente proComp) {
        Fachada.getInstancia().guardar(proComp);
        
        //sumarle uno al numerador
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
            criterioNumerador.add(Restrictions.eq("codificacion", "4.1."));
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        Numerador numerador = numeroDisponibles.get(0);
        String numString = numerador.getUltimaClasificacion();
        int numInt = Integer.parseInt(numString);
        numInt++;
        numerador.setUltimaClasificacion(String.valueOf(numInt));
        Fachada.getInstancia().guardar(numerador);
        
    }

    public ProductoComponente buscarProductoComponente(String codigoProductoComponente) {
        
        List<ProductoComponente> listaProductoComponente = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(ProductoComponente.class);
        criterioNumerador.add(Restrictions.eq("codigo", codigoProductoComponente));
        listaProductoComponente = Fachada.getInstancia().buscar(ProductoComponente.class, criterioNumerador);
        productoComponenteExperto = listaProductoComponente.get(0);
        return productoComponenteExperto;
        
    }

    public ProductoComponente getProductoComponente(){
        return productoComponenteExperto;
    }

    public void editar(ProductoComponente proComp) {
        Fachada.getInstancia().guardar(proComp);
    }

}
