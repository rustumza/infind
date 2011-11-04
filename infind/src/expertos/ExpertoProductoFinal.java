/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.ProductoFinal;
import Entidades.Numerador;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoProductoFinal extends Experto {

    ProductoFinal pFinalExperto;

    public void guardar(ProductoFinal pFinal) {
        Fachada.getInstancia().guardar(pFinal);

        //sumarle uno al numerador
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioNumerador.add(Restrictions.eq("codificacion", "4.1.1."));
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        Numerador numerador = numeroDisponibles.get(0);
        String numString = numerador.getUltimaClasificacion();
        int numInt = Integer.parseInt(numString);
        numInt++;
        numerador.setUltimaClasificacion(String.valueOf(numInt));
        Fachada.getInstancia().guardar(numerador);

    }

    public ProductoFinal buscarProductoFinal(String codigoPFinal) {

        List<ProductoFinal> listaMateriaPrima = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        criterioNumerador.add(Restrictions.eq("codigo", codigoPFinal));
        listaMateriaPrima = Fachada.getInstancia().buscar(ProductoFinal.class, criterioNumerador);
        pFinalExperto = listaMateriaPrima.get(0);
        return pFinalExperto;

    }

    public ProductoFinal getProductoFinal() {
        return pFinalExperto;
    }

    public void editar(ProductoFinal pFinal) {
        Fachada.getInstancia().guardar(pFinal);
    }
}
