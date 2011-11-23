/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.Numerador;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;
import Entidades.Stock;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author diego
 */
public class ExpertoProductoIntermedio extends Experto {

    ProductoIntermedio productoIntermedioExperto;

    public void guardar(ProductoIntermedio proInter) {
        Stock stock = new Stock();
        stock.setCantidadFisicaReal(0);
        stock.setCantidadPorEntrar(0);
        stock.setCantidadReservada(0);
        stock.setEliminado(Boolean.TRUE);
        proInter.setStock(stock);
        Fachada.getInstancia().guardar(proInter);

        //sumarle uno al numerador
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioNumerador.add(Restrictions.eq("codificacion", "3.1."));
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        Numerador numerador = numeroDisponibles.get(0);
        String numString = numerador.getUltimaClasificacion();
        int numInt = Integer.parseInt(numString);
        numInt++;
        numerador.setUltimaClasificacion(String.valueOf(numInt));
        Fachada.getInstancia().guardar(numerador);

    }

    public ProductoIntermedio buscarProductoIntermedio(String codigoProductoIntermedio) {

        List<ProductoIntermedio> listaProductoIntermedio = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(ProductoIntermedio.class);
        criterioNumerador.add(Restrictions.eq("codigo", codigoProductoIntermedio));
        listaProductoIntermedio = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioNumerador);
        productoIntermedioExperto = listaProductoIntermedio.get(0);
        return productoIntermedioExperto;

    }

    public ProductoIntermedio getProductoIntermedio() {
        return productoIntermedioExperto;
    }

    public void editar(ProductoIntermedio proInter) {
        Fachada.getInstancia().guardar(proInter);
    }
    
    public List<ProductoTipoIQE> buscarProductosIQE(){
        
        List<ProductoTipoIQE> listaProductosIQE = null;
        Criteria criterioPIQE = Fachada.getInstancia().crearCriterioSinEliminado(ProductoTipoIQE.class);
        listaProductosIQE = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioPIQE);
        if(listaProductosIQE == null){
            listaProductosIQE = new ArrayList<ProductoTipoIQE>();
        }
        return listaProductosIQE;
    
    }
}
