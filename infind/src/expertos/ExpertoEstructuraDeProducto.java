/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.DetalleEstructuraDeProducto;
import Entidades.MaestroDeEstructuraDeProducto;
import Entidades.MateriaPrima;
import Entidades.ProductoComponente;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;
import Entidades.ProductosFabricables;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoEstructuraDeProducto {

    ProductosFabricables prod;
    MaestroDeEstructuraDeProducto estructura;

    public ExpertoEstructuraDeProducto() {
        estructura = new MaestroDeEstructuraDeProducto();
        estructura.setDetalleEstructuraProductoList(new ArrayList<DetalleEstructuraDeProducto>());
    }
    
    
    
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

    public MaestroDeEstructuraDeProducto buscarProductoFinal(String codigo) {
        List listaProdFinal = null;
        Criteria criterioProdFin = Fachada.getInstancia().crearCriterio(ProductoFinal.class);
        listaProdFinal = Fachada.getInstancia().buscar(ProductoFinal.class, criterioProdFin);
        if(listaProdFinal.isEmpty()){
            estructura.setProductoFinal((ProductoFinal)listaProdFinal.get(0));
        }
        return estructura;
    }

    public MaestroDeEstructuraDeProducto buscarProductoIQE(String codigo) {
        List listaProdIQE = null;
        Criteria criterioProdIQE = Fachada.getInstancia().crearCriterio(ProductoTipoIQE.class);
        listaProdIQE = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioProdIQE);        
        if(listaProdIQE.isEmpty()){
            estructura.setProductoTipoIQE((ProductoTipoIQE)listaProdIQE.get(0));
        }
        return estructura;
    }

    public MaestroDeEstructuraDeProducto buscarProductoIntermedio(String codigo) {
        
        List listaProdInt = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        listaProdInt = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioProdInt);        
        if(listaProdInt.isEmpty()){
            estructura.setProductoIntermedio((ProductoIntermedio)listaProdInt.get(0));
        }
        return estructura;
        
    }
    
}
