/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOArticulo;
import DTOs.DTOCentro;
import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
import Entidades.DetalleEstructuraDeProducto;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.Herramientas;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.MaestroDeEstructuraDeProducto;
import Entidades.MaestroDeRutaDeFabricacion;
import Entidades.MateriaPrima;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;

import excepciones.ExpertoExceptionRutaFabricacion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoRutaDeFabricacion extends Experto {

    List<EtapaDeRutaDeFabricacion> etapaEnEspera;
    List<DetalleDeArticuloEnEtapaDeFabricacion> detalleArticuloGuardado;
    List<DetalleDeArticuloEnEtapaDeFabricacion> listaDetallesDeAticulosAGuardar = new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>();
    List<DetalleDeArticuloEnEtapaDeFabricacion> listaDetallesDeAticulosADevolver;
    List<DetalleEstructuraDeProducto> listaDetallesEstructuraDeProductosCantidad ;
    MaestroDeRutaDeFabricacion ruta;

    public ExpertoRutaDeFabricacion() {

        etapaEnEspera = new ArrayList<EtapaDeRutaDeFabricacion>();
        detalleArticuloGuardado = new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>();
        listaDetallesEstructuraDeProductosCantidad = new ArrayList<DetalleEstructuraDeProducto>();
        listaDetallesDeAticulosADevolver = new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>();
        ruta = new MaestroDeRutaDeFabricacion();
        ruta.setEtapaRutaFabricacion(new ArrayList<EtapaDeRutaDeFabricacion>());

    }

    public MaestroDeCentroDeTrabajo buscarCentros(DTOCentro dtoCentro) throws ExpertoExceptionRutaFabricacion {

        List<MaestroDeCentroDeTrabajo> centroEncontrado = null;
        MaestroDeCentroDeTrabajo centroDevuelto = null;

        if (dtoCentro == null) {

            centroEncontrado = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, null);
        } else {
            Criteria criterioCentro = Fachada.getInstancia().crearCriterio(MaestroDeCentroDeTrabajo.class);
            if (dtoCentro.getCodigoCentro() != null) {
                criterioCentro.add(Restrictions.like("codigo", dtoCentro.getCodigoCentro()));
            }

            if (dtoCentro.getNombreCentro() != null) {
                criterioCentro.add(Restrictions.like("nombreCentro", dtoCentro.getNombreCentro()));
            }

            centroEncontrado = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, criterioCentro);


        }
        if (centroEncontrado.isEmpty()) {
            throw new ExpertoExceptionRutaFabricacion("No se encontró Centro de Trabajo para los datos ingresados");

        }

        centroDevuelto = centroEncontrado.get(0);
        return centroDevuelto;
    }

    public List<MateriaPrima> buscarMateriasPrimas() {

        List<MateriaPrima> listaMateriaPrima = null;
        Criteria criterioMatPrim = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterioMatPrim);
        return listaMateriaPrima;

    }

    public void guardarEtapaRutaFabricacion(EtapaDeRutaDeFabricacion nuevaEtapa) {
        etapaEnEspera.add(nuevaEtapa);

    }
    
    public void guardarDetallesEstructuraProductosCAntidades(DetalleEstructuraDeProducto detalle){
        
        listaDetallesEstructuraDeProductosCantidad.add(detalle);
    }

    public List<EtapaDeRutaDeFabricacion> devolverEtapasAGuardar() {

        return etapaEnEspera;

    }
    
    public void persistirEstructuraDeProducto(MaestroDeEstructuraDeProducto maestroEstructuraDeProducto) {
        Fachada.getInstancia().guardarSinTranasaccion(maestroEstructuraDeProducto);
    }

    public void persistirRutaDeFabricacion(MaestroDeRutaDeFabricacion rutaNueva) {


        Fachada.getInstancia().guardarSinTranasaccion(rutaNueva);

    }

    public void persistirEtapaRutaFabricacion(EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion) {
        Fachada.getInstancia().guardarSinTranasaccion(etapaDeRutaDeFabricacion);
    }

    public void persistirDetalleArticuloEnEtapaFabricacion(DetalleDeArticuloEnEtapaDeFabricacion detalleArticulo) {
        Fachada.getInstancia().guardarSinTranasaccion(detalleArticulo);
    }

    public DetalleDeArticuloEnEtapaDeFabricacion buscarDetalleArticulo(DetalleDeArticuloEnEtapaDeFabricacion detalle) {
        List<DetalleDeArticuloEnEtapaDeFabricacion> llistaDetalleArticulo = null;

        Criteria criterioDetalleArticulo = Fachada.getInstancia().crearCriterio(DetalleDeArticuloEnEtapaDeFabricacion.class);
        criterioDetalleArticulo.add(Restrictions.eq("numero", detalle.getNumero()));
        llistaDetalleArticulo = Fachada.getInstancia().buscar(DetalleDeArticuloEnEtapaDeFabricacion.class, criterioDetalleArticulo);

        return llistaDetalleArticulo.get(0);

    }

    public void guardarDetalleArticuloEnEtapaFabricacion(DetalleDeArticuloEnEtapaDeFabricacion detalleArticulo) {
        listaDetallesDeAticulosAGuardar.add(detalleArticulo);
    }

    public List<DetalleDeArticuloEnEtapaDeFabricacion> devolverDetalleDeLaEtapa(EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion) {
        listaDetallesDeAticulosADevolver = null;
        if (listaDetallesDeAticulosADevolver == null) {
            listaDetallesDeAticulosADevolver = new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>();

        }

        for (int i = 0; i < listaDetallesDeAticulosAGuardar.size(); i++) {
            if (listaDetallesDeAticulosAGuardar.get(i).getEtapaRutaFabricacion().getNroEtapa() == etapaDeRutaDeFabricacion.getNroEtapa()) {
                listaDetallesDeAticulosADevolver.add(listaDetallesDeAticulosAGuardar.get(i));

            }

        }
        return listaDetallesDeAticulosADevolver;
        
    }

    public ProductoFinal buscarProductoFinal(DTOArticulo dtoArticulo) throws ExpertoExceptionRutaFabricacion {


        List<ProductoFinal> articuloEncontrado = null;
        ProductoFinal articuloDevuelto = null;

        if (dtoArticulo == null) {

            articuloEncontrado = Fachada.getInstancia().buscar(ProductoFinal.class, null);
        } else {
            Criteria criterioArticulo = Fachada.getInstancia().crearCriterio(ProductoFinal.class);
            if (dtoArticulo.getCodigoArticulo() != null) {
                criterioArticulo.add(Restrictions.like("codigo", dtoArticulo.getCodigoArticulo()));
            }

            if (dtoArticulo.getNombreArticulo() != null) {
                criterioArticulo.add(Restrictions.like("nombre", dtoArticulo.getNombreArticulo()));
            }

            articuloEncontrado = Fachada.getInstancia().buscar(ProductoFinal.class, criterioArticulo);


        }
        if (articuloEncontrado.isEmpty()) {
            throw new ExpertoExceptionRutaFabricacion("No se encontró Productos para los datos ingresados");

        }

        articuloDevuelto = articuloEncontrado.get(0);
        return articuloDevuelto;
    }

    public List<EtapaDeRutaDeFabricacion> buscarEtapas(MaestroDeRutaDeFabricacion rutaNueva) {

        List<MaestroDeRutaDeFabricacion> rutaEncontrada = null;

//        Criteria criterioRuta = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeRutaDeFabricacion.class);
        //      criterioRuta.add(Restrictions.eq("numero", rutaNueva.getNumero()));
        //    rutaEncontrada = Fachada.getInstancia().buscar(MaestroDeRutaDeFabricacion.class, criterioRuta);

        List<EtapaDeRutaDeFabricacion> etapasEncontrado = null;


        Criteria criterioEtapa = Fachada.getInstancia().crearCriterioSinEliminado(EtapaDeRutaDeFabricacion.class);
        criterioEtapa.add(Restrictions.eq("MaestroDeRutaDeFabricacion", rutaNueva));
//TODO tira error en el id del maestro de ruta, no se como poner el campo relacionado
        etapasEncontrado = Fachada.getInstancia().buscar(EtapaDeRutaDeFabricacion.class, criterioEtapa);

        return etapasEncontrado;

    }

    public List<EtapaDeRutaDeFabricacion> buscarEtapasSinTx(MaestroDeRutaDeFabricacion rutaNueva) {

        List<MaestroDeRutaDeFabricacion> rutaEncontrada = null;

//        Criteria criterioRuta = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeRutaDeFabricacion.class);
        //      criterioRuta.add(Restrictions.eq("numero", rutaNueva.getNumero()));
        //    rutaEncontrada = Fachada.getInstancia().buscar(MaestroDeRutaDeFabricacion.class, criterioRuta);

        List<EtapaDeRutaDeFabricacion> etapasEncontrado = null;


        Criteria criterioEtapa = Fachada.getInstancia().crearCriterioSinEliminado(EtapaDeRutaDeFabricacion.class);
        criterioEtapa.add(Restrictions.eq("MaestroDeRutaDeFabricacion", rutaNueva));
//TODO tira error en el id del maestro de ruta, no se como poner el campo relacionado
        etapasEncontrado = Fachada.getInstancia().buscarSinTx(EtapaDeRutaDeFabricacion.class, criterioEtapa);

        return etapasEncontrado;

    }

    public ProductoIntermedio buscarProductoIntermedio(DTOArticulo dtoArticulo) throws ExpertoExceptionRutaFabricacion {


        List<ProductoIntermedio> articuloEncontrado = null;
        ProductoIntermedio articuloDevuelto = null;

        if (dtoArticulo == null) {

            articuloEncontrado = Fachada.getInstancia().buscar(ProductoIntermedio.class, null);
        } else {
            Criteria criterioArticulo = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
            if (dtoArticulo.getCodigoArticulo() != null) {
                criterioArticulo.add(Restrictions.like("codigo", dtoArticulo.getCodigoArticulo()));
            }

            if (dtoArticulo.getNombreArticulo() != null) {
                criterioArticulo.add(Restrictions.like("nombre", dtoArticulo.getNombreArticulo()));
            }

            articuloEncontrado = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioArticulo);


        }
        if (articuloEncontrado.isEmpty()) {
            throw new ExpertoExceptionRutaFabricacion("No se encontró Productos para los datos ingresados");

        }

        articuloDevuelto = articuloEncontrado.get(0);
        return articuloDevuelto;
    }

    public ProductoTipoIQE buscarProductoIQE(DTOArticulo dtoArticulo) throws ExpertoExceptionRutaFabricacion {


        List<ProductoTipoIQE> articuloEncontrado = null;
        ProductoTipoIQE articuloDevuelto = null;

        if (dtoArticulo == null) {

            articuloEncontrado = Fachada.getInstancia().buscar(ProductoTipoIQE.class, null);
        } else {
            Criteria criterioArticulo = Fachada.getInstancia().crearCriterio(ProductoTipoIQE.class);
            if (dtoArticulo.getCodigoArticulo() != null) {
                criterioArticulo.add(Restrictions.like("codigo", dtoArticulo.getCodigoArticulo()));
            }

            if (dtoArticulo.getNombreArticulo() != null) {
                criterioArticulo.add(Restrictions.like("nombre", dtoArticulo.getNombreArticulo()));
            }

            articuloEncontrado = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioArticulo);


        }
        if (articuloEncontrado.isEmpty()) {
            throw new ExpertoExceptionRutaFabricacion("No se encontró Productos para los datos ingresados");

        }

        articuloDevuelto = articuloEncontrado.get(0);
        return articuloDevuelto;
    }

    public void eliminarEtapa(EtapaDeRutaDeFabricacion etapaSeleccionada) {
        etapaEnEspera.remove(etapaSeleccionada);
    }

    public EtapaDeRutaDeFabricacion buscarEtapasAEliminar(Object valorEtapa) {


        //EtapaDeRutaDeFabricacion etapasEncontrado = null;


        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaEnEspera) {
            int nroEtapa = etapaDeRutaDeFabricacion.getNroEtapa();

            if (String.valueOf(nroEtapa) == null ? valorEtapa.toString() == null : String.valueOf(nroEtapa).equals(valorEtapa.toString())) {

                return etapaDeRutaDeFabricacion;
            }

        }


        return null;

    }

    public ProductoTipoIQE buscarTipoIQE(ProductoFinal articuloEncontradoFinal) {


        List<MaestroDeEstructuraDeProducto> esstructuraEncontrado = null;
        ProductoTipoIQE productoDevuelto = null;



        Criteria criterioArticulo = Fachada.getInstancia().crearCriterio(MaestroDeEstructuraDeProducto.class);

        criterioArticulo.add(Restrictions.eq("ProductoFinal", articuloEncontradoFinal));

        esstructuraEncontrado = Fachada.getInstancia().buscar(MaestroDeEstructuraDeProducto.class, criterioArticulo);
        productoDevuelto = esstructuraEncontrado.get(0).getProductoTipoIQE();


        return productoDevuelto;
    }

    public void guardarEtapasEnLaRuta(MaestroDeRutaDeFabricacion rutaNueva) {
       // System.out.println(etapaEnEspera.size());
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaEnEspera) {
            rutaNueva.addDetalle(etapaDeRutaDeFabricacion);
        }
    }
    
    public void guardarDetallesEnLaEtapa(EtapaDeRutaDeFabricacion etapa) {
     //   System.out.println(etapaEnEspera.size());
        for (DetalleDeArticuloEnEtapaDeFabricacion detalleArticulo : listaDetallesDeAticulosADevolver) {
            etapa.addDetalle(detalleArticulo);
        }
    }

    
}
