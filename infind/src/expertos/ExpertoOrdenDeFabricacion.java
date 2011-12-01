/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.DetalleEstructuraDeProducto;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeEstructuraDeProducto;
import Entidades.OrdenDeFabricacion;
import Entidades.PedidoAProveedor;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;
import Entidades.ProductosFabricables;
import excepciones.StockExcepcion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Conexion;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoOrdenDeFabricacion extends Experto{
    
    MaestroDeArticulo articulo;

    public MaestroDeArticulo buscarProductoFinal(String codigo) {
        List<ProductoFinal> listaFinal = null;
        Criteria criterioProdFinal = Fachada.getInstancia().crearCriterio(ProductoFinal.class);
        criterioProdFinal.add(Restrictions.eq("codigo", codigo));
        listaFinal = Fachada.getInstancia().buscar(ProductoFinal.class, criterioProdFinal);
        articulo = listaFinal.get(0);
        return articulo;
    }

    public MaestroDeArticulo buscarProductoIntermedio(String codigo) {
        List<ProductoIntermedio> listaIntermedio = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        criterioProdInt.add(Restrictions.eq("codigo", codigo));
        listaIntermedio = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioProdInt);
        articulo = listaIntermedio.get(0);
        return articulo;
    }
    
    
    public OrdenDeFabricacion probarGeneracionDeOrdenDeFabricacion(Date fecha, int cantidadDeLotesAFabricar){
        OrdenDeFabricacion orden = new OrdenDeFabricacion();
        orden.setEliminado(false);
        ((ProductosFabricables)articulo).addOrden(orden);
        ProductoTipoIQE iqe = ((ProductosFabricables)articulo).getProductoTipoIQE();        
        MaestroDeEstructuraDeProducto estructuraIQE = iqe.getMaestroEstructuraDeProducto();                
        //aramar metodo para uqe te devuleva la estructura completa de un producto fabricable (estructura propia + iqe)
        MaestroDeEstructuraDeProducto estructura = null;
        if(articulo.getClass().equals(ProductoFinal.class)){
            estructura = ((ProductoFinal)articulo).getMaestroEstructuraDeProducto();
        }else{
            estructura = ((ProductoIntermedio)articulo).getMaestroEstructuraDeProducto();        
        }
        ExpertoPedidoAproveedores expertoPedidoAProveedores = new ExpertoPedidoAproveedores();
        ExpertoStock expertoStock = new ExpertoStock();
        for (DetalleEstructuraDeProducto  detalle : estructura.getDetalleEstructuraProductoList()) {
            System.out.println("------------------------------");
            System.out.println(detalle.getMaestroArticulo().getNombre());
            if(!expertoStock.getDisponiblilidadDeStockParaFechaDeterminada(detalle.getMaestroArticulo(), detalle.getCantidad() * cantidadDeLotesAFabricar, fecha)){
                int cantidadLotesAUsarDeUnProducto = (int)((detalle.getCantidad()*cantidadDeLotesAFabricar)/detalle.getMaestroArticulo().getTamanioLoteEstandar());
                if((detalle.getCantidad()*cantidadDeLotesAFabricar)%detalle.getMaestroArticulo().getTamanioLoteEstandar() > 0){
                    cantidadLotesAUsarDeUnProducto++;
                }
                System.out.println("cantidad de lotes " + detalle.getMaestroArticulo().getTamanioLoteEstandar());
                System.out.println(cantidadLotesAUsarDeUnProducto);
                System.out.println("cantidad a usar");
                System.out.println(detalle.getCantidad() * cantidadDeLotesAFabricar);
                Calendar calendar = new  GregorianCalendar(fecha.getYear()+1900, fecha.getMonth(), fecha.getDate());
                calendar.add(Calendar.DATE, - detalle.getMaestroArticulo().getTiempoDeObtenecion());
                
                if(detalle.getMaestroArticulo().getClass().equals(ProductoIntermedio.class)){
                    ExpertoOrdenDeFabricacion expertoAux = new ExpertoOrdenDeFabricacion();
                    expertoAux.buscarProductoIntermedio(detalle.getMaestroArticulo().getCodigo());
                    orden.addOrden(expertoAux.probarGeneracionDeOrdenDeFabricacion(calendar.getTime(), cantidadLotesAUsarDeUnProducto));
                }else{
                    expertoPedidoAProveedores.generarPedidoAProveedorPredeterminado(detalle.getMaestroArticulo().getCodigo(), cantidadLotesAUsarDeUnProducto, calendar.getTime());
                }
            }
        }
        for (DetalleEstructuraDeProducto  detalle : estructuraIQE.getDetalleEstructuraProductoList()) {
            System.out.println("------------------------------");
            System.out.println(detalle.getMaestroArticulo().getNombre());
            if(!expertoStock.getDisponiblilidadDeStockParaFechaDeterminada(detalle.getMaestroArticulo(), detalle.getCantidad() * cantidadDeLotesAFabricar, fecha)){
                int cantidadLotesAUsarDeUnProducto = (int)((detalle.getCantidad()*cantidadDeLotesAFabricar)/detalle.getMaestroArticulo().getTamanioLoteEstandar());
                if((detalle.getCantidad()*cantidadDeLotesAFabricar)%detalle.getMaestroArticulo().getTamanioLoteEstandar() > 0){
                    cantidadLotesAUsarDeUnProducto++;
                }
                
                System.out.println("cantidad de lotes " + detalle.getMaestroArticulo().getTamanioLoteEstandar());
                System.out.println(cantidadLotesAUsarDeUnProducto);
                System.out.println("cantidad a usar");
                System.out.println(detalle.getCantidad() * cantidadDeLotesAFabricar);
                
                Calendar calendar = new  GregorianCalendar(fecha.getYear()+1900, fecha.getMonth(), fecha.getDate());
                calendar.add(Calendar.DATE, - detalle.getMaestroArticulo().getTiempoDeObtenecion());
                if(detalle.getMaestroArticulo().getClass().equals(ProductoIntermedio.class)){
                    ExpertoOrdenDeFabricacion expertoAux = new ExpertoOrdenDeFabricacion();
                    expertoAux.buscarProductoIntermedio(detalle.getMaestroArticulo().getCodigo());
                    orden.addOrden(expertoAux.probarGeneracionDeOrdenDeFabricacion(calendar.getTime(), cantidadLotesAUsarDeUnProducto));
                }else{
                    expertoPedidoAProveedores.generarPedidoAProveedorPredeterminado(detalle.getMaestroArticulo().getCodigo(), cantidadLotesAUsarDeUnProducto, calendar.getTime());
                }
                
                
            }
        }
        
        
        Date fechaQueMasDemoraEnTerminar = new Date();
        for (OrdenDeFabricacion ord : orden.getListaDeOrdenes()) {
            Calendar calendarFechaQueMasDemoraEnTerminar = new GregorianCalendar(ord.getFecha().getYear() + 1900, ord.getFecha().getMonth(), ord.getFecha().getDate());
            calendarFechaQueMasDemoraEnTerminar.add(Calendar.DATE, ord.getProductoFabricable().getTiempoDeObtenecion());
            Date fechaAux = calendarFechaQueMasDemoraEnTerminar.getTime();
            if(fechaAux.after(fechaQueMasDemoraEnTerminar)){
                fechaQueMasDemoraEnTerminar = fechaAux;
            }
        }
        
        int numeroMasGrande = 0;
        for (PedidoAProveedor pedido : expertoPedidoAProveedores.getListaDePedidos()) {
            if(numeroMasGrande < pedido.getArticulo().getTiempoDeObtenecion()){
                numeroMasGrande = pedido.getArticulo().getTiempoDeObtenecion();
            }
        }
        
        Calendar calendar = new  GregorianCalendar(fecha.getYear()+1900, fecha.getMonth(), fecha.getDate());
        calendar.add(Calendar.DATE, - (numeroMasGrande+2));
        
        if(new Date().after(calendar.getTime())){
            Calendar calendarAux = new  GregorianCalendar();
            calendarAux.add(Calendar.DATE, numeroMasGrande+2);
            orden.setFecha(calendarAux.getTime());
            for (PedidoAProveedor pedido : expertoPedidoAProveedores.getListaDePedidos()) {
                int intAux = numeroMasGrande + 1 - pedido.getArticulo().getTiempoDeObtenecion();
                Calendar calendarAuxPedido = new  GregorianCalendar();
                calendarAuxPedido.add(Calendar.DATE, intAux);
                pedido.setFechaARealizarElPedido(calendarAuxPedido.getTime());
                orden.addPedido(pedido);
            }
        }else{
            orden.setFecha(fecha);
            for (PedidoAProveedor pedi : expertoPedidoAProveedores.getListaDePedidos()) {
                orden.addPedido(pedi);
            }
        }
        
        if(fechaQueMasDemoraEnTerminar.after(orden.getFecha())){
            orden.setFecha(fechaQueMasDemoraEnTerminar);
        }
        
        orden.setCantidadDeLotesOptimos(cantidadDeLotesAFabricar);
        
        return orden;
    }


    
    public void generarOrdenes(OrdenDeFabricacion orden) throws StockExcepcion{
        //Conexion.getInstancia().iniciarTX();        
        metodoAux(orden);
        //Conexion.getInstancia().confirmarTx();
    }
    
    private void metodoAux(OrdenDeFabricacion orden) throws StockExcepcion{
        try{
        for (OrdenDeFabricacion ord : orden.getListaDeOrdenes()) {            
            metodoAux(ord);
        }
        ExpertoPedidoAproveedores expPedido = new ExpertoPedidoAproveedores();
        
        
        //Conexion.getInstancia().iniciarTX();        
        for (PedidoAProveedor pedido : orden.getListaDePedido()) {
            expPedido.generarPedidoAProveedorPredeterminadoDesdeOrden(pedido.getArticulo().getCodigo(), pedido.getArticulo().getTamanioLoteEstandar(), pedido.getFechaARealizarElPedido(),orden);
        }
        orden.setListaDePedido(new ArrayList<PedidoAProveedor>());
        for (PedidoAProveedor ped : expPedido.getListaDePedidos()) {
            orden.addPedido(ped);
        }
        
        
        
        ExpertoStock expStock = new ExpertoStock();
        List<PedidoAProveedor> listaDePedidos = expPedido.getListaDePedidos();
        Conexion.getInstancia().iniciarTX();
        for (PedidoAProveedor pedidoAProveedor : listaDePedidos) {
            Fachada.getInstancia().guardarSinTranasaccion(pedidoAProveedor);
            expStock.agregarStockPorLlegar((MaestroDeArticulo)pedidoAProveedor.getArticulo(),pedidoAProveedor.getCantidad());
        }
        Conexion.getInstancia().confirmarTx();
        //expPedido.asentarPedidos();
        
        //reserva de stock
        ProductoTipoIQE iqe = orden.getProductoFabricable().getProductoTipoIQE();        
        MaestroDeEstructuraDeProducto estructuraIQE = iqe.getMaestroEstructuraDeProducto();                
        //aramar metodo para uqe te devuleva la estructura completa de un producto fabricable (estructura propia + iqe)
        MaestroDeEstructuraDeProducto estructura = null;
        if(orden.getProductoFabricable().getClass().equals(ProductoFinal.class)){
            estructura = ((ProductoFinal)orden.getProductoFabricable()).getMaestroEstructuraDeProducto();
        }else{
            estructura = ((ProductoIntermedio)orden.getProductoFabricable()).getMaestroEstructuraDeProducto();        
        }
        expStock.iniciarManejoDeStock();
        for (DetalleEstructuraDeProducto detalleEstruc : estructura.getDetalleEstructuraProductoList()) {
            float cant = detalleEstruc.getCantidad() * orden.getCantidadDeLotesOptimos();
            expStock.reservarStock(detalleEstruc.getMaestroArticulo(), cant);
        }
        for (DetalleEstructuraDeProducto detalleEstruc : estructuraIQE.getDetalleEstructuraProductoList()) {
            float cant = detalleEstruc.getCantidad() * orden.getCantidadDeLotesOptimos();
            expStock.reservarStock(detalleEstruc.getMaestroArticulo(), cant);
        }
        expStock.finalizarManejoDeStock();
        
        Fachada.getInstancia().guardar(orden);
        Fachada.getInstancia().guardar(orden.getProductoFabricable());
        //Conexion.getInstancia().confirmarTx();
        
        expStock.iniciarManejoDeStock();
        expStock.agregarStockPorLlegar(orden.getProductoFabricable(), orden.getCantidadDeLotesOptimos()*orden.getProductoFabricable().getTamanioLoteEstandar());
        expStock.finalizarManejoDeStock();
        //expPedido.asentarPedidosSinTx();
        
        
        }catch(StockExcepcion e){
            throw e;
        }
    
    }
}
