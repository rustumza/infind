/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.Numerador;
import Entidades.ProductoIntermedio;
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
        Fachada.getInstancia().guardar(proInter);

        //sumarle uno al numerador
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
    //    if ((proInter.getTipo()).equals("PInter")) {
            criterioNumerador.add(Restrictions.eq("codificacion", "3.1."));
      //  }
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
}
