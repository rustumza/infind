/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.Numerador;
import Entidades.ProductoTipoIQE;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoProductoIQE extends Experto {

    ProductoTipoIQE pIQEExperto;

    public void guardar(ProductoTipoIQE pIQE) {
        Fachada.getInstancia().guardar(pIQE);

        //sumarle uno al numerador
        List<Numerador> numeroDisponibles = null;
        Criteria criterioProdIQE = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioProdIQE.add(Restrictions.eq("codificacion", "3.2."));//TODO: buscar codificacion
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioProdIQE);
        Numerador numerador = numeroDisponibles.get(0);
        String numString = numerador.getUltimaClasificacion();
        int numInt = Integer.parseInt(numString);
        numInt++;
        numerador.setUltimaClasificacion(String.valueOf(numInt));
        Fachada.getInstancia().guardar(numerador);

    }

    public ProductoTipoIQE buscarProductoIQE(String codigoPIQE) {

        List<ProductoTipoIQE> listaMateriaPrima = null;
        Criteria criterioProdIQE = Fachada.getInstancia().crearCriterioSinEliminado(ProductoTipoIQE.class);
        criterioProdIQE.add(Restrictions.eq("codigo", codigoPIQE));
        listaMateriaPrima = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioProdIQE);
        pIQEExperto = listaMateriaPrima.get(0);
        return pIQEExperto;

    }

    public ProductoTipoIQE getProductoIQE() {
        return pIQEExperto;
    }

    public void editar(ProductoTipoIQE pIQE) {
        Fachada.getInstancia().guardar(pIQE);
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
