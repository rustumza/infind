/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOCentro;
import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.Herramientas;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.MaestroDeRutaDeFabricacion;
import Entidades.Maquina;
import Entidades.MateriaPrima;
import Entidades.Operario;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoExceptionRutaFabricacion;
import expertos.ExpertoRutaDeFabricacion;
import interfacesGraficas.ModeloComboYListas.ModeloJListListaMateriaPrima;
import interfacesGraficas.ModeloComboYListas.ModeloJListaMaquinas;
import interfacesGraficas.ModeloComboYListas.ModeloJlistaHerramientas;
import interfacesGraficas.ModeloTablas.ModeloTablaEtapaRutaAgregada;
import interfacesGraficas.PantallaCrearOperario;
import interfacesGraficas.PantallaCrearRutaDeFabricacion;
import interfacesGraficas.PantallaMadre;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;

/**
 *
 * @author eduardo
 */
public class ControladorCrearRutaDeFabricacion {

    PantallaCrearRutaDeFabricacion pantallaCrearRutaFabricacion;
    PantallaMadre pantallaMadre;
    ExpertoRutaDeFabricacion expertoRutaFabricacion;
    ControladorPantallaMadre controladorPantallaMadre;
    ModeloTablaEtapaRutaAgregada modeloTAblaEtapaAgregada;
    MaestroDeCentroDeTrabajo centroEncontrado;
    EtapaDeRutaDeFabricacion nuevaEtapa = new EtapaDeRutaDeFabricacion();
    ModeloJListListaMateriaPrima modeloListaMateriasPrimas = new ModeloJListListaMateriaPrima();
    MaestroDeRutaDeFabricacion rutaNueva = new MaestroDeRutaDeFabricacion();

    public ControladorCrearRutaDeFabricacion(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();


    }

    public void crearRutaFabricacion() {

        expertoRutaFabricacion = (ExpertoRutaDeFabricacion) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.RUTA_FABRICACION);

        pantallaCrearRutaFabricacion = new PantallaCrearRutaDeFabricacion(pantallaMadre, false, this);
        modeloTAblaEtapaAgregada = new ModeloTablaEtapaRutaAgregada();
        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
        List<MateriaPrima> listaMatPrim = expertoRutaFabricacion.buscarMateriasPrimas();
        pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMateriaPrima(listaMatPrim));
        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMateriaPrima(new ArrayList<MateriaPrima>()));
        pantallaCrearRutaFabricacion.getListaHerramientasAgregadas().setModel(new ModeloJlistaHerramientas(new ArrayList<Herramientas>()));
        pantallaCrearRutaFabricacion.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(new ArrayList<Herramientas>()));
        pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(new ArrayList<Maquina>()));
        pantallaCrearRutaFabricacion.getListaMaquinasAgregadas().setModel(new ModeloJListaMaquinas(new ArrayList<Maquina>()));

        pantallaCrearRutaFabricacion.setVisible(true);
        pantallaCrearRutaFabricacion.setLocationRelativeTo(null);

    }

    public void guardarRutaDeFabricacion() {


        rutaNueva.setEliminado(Boolean.FALSE);

        rutaNueva.setEtapaRutaFabricacion(expertoRutaFabricacion.devolverEtapasAGuardar());

        expertoRutaFabricacion.guardarRutaDeFabricacion(rutaNueva);



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
                pantallaCrearRutaFabricacion.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(herramientas));
                pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(maquinas));

                //TODO: tengo que mostrar los nombres y no el id, no se como se hace
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

    public void guardarEtapa() {

        DetalleDeArticuloEnEtapaDeFabricacion detalleArticulo = new DetalleDeArticuloEnEtapaDeFabricacion();
        List<DetalleDeArticuloEnEtapaDeFabricacion> listaDeDetallesArticulos = new ArrayList<DetalleDeArticuloEnEtapaDeFabricacion>();
        List<MateriaPrima> materiasPrimas = new ArrayList<MateriaPrima>();
        if (nuevaEtapa != null) {
            nuevaEtapa = new EtapaDeRutaDeFabricacion();

        }
        //ver el tema de las maquinas y herramientas que usa

        nuevaEtapa.setCantidadDeOperarios(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoNroOperariosEtapaRuta().getText()));
        nuevaEtapa.setEliminado(Boolean.FALSE);
        nuevaEtapa.setMaestroCentroTrabajo(centroEncontrado);
        nuevaEtapa.setNombreEtapa(pantallaCrearRutaFabricacion.getCampoNombreEtapaRuta().getText());
        nuevaEtapa.setNroEtapa(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoNumeroEtapaRuta().getText()));
        nuevaEtapa.setTiempoDeTrabajoDeMaquinas(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().getText()));
        nuevaEtapa.setTiempoDeTrabajoDeOperarios(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().getText()));
        nuevaEtapa.setTiempoDeTrabajoTotal(Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoMaquinaEtapaRuta().getText()) + Integer.parseInt(pantallaCrearRutaFabricacion.getCampoTpoOperarioEtapaRuta().getText()));

        List<MateriaPrima> listaMatPrims = ((ModeloJListListaMateriaPrima) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getMateriasPrimas();

        for (int i = 0; i < (listaMatPrims.size()); i++) {

            MateriaPrima matPrima = ((ModeloJListListaMateriaPrima) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getElementAt(i);
            materiasPrimas.add(matPrima);

            detalleArticulo.setCantidad(2);
            detalleArticulo.setEliminado(Boolean.FALSE);
            detalleArticulo.setUnidadDeMedida("cm");
            detalleArticulo.setMaestroArticulo(matPrima);
            listaDeDetallesArticulos.add(detalleArticulo);

        }


        nuevaEtapa.setDetallesArtEnEtapaFabList(listaDeDetallesArticulos);

        expertoRutaFabricacion.guardarEtapaRutaFabricacion(nuevaEtapa);

        modeloTAblaEtapaAgregada.addRow(nuevaEtapa);
        limpiarPantallaEtapa();

    }

    public void agregarMatriaPrima() {
        int seleccionado = pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().getSelectedIndex();
        MateriaPrima materiaPrimaSeleccionada = ((ModeloJListListaMateriaPrima) pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().getModel()).getElementAt(seleccionado);
        List<MateriaPrima> listaMatPrims = ((ModeloJListListaMateriaPrima) pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().getModel()).getMateriasPrimas();

        if (!listaMatPrims.contains(materiaPrimaSeleccionada)) {
            listaMatPrims.add(materiaPrimaSeleccionada);
        }



        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMateriaPrima(listaMatPrims));

    }

    public void sacarMateriaPrima() {
        //TODO: hacer metodo
        System.out.println("sacar materia prima");
        System.out.println("metodo no implementado!!");

    }
    
    
    public void limpiarPantallaEtapa(){
        
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
        pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas());
        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMateriaPrima());
        List<MateriaPrima> listaMatPrim = expertoRutaFabricacion.buscarMateriasPrimas();
        pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMateriaPrima(listaMatPrim));
        
        
        
    }
}
