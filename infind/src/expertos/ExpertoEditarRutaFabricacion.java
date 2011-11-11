/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOArticulo;
import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.Herramientas;
import Entidades.MaestroDeRutaDeFabricacion;
import Entidades.Maquina;
import Entidades.MateriaPrima;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;
import excepciones.ExpertoExceptionRutaFabricacion;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoEditarRutaFabricacion extends Experto {

    List<EtapaDeRutaDeFabricacion> etapaEnEspera = new ArrayList<EtapaDeRutaDeFabricacion>();
    List<DetalleDeArticuloEnEtapaDeFabricacion> detalleArticuloGuardado = new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>();
    List<DetalleDeArticuloEnEtapaDeFabricacion> listaDetallesDeAticulosAGuardar = new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>();

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

    public List<MateriaPrima> buscarMateriasPrimas() {

        List<MateriaPrima> listaMateriaPrima = null;
        Criteria criterioMatPrim = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterioMatPrim);
        return listaMateriaPrima;

    }

    public List<Herramientas> buscarHerramientas() {

        List<Herramientas> listaHerramientas = null;
        Criteria criterioHerram = Fachada.getInstancia().crearCriterio(Herramientas.class);
        listaHerramientas = Fachada.getInstancia().buscar(Herramientas.class, criterioHerram);
        return listaHerramientas;

    }

    public List<Maquina> buscarMaquinas() {

        List<Maquina> listaMaquinas = null;
        Criteria criterioMaqu = Fachada.getInstancia().crearCriterio(Maquina.class);
        listaMaquinas = Fachada.getInstancia().buscar(Maquina.class, criterioMaqu);
        return listaMaquinas;

    }

    public EtapaDeRutaDeFabricacion buscarEtapasAEliminar(Object valorEtapa, MaestroDeRutaDeFabricacion rutaCargada) {

        //pueden se las etapas que se encuentran en la ruta cargada o las etapas nuevas que creé

        for (EtapaDeRutaDeFabricacion etapaDeRutaCreada : etapaEnEspera) {
            int nroEtapaCreada = etapaDeRutaCreada.getNroEtapa();

            if (String.valueOf(nroEtapaCreada) == null ? valorEtapa.toString() == null : String.valueOf(nroEtapaCreada).equals(valorEtapa.toString())) {

                return etapaDeRutaCreada;
            } else {
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = rutaCargada.getEtapaRutaFabricacion();
                for (EtapaDeRutaDeFabricacion etapaDeRutaCargada : etapaRutaFabricacion) {
                    int nroEtapaCargada = etapaDeRutaCargada.getNroEtapa();

                    if (String.valueOf(nroEtapaCargada) == null ? valorEtapa.toString() == null : String.valueOf(nroEtapaCargada).equals(valorEtapa.toString())) {

                        return etapaDeRutaCreada;


                    }


                }

                

            }
           
        }
        return null;
    }

    public void eliminarEtapa(EtapaDeRutaDeFabricacion etapaSeleccionada) {

        //puedo eliminar las etapas que creé nuevas o las que ya tenia la ruta cargada

        etapaEnEspera.remove(etapaSeleccionada);


    }
}
