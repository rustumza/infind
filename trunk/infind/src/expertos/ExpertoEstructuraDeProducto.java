/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MateriaPrima;
import Entidades.ProductoComponente;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;
import Entidades.ProductosFabricables;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoEstructuraDeProducto {

    ProductosFabricables prod;
    
    public List buscarMateriasPrimas() {
        List listaMateriaPrima = null;
        Criteria criterioMatPrim = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterioMatPrim);
        return listaMateriaPrima;
    }

    public List buscarProductoComponente() {
        List listaProductoComponente = null;
        Criteria criterioProdComp = Fachada.getInstancia().crearCriterio(ProductoComponente.class);
        listaProductoComponente = Fachada.getInstancia().buscar(ProductoComponente.class, criterioProdComp);
        return listaProductoComponente;
    }

    public List buscarProductoIntermedio() {
        List listaIntermedio = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        listaIntermedio = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioProdInt);
        return listaIntermedio;
    }

    public ProductosFabricables buscarProductoFinal(String codigo) {
        List listaProdFinal = null;
        Criteria criterioProdFin = Fachada.getInstancia().crearCriterio(ProductoFinal.class);
        listaProdFinal = Fachada.getInstancia().buscar(ProductoFinal.class, criterioProdFin);
        if(listaProdFinal.isEmpty()){
            prod = (ProductoFinal)listaProdFinal.get(0);
        }
        return prod;
    }

    public ProductosFabricables buscarProductoIQE(String codigo) {
        List listaProdIQE = null;
        Criteria criterioProdIQE = Fachada.getInstancia().crearCriterio(ProductoTipoIQE.class);
        listaProdIQE = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioProdIQE);        
        if(listaProdIQE.isEmpty()){
            prod = (ProductoTipoIQE)listaProdIQE.get(0);
        }
        return prod;
    }

    public ProductosFabricables buscarProductoIntermedio(String codigo) {
        
        List listaProdInt = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        listaProdInt = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioProdInt);        
        if(listaProdInt.isEmpty()){
            prod = (ProductoIntermedio)listaProdInt.get(0);
        }
        return prod;
        
    }
    
}
