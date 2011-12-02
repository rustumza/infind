/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MaestroDeArticulo;
import Entidades.MateriaPrima;
import Entidades.OrdenDeFabricacion;
import Entidades.PedidoAProveedor;
import Entidades.ProductoComponente;
import Entidades.Proveedor;
import excepciones.StockExcepcion;
import java.util.ArrayList;
import java.util.Date;
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
    
    public List<PedidoAProveedor> generarPedidoAProveedores(String codigo, int cantidadDeLostesOptimos, Proveedor prov, Date fecha){
        
        Criteria criterio = Fachada.getInstancia().crearCriterio(MaestroDeArticulo.class);
        criterio.add(Restrictions.like("codigo", codigo));
        List<MaestroDeArticulo> listaArticulos = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterio);        
        MaestroDeArticulo articulo = listaArticulos.get(0);
        PedidoAProveedor pedido = new PedidoAProveedor();
        pedido.setArticulo(articulo);
        pedido.setFechaARealizarElPedido(fecha);
        pedido.setCantidad(cantidadDeLostesOptimos*articulo.getTamanioLoteEstandar());
        pedido.setEstaConcretado(false);
        pedido.setEliminado(false);
        pedido.setProveedor(prov);
        listaDePedidos.add(pedido);
        return listaDePedidos;

    }
    
        public List<PedidoAProveedor> generarPedidoAProveedorPredeterminado(String codigo, int cantidadDeLostesOptimos, Date fecha){
        
        Criteria criterio = Fachada.getInstancia().crearCriterio(MaestroDeArticulo.class);
        criterio.add(Restrictions.like("codigo", codigo));
        List<MaestroDeArticulo> listaArticulos = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterio);        
        MaestroDeArticulo articulo = listaArticulos.get(0);
        PedidoAProveedor pedido = new PedidoAProveedor();
        pedido.setArticulo(articulo);
        pedido.setFechaARealizarElPedido(fecha);
        pedido.setCantidad(cantidadDeLostesOptimos*articulo.getTamanioLoteEstandar());
        pedido.setEstaConcretado(false);
        //pedido.setOrdenDeFabricacion(null);
        pedido.setEliminado(false);
        if(articulo.getClass().equals(MateriaPrima.class)){
            pedido.setProveedor(((MateriaPrima)articulo).getProveedorPredeterminado());
        }else{
            pedido.setProveedor(((ProductoComponente)articulo).getProveedor());
        }
        listaDePedidos.add(pedido);
        return listaDePedidos;

    }
    
        
        
        public List<PedidoAProveedor> generarPedidoAProveedorPredeterminadoDesdeOrden(MaestroDeArticulo articulo, int cantidadDeLostesOptimos, Date fecha, OrdenDeFabricacion orden){
        
            PedidoAProveedor pedido = new PedidoAProveedor();
            pedido.setArticulo(articulo);
            pedido.setFechaARealizarElPedido(fecha);
            pedido.setCantidad(cantidadDeLostesOptimos*articulo.getTamanioLoteEstandar());
            pedido.setEstaConcretado(false);
            pedido.setEliminado(false);
            //orden.addPedido(pedido);
            if(articulo.getClass().equals(MateriaPrima.class)){
                pedido.setProveedor(((MateriaPrima)articulo).getProveedorPredeterminado());
            }else{
                pedido.setProveedor(((ProductoComponente)articulo).getProveedor());
            }
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
    
    public void asentarPedidosSinTx(){
        ExpertoStock exp = new ExpertoStock();
        for (PedidoAProveedor pedidoAProveedor : listaDePedidos) {
            Fachada.getInstancia().guardarSinTranasaccion(pedidoAProveedor);
            exp.agregarStockPorLlegar((MaestroDeArticulo)pedidoAProveedor.getArticulo(),pedidoAProveedor.getCantidad());
        }
    }
    
    public void confirmarPedido() throws StockExcepcion{
    
        Conexion.getInstancia().iniciarTX();
        ExpertoStock exp = new ExpertoStock();
        for (PedidoAProveedor pedidoAProveedor : listaDePedidos) {
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

    public List<PedidoAProveedor> editar(int seleccionado, int cantidadLotesOptimos, Proveedor prov, boolean concretado) {
        listaDePedidos.get(seleccionado).setCantidad(cantidadLotesOptimos * listaDePedidos.get(seleccionado).getArticulo().getTamanioLoteEstandar());
        listaDePedidos.get(seleccionado).setProveedor(prov);
        listaDePedidos.get(seleccionado).setEstaConcretado(concretado);
        return listaDePedidos;
    }
    
    public List<PedidoAProveedor> quitar(int seleccionado) {
        listaDePedidos.remove(seleccionado);
        return listaDePedidos;
    }
    
    
    public List<PedidoAProveedor> buscarPedidos(boolean soloSinConfirmar){
    
        Criteria criterio = Fachada.getInstancia().crearCriterio(PedidoAProveedor.class);
        if(soloSinConfirmar){
            criterio.add(Restrictions.eq("estaConcretado", Boolean.FALSE));
        }
        List<PedidoAProveedor> listaPedidoAProveedors = Fachada.getInstancia().buscar(PedidoAProveedor.class, criterio);
        listaDePedidos = listaPedidoAProveedors;
        return listaPedidoAProveedors;
    
    }
    
    
}