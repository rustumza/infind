/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.Controladores;

import DTOs.DTOCentro;
import Entidades.Herramientas;
import Entidades.MaestroDeCentroDeTrabajo;
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

    public ControladorCrearRutaDeFabricacion(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre= controladorPantallaMadre.getPantalla();


    }


    public void crearRutaFabricacion(){

        expertoRutaFabricacion  = (ExpertoRutaDeFabricacion) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.RUTA_FABRICACION);

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

    public void guardarRutaDeFabricacion(){

    }

    public void buscarCentro() throws ExpertoExceptionRutaFabricacion{



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
                // List<Herramientas> herramientas = expertoCentroDeTrabajo.buscarHerramientaAgregadas(centroEncontrado.getCodigo());
                //List<Maquina> maquinas = expertoCentroDeTrabajo.buscarMaquinaAgregadas(centroEncontrado.getCodigo());
                //List<Operario> operarios = expertoCentroDeTrabajo.buscarOperariosAgregadas(centroEncontrado.getCodigo());
                
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



}
