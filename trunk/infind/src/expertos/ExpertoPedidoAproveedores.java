/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MaestroDeArticulo;
import Entidades.MateriaPrima;
import Entidades.PedidoAProveedor;
import Entidades.ProductoComponente;
import Entidades.Proveedor;
import excepciones.StockExcepcion;
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
public class ExpertoPedidoAproveedores {
    
    List<PedidoAProveedor> listaDePedidos;
    
    public ExpertoPedidoAproveedores(){
        listaDePedidos = new ArrayList<PedidoAProveedor>();
    
    }
    
    public List<PedidoAProveedor> generarPedidoAProveedores(String codigo, int cantidadDeLostesOptimos, Proveedor prov){
        
        Criteria criterio = Fachada.getInstancia().crearCriterio(MaestroDeArticulo.class);
        criterio.add(Restrictions.like("codigo", codigo));
        List<MateriaPrima> listaArticulos = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterio);        
        MaestroDeArticulo articulo = listaArticulos.get(0);
        PedidoAProveedor pedido = new PedidoAProveedor();
        pedido.setArticulo(articulo);
        pedido.setCantidad(cantidadDeLostesOptimos*articulo.getTamanioLoteEstandar());
        pedido.setEstaConcretado(false);
        pedido.setProveedor(prov);
        listaDePedidos.add(pedido);
        return listaDePedidos;

    }
    
    public void asentarPedidos(){
        Conexion.getInstancia().iniciarTX();
        ExpertoStock exp = new ExpertoStock();
        for (PedidoAProveedor pedidoAProveedor : listaDePedidos) {
            Fachada.getInstancia().guardarSinTranasaccion(pedidoAProveedor);
            exp.agregarStockPorLlegar(pedidoAProveedor.getArticulo(),pedidoAProveedor.getCantidad());
        }
        Conexion.getInstancia().confirmarTx();
    }
    
    public void confirmarPedido() throws StockExcepcion{
    
        Conexion.getInstancia().iniciarTX();
        ExpertoStock exp = new ExpertoStock();
        for (PedidoAProveedor pedidoAProveedor : listaDePedidos) {
            pedidoAProveedor.setEstaConcretado(true);
            Fachada.getInstancia().guardarSinTranasaccion(pedidoAProveedor);
            if(pedidoAProveedor.isEstaConcretado()){
                exp.cambiarStockPorLlegarPorStockReal(pedidoAProveedor.getArticulo(), pedidoAProveedor.getCantidad());
            }
        }
        Conexion.getInstancia().confirmarTx();
        
    }

public List<PedidoAProveedor> getListaDePedidos(){
    return listaDePedidos;
}
    
/*
    public List<MaestroDeArticulo> buscarArticulos() {
        
        Criteria criterio = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        List<MateriaPrima> listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterio);
        
        Criteria criterio1 = Fachada.getInstancia().crearCriterio(ProductoComponente.class);
        List<ProductoComponente> listaProductoComponentes = Fachada.getInstancia().buscar(ProductoComponente.class, criterio1);
        
        List<MaestroDeArticulo> listaDeArticulos = new ArrayList<MaestroDeArticulo>();
        listaDeArticulos.addAll(listaMateriaPrima);
        listaDeArticulos.addAll(listaProductoComponentes);
        
        return listaDeArticulos;
        
    }
    
  */

    public MaestroDeArticulo buscarMateriaPrima(String codigo) {
        Criteria criterio = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        criterio.add(Restrictions.like("codigo", codigo));
        List<MateriaPrima> listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterio);
        if(!listaMateriaPrima.isEmpty()){
            return listaMateriaPrima.get(0);
        }else{
            return null;
        }
    }

    public MaestroDeArticulo buscarProductoComponente(String codigo) {
        
        Criteria criterio = Fachada.getInstancia().crearCriterio(ProductoComponente.class);
        criterio.add(Restrictions.like("codigo", codigo));
        List<ProductoComponente> listaProductoComponente = Fachada.getInstancia().buscar(ProductoComponente.class, criterio);
        if(!listaProductoComponente.isEmpty()){
            return listaProductoComponente.get(0);
        }else{
            return null;
        }
        
    }

    public List<PedidoAProveedor> editar(int seleccionado, int cantidad, Proveedor prov, boolean concretado) {
        listaDePedidos.get(seleccionado).setCantidad(cantidad);
        listaDePedidos.get(seleccionado).setProveedor(prov);
        return listaDePedidos;
    }

    public List<PedidoAProveedor> quitar(int seleccionado) {
        listaDePedidos.remove(seleccionado);
        return listaDePedidos;
    }
    
    
    public List<PedidoAProveedor> buscarPedidos(boolean soloSinConfirmar){
    
        Criteria criterio = Fachada.getInstancia().crearCriterio(PedidoAProveedor.class);
        criterio.add(Restrictions.like("estaConcretado", !soloSinConfirmar));
        List<PedidoAProveedor> listaPedidoAProveedors = Fachada.getInstancia().buscar(PedidoAProveedor.class, criterio);
        listaDePedidos = listaPedidoAProveedors;
        return listaPedidoAProveedors;
    
    }
    
    
}