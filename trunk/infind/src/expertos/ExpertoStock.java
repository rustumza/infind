/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MaestroDeArticulo;
import Entidades.PedidoAProveedor;
import Entidades.Stock;
import excepciones.StockExcepcion;
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
public class ExpertoStock {
    
    
    
    public ExpertoStock(){
    
    }
    
    public void iniciarManejoDeStock(){
        Conexion.getInstancia().iniciarTX();
    
    }
    
    public void finalizarManejoDeStock(){
        Conexion.getInstancia().confirmarTx();
    
    }
    
    public void cancelarManejoDeStock(){
        Conexion.getInstancia().deshacerTx();
    
    }
    
    public void agregarStockReal(MaestroDeArticulo articulo, float cantidad){
    
        Stock stock = articulo.getStock();
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
    }
    
    public void agregarStockPorLlegar(MaestroDeArticulo articulo, float cantidad){
    
    
        Stock stock = articulo.getStock();
        stock.setCantidadPorEntrar(stock.getCantidadPorEntrar() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void reservarStock(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
    
    
        Stock stock = articulo.getStock();
        if(((stock.getCantidadFisicaReal() + stock.getCantidadPorEntrar())-stock.getCantidadReservada()) < cantidad){
            throw new StockExcepcion(3);
        }
        stock.setCantidadReservada(stock.getCantidadReservada() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void restarStock(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
    
    
        Stock stock = articulo.getStock();
        if(stock.getCantidadFisicaReal() < cantidad){
            throw new StockExcepcion(2);
        }
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() - cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void cambiarStockPorLlegarPorStockReal(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
        
    
        Stock stock = articulo.getStock();
        if(stock.getCantidadPorEntrar()< cantidad){
            throw new StockExcepcion(1);
        }
        stock.setCantidadPorEntrar(stock.getCantidadPorEntrar() - cantidad);
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
    }
    
    
    public boolean getDisponiblilidadDeStockParaFechaDeterminada(MaestroDeArticulo articulo, float cantidad, Date fecha){
    
        float cantidadRealDisponible = articulo.getStock().getCantidadFisicaReal() - articulo.getStock().getCantidadReservada();
        System.out.println("cantidad fisica");
        System.out.println(articulo.getStock().getCantidadFisicaReal());
        if(cantidadRealDisponible >= cantidad){
            return true;
        }
        List<PedidoAProveedor> pedidos = null;        
        Criteria criterioPedidosDelArticulo = Fachada.getInstancia().crearCriterio(PedidoAProveedor.class);
        criterioPedidosDelArticulo.add(Restrictions.eq("articulo", articulo));
        criterioPedidosDelArticulo.add(Restrictions.eq("estaConcretado", Boolean.FALSE));
        pedidos = Fachada.getInstancia().buscar(PedidoAProveedor.class, criterioPedidosDelArticulo);
        float cantidadAux = 0;
        
        for (PedidoAProveedor pedidoAProveedor : pedidos) {
            if(fecha.before(pedidoAProveedor.getFechaARealizarElPedido())){
                cantidadAux += pedidoAProveedor.getCantidad();
            }
        }
        System.out.println("cantidad disponible");
        System.out.println(cantidadAux + cantidadRealDisponible);
        if(cantidadAux + cantidadRealDisponible - articulo.getStock().getCantidadReservada() >= cantidad){
            return true;
        }
        return false;
    }
    
    
    public boolean getDisponiblilidadDeStockParaFechaDeterminadaSinTx(MaestroDeArticulo articulo, float cantidad, Date fecha){
    
        float cantidadRealDisponible = articulo.getStock().getCantidadFisicaReal() - articulo.getStock().getCantidadReservada();
        System.out.println("cantidad fisica");
        System.out.println(articulo.getStock().getCantidadFisicaReal());
        if(cantidadRealDisponible >= cantidad){
            return true;
        }
        List<PedidoAProveedor> pedidos = null;        
        Criteria criterioPedidosDelArticulo = Fachada.getInstancia().crearCriterio(PedidoAProveedor.class);
        criterioPedidosDelArticulo.add(Restrictions.eq("articulo", articulo));
        criterioPedidosDelArticulo.add(Restrictions.eq("estaConcretado", Boolean.FALSE));
        pedidos = Fachada.getInstancia().buscarSinTx(PedidoAProveedor.class, criterioPedidosDelArticulo);
        float cantidadAux = 0;
        
        for (PedidoAProveedor pedidoAProveedor : pedidos) {
            if(fecha.before(pedidoAProveedor.getFechaARealizarElPedido())){
                cantidadAux += pedidoAProveedor.getCantidad();
            }
        }
        System.out.println("cantidad disponible");
        System.out.println(cantidadAux + cantidadRealDisponible);
        if(cantidadAux + cantidadRealDisponible - articulo.getStock().getCantidadReservada() >= cantidad){
            return true;
        }
        return false;
    }
}
