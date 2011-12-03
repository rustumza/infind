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
import excepciones.OrdenDeFabricacionExeption;
import excepciones.StockExcepcion;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Conexion;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoOrdenDeFabricacion extends Experto {

    MaestroDeArticulo articulo;
    List<OrdenDeFabricacion> listaDeOrdenesDeFabricacion;
    OrdenDeFabricacion ordenEditar;

    public MaestroDeArticulo buscarProductoFinal(String codigo) {
        List<ProductoFinal> listaFinal = null;
        Criteria criterioProdFinal = Fachada.getInstancia().crearCriterio(ProductoFinal.class);
        criterioProdFinal.add(Restrictions.eq("codigo", codigo));
        listaFinal = Fachada.getInstancia().buscar(ProductoFinal.class, criterioProdFinal);
        if(listaFinal.isEmpty()){
            articulo = null;
            return null;
        }else{
            articulo = listaFinal.get(0);
            return articulo;
        }
    }

    public MaestroDeArticulo buscarProductoIntermedio(String codigo) {
        List<ProductoIntermedio> listaIntermedio = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        criterioProdInt.add(Restrictions.eq("codigo", codigo));
        listaIntermedio = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioProdInt);
        if(listaIntermedio.isEmpty()){
            articulo = null;
            return null;
        }else{
            articulo = listaIntermedio.get(0);
            return articulo;
        }
    }
    
    public void setArticulo(MaestroDeArticulo art){
        articulo = art;
    }
    public MaestroDeArticulo getArticulo(){
        return articulo;
    }

    public MaestroDeArticulo buscarProductoIntermedioSinTx(String codigo) {
        List<ProductoIntermedio> listaIntermedio = null;
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        criterioProdInt.add(Restrictions.eq("codigo", codigo));
        listaIntermedio = Fachada.getInstancia().buscarSinTx(ProductoIntermedio.class, criterioProdInt);
        articulo = listaIntermedio.get(0);
        return articulo;
    }
    
    public OrdenDeFabricacion probarGeneracionDeOrdenDeFabricacion(Date fecha, int cantidadDeLotesAFabricar) {
        OrdenDeFabricacion orden = new OrdenDeFabricacion();
        orden.setEliminado(false);
        orden.setEstado("Generada");
        ((ProductosFabricables) articulo).addOrden(orden);
        ProductoTipoIQE iqe = ((ProductosFabricables) articulo).getProductoTipoIQE();
        MaestroDeEstructuraDeProducto estructuraIQE = iqe.getMaestroEstructuraDeProducto();
        //aramar metodo para uqe te devuleva la estructura completa de un producto fabricable (estructura propia + iqe)
        MaestroDeEstructuraDeProducto estructura = null;
        if (articulo.getClass().equals(ProductoFinal.class)) {
            estructura = ((ProductoFinal) articulo).getMaestroEstructuraDeProducto();
        } else {
            estructura = ((ProductoIntermedio) articulo).getMaestroEstructuraDeProducto();
        }
        ExpertoPedidoAproveedores expertoPedidoAProveedores = new ExpertoPedidoAproveedores();
        ExpertoStock expertoStock = new ExpertoStock();
        for (DetalleEstructuraDeProducto detalle : estructura.getDetalleEstructuraProductoList()) {
            if (!expertoStock.getDisponiblilidadDeStockParaFechaDeterminada(detalle.getMaestroArticulo(), detalle.getCantidad() * cantidadDeLotesAFabricar, fecha)) {
                int cantidadLotesAUsarDeUnProducto = (int) ((detalle.getCantidad() * cantidadDeLotesAFabricar) / detalle.getMaestroArticulo().getTamanioLoteEstandar());
                if ((detalle.getCantidad() * cantidadDeLotesAFabricar) % detalle.getMaestroArticulo().getTamanioLoteEstandar() > 0) {
                    cantidadLotesAUsarDeUnProducto++;
                }
                Calendar calendar = new GregorianCalendar(fecha.getYear() + 1900, fecha.getMonth(), fecha.getDate());
                calendar.add(Calendar.DATE, -detalle.getMaestroArticulo().getTiempoDeObtenecion());

                if (detalle.getMaestroArticulo().getClass().equals(ProductoIntermedio.class)) {
                    ExpertoOrdenDeFabricacion expertoAux = new ExpertoOrdenDeFabricacion();
                    expertoAux.buscarProductoIntermedio(detalle.getMaestroArticulo().getCodigo());
                    orden.addOrden(expertoAux.probarGeneracionDeOrdenDeFabricacion(calendar.getTime(), cantidadLotesAUsarDeUnProducto));
                } else {
                    expertoPedidoAProveedores.generarPedidoAProveedorPredeterminado(detalle.getMaestroArticulo().getCodigo(), cantidadLotesAUsarDeUnProducto, calendar.getTime());
                }
            }
        }
        for (DetalleEstructuraDeProducto detalle : estructuraIQE.getDetalleEstructuraProductoList()) {
            if (!expertoStock.getDisponiblilidadDeStockParaFechaDeterminada(detalle.getMaestroArticulo(), detalle.getCantidad() * cantidadDeLotesAFabricar, fecha)) {
                int cantidadLotesAUsarDeUnProducto = (int) ((detalle.getCantidad() * cantidadDeLotesAFabricar) / detalle.getMaestroArticulo().getTamanioLoteEstandar());
                if ((detalle.getCantidad() * cantidadDeLotesAFabricar) % detalle.getMaestroArticulo().getTamanioLoteEstandar() > 0) {
                    cantidadLotesAUsarDeUnProducto++;
                }
                Calendar calendar = new GregorianCalendar(fecha.getYear() + 1900, fecha.getMonth(), fecha.getDate());
                calendar.add(Calendar.DATE, -detalle.getMaestroArticulo().getTiempoDeObtenecion());
                if (detalle.getMaestroArticulo().getClass().equals(ProductoIntermedio.class)) {
                    ExpertoOrdenDeFabricacion expertoAux = new ExpertoOrdenDeFabricacion();
                    expertoAux.buscarProductoIntermedio(detalle.getMaestroArticulo().getCodigo());
                    orden.addOrden(expertoAux.probarGeneracionDeOrdenDeFabricacion(calendar.getTime(), cantidadLotesAUsarDeUnProducto));
                } else {
                    expertoPedidoAProveedores.generarPedidoAProveedorPredeterminado(detalle.getMaestroArticulo().getCodigo(), cantidadLotesAUsarDeUnProducto, calendar.getTime());
                }


            }
        }


        Date fechaQueMasDemoraEnTerminar = new Date();
        for (OrdenDeFabricacion ord : orden.getListaDeOrdenes()) {
            Calendar calendarFechaQueMasDemoraEnTerminar = new GregorianCalendar(ord.getFecha().getYear() + 1900, ord.getFecha().getMonth(), ord.getFecha().getDate());
            calendarFechaQueMasDemoraEnTerminar.add(Calendar.DATE, ord.getProductoFabricable().getTiempoDeObtenecion());
            Date fechaAux = calendarFechaQueMasDemoraEnTerminar.getTime();
            if (fechaAux.after(fechaQueMasDemoraEnTerminar)) {
                fechaQueMasDemoraEnTerminar = fechaAux;
            }
        }

        int numeroMasGrande = 0;
        for (PedidoAProveedor pedido : expertoPedidoAProveedores.getListaDePedidos()) {
            if (numeroMasGrande < pedido.getArticulo().getTiempoDeObtenecion()) {
                numeroMasGrande = pedido.getArticulo().getTiempoDeObtenecion();
            }
        }

        Calendar calendar = new GregorianCalendar(fecha.getYear() + 1900, fecha.getMonth(), fecha.getDate());
        calendar.add(Calendar.DATE, -(numeroMasGrande + 2));

        if (new Date().after(calendar.getTime())) {
            Calendar calendarAux = new GregorianCalendar();
            calendarAux.add(Calendar.DATE, numeroMasGrande + 2);
            orden.setFecha(calendarAux.getTime());
            for (PedidoAProveedor pedido : expertoPedidoAProveedores.getListaDePedidos()) {
                int intAux = numeroMasGrande + 1 - pedido.getArticulo().getTiempoDeObtenecion();
                Calendar calendarAuxPedido = new GregorianCalendar();
                calendarAuxPedido.add(Calendar.DATE, intAux);
                pedido.setFechaARealizarElPedido(calendarAuxPedido.getTime());
                orden.addPedido(pedido);
            }
        } else {
            orden.setFecha(fecha);
            for (PedidoAProveedor pedi : expertoPedidoAProveedores.getListaDePedidos()) {
                orden.addPedido(pedi);
            }
        }

        if (fechaQueMasDemoraEnTerminar.after(orden.getFecha())) {
            orden.setFecha(fechaQueMasDemoraEnTerminar);
        }

        orden.setCantidadDeLotesOptimos(cantidadDeLotesAFabricar);
        
        return orden;
    }

    /*public void generarOrdenes(OrdenDeFabricacion orden) throws StockExcepcion{
    //Conexion.getInstancia().iniciarTX();        
    metodoAux(orden);
    //Conexion.getInstancia().confirmarTx();
    }*/
    /*private void metodoAux(OrdenDeFabricacion orden) throws StockExcepcion {
        try {
            for (OrdenDeFabricacion ord : orden.getListaDeOrdenes()) {
                metodoAux(ord);
            }
            Conexion.getInstancia().iniciarTX();
            ExpertoPedidoAproveedores expPedido = new ExpertoPedidoAproveedores();


            //Conexion.getInstancia().iniciarTX();        
            for (PedidoAProveedor pedido : orden.getListaDePedido()) {
                expPedido.generarPedidoAProveedorPredeterminadoDesdeOrden(pedido.getArticulo(), ((int) pedido.getCantidad() / pedido.getArticulo().getTamanioLoteEstandar()), pedido.getFechaARealizarElPedido(), orden);
            }
            orden.setListaDePedido(new ArrayList<PedidoAProveedor>());
            for (PedidoAProveedor ped : expPedido.getListaDePedidos()) {
                orden.addPedido(ped);
            }



            ExpertoStock expStock = new ExpertoStock();
            expPedido.asentarPedidosSinTx();
            Conexion.getInstancia().confirmarTx();
            //List<PedidoAProveedor> listaDePedidos = expPedido.getListaDePedidos();


            //Conexion.getInstancia().iniciarTXTx()();
        for (PedidoAProveedor pedidoAProveedor : listaDePedidos) {
            Fachada.getInstancia().guardarSinTranasaccion(pedidoAProveedor);
            expStock.agregarStockPorLlegar((MaestroDeArticulo)pedidoAProveedor.getArticulo(),pedidoAProveedor.getCantidad());
            }
            //Conexion.getInstancia().confirmarTx();
            //expPedido.asentarPedidos();

            //reserva de stock
            Conexion.getInstancia().iniciarTX();
            ProductoTipoIQE iqe = orden.getProductoFabricable().getProductoTipoIQE();
            MaestroDeEstructuraDeProducto estructuraIQE = iqe.getMaestroEstructuraDeProducto();
            //aramar metodo para uqe te devuleva la estructura completa de un producto fabricable (estructura propia + iqe)
            MaestroDeEstructuraDeProducto estructura = null;
            if (orden.getProductoFabricable().getClass().equals(ProductoFinal.class)) {
                estructura = ((ProductoFinal) orden.getProductoFabricable()).getMaestroEstructuraDeProducto();
            } else {
                estructura = ((ProductoIntermedio) orden.getProductoFabricable()).getMaestroEstructuraDeProducto();
            }
            //expStock.iniciarManejoDeStock();
            for (DetalleEstructuraDeProducto detalleEstruc : estructura.getDetalleEstructuraProductoList()) {
                float cant = detalleEstruc.getCantidad() * orden.getCantidadDeLotesOptimos();
                expStock.reservarStock(detalleEstruc.getMaestroArticulo(), cant);
            }
            for (DetalleEstructuraDeProducto detalleEstruc : estructuraIQE.getDetalleEstructuraProductoList()) {
                float cant = detalleEstruc.getCantidad() * orden.getCantidadDeLotesOptimos();
                expStock.reservarStock(detalleEstruc.getMaestroArticulo(), cant);
            }
            //expStock.finalizarManejoDeStock();

            Fachada.getInstancia().guardarSinTranasaccion(orden);
            Fachada.getInstancia().guardarSinTranasaccion(orden.getProductoFabricable());
            //Conexion.getInstancia().confirmarTx();

            //expStock.iniciarManejoDeStock();
            expStock.agregarStockPorLlegar(orden.getProductoFabricable(), orden.getCantidadDeLotesOptimos() * orden.getProductoFabricable().getTamanioLoteEstandar());
            //expStock.finalizarManejoDeStock();
            //expPedido.asentarPedidosSinTx();

            Conexion.getInstancia().confirmarTx();
        } catch (StockExcepcion e) {
            throw e;
        }

    }*/

    public OrdenDeFabricacion generarOrdenesPrimero(Date fecha, int cantidadDeLotesAFabricar) throws StockExcepcion {
        try{
            Conexion.getInstancia().iniciarTX();
            OrdenDeFabricacion orden = generarOrdenes(articulo, fecha, cantidadDeLotesAFabricar);
            Conexion.getInstancia().confirmarTx();
            return orden;
        }catch(StockExcepcion e){
            Conexion.getInstancia().deshacerTx();
            throw e;
            
        }
        catch(Exception e){
            Conexion.getInstancia().deshacerTx();
            Logger.getLogger(ExpertoStock.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        
    }
    
    
    public OrdenDeFabricacion generarOrdenes(MaestroDeArticulo artic, Date fecha, int cantidadDeLotesAFabricar) throws StockExcepcion {
        try {
            OrdenDeFabricacion orden = new OrdenDeFabricacion();
            orden.setEliminado(false);
            orden.setEstado("Generada");
            ((ProductosFabricables) artic).addOrden(orden);
            ProductoTipoIQE iqe = ((ProductosFabricables) artic).getProductoTipoIQE();
            MaestroDeEstructuraDeProducto estructuraIQE = iqe.getMaestroEstructuraDeProducto();
            //aramar metodo para uqe te devuleva la estructura completa de un producto fabricable (estructura propia + iqe)
            MaestroDeEstructuraDeProducto estructura = null;
            if (artic.getClass().equals(ProductoFinal.class)) {
                estructura = ((ProductoFinal) artic).getMaestroEstructuraDeProducto();
            } else {
                estructura = ((ProductoIntermedio) artic).getMaestroEstructuraDeProducto();
            }
            ExpertoPedidoAproveedores expertoPedidoAProveedores = new ExpertoPedidoAproveedores();
            ExpertoStock expertoStock = new ExpertoStock();
            for (DetalleEstructuraDeProducto detalle : estructura.getDetalleEstructuraProductoList()) {
                if (!expertoStock.getDisponiblilidadDeStockParaFechaDeterminadaSinTx(detalle.getMaestroArticulo(), detalle.getCantidad() * cantidadDeLotesAFabricar, fecha)) {
                    int cantidadLotesAUsarDeUnProducto = (int) ((detalle.getCantidad() * cantidadDeLotesAFabricar) / detalle.getMaestroArticulo().getTamanioLoteEstandar());
                    if ((detalle.getCantidad() * cantidadDeLotesAFabricar) % detalle.getMaestroArticulo().getTamanioLoteEstandar() > 0) {
                        cantidadLotesAUsarDeUnProducto++;
                    }
                    Calendar calendar = new GregorianCalendar(fecha.getYear() + 1900, fecha.getMonth(), fecha.getDate());
                    calendar.add(Calendar.DATE, -detalle.getMaestroArticulo().getTiempoDeObtenecion());

                    if (detalle.getMaestroArticulo().getClass().equals(ProductoIntermedio.class)) {
                        ExpertoOrdenDeFabricacion expertoAux = new ExpertoOrdenDeFabricacion();
                        //expertoAux.buscarProductoIntermedioSinTx(detalle.getMaestroArticulo().getCodigo());
                        expertoAux.setArticulo(detalle.getMaestroArticulo());
                        orden.addOrden(expertoAux.generarOrdenes(detalle.getMaestroArticulo(),calendar.getTime(), cantidadLotesAUsarDeUnProducto));
                    } else {
                        expertoPedidoAProveedores.generarPedidoAProveedorPredeterminadoDesdeOrden(detalle.getMaestroArticulo(), cantidadLotesAUsarDeUnProducto, calendar.getTime(),orden);
                    }
                }
            }
            for (DetalleEstructuraDeProducto detalle : estructuraIQE.getDetalleEstructuraProductoList()) {
                if (!expertoStock.getDisponiblilidadDeStockParaFechaDeterminadaSinTx(detalle.getMaestroArticulo(), detalle.getCantidad() * cantidadDeLotesAFabricar, fecha)) {
                    int cantidadLotesAUsarDeUnProducto = (int) ((detalle.getCantidad() * cantidadDeLotesAFabricar) / detalle.getMaestroArticulo().getTamanioLoteEstandar());
                    if ((detalle.getCantidad() * cantidadDeLotesAFabricar) % detalle.getMaestroArticulo().getTamanioLoteEstandar() > 0) {
                        cantidadLotesAUsarDeUnProducto++;
                    }

                    Calendar calendar = new GregorianCalendar(fecha.getYear() + 1900, fecha.getMonth(), fecha.getDate());
                    calendar.add(Calendar.DATE, -detalle.getMaestroArticulo().getTiempoDeObtenecion());
                    if (detalle.getMaestroArticulo().getClass().equals(ProductoIntermedio.class)) {
                        ExpertoOrdenDeFabricacion expertoAux = new ExpertoOrdenDeFabricacion();
                        //expertoAux.buscarProductoIntermedioSinTx(detalle.getMaestroArticulo().getCodigo());
                        expertoAux.setArticulo(detalle.getMaestroArticulo());
                        orden.addOrden(expertoAux.generarOrdenes(detalle.getMaestroArticulo(), calendar.getTime(), cantidadLotesAUsarDeUnProducto));
                    } else {
                        expertoPedidoAProveedores.generarPedidoAProveedorPredeterminadoDesdeOrden(detalle.getMaestroArticulo(), cantidadLotesAUsarDeUnProducto, calendar.getTime(),orden);
                    }


                }
            }


            Date fechaQueMasDemoraEnTerminar = new Date();
            for (OrdenDeFabricacion ord : orden.getListaDeOrdenes()) {
                Calendar calendarFechaQueMasDemoraEnTerminar = new GregorianCalendar(ord.getFecha().getYear() + 1900, ord.getFecha().getMonth(), ord.getFecha().getDate());
                calendarFechaQueMasDemoraEnTerminar.add(Calendar.DATE, ord.getProductoFabricable().getTiempoDeObtenecion());
                Date fechaAux = calendarFechaQueMasDemoraEnTerminar.getTime();
                if (fechaAux.after(fechaQueMasDemoraEnTerminar)) {
                    fechaQueMasDemoraEnTerminar = fechaAux;
                }
            }

            int numeroMasGrande = 0;
            for (PedidoAProveedor pedido : expertoPedidoAProveedores.getListaDePedidos()) {
                if (numeroMasGrande < pedido.getArticulo().getTiempoDeObtenecion()) {
                    numeroMasGrande = pedido.getArticulo().getTiempoDeObtenecion();
                }
            }

            Calendar calendar = new GregorianCalendar(fecha.getYear() + 1900, fecha.getMonth(), fecha.getDate());
            calendar.add(Calendar.DATE, -(numeroMasGrande + 2));

            if (new Date().after(calendar.getTime())) {
                Calendar calendarAux = new GregorianCalendar();
                calendarAux.add(Calendar.DATE, numeroMasGrande + 2);
                orden.setFecha(calendarAux.getTime());
                for (PedidoAProveedor pedido : expertoPedidoAProveedores.getListaDePedidos()) {
                    int intAux = numeroMasGrande + 1 - pedido.getArticulo().getTiempoDeObtenecion();
                    Calendar calendarAuxPedido = new GregorianCalendar();
                    calendarAuxPedido.add(Calendar.DATE, intAux);
                    pedido.setFechaARealizarElPedido(calendarAuxPedido.getTime());
                    orden.addPedido(pedido);
                }
            } else {
                orden.setFecha(fecha);
                for (PedidoAProveedor pedi : expertoPedidoAProveedores.getListaDePedidos()) {
                    orden.addPedido(pedi);
                }
            }

            if (fechaQueMasDemoraEnTerminar.after(orden.getFecha())) {
                orden.setFecha(fechaQueMasDemoraEnTerminar);
            }

            orden.setCantidadDeLotesOptimos(cantidadDeLotesAFabricar);

            expertoPedidoAProveedores.asentarPedidosSinTx();

            for (DetalleEstructuraDeProducto detalleEstruc : estructura.getDetalleEstructuraProductoList()) {
                float cant = detalleEstruc.getCantidad() * orden.getCantidadDeLotesOptimos();
                expertoStock.reservarStock(detalleEstruc.getMaestroArticulo(), cant);
            }
            for (DetalleEstructuraDeProducto detalleEstruc : estructuraIQE.getDetalleEstructuraProductoList()) {
                float cant = detalleEstruc.getCantidad() * orden.getCantidadDeLotesOptimos();
                expertoStock.reservarStock(detalleEstruc.getMaestroArticulo(), cant);
            }
            //expStock.finalizarManejoDeStock();

            Fachada.getInstancia().guardarSinTranasaccion(orden);
            Fachada.getInstancia().guardarSinTranasaccion(orden.getProductoFabricable());
            //Conexion.getInstancia().confirmarTx();

            //expStock.iniciarManejoDeStock();
            expertoStock.agregarStockPorLlegar(orden.getProductoFabricable(), orden.getCantidadDeLotesOptimos() * orden.getProductoFabricable().getTamanioLoteEstandar());
            for (OrdenDeFabricacion ordenDeFabricacion : orden.getListaDeOrdenes()) {
                Fachada.getInstancia().guardarSinTranasaccion(ordenDeFabricacion);
            }
            return orden;
        } catch (StockExcepcion e) {
            throw e;
        }
    }
    
    
    //LISTAR ORDENES
    
    
    public List<OrdenDeFabricacion> buscarOrdenes(String estado){
        
        Criteria criterioOrdenes = Fachada.getInstancia().crearCriterio(OrdenDeFabricacion.class);
        if(!estado.equals("Todas")){
            criterioOrdenes.add(Restrictions.eq("estado", estado));
        }
        listaDeOrdenesDeFabricacion = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioOrdenes);
        return listaDeOrdenesDeFabricacion;
    
    }

    public OrdenDeFabricacion getOrdenSeleccionada(int columnaSeleccionada) {
        ordenEditar = listaDeOrdenesDeFabricacion.get(columnaSeleccionada);
        return ordenEditar;
    }

    public void camibiarEstadoDeLaOrden() throws OrdenDeFabricacionExeption, StockExcepcion {
        try{
            ExpertoStock expStock = new ExpertoStock();
            if(ordenEditar.getEstado().equals("Generada")){
                for (OrdenDeFabricacion ordenDeFabricacion : ordenEditar.getListaDeOrdenes()) {
                    if(!ordenDeFabricacion.getEstado().equals("Finalizada")){
                        throw new OrdenDeFabricacionExeption(1);
                    }
                }

                for (PedidoAProveedor ordenDeCompra : ordenEditar.getListaDePedido()) {
                    if(!ordenDeCompra.isEstaConcretado()){
                        throw new OrdenDeFabricacionExeption(2);
                    }
                }

                for (DetalleEstructuraDeProducto detalle : ordenEditar.getProductoFabricable().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {
                    float cantidad = detalle.getCantidad() * ordenEditar.getCantidadDeLotesOptimos();
                    if(detalle.getMaestroArticulo().getStock().getCantidadFisicaReal()< cantidad){
                        throw new OrdenDeFabricacionExeption(2, detalle.getMaestroArticulo().getCodigo());
                    }
                }

                for (DetalleEstructuraDeProducto detalle : ordenEditar.getProductoFabricable().getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {
                    float cantidad = detalle.getCantidad() * ordenEditar.getCantidadDeLotesOptimos();
                    if(detalle.getMaestroArticulo().getStock().getCantidadFisicaReal()< cantidad){
                        throw new OrdenDeFabricacionExeption(2, detalle.getMaestroArticulo().getCodigo());
                    }
                }

                ordenEditar.setEstado("En curso");
                

                Conexion.getInstancia().iniciarTX();

                for (DetalleEstructuraDeProducto detalle : ordenEditar.getProductoFabricable().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {
                    float cantidad = detalle.getCantidad() * ordenEditar.getCantidadDeLotesOptimos();
                    expStock.restarStock(detalle.getMaestroArticulo(), cantidad);
                }

                for (DetalleEstructuraDeProducto detalle : ordenEditar.getProductoFabricable().getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {
                    float cantidad = detalle.getCantidad() * ordenEditar.getCantidadDeLotesOptimos();
                    expStock.restarStock(detalle.getMaestroArticulo(), cantidad);
                }
                Fachada.getInstancia().guardarSinTranasaccion(ordenEditar);
                
                Conexion.getInstancia().confirmarTx();
                


            }else{
                ordenEditar.setEstado("Finalizada");
                Conexion.getInstancia().iniciarTX();
                expStock.cambiarStockPorLlegarPorStockReal(ordenEditar.getProductoFabricable(), ordenEditar.getCantidadDeLotesOptimos() * ordenEditar.getProductoFabricable().getTamanioLoteEstandar());
                Fachada.getInstancia().guardarSinTranasaccion(ordenEditar);
                Conexion.getInstancia().confirmarTx();
            }
    
        }catch(StockExcepcion e){
            Conexion.getInstancia().deshacerTx();
            throw e;
        }
    }

    public void eliminarOrden() throws StockExcepcion, OrdenDeFabricacionExeption {
        try{
            if(ordenEditar.getOrden() != null){
                if(!ordenEditar.getEliminado()){
                    throw new OrdenDeFabricacionExeption(4);
                }
            }
            ordenEditar.setEliminado(Boolean.TRUE);
            ExpertoStock expStock = new ExpertoStock();
            Conexion.getInstancia().iniciarTX();
            Fachada.getInstancia().guardarSinTranasaccion(ordenEditar);
            for (DetalleEstructuraDeProducto detalle : ordenEditar.getProductoFabricable().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {
                float cantidad = detalle.getCantidad() * ordenEditar.getCantidadDeLotesOptimos();
                expStock.liberarStockReservado(detalle.getMaestroArticulo(), cantidad);
            }

            for (DetalleEstructuraDeProducto detalle : ordenEditar.getProductoFabricable().getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {
                float cantidad = detalle.getCantidad() * ordenEditar.getCantidadDeLotesOptimos();
                expStock.liberarStockReservado(detalle.getMaestroArticulo(), cantidad);
            }

            Conexion.getInstancia().confirmarTx();
        }catch(StockExcepcion e){
            Conexion.getInstancia().deshacerTx();
            throw e;
        }
}
    
    
    
    
}
