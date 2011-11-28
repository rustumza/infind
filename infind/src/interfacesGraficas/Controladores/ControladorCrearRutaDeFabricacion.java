/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOArticulo;
import DTOs.DTOCantidades;
import DTOs.DTOCentro;
import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
import Entidades.DetalleEstructuraDeProducto;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.Herramientas;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.MaestroDeEstructuraDeProducto;
import Entidades.MaestroDeRutaDeFabricacion;
import Entidades.Maquina;
import Entidades.MateriaPrima;
import Entidades.Operario;
import Entidades.ProductoComponente;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoExceptionRutaFabricacion;
import expertos.ExpertoRutaDeFabricacion;
import interfacesGraficas.ModeloComboYListas.ModeloJListListaMaestroDeArticulo;
import interfacesGraficas.ModeloComboYListas.ModeloJListListaMateriaPrima;
import interfacesGraficas.ModeloComboYListas.ModeloJListaMaquinas;
import interfacesGraficas.ModeloComboYListas.ModeloJlistaHerramientas;
import interfacesGraficas.ModeloTablas.ModeloTablaEtapaRutaAgregada;
import interfacesGraficas.PantCrearRutaDeFabricacion;
import interfacesGraficas.PantallaCantidad;
import interfacesGraficas.PantallaMadre;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import persistencia.Conexion;

/**
 *
 * @author eduardo
 */
public class ControladorCrearRutaDeFabricacion {

    PantCrearRutaDeFabricacion pantallaCrearRutaFabricacion;
    PantallaMadre pantallaMadre;
    PantallaCantidad pantallaCantidad;
    ExpertoRutaDeFabricacion expertoRutaFabricacion;
    ControladorPantallaMadre controladorPantallaMadre;
    ModeloTablaEtapaRutaAgregada modeloTAblaEtapaAgregada;
    MaestroDeCentroDeTrabajo centroEncontrado;
    ProductoFinal articuloEncontradoFinal;
    ProductoIntermedio articuloEncontradoIntermedio;
    ProductoTipoIQE articuloEncontradoIQE;
    ProductoTipoIQE TipoIQEencontrado;
    EtapaDeRutaDeFabricacion nuevaEtapa = new EtapaDeRutaDeFabricacion();
    EtapaDeRutaDeFabricacion etapaSeleccionada;
    EtapaDeRutaDeFabricacion etapaAMostrar;
    ModeloJListListaMateriaPrima modeloListaMateriasPrimas = new ModeloJListListaMateriaPrima();
    MaestroDeRutaDeFabricacion rutaNueva = new MaestroDeRutaDeFabricacion();
    Object productoBuscado;
    private List<DetalleDeArticuloEnEtapaDeFabricacion> detallesAGuardar;
    List<DTOCantidades> listaDetallesCantidad;
    DetalleEstructuraDeProducto detalleEstructuraProductoCantidades = new DetalleEstructuraDeProducto();

    // String cantidad;
    public ControladorCrearRutaDeFabricacion(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();



    }

    public void crearRutaFabricacion() {

        expertoRutaFabricacion = (ExpertoRutaDeFabricacion) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.RUTA_FABRICACION);

        pantallaCrearRutaFabricacion = new PantCrearRutaDeFabricacion(pantallaMadre, false, this);
        pantallaCantidad = new PantallaCantidad(this);
        modeloTAblaEtapaAgregada = new ModeloTablaEtapaRutaAgregada();
        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
        pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(new ArrayList<MaestroDeArticulo>()));
        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMaestroDeArticulo(new ArrayList<MaestroDeArticulo>()));
        pantallaCrearRutaFabricacion.getListaHerramientasAgregadas().setModel(new ModeloJlistaHerramientas(new ArrayList<Herramientas>()));
        pantallaCrearRutaFabricacion.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(new ArrayList<Herramientas>()));
        pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(new ArrayList<Maquina>()));
        pantallaCrearRutaFabricacion.getListaMaquinasAgregadas().setModel(new ModeloJListaMaquinas(new ArrayList<Maquina>()));
        pantallaCrearRutaFabricacion.setVisible(true);
        pantallaCrearRutaFabricacion.setLocationRelativeTo(null);



    }

    public void guardarRutaDeFabricacion() throws ExpertoExceptionRutaFabricacion {


        if (rutaNueva != null) {
            rutaNueva = new MaestroDeRutaDeFabricacion();

        }
        rutaNueva.setEliminado(Boolean.FALSE);
        rutaNueva.setNumero("7");
        rutaNueva.setEtapaRutaFabricacion(new ArrayList<EtapaDeRutaDeFabricacion>());

        Conexion.getInstancia().iniciarTX();


        expertoRutaFabricacion.persistirRutaDeFabricacion(rutaNueva);

        List<EtapaDeRutaDeFabricacion> etapasAGuardar = expertoRutaFabricacion.devolverEtapasAGuardar();
        expertoRutaFabricacion.guardarEtapasEnLaRuta(rutaNueva);

        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapasAGuardar) {

            if (!etapaDeRutaDeFabricacion.isTipoIQE()) {
                expertoRutaFabricacion.persistirEtapaRutaFabricacion(etapaDeRutaDeFabricacion);

            } else {
                expertoRutaFabricacion.persistirEtapaRutaFabricacion(etapaDeRutaDeFabricacion);
                //busco los detalles d la etapa
                detallesAGuardar = expertoRutaFabricacion.devolverDetalleDeLaEtapa(etapaDeRutaDeFabricacion);

                expertoRutaFabricacion.guardarDetallesEnLaEtapa(etapaDeRutaDeFabricacion);

                for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesAGuardar) {

                    expertoRutaFabricacion.persistirDetalleArticuloEnEtapaFabricacion(detalleDeArticuloEnEtapaDeFabricacion);
                }
                etapaDeRutaDeFabricacion.setDetallesArtEnEtapaFabList(detallesAGuardar);

                expertoRutaFabricacion.persistirEtapaRutaFabricacion(etapaDeRutaDeFabricacion);
            }
        }
        expertoRutaFabricacion.persistirRutaDeFabricacion(rutaNueva);


        Object Item = pantallaCrearRutaFabricacion.getComboBoxTipoProducto().getSelectedItem();

        //debo asignarle la ruta guardadda al maestro de estructura de producto del producto buscado en pantalla al principio
        if (Item.toString() == "Producto Final") {
            MaestroDeEstructuraDeProducto maestroEstructuraDeProducto = articuloEncontradoFinal.getMaestroEstructuraDeProducto();
            maestroEstructuraDeProducto.setMaestroRutaFabricacion(rutaNueva);
            expertoRutaFabricacion.persistirEstructuraDeProducto(maestroEstructuraDeProducto);
        } else if (Item.toString() == "Producto Intermedio") {
            MaestroDeEstructuraDeProducto maestroEstructuraDeProducto = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto();
            maestroEstructuraDeProducto.setMaestroRutaFabricacion(rutaNueva);
            expertoRutaFabricacion.persistirEstructuraDeProducto(maestroEstructuraDeProducto);


        } else {

            MaestroDeEstructuraDeProducto maestroEstructuraDeProducto = articuloEncontradoIQE.getMaestroEstructuraDeProducto();
            maestroEstructuraDeProducto.setMaestroRutaFabricacion(rutaNueva);
            expertoRutaFabricacion.persistirEstructuraDeProducto(maestroEstructuraDeProducto);

        }



        Conexion.getInstancia().confirmarTx();
        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        limpiarPantalla();
        
        limpiarExpertoRuta();
    }
    
    public void limpiarExpertoRuta(){
        expertoRutaFabricacion.limpiarExperto();
    }

    public void limpiarPantalla() {

        pantallaCrearRutaFabricacion.getCampoBuscaCodigoCentro().setText("");
        pantallaCrearRutaFabricacion.getCampoBuscaNombreCentro().setText("");
        pantallaCrearRutaFabricacion.getCampoCodigoCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoDescripcionCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoNombreCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoBuscaCodigoProdFinal().setText("");
        pantallaCrearRutaFabricacion.getCampoBuscaNombreProdFinal().setText("");
        pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText("");
        pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText("");
        pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText("");
        pantallaCrearRutaFabricacion.getListaHerramientasAgregadas().setModel(new ModeloJlistaHerramientas());
        pantallaCrearRutaFabricacion.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas());
        pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas());
        pantallaCrearRutaFabricacion.getListaMaquinasAgregadas().setModel(new ModeloJListaMaquinas());
        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMaestroDeArticulo());
        pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo());
        modeloTAblaEtapaAgregada.clear();
        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
    }

    public void buscarCentro() throws ExpertoExceptionRutaFabricacion {



        if (pantallaCrearRutaFabricacion.getRadioBotonBuscaCodigoCentro().isSelected()) {
            centroEncontrado = expertoRutaFabricacion.buscarCentros(armarDTOCentro(1));

            if (!centroEncontrado.getCodigo().isEmpty()) {
                pantallaCrearRutaFabricacion.getCampoCodigoCentroEncontrado().setText(centroEncontrado.getCodigo());
                pantallaCrearRutaFabricacion.getCampoDescripcionCentroEncontrado().setText(centroEncontrado.getDescripcion());
                pantallaCrearRutaFabricacion.getCampoNombreCentroEncontrado().setText(centroEncontrado.getNombreCentro());
                List<Herramientas> herramientas = centroEncontrado.getHerramientas();
                List<Maquina> maquinas = centroEncontrado.getMaquinas();
                List<Operario> operario = centroEncontrado.getOperario();
                List<Herramientas> listaHerramientas = new ArrayList<Herramientas>();
                for (Herramientas listaherramientas : herramientas) {
                    listaHerramientas.add(listaherramientas);
                }
                pantallaCrearRutaFabricacion.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(listaHerramientas));
                pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(maquinas));
            }

        } else if (pantallaCrearRutaFabricacion.getRadioBotonBuscaNombreCentro().isSelected()) {
            centroEncontrado = expertoRutaFabricacion.buscarCentros(armarDTOCentro(2));

            if (!centroEncontrado.getNombreCentro().isEmpty()) {
                pantallaCrearRutaFabricacion.getCampoCodigoCentroEncontrado().setText(centroEncontrado.getCodigo());
                pantallaCrearRutaFabricacion.getCampoDescripcionCentroEncontrado().setText(centroEncontrado.getDescripcion());
                pantallaCrearRutaFabricacion.getCampoNombreCentroEncontrado().setText(centroEncontrado.getNombreCentro());
                List<Herramientas> herramientas = centroEncontrado.getHerramientas();
                List<Maquina> maquinas = centroEncontrado.getMaquinas();
                List<Operario> operario = centroEncontrado.getOperario();
                pantallaCrearRutaFabricacion.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(herramientas));
                pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(maquinas));

            }
        }
    }

    public DTOCentro armarDTOCentro(Integer caso) {

        DTOCentro nuevoDto = new DTOCentro();

        switch (caso) {
            case 1:

                if (!pantallaCrearRutaFabricacion.getCampoBuscaCodigoCentro().getText().equals("")) {
                    nuevoDto.setCodigoCentro(pantallaCrearRutaFabricacion.getCampoBuscaCodigoCentro().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaCrearRutaFabricacion.getCampoBuscaNombreCentro().getText().equals("")) {
                    nuevoDto.setNombreCentro(pantallaCrearRutaFabricacion.getCampoBuscaNombreCentro().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void guardarEtapa() throws ExpertoExceptionRutaFabricacion {

        DetalleDeArticuloEnEtapaDeFabricacion detalleArticulo = new DetalleDeArticuloEnEtapaDeFabricacion();
        List<DetalleDeArticuloEnEtapaDeFabricacion> listaDeDetallesArticulos = new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>();
        List<MaestroDeArticulo> materiasPrimas = new ArrayList<MaestroDeArticulo>();

        if (nuevaEtapa != null) {
            nuevaEtapa = new EtapaDeRutaDeFabricacion();

        }

//verifica si es un producto tipoIQE
        if (pantallaCrearRutaFabricacion.getComboBoxTipoProducto().getModel().getSelectedItem().equals("Producto Tipo IQE")) {

            //verifica si el campo TipoIQE esta seleccionado
            if (pantallaCrearRutaFabricacion.getCheckEsTipoIQE().isSelected()) {

                //ver el tema de las maquinas y herramientas que usa

                nuevaEtapa.setCantidadDeOperarios(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().getText()));
                nuevaEtapa.setEliminado(Boolean.FALSE);
                nuevaEtapa.setMaestroCentroTrabajo(centroEncontrado);
                nuevaEtapa.setNombreEtapa(pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().getText());
                nuevaEtapa.setNroEtapa(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().getText()));
                nuevaEtapa.setTiempoDeTrabajoDeMaquinas(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().getText()));
                nuevaEtapa.setTiempoDeTrabajoDeOperarios(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().getText()));
                nuevaEtapa.setTiempoDeTrabajoTotal(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().getText()) + Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().getText()));
                nuevaEtapa.setTipoIQE(true);
                nuevaEtapa.setDetallesArtEnEtapaFabList(new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>());

                List<MaestroDeArticulo> listaMatPrims = ((ModeloJListListaMaestroDeArticulo) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getMaestrosDeArticulo();

                for (int i = 0; i < (listaMatPrims.size()); i++) {

                    MaestroDeArticulo matPrima = ((ModeloJListListaMaestroDeArticulo) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getElementAt(i);
                    materiasPrimas.add(matPrima);
                    if (detalleArticulo == null) {
                        detalleArticulo = new DetalleDeArticuloEnEtapaDeFabricacion();
                    }

                    Object selected = pantallaCrearRutaFabricacion.getComboBoxTipoProducto().getSelectedItem();

                    //busca las cantidades que hay de cada materia prima o producto componente en la tabla detalleEstructuraproducto
                    // y se lo setea al detalleArticulo

                    if (selected.toString() == "Producto Final") {
                        List<DetalleEstructuraDeProducto> detalleEstructuraProductoList = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoList) {

                            if (detalleEstructuraDeProducto.getMaestroArticulo().equals(matPrima)) {
                                for (int j = 0; j < listaDetallesCantidad.size(); j++) {
                                    if (listaDetallesCantidad.get(i).getNombre().equals(matPrima)) {

                                        detalleArticulo.setCantidad(Float.valueOf(listaDetallesCantidad.get(i).getCantidad()));
                                        detalleEstructuraProductoCantidades.setMaestroArticulo(matPrima);
                                        detalleEstructuraProductoCantidades.setCantidad(detalleEstructuraDeProducto.getCantidad() - Float.valueOf(listaDetallesCantidad.get(i).getCantidad()));
                                        expertoRutaFabricacion.guardarDetallesEstructuraProductosCAntidades(detalleEstructuraProductoCantidades);

                                    }

                                }


                            }
                        }
                    } else if (selected.toString() == "Producto Intermedio") {
                        List<DetalleEstructuraDeProducto> detalleEstructuraProductoList = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoList) {

                            if (detalleEstructuraDeProducto.getMaestroArticulo().equals(matPrima)) {
                                for (int j = 0; j < listaDetallesCantidad.size(); j++) {
                                    if (listaDetallesCantidad.get(i).getNombre().equals(matPrima)) {

                                        detalleArticulo.setCantidad(Float.valueOf(listaDetallesCantidad.get(i).getCantidad()));
                                        detalleEstructuraProductoCantidades.setMaestroArticulo(matPrima);
                                        detalleEstructuraProductoCantidades.setCantidad(detalleEstructuraDeProducto.getCantidad() - Float.valueOf(listaDetallesCantidad.get(i).getCantidad()));
                                        expertoRutaFabricacion.guardarDetallesEstructuraProductosCAntidades(detalleEstructuraProductoCantidades);
                                    }

                                }
                            }
                        }
                    } else if (selected.toString() == "Producto TipoIQE") {
                        List<DetalleEstructuraDeProducto> detalleEstructuraProductoList = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoList) {

                            if (detalleEstructuraDeProducto.getMaestroArticulo().equals(matPrima)) {
                                for (int j = 0; j < listaDetallesCantidad.size(); j++) {
                                    if (listaDetallesCantidad.get(i).getNombre().equals(matPrima)) {

                                        detalleArticulo.setCantidad(Float.valueOf(listaDetallesCantidad.get(i).getCantidad()));
                                        detalleEstructuraProductoCantidades.setMaestroArticulo(matPrima);
                                        detalleEstructuraProductoCantidades.setCantidad(detalleEstructuraDeProducto.getCantidad() - Float.valueOf(listaDetallesCantidad.get(i).getCantidad()));
                                        expertoRutaFabricacion.guardarDetallesEstructuraProductosCAntidades(detalleEstructuraProductoCantidades);
                                    }

                                }
                            }
                        }
                    }

                    detalleArticulo.setEliminado(Boolean.FALSE);
                    detalleArticulo.setUnidadDeMedida(matPrima.getUnidadDeMedida());
                    detalleArticulo.setNumero(1);
                    detalleArticulo.setMaestroArticulo(matPrima);
                    detalleArticulo.setEtapaRutaFabricacion(nuevaEtapa);
                    expertoRutaFabricacion.guardarDetalleArticuloEnEtapaFabricacion(detalleArticulo);
                    listaDeDetallesArticulos.add(detalleArticulo);
                    detalleArticulo = null;

                }

                expertoRutaFabricacion.guardarEtapaRutaFabricacion(nuevaEtapa);
                modeloTAblaEtapaAgregada.addRow(nuevaEtapa);
                limpiarPantallaEtapa();
                pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);

//si no esta seleccionado el campo TipoIQE, guarda la etapa vacía para que se pueda cargar en la búsquedas de los productos         
            } else {
                nuevaEtapa.setEliminado(Boolean.FALSE);
                nuevaEtapa.setNroEtapa(0);
                nuevaEtapa.setTipoIQE(false);
                nuevaEtapa.setCantidadDeOperarios(0);
                nuevaEtapa.setMaestroCentroTrabajo(null);
                nuevaEtapa.setNombreEtapa("");
                nuevaEtapa.setTiempoDeTrabajoDeMaquinas(0);
                nuevaEtapa.setTiempoDeTrabajoDeOperarios(0);
                nuevaEtapa.setTiempoDeTrabajoTotal(0);
                nuevaEtapa.setDetallesArtEnEtapaFabList(null);
                expertoRutaFabricacion.guardarEtapaRutaFabricacion(nuevaEtapa);
                modeloTAblaEtapaAgregada.addRow(nuevaEtapa);
                limpiarPantallaEtapa();
                pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);
            }
//si es producto intermedio o producto final     
        } else {
            nuevaEtapa.setCantidadDeOperarios(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().getText()));
            nuevaEtapa.setEliminado(Boolean.FALSE);
            nuevaEtapa.setMaestroCentroTrabajo(centroEncontrado);
            nuevaEtapa.setNombreEtapa(pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().getText());
            nuevaEtapa.setNroEtapa(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().getText()));
            nuevaEtapa.setTiempoDeTrabajoDeMaquinas(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().getText()));
            nuevaEtapa.setTiempoDeTrabajoDeOperarios(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().getText()));
            nuevaEtapa.setTiempoDeTrabajoTotal(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().getText()) + Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().getText()));
            nuevaEtapa.setTipoIQE(false);

            List<MaestroDeArticulo> listaMatPrims = ((ModeloJListListaMaestroDeArticulo) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getMaestrosDeArticulo();

            for (int i = 0; i < (listaMatPrims.size()); i++) {
                
                if (detalleArticulo == null) {
                    detalleArticulo = new DetalleDeArticuloEnEtapaDeFabricacion();
                }

                MaestroDeArticulo matPrima = ((ModeloJListListaMaestroDeArticulo) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getElementAt(i);
                materiasPrimas.add(matPrima);

                detalleArticulo.setCantidad(2);
                detalleArticulo.setEliminado(Boolean.FALSE);
                detalleArticulo.setUnidadDeMedida("LITROS");
                detalleArticulo.setNumero(1);
                detalleArticulo.setMaestroArticulo(matPrima);
                detalleArticulo.setEtapaRutaFabricacion(nuevaEtapa);
                expertoRutaFabricacion.guardarDetalleArticuloEnEtapaFabricacion(detalleArticulo);
                listaDeDetallesArticulos.add(detalleArticulo);
                detalleArticulo = null;
            }

            expertoRutaFabricacion.guardarEtapaRutaFabricacion(nuevaEtapa);
//            modeloTAblaEtapaAgregada.removeElement(etapaAMostrar);
            modeloTAblaEtapaAgregada.addRow(nuevaEtapa);
            modeloTAblaEtapaAgregada.fireTableDataChanged();
            limpiarPantallaEtapa();


        }


        if (pantallaCrearRutaFabricacion.getCheckEsTipoIQE().isSelected()) {
            pantallaCrearRutaFabricacion.getCheckEsTipoIQE().setSelected(false);

        }
        /* if (!verificarDatosEtapa()) {
        } else {
        throw new ExpertoExceptionRutaFabricacion("Faltan Completar Campos");
        }*/
    }

    public void agregarMatriaPrima() {
        int seleccionado = pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().getSelectedIndex();
        MaestroDeArticulo materiaPrimaSeleccionada = ((ModeloJListListaMaestroDeArticulo) pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().getModel()).getElementAt(seleccionado);
        List<MaestroDeArticulo> listaMatPrims = ((ModeloJListListaMaestroDeArticulo) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getMaestrosDeArticulo();

        if (!listaMatPrims.contains(materiaPrimaSeleccionada)) {
            listaMatPrims.add(materiaPrimaSeleccionada);
        }


        Object selected = pantallaCrearRutaFabricacion.getComboBoxTipoProducto().getSelectedItem();
        if (selected.toString() == "Producto Final") {
            List<DetalleEstructuraDeProducto> detalleEstructuraProductoList = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
            for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoList) {

                if (detalleEstructuraDeProducto.getMaestroArticulo().equals(materiaPrimaSeleccionada)) {
                    pantallaCantidad = new PantallaCantidad(this);
                    pantallaCantidad.getCampoCantidadDisopnible().setText(String.valueOf(detalleEstructuraDeProducto.getCantidad()));
                    pantallaCantidad.getCampoElemento().setText(materiaPrimaSeleccionada.getNombre());
                    pantallaCantidad.setVisible(true);
                    //detalleArticulo.setCantidad(detalleEstructuraDeProducto.getCantidad());
                }
            }
        } else if (selected.toString() == "Producto Intermedio") {
            List<DetalleEstructuraDeProducto> detalleEstructuraProductoList = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
            for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoList) {

                if (detalleEstructuraDeProducto.getMaestroArticulo().equals(materiaPrimaSeleccionada)) {
                    pantallaCantidad = new PantallaCantidad(this);
                    pantallaCantidad.getCampoCantidadDisopnible().setText(String.valueOf(detalleEstructuraDeProducto.getCantidad()));
                    pantallaCantidad.getCampoElemento().setText(materiaPrimaSeleccionada.getNombre());
                    pantallaCantidad.setVisible(true);

                }
            }
        } else if (selected.toString() == "Producto Tipo IQE") {
            List<DetalleEstructuraDeProducto> detalleEstructuraProductoList = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
            for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoList) {

                if (detalleEstructuraDeProducto.getMaestroArticulo().equals(materiaPrimaSeleccionada)) {
                    pantallaCantidad = new PantallaCantidad(this);
                    pantallaCantidad.getCampoCantidadDisopnible().setText(String.valueOf(detalleEstructuraDeProducto.getCantidad()));
                    pantallaCantidad.getCampoElemento().setText(materiaPrimaSeleccionada.getNombre());
                    pantallaCantidad.setVisible(true);

                }
            }
        }




        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMaestroDeArticulo(listaMatPrims));

    }

    public void sacarMateriaPrima() {
        //TODO: hacer metodo
        System.out.println("sacar materia prima");
        System.out.println("metodo no implementado!!");

    }

    public void agregarHerramientas() {
        int seleccionado = pantallaCrearRutaFabricacion.getListaHerramientasCargadas().getSelectedIndex();
        Herramientas herramientasSeleccionada = ((ModeloJlistaHerramientas) pantallaCrearRutaFabricacion.getListaHerramientasCargadas().getModel()).getElementAt(seleccionado);
        List<Herramientas> listaHerramientas = ((ModeloJlistaHerramientas) pantallaCrearRutaFabricacion.getListaHerramientasAgregadas().getModel()).getHerramientas();

        if (!listaHerramientas.contains(herramientasSeleccionada)) {
            listaHerramientas.add(herramientasSeleccionada);
        }



        pantallaCrearRutaFabricacion.getListaHerramientasAgregadas().setModel(new ModeloJlistaHerramientas(listaHerramientas));

    }

    public void sacarHerramientas() {
        //TODO: hacer metodo
        System.out.println("sacar herramientas");
        System.out.println("metodo no implementado!!");

    }

    public void agregarMaquinas() {
        int seleccionado = pantallaCrearRutaFabricacion.getListaMaquiansCargadas().getSelectedIndex();
        Maquina maquinasSeleccionada = ((ModeloJListaMaquinas) pantallaCrearRutaFabricacion.getListaMaquiansCargadas().getModel()).getElementAt(seleccionado);
        List<Maquina> listaMaquinas = ((ModeloJListaMaquinas) pantallaCrearRutaFabricacion.getListaMaquinasAgregadas().getModel()).getMaquinas();

        if (!listaMaquinas.contains(maquinasSeleccionada)) {
            listaMaquinas.add(maquinasSeleccionada);
        }



        pantallaCrearRutaFabricacion.getListaMaquinasAgregadas().setModel(new ModeloJListaMaquinas(listaMaquinas));

    }

    public void sacarMaquina() {
        //TODO: hacer metodo
        System.out.println("sacar maquina");
        System.out.println("metodo no implementado!!");

    }

    public void limpiarPantallaEtapa() {

        pantallaCrearRutaFabricacion.getCampoBuscaCodigoCentro().setText("");
        pantallaCrearRutaFabricacion.getCampoBuscaNombreCentro().setText("");
        pantallaCrearRutaFabricacion.getCampoCodigoCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoDescripcionCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoNombreCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getListaHerramientasAgregadas().setModel(new ModeloJlistaHerramientas());
        pantallaCrearRutaFabricacion.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas());
        pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas());
        pantallaCrearRutaFabricacion.getListaMaquinasAgregadas().setModel(new ModeloJListaMaquinas());
        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMaestroDeArticulo());
        listaDetallesCantidad = null;
        pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(false);
    }

    public void buscarProducto() throws ExpertoExceptionRutaFabricacion {
        Object selectedItem = pantallaCrearRutaFabricacion.getComboBoxTipoProducto().getSelectedItem();
        List<MaestroDeArticulo> listaDeLosProductos = new ArrayList<MaestroDeArticulo>();



        if (selectedItem.toString() == "Producto Final") {
            modeloTAblaEtapaAgregada.clear();
            pantallaCrearRutaFabricacion.getCheckEsTipoIQE().setEnabled(false);
            
            if (pantallaCrearRutaFabricacion.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoFinal = expertoRutaFabricacion.buscarProductoFinal(armarDTOArticulo(1));
                if (!articuloEncontradoFinal.getCodigo().isEmpty()) {
                    
                    if (articuloEncontradoFinal.getProductoTipoIQE() != null) {
                        MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                        if (maestroRutaFabricacion != null) {

                            JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + " '" + " ya tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                        } else {

                            pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                            pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                            pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());
                            //pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);
                            List<DetalleEstructuraDeProducto> materiasPrimasProdFinal = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

                            for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdFinal) {

                                //si es materia prima
                                if (detalleEstructuraDeProducto.getTipo().equals("Materia prima")) {
                                    MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                                    listaDeLosProductos.add(materia);
                                } else {// si es producto componente
                                    ProductoIntermedio materia = (ProductoIntermedio) detalleEstructuraDeProducto.getMaestroArticulo();
                                    listaDeLosProductos.add(materia);
                                }
                            }
                            //busco las etapas del producto TIpoIQE relacionado
                            MaestroDeRutaDeFabricacion maestroRutaFabricacionTipoIQE = articuloEncontradoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                            if (maestroRutaFabricacionTipoIQE != null) {
                                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = maestroRutaFabricacionTipoIQE.getEtapaRutaFabricacion();
                                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                    if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                        modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada   


                                    } else if (!etapaDeRutaDeFabricacion.isTipoIQE()) {

                                        modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);

                                    }

                                }
                                pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);

                            } else {
                                JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Tipo IQE asociado al Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + "' " + " no tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                                pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(false);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + "' " + " no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                        //lanzar excepcion
                    }

                }

            } else if (pantallaCrearRutaFabricacion.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoFinal = (ProductoFinal) expertoRutaFabricacion.buscarProductoFinal(armarDTOArticulo(2));

                if (!articuloEncontradoFinal.getNombre().isEmpty()) {
                    if (articuloEncontradoFinal.getProductoTipoIQE() != null) {
                        MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                        if (maestroRutaFabricacion != null) {

                            JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + " '" + " ya tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                        } else {

                            pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                            pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                            pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());
                            //pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);
                            List<DetalleEstructuraDeProducto> materiasPrimasProdFinal = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                            for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdFinal) {

                                //si es materia prima
                                if (detalleEstructuraDeProducto.getTipo().equals("Materia prima")) {
                                    MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                                    listaDeLosProductos.add(materia);
                                } else {// si es producto componente
                                    ProductoIntermedio materia = (ProductoIntermedio) detalleEstructuraDeProducto.getMaestroArticulo();
                                    listaDeLosProductos.add(materia);
                                }
                            }



                            MaestroDeRutaDeFabricacion maestroRutaFabricacionTipoIQE = articuloEncontradoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                            if (maestroRutaFabricacionTipoIQE != null) {

                                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = maestroRutaFabricacionTipoIQE.getEtapaRutaFabricacion();

                                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                    if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                        modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    
                                        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    } else {
                                        modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    }

                                }

                            } else {
                                JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Tipo IQE asociado al Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + "' " + " no tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                                pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(false);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + " '" + " no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                        //lanzar excepcion
                    }
                }
            }
            pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));

        } else if (selectedItem.toString() == "Producto Intermedio") {
            modeloTAblaEtapaAgregada.clear();
            pantallaCrearRutaFabricacion.getCheckEsTipoIQE().setEnabled(false);
            if (pantallaCrearRutaFabricacion.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoIntermedio = expertoRutaFabricacion.buscarProductoIntermedio(armarDTOArticulo(1));

                if (!articuloEncontradoIntermedio.getCodigo().isEmpty()) {
                    if (articuloEncontradoIntermedio.getProductoTipoIQE() != null) {
                        MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                        if (maestroRutaFabricacion != null) {

                            JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Intermedio: " + " '" + articuloEncontradoIntermedio.getNombre() + " '" + " ya tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                        } else {

                            pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoIntermedio.getCodigo());
                            pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoIntermedio.getDescripcion());
                            pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoIntermedio.getNombre());
                            //pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);
                            List<DetalleEstructuraDeProducto> materiasPrimasProdIntermedio = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                            for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIntermedio) {

                                //si es materia prima
                                if (detalleEstructuraDeProducto.getTipo().equals("Materia prima")) {
                                    MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                                    listaDeLosProductos.add(materia);
                                } else {// si es producto componente
                                    ProductoIntermedio materia = (ProductoIntermedio) detalleEstructuraDeProducto.getMaestroArticulo();
                                    listaDeLosProductos.add(materia);
                                }
                            }


                            MaestroDeRutaDeFabricacion maestroRutaFabricacionTipoIQE = articuloEncontradoIntermedio.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                            if (maestroRutaFabricacionTipoIQE != null) {

                                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = maestroRutaFabricacionTipoIQE.getEtapaRutaFabricacion();

                                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                    if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                        modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada 
                                        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    } else {
                                        modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    }

                                }
                            } else {
                                JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Tipo IQE asociado al Producto Intermedio: " + " '" + articuloEncontradoFinal.getNombre() + "' " + " no tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                                pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(false);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Intermedio: " + " '" + articuloEncontradoIntermedio.getNombre() + "' " + " no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                    }


                }

            } else if (pantallaCrearRutaFabricacion.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoIntermedio = expertoRutaFabricacion.buscarProductoIntermedio(armarDTOArticulo(2));

                if (!articuloEncontradoIntermedio.getNombre().isEmpty()) {
                    if (articuloEncontradoIntermedio.getProductoTipoIQE() != null) {
                        MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                        if (maestroRutaFabricacion != null) {

                            JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Intermedio: " + " '" + articuloEncontradoIntermedio.getNombre() + " '" + " ya tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                        } else {

                            pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoIntermedio.getCodigo());
                            pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoIntermedio.getDescripcion());
                            pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoIntermedio.getNombre());
                           // pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);


                            List<DetalleEstructuraDeProducto> materiasPrimasProdIntermedio = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();


                            for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIntermedio) {

                                //si es materia prima
                                if (detalleEstructuraDeProducto.getTipo().equals("Materia prima")) {
                                    MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                                    listaDeLosProductos.add(materia);
                                } else {// si es producto componente
                                    ProductoIntermedio materia = (ProductoIntermedio) detalleEstructuraDeProducto.getMaestroArticulo();
                                    listaDeLosProductos.add(materia);
                                }
                            }

                            MaestroDeRutaDeFabricacion maestroRutaFabricacionTipoIQE = articuloEncontradoIntermedio.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                            if (maestroRutaFabricacionTipoIQE != null) {

                                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = maestroRutaFabricacionTipoIQE.getEtapaRutaFabricacion();

                                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                    if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                        modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada  
                                        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    } else {
                                        modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    }

                                }

                            } else {
                                JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Tipo IQE asociado al Producto Intermedio: " + " '" + articuloEncontradoFinal.getNombre() + "' " + " no tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                                pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(false);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Intermedio: " + " '" + articuloEncontradoIntermedio.getNombre() + "' " + " no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                    }

                }


            }

            pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));

        } else if (selectedItem.toString() == "Producto Tipo IQE") {
            if (!pantallaCrearRutaFabricacion.getCheckEsTipoIQE().isEnabled()) {
                pantallaCrearRutaFabricacion.getCheckEsTipoIQE().setEnabled(true);
            }
            modeloTAblaEtapaAgregada.clear();
            if (pantallaCrearRutaFabricacion.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoIQE = expertoRutaFabricacion.buscarProductoIQE(armarDTOArticulo(1));

                if (!articuloEncontradoIQE.getCodigo().isEmpty()) {
                    MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                    if (maestroRutaFabricacion != null) {

                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Tipo IQE: " + " '" + articuloEncontradoIQE.getNombre() + " '" + " ya tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                    } else {

                        pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoIQE.getCodigo());
                        pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoIQE.getDescripcion());
                        pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoIQE.getNombre());
                        pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);


                        List<DetalleEstructuraDeProducto> materiasPrimasProdIQE = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIQE) {

                            //si es materia prima
                            if (detalleEstructuraDeProducto.getTipo().equals("Materia prima")) {
                                MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                                listaDeLosProductos.add(materia);
                            } else {// si es producto componente
                                ProductoIntermedio materia = (ProductoIntermedio) detalleEstructuraDeProducto.getMaestroArticulo();
                                listaDeLosProductos.add(materia);
                            }


                        }
                    }
                }
            } else if (pantallaCrearRutaFabricacion.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoIQE = expertoRutaFabricacion.buscarProductoIQE(armarDTOArticulo(2));

                if (!articuloEncontradoFinal.getNombre().isEmpty()) {
                    MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                    if (maestroRutaFabricacion != null) {

                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Tipo IQE: " + " '" + articuloEncontradoIQE.getNombre() + " '" + " ya tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                    } else {

                        pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoIQE.getCodigo());
                        pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoIQE.getDescripcion());
                        pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoIQE.getNombre());
                        pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);
                        List< DetalleEstructuraDeProducto> materiasPrimasProdIQE = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIQE) {

                            //si es materia prima
                            if (detalleEstructuraDeProducto.getTipo().equals("Materia prima")) {
                                MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                                listaDeLosProductos.add(materia);
                            } else {// si es producto componente
                                ProductoIntermedio materia = (ProductoIntermedio) detalleEstructuraDeProducto.getMaestroArticulo();
                                listaDeLosProductos.add(materia);
                            }
                        }
                    }
                }

            }

            pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));
        }
    }

    public DTOArticulo armarDTOArticulo(Integer caso) {

        DTOArticulo nuevoDto = new DTOArticulo();

        switch (caso) {
            case 1:

                if (!pantallaCrearRutaFabricacion.getCampoBuscaCodigoProdFinal().getText().equals("")) {
                    nuevoDto.setCodigoArticulo(pantallaCrearRutaFabricacion.getCampoBuscaCodigoProdFinal().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaCrearRutaFabricacion.getCampoBuscaNombreProdFinal().getText().equals("")) {
                    nuevoDto.setNombreArticulo(pantallaCrearRutaFabricacion.getCampoBuscaNombreProdFinal().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    private boolean verificarDatosEtapa() {


        if ((pantallaCrearRutaFabricacion.getCampoCodigoCentroEncontrado().getText().equals(""))) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().getText().equals("")) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().getText().equals("")) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().getText().equals("")) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().getText().equals("")) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().getText().equals("")) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().getText().equals("")) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel().getSize() == 0) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getListaHerramientasAgregadas().getModel().getSize() == 0) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getListaMaquinasAgregadas().getModel().getSize() == 0) {
            return true;
        } else if (pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().getText().equals("")) {
            return true;
        } else {
            return false;
        }

    }

    public void setearCantidadArticulo(String cantidadAIngresar, String elemento) {

        if (listaDetallesCantidad == null) {
            listaDetallesCantidad = new ArrayList<DTOCantidades>();
        }
        DTOCantidades dtoCant = new DTOCantidades();
        dtoCant.setCantidad(cantidadAIngresar);
        dtoCant.setNombre(elemento);

        listaDetallesCantidad.add(dtoCant);

    }

    public void eliminarEtapa() {


        int r = pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "Debe seleccionar una Etapa a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        } else {
            Object valorEtapa = pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().getModel().getValueAt(r, 1);
            etapaSeleccionada = expertoRutaFabricacion.buscarEtapasAEliminar(valorEtapa);
            expertoRutaFabricacion.eliminarEtapa(etapaSeleccionada);
            modeloTAblaEtapaAgregada.removeRow(r);
            modeloTAblaEtapaAgregada.fireTableDataChanged();

        }
    }

    public void tabla(Integer fila, Integer click) {

        Object selectedItem = pantallaCrearRutaFabricacion.getComboBoxTipoProducto().getSelectedItem();
        etapaAMostrar = modeloTAblaEtapaAgregada.buscarEtapasRutas(pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().getValueAt(fila, 1).toString());

        if (selectedItem.toString() == "Producto Final") {


            if (etapaAMostrar.isTipoIQE()) {
                limpiarPantallaEtapa();
                bloquearCampos();
                cargarEtapaNoEditable(etapaAMostrar);
            } else {
                limpiarPantallaEtapa();
                desbloquearCampos();
                cargarEtapaEditable(etapaAMostrar);
            }
        } else if (selectedItem.toString() == "Producto Intermedio") {
            if (etapaAMostrar.isTipoIQE()) {
                limpiarPantallaEtapa();
                bloquearCampos();
                cargarEtapaNoEditable(etapaAMostrar);
            } else {
                limpiarPantallaEtapa();
                desbloquearCampos();
                cargarEtapaEditable(etapaAMostrar);
            }

        } else if (selectedItem.toString() == "Producto Tipo IQE") {
            limpiarPantallaEtapa();
            cargarEtapaParaEditar(etapaAMostrar);
        }




    }

    public void cargarEtapaParaEditar(EtapaDeRutaDeFabricacion etapaAMostrar) {



        if (modeloListaMateriasPrimas == null) {
            modeloListaMateriasPrimas = new ModeloJListListaMateriaPrima();

        }

        //busco los detalles de articulo en etapa de fabricacioon
        List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArtEnEtapaFabList = etapaAMostrar.getDetallesArtEnEtapaFabList();
        List<MaestroDeArticulo> listaMateriasPrimas = new ArrayList<MaestroDeArticulo>();

        // por cada detalle saco la materia prima relacionada y se la cargo a la tabla
        for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArtEnEtapaFabList) {
            MaestroDeArticulo maestroArti = detalleDeArticuloEnEtapaDeFabricacion.getMaestroArticulo();
            listaMateriasPrimas.add(maestroArti);

        }

        pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaMateriasPrimas));
        pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().setText(etapaAMostrar.getNombreEtapa());
        pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().setText(String.valueOf(etapaAMostrar.getCantidadDeOperarios()));
        pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().setText(String.valueOf(etapaAMostrar.getNroEtapa()));
        pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().setText(String.valueOf(etapaAMostrar.getTiempoDeTrabajoDeMaquinas()));
        pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().setText(String.valueOf(etapaAMostrar.getTiempoDeTrabajoDeOperarios()));
        pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().setText(String.valueOf(etapaAMostrar.getTiempoDeTrabajoTotal()));

        //inhabilito los campos para que no se puedan modifica

        if (pantallaCrearRutaFabricacion.getCheckEsTipoIQE().isSelected()) {
            pantallaCrearRutaFabricacion.getjScrollPane3().setEnabled(false);
            pantallaCrearRutaFabricacion.getjScrollPane4().setEnabled(false);
            pantallaCrearRutaFabricacion.getjScrollPane5().setEnabled(false);
            pantallaCrearRutaFabricacion.getjScrollPane6().setEnabled(false);
            pantallaCrearRutaFabricacion.getjScrollPane7().setEnabled(false);
            pantallaCrearRutaFabricacion.getjScrollPane8().setEnabled(false);
            pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().setEnabled(false);
            pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().setEnabled(false);
            pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().setEnabled(false);
            pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().setEnabled(false);
            pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().setEnabled(false);
            pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().setEnabled(false);

        }

    }

    private void cargarEtapaNoEditable(EtapaDeRutaDeFabricacion etapaSeleccionado) {

        pantallaCrearRutaFabricacion.getCampoCodigoCentroEncontrado().setText(etapaSeleccionado.getMaestroCentroTrabajo().getCodigo());
        pantallaCrearRutaFabricacion.getCampoNombreCentroEncontrado().setText(etapaSeleccionado.getMaestroCentroTrabajo().getNombreCentro());
        pantallaCrearRutaFabricacion.getCampoDescripcionCentroEncontrado().setText(etapaSeleccionado.getMaestroCentroTrabajo().getDescripcion());
        pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().setText(etapaSeleccionado.getNombreEtapa());
        pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().setText(String.valueOf(etapaSeleccionado.getNroEtapa()));
        pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().setText(String.valueOf(etapaSeleccionado.getTiempoDeTrabajoTotal()));
        pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().setText(String.valueOf(etapaSeleccionado.getTiempoDeTrabajoDeOperarios()));
        pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().setText(String.valueOf(etapaSeleccionado.getTiempoDeTrabajoDeMaquinas()));
        pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().setText(String.valueOf(etapaSeleccionado.getCantidadDeOperarios()));
        List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArtEnEtapaFabList = etapaSeleccionado.getDetallesArtEnEtapaFabList();
        List<MaestroDeArticulo> listaAAgregar = new ArrayList<MaestroDeArticulo>();

        for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArtEnEtapaFabList) {
            if (detalleDeArticuloEnEtapaDeFabricacion.getMaestroArticulo().getClass().equals(MateriaPrima.class)) {
                MateriaPrima maestroArticulo = (MateriaPrima) detalleDeArticuloEnEtapaDeFabricacion.getMaestroArticulo();
                listaAAgregar.add(maestroArticulo);
            } else if (detalleDeArticuloEnEtapaDeFabricacion.getMaestroArticulo().getClass().equals(ProductoComponente.class)) {// si es producto componente
                ProductoIntermedio maestroArticulo = (ProductoIntermedio) detalleDeArticuloEnEtapaDeFabricacion.getMaestroArticulo();
                listaAAgregar.add(maestroArticulo);
            }

        }

        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMaestroDeArticulo(listaAAgregar));

    }

    public void desbloquearCampos() {
        pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().setEnabled(true);
        pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().setEnabled(true);
        pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().setEnabled(true);
        pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().setEnabled(true);
        pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().setEnabled(true);
        pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().setEnabled(true);
        pantallaCrearRutaFabricacion.getBotonAgregaHerramientas().setEnabled(true);
        pantallaCrearRutaFabricacion.getBotonAgregaMaquinas().setEnabled(true);
        pantallaCrearRutaFabricacion.getBotonAgregaMateriaPrima().setEnabled(true);
        pantallaCrearRutaFabricacion.getBotonQuitaHerramientas().setEnabled(true);
        pantallaCrearRutaFabricacion.getBotonQuitaMaquinas().setEnabled(true);
        pantallaCrearRutaFabricacion.getBotonQuitaMateriaPrima().setEnabled(true);
        pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(true);
    }

    private void cargarEtapaEditable(EtapaDeRutaDeFabricacion etapaSeleccionado) {

        pantallaCrearRutaFabricacion.getCampoCodigoCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoNombreCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoDescripcionCentroEncontrado().setText("");
        pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().setText("");
        pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().setText("");

    }

    public void bloquearCampos() {
        pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().setEnabled(false);
        pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().setEnabled(false);
        pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().setEnabled(false);
        pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().setEnabled(false);
        pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().setEnabled(false);
        pantallaCrearRutaFabricacion.getCampoTpoTotalEtapaRuta().setEnabled(false);
        pantallaCrearRutaFabricacion.getBotonAgregaHerramientas().setEnabled(false);
        pantallaCrearRutaFabricacion.getBotonAgregaMaquinas().setEnabled(false);
        pantallaCrearRutaFabricacion.getBotonAgregaMateriaPrima().setEnabled(false);
        pantallaCrearRutaFabricacion.getBotonQuitaHerramientas().setEnabled(false);
        pantallaCrearRutaFabricacion.getBotonQuitaMaquinas().setEnabled(false);
        pantallaCrearRutaFabricacion.getBotonQuitaMateriaPrima().setEnabled(false);
        pantallaCrearRutaFabricacion.getBotonGuardarEtapa().setEnabled(false);
    }
}
