/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MaestroDeArticulo;
import Entidades.MateriaPrima;
import Entidades.PedidoAProveedor;
import Entidades.ProductoComponente;
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
    MaestroDeArticulo articulo;
    
    public ExpertoPedidoAproveedores(){
        listaDePedidos = new ArrayList<PedidoAProveedor>();
    
    }
    
    public void generarPedidoAProveedores(MaestroDeArticulo articulo, int cantidadDeLostesOptimos){
        
        PedidoAProveedor pedido = new PedidoAProveedor();
        pedido.setArticulo(articulo);
        pedido.setCantidad(cantidadDeLostesOptimos*articulo.getTamanioLoteEstandar());
        pedido.setEstaConcretado(false);
        listaDePedidos.add(pedido);

    }
    
    public void asentarPedidos(){
        Conexion.getInstancia().iniciarTX();
        for (PedidoAProveedor pedidoAProveedor : listaDePedidos) {
            Fachada.getInstancia().guardarSinTranasaccion(pedidoAProveedor);
        }
        Conexion.getInstancia().confirmarTx();
    }
    
    public void confirmarPedido(List<PedidoAProveedor> lista){
    
        Conexion.getInstancia().iniciarTX();
        for (PedidoAProveedor pedidoAProveedor : lista) {
            pedidoAProveedor.setEstaConcretado(true);
            Fachada.getInstancia().guardarSinTranasaccion(pedidoAProveedor);
        }
        Conexion.getInstancia().confirmarTx();
        
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
            articulo = listaMateriaPrima.get(0);
        }
        return articulo;
    }

    public MaestroDeArticulo buscarProductoComponente(String codigo) {
        
        Criteria criterio = Fachada.getInstancia().crearCriterio(ProductoComponente.class);
        criterio.add(Restrictions.like("codigo", codigo));
        List<ProductoComponente> listaProductoComponente = Fachada.getInstancia().buscar(ProductoComponente.class, criterio);
        if(!listaProductoComponente.isEmpty()){
            articulo = listaProductoComponente.get(0);
        }
        return articulo;
        
    }
    
}
