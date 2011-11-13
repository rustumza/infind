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
import org.hibernate.criterion.Restrictions;
import persistencia.Conexion;
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
        criterioProdFin.add(Restrictions.like("codigo", codigo));
        listaProdFinal = Fachada.getInstancia().buscar(ProductoFinal.class, criterioProdFin);
        if(!listaProdFinal.isEmpty()){
            prod = (ProductoFinal)listaProdFinal.get(0);
            if(prod.getMaestroEstructuraDeProducto() != null){
                estructura = prod.getMaestroEstructuraDeProducto();
                
            }else{
                estructura = new MaestroDeEstructuraDeProducto();
                estructura.setDetalleEstructuraProductoList(new ArrayList<DetalleEstructuraDeProducto>());
                estructura.setProductoFinal((ProductoFinal)prod);
            }
            prod.setMaestroEstructuraDeProducto(estructura);
        }else{
            prod = null;
            estructura = new MaestroDeEstructuraDeProducto();
        }
        return estructura;
    }

    public MaestroDeEstructuraDeProducto buscarProductoIQE(String codigo) {
        List listaProdIQE = null;
        Criteria criterioProdIQE = Fachada.getInstancia().crearCriterio(ProductoTipoIQE.class);
        criterioProdIQE.add(Restrictions.like("codigo", codigo));
        listaProdIQE = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioProdIQE);        
        if(!listaProdIQE.isEmpty()){
            prod = (ProductoTipoIQE)listaProdIQE.get(0);
            if(prod.getMaestroEstructuraDeProducto() != null){
                estructura = prod.getMaestroEstructuraDeProducto();
            }else{
                estructura = new MaestroDeEstructuraDeProducto();
                estructura.setDetalleEstructuraProductoList(new ArrayList<DetalleEstructuraDeProducto>());
                estructura.setProductoTipoIQE((ProductoTipoIQE)prod);
            }
            prod.setMaestroEstructuraDeProducto(estructura);
        }else{
            prod = null;
            estructura = new MaestroDeEstructuraDeProducto();
        }
        
        return estructura;
    }

    public MaestroDeEstructuraDeProducto buscarProductoIntermedio(String codigo) {
        
        List listaProdInt = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        criterioProdInt.add(Restrictions.like("codigo", codigo));
        listaProdInt = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioProdInt);        
        if(!listaProdInt.isEmpty()){
            prod = (ProductoIntermedio)listaProdInt.get(0);
            if(prod.getMaestroEstructuraDeProducto() != null){
                estructura = prod.getMaestroEstructuraDeProducto();
            }else{
                estructura = new MaestroDeEstructuraDeProducto();
                estructura.setDetalleEstructuraProductoList(new ArrayList<DetalleEstructuraDeProducto>());
                estructura.setProductoIntermedio((ProductoIntermedio)prod);
            }
            prod.setMaestroEstructuraDeProducto(estructura);
        }else{
            prod = null;
            estructura = new MaestroDeEstructuraDeProducto();
        }
        return estructura;
        
    }

    public MaestroDeEstructuraDeProducto agregarDetalle(DetalleEstructuraDeProducto detalle) {
        estructura.addDetalle(detalle);
        return estructura;
    }

    public MaestroDeEstructuraDeProducto quitarDetalle(DetalleEstructuraDeProducto detalleEstructuraDeProducto) {
        estructura.quitarDetalla(detalleEstructuraDeProducto);
        return estructura;
    }

    public void guardarEstructura() {
        Conexion.getInstancia().iniciarTX();
        for (DetalleEstructuraDeProducto detalle : estructura.getDetalleEstructuraProductoList()) {
            Fachada.getInstancia().guardarSinTranasaccion(detalle);
        }
        
        Fachada.getInstancia().guardarSinTranasaccion(estructura);
        Fachada.getInstancia().guardarSinTranasaccion(prod);
        Conexion.getInstancia().confirmarTx();
    }

    public MaestroDeEstructuraDeProducto editar(DetalleEstructuraDeProducto detalle, int detalleSeleccionado) {
        estructura.getDetalleEstructuraProductoList().get(detalleSeleccionado).setCantidad(detalle.getCantidad());
        estructura.getDetalleEstructuraProductoList().get(detalleSeleccionado).setMaestroArticulo(detalle.getMaestroArticulo());
        estructura.getDetalleEstructuraProductoList().get(detalleSeleccionado).setTipo(detalle.getTipo());
        return estructura;
    }

}
