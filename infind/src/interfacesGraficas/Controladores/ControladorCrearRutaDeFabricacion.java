/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOArticulo;
import DTOs.DTOCentro;
import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
import Entidades.DetalleEstructuraDeProducto;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.Herramientas;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.MaestroDeRutaDeFabricacion;
import Entidades.Maquina;
import Entidades.MateriaPrima;
import Entidades.Operario;
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
import interfacesGraficas.PantallaCrearOperario;

import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;
import persistencia.Conexion;
import persistencia.Fachada;
import utilidades.tablas.RenderTablaListaEtapasRutaFabricacion;

/**
 *
 * @author eduardo
 */
public class ControladorCrearRutaDeFabricacion {

    PantCrearRutaDeFabricacion pantallaCrearRutaFabricacion;
    PantallaMadre pantallaMadre;
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

    public ControladorCrearRutaDeFabricacion(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();



    }

    public void crearRutaFabricacion() {

        expertoRutaFabricacion = (ExpertoRutaDeFabricacion) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.RUTA_FABRICACION);

        pantallaCrearRutaFabricacion = new PantCrearRutaDeFabricacion(pantallaMadre, false, this);
        modeloTAblaEtapaAgregada = new ModeloTablaEtapaRutaAgregada();
        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
        //pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setDefaultRenderer(Object.class, new RenderTablaListaEtapasRutaFabricacion());
        //List<MateriaPrima> listaMatPrim = expertoRutaFabricacion.buscarMateriasPrimas();



        //pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMateriaPrima(listaDeLosProductos));
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


            /*List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArticuloAPersistir = etapaDeRutaDeFabricacion.getDetallesArtEnEtapaFabList();
            for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArticuloAPersistir) {
            //persisto cada detalleArticulo de cada etapa
            expertoRutaFabricacion.persistirDetalleArticuloEnEtapaFabricacion(detalleDeArticuloEnEtapaDeFabricacion);
            
            }*/

            //etapaDeRutaDeFabricacion.setMaestroRutaFabricacionList(rutaNueva);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            expertoRutaFabricacion.persistirEtapaRutaFabricacion(etapaDeRutaDeFabricacion);//TODO al persistir la etapa es cuando tire el error
            //List<EtapaDeRutaDeFabricacion> etapasGu = expertoRutaFabricacion.buscarEtapasSinTx(rutaNueva);
            //for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion1 : etapasGu) {
            List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArtEnEtapaFabList = etapaDeRutaDeFabricacion.getDetallesArtEnEtapaFabList();
            for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArtEnEtapaFabList) {
                detalleDeArticuloEnEtapaDeFabricacion.setEtapaRutaFabricacion(etapaDeRutaDeFabricacion);
                expertoRutaFabricacion.persistirDetalleArticuloEnEtapaFabricacion(detalleDeArticuloEnEtapaDeFabricacion);
            }
            //}


            //expertoRutaFabricacion.persistirDetalleArticuloEnEtapaFabricacion();
        }
        expertoRutaFabricacion.persistirRutaDeFabricacion(rutaNueva);
        /*List<EtapaDeRutaDeFabricacion> etapasGuardadas = expertoRutaFabricacion.buscarEtapas(rutaNueva);
        
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapasGuardadas) {
        
        List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArticuloAPersistir = etapaDeRutaDeFabricacion.getDetallesArtEnEtapaFabList();
        for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArticuloAPersistir) {
        //persisto cada detalleArticulo de cada etapa
        detalleDeArticuloEnEtapaDeFabricacion.setEtapaRutaFabricacion(etapaDeRutaDeFabricacion);
        expertoRutaFabricacion.persistirDetalleArticuloEnEtapaFabricacion(detalleDeArticuloEnEtapaDeFabricacion);
        
        }
        
        }*/

        //debo asignarle la ruta guardadda al maestro de estructura de producto del producto buscado
        //en pantalla al principio
        Conexion.getInstancia().confirmarTx();
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

//verifica si es un producto tipoIQR
        if (pantallaCrearRutaFabricacion.getComboBoxTipoProducto().equals("Producto Tipo IQE")) {

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

                List<MaestroDeArticulo> listaMatPrims = ((ModeloJListListaMaestroDeArticulo) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getMaestrosDeArticulo();

                for (int i = 0; i < (listaMatPrims.size()); i++) {

                    MaestroDeArticulo matPrima = ((ModeloJListListaMateriaPrima) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getElementAt(i);
                    materiasPrimas.add(matPrima);

                    detalleArticulo.setCantidad(2);
                    detalleArticulo.setEliminado(Boolean.FALSE);
                    detalleArticulo.setUnidadDeMedida("LITROS");
                    detalleArticulo.setNumero(1);
                    detalleArticulo.setMaestroArticulo(matPrima);
                    detalleArticulo.setEtapaRutaFabricacion(nuevaEtapa);
                    expertoRutaFabricacion.guardarDetalleArticuloEnEtapaFabricacion(detalleArticulo);
                    // nuevaEtapa.addDetalle(detalleArticulo);
                    listaDeDetallesArticulos.add(detalleArticulo);

                }

                nuevaEtapa.setDetallesArtEnEtapaFabList(listaDeDetallesArticulos);

                nuevaEtapa.addDetalle(detalleArticulo);
                expertoRutaFabricacion.guardarEtapaRutaFabricacion(nuevaEtapa);

                modeloTAblaEtapaAgregada.addRow(nuevaEtapa);

                limpiarPantallaEtapa();
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
                nuevaEtapa.addDetalle(null);
                expertoRutaFabricacion.guardarEtapaRutaFabricacion(nuevaEtapa);

                modeloTAblaEtapaAgregada.addRow(nuevaEtapa);

                limpiarPantallaEtapa();


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

                MaestroDeArticulo matPrima = ((ModeloJListListaMateriaPrima) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getElementAt(i);
                materiasPrimas.add(matPrima);

                detalleArticulo.setCantidad(2);
                detalleArticulo.setEliminado(Boolean.FALSE);
                detalleArticulo.setUnidadDeMedida("LITROS");
                detalleArticulo.setNumero(1);
                detalleArticulo.setMaestroArticulo(matPrima);
                detalleArticulo.setEtapaRutaFabricacion(nuevaEtapa);
                expertoRutaFabricacion.guardarDetalleArticuloEnEtapaFabricacion(detalleArticulo);
                // nuevaEtapa.addDetalle(detalleArticulo);
                listaDeDetallesArticulos.add(detalleArticulo);

            }

            nuevaEtapa.setDetallesArtEnEtapaFabList(listaDeDetallesArticulos);

            nuevaEtapa.addDetalle(detalleArticulo);
            expertoRutaFabricacion.guardarEtapaRutaFabricacion(nuevaEtapa);

            modeloTAblaEtapaAgregada.addRow(nuevaEtapa);

            limpiarPantallaEtapa();


        }


        /* if (!verificarDatosEtapa()) {
        } else {
        throw new ExpertoExceptionRutaFabricacion("Faltan Completar Campos");
        }*/
    }

    public void agregarMatriaPrima() {
        int seleccionado = pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().getSelectedIndex();
        MaestroDeArticulo materiaPrimaSeleccionada = ((ModeloJListListaMateriaPrima) pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().getModel()).getElementAt(seleccionado);
        List<MaestroDeArticulo> listaMatPrims = ((ModeloJListListaMaestroDeArticulo) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getMaestrosDeArticulo();

        if (!listaMatPrims.contains(materiaPrimaSeleccionada)) {
            listaMatPrims.add(materiaPrimaSeleccionada);
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
        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMateriaPrima());
        List<MateriaPrima> listaMatPrim = expertoRutaFabricacion.buscarMateriasPrimas();
        List<MaestroDeArticulo> mater = new ArrayList<MaestroDeArticulo>();
        mater.addAll(listaMatPrim);
        pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(mater));



    }

    public void buscarProducto() throws ExpertoExceptionRutaFabricacion {
        Object selectedItem = pantallaCrearRutaFabricacion.getComboBoxTipoProducto().getSelectedItem();
        List<MaestroDeArticulo> listaDeLosProductos = new ArrayList<MaestroDeArticulo>();



        if (selectedItem.toString() == "Producto Final") {

            if (pantallaCrearRutaFabricacion.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoFinal = expertoRutaFabricacion.buscarProductoFinal(armarDTOArticulo(1));




                if (!articuloEncontradoFinal.getCodigo().isEmpty()) {
                    if (articuloEncontradoFinal.getProductoTipoIQE() == null) {
                        pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                        pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                        pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());

                        List<DetalleEstructuraDeProducto> materiasPrimasProdFinal = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdFinal) {

                            MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                            listaDeLosProductos.add(materia);
                        }
                        //busco las materias del prod IQE del producto final encontrado
                        List<DetalleEstructuraDeProducto> materiasDelProductoFinal = articuloEncontradoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasDelProductoFinal) {
                            MaestroDeArticulo materiaDelProFinal = detalleEstructuraDeProducto.getMaestroArticulo();//no lo puede castear
                            listaDeLosProductos.add(materiaDelProFinal);
                        }
                        //busco las etapas del producto TIpoIQE relacionado
                        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = articuloEncontradoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                        if (etapaRutaFabricacionTipoIQE != null) {
                            for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                    modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    

                                } else {

                                    modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                }

                            }


                        }
                    }else{
                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Final: " +" '" +articuloEncontradoFinal.getNombre() + "' "+" no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                        //lanzar excepcion
                    }

                }

            } else if (pantallaCrearRutaFabricacion.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoFinal = (ProductoFinal) expertoRutaFabricacion.buscarProductoFinal(armarDTOArticulo(2));

                if (!articuloEncontradoFinal.getNombre().isEmpty()) {
                    if (articuloEncontradoFinal.getProductoTipoIQE() != null) {
                        pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                        pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                        pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());
                        List<DetalleEstructuraDeProducto> materiasPrimasProdFinal = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdFinal) {

                            MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                            listaDeLosProductos.add(materia);
                        }
                        List<DetalleEstructuraDeProducto> materiasDelProductoFinal = articuloEncontradoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasDelProductoFinal) {
                            MateriaPrima materiaDelProFinal = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                            listaDeLosProductos.add(materiaDelProFinal);
                        }

                        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = articuloEncontradoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                        if (etapaRutaFabricacionTipoIQE != null) {
                            for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                    modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    
                                } else {
                                    modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                }

                            }

                        }
                    }else{
                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Final: " + " '"+ articuloEncontradoFinal.getNombre() +" '" +" no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                        //lanzar excepcion
                    }
                }
            }
            pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));

        } else if (selectedItem.toString() == "Producto Intermedio") {

            if (pantallaCrearRutaFabricacion.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoIntermedio = expertoRutaFabricacion.buscarProductoIntermedio(armarDTOArticulo(1));

                if (!articuloEncontradoIntermedio.getCodigo().isEmpty()) {
                    if (articuloEncontradoIntermedio.getProductoTipoIQE() != null) {
                        pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoIntermedio.getCodigo());
                        pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoIntermedio.getDescripcion());
                        pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoIntermedio.getNombre());

                        List<DetalleEstructuraDeProducto> materiasPrimasProdIntermedio = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIntermedio) {
                            detalleEstructuraDeProducto.getMaestroArticulo();
                            MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                            listaDeLosProductos.add(materia);
                        }

                        List<DetalleEstructuraDeProducto> materiasDelProductoFinal = articuloEncontradoIntermedio.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasDelProductoFinal) {
                            MateriaPrima materiaDelProFinal = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                            listaDeLosProductos.add(materiaDelProFinal);
                        }


                        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = articuloEncontradoIntermedio.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                        if (etapaRutaFabricacionTipoIQE != null) {
                            for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                    modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    
                                } else {
                                    modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                }

                            }
                        }
                    }else{
                       JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Intermedio: " +" '" +articuloEncontradoIntermedio.getNombre() + "' "+" no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                        //lanzar excepcion
                    }


                }

            } else if (pantallaCrearRutaFabricacion.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoIntermedio = expertoRutaFabricacion.buscarProductoIntermedio(armarDTOArticulo(2));

                if (!articuloEncontradoIntermedio.getNombre().isEmpty()) {
                    if (articuloEncontradoIntermedio.getProductoTipoIQE() != null) {
                        pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoIntermedio.getCodigo());
                        pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoIntermedio.getDescripcion());
                        pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoIntermedio.getNombre());

                        List<DetalleEstructuraDeProducto> materiasPrimasProdIntermedio = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIntermedio) {
                            detalleEstructuraDeProducto.getMaestroArticulo();
                            MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                            listaDeLosProductos.add(materia);
                        }
                        List<DetalleEstructuraDeProducto> materiasDelProductoFinal = articuloEncontradoIntermedio.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasDelProductoFinal) {
                            MateriaPrima materiaDelProFinal = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                            listaDeLosProductos.add(materiaDelProFinal);
                        }


                        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = articuloEncontradoIntermedio.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                        if (etapaRutaFabricacionTipoIQE != null) {
                            for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                    modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    
                                } else {
                                    modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                }

                            }

                        }
                    }else{
                        JOptionPane.showMessageDialog(pantallaCrearRutaFabricacion, "El Producto Intermedio: " +" '" +articuloEncontradoIntermedio.getNombre() + "' "+" no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                        //lanzar excepcion
                    }

                }


            }

            pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));

        } else if (selectedItem.toString() == "Producto Tipo IQE") {

            if (pantallaCrearRutaFabricacion.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoIQE = expertoRutaFabricacion.buscarProductoIQE(armarDTOArticulo(1));

                if (!articuloEncontradoIQE.getCodigo().isEmpty()) {
                    pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoIQE.getCodigo());
                    pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoIQE.getDescripcion());
                    pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoIQE.getNombre());

                    List<DetalleEstructuraDeProducto> materiasPrimasProdIQE = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                    for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIQE) {
                        detalleEstructuraDeProducto.getMaestroArticulo();
                        MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                        listaDeLosProductos.add(materia);
                    }

                }

            } else if (pantallaCrearRutaFabricacion.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoIQE = expertoRutaFabricacion.buscarProductoIQE(armarDTOArticulo(2));

                if (!articuloEncontradoFinal.getNombre().isEmpty()) {
                    pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoIQE.getCodigo());
                    pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoIQE.getDescripcion());
                    pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoIQE.getNombre());

                    List<DetalleEstructuraDeProducto> materiasPrimasProdIQE = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                    for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIQE) {
                        detalleEstructuraDeProducto.getMaestroArticulo();
                        MateriaPrima materia = (MateriaPrima) detalleEstructuraDeProducto.getMaestroArticulo();
                        listaDeLosProductos.add(materia);
                    }
                }

            }

        }
        pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));
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

        etapaAMostrar = modeloTAblaEtapaAgregada.buscarEtapasRutas(pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().getValueAt(fila, 1).toString());

        if (click == 2) {

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
}
