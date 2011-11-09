/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOArticulo;
import DTOs.DTOCentro;
import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
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
import interfacesGraficas.ModeloComboYListas.ModeloJListListaMateriaPrima;
import interfacesGraficas.ModeloComboYListas.ModeloJListaMaquinas;
import interfacesGraficas.ModeloComboYListas.ModeloJlistaHerramientas;
import interfacesGraficas.ModeloTablas.ModeloTablaEtapaRutaAgregada;
import interfacesGraficas.PantCrearRutaDeFabricacion;
import interfacesGraficas.PantallaCrearOperario;

import interfacesGraficas.PantallaMadre;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
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
    EtapaDeRutaDeFabricacion nuevaEtapa = new EtapaDeRutaDeFabricacion();
    ModeloJListListaMateriaPrima modeloListaMateriasPrimas = new ModeloJListListaMateriaPrima();
    MaestroDeRutaDeFabricacion rutaNueva = new MaestroDeRutaDeFabricacion();

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


        if (rutaNueva != null) {
            rutaNueva = new MaestroDeRutaDeFabricacion();
            
        }
        rutaNueva.setEliminado(Boolean.FALSE);
        rutaNueva.setNumero("7");
        



        expertoRutaFabricacion.persistirRutaDeFabricacion(rutaNueva);
        
        List<EtapaDeRutaDeFabricacion> etapasAGuardar = expertoRutaFabricacion.devolverEtapasAGuardar();
        
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapasAGuardar) {
          
            
            /*List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArticuloAPersistir = etapaDeRutaDeFabricacion.getDetallesArtEnEtapaFabList();
            for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArticuloAPersistir) {
                //persisto cada detalleArticulo de cada etapa
                expertoRutaFabricacion.persistirDetalleArticuloEnEtapaFabricacion(detalleDeArticuloEnEtapaDeFabricacion);
                
            }*/
            etapaDeRutaDeFabricacion.setMaestroRutaFabricacionList(rutaNueva);
            expertoRutaFabricacion.persistirEtapaRutaFabricacion(etapaDeRutaDeFabricacion);
            
            
            //expertoRutaFabricacion.persistirDetalleArticuloEnEtapaFabricacion();
        }
        
        List<EtapaDeRutaDeFabricacion> etapasGuardadas = expertoRutaFabricacion.buscarEtapas(rutaNueva);
        
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapasGuardadas) {
        
            List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArticuloAPersistir = etapaDeRutaDeFabricacion.getDetallesArtEnEtapaFabList();
            for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArticuloAPersistir) {
                //persisto cada detalleArticulo de cada etapa
                detalleDeArticuloEnEtapaDeFabricacion.setEtapaRutaFabricacion(etapaDeRutaDeFabricacion);
                expertoRutaFabricacion.persistirDetalleArticuloEnEtapaFabricacion(detalleDeArticuloEnEtapaDeFabricacion);
                
            }
        
        }
        
        //debo asignarle la ruta guardadda al maestro de estructura de producto del producto buscado
        //en pantalla al principio

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
            detalleArticulo.setUnidadDeMedida("LITROS");
            detalleArticulo.setNumero(1);
            detalleArticulo.setMaestroArticulo(matPrima);
            
            expertoRutaFabricacion.guardarDetalleArticuloEnEtapaFabricacion(detalleArticulo);
           // nuevaEtapa.addDetalle(detalleArticulo);
            listaDeDetallesArticulos.add(detalleArticulo);
            
        }


        nuevaEtapa.setDetallesArtEnEtapaFabList(listaDeDetallesArticulos);

        expertoRutaFabricacion.guardarEtapaRutaFabricacion(nuevaEtapa);

        modeloTAblaEtapaAgregada.addRow(nuevaEtapa);
        
        if (pantallaCrearRutaFabricacion.getCheckEsTipoIQE().isSelected()) {
            
            //pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setDefaultRenderer(Object.class, new RenderTablaListaEtapasRutaFabricacion());
            RenderTablaListaEtapasRutaFabricacion render = new RenderTablaListaEtapasRutaFabricacion();
            render.getTableCellRendererComponent(pantallaCrearRutaFabricacion.getTablaEtapasAgregadas(), render, true, true, 1, 1);
            pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setDefaultRenderer(Object.class, render);
            
        }else{
            //TODO: arreglar el tema de los colores de la tabla
        }
        
        
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
        pantallaCrearRutaFabricacion.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas());
        pantallaCrearRutaFabricacion.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMateriaPrima());
        List<MateriaPrima> listaMatPrim = expertoRutaFabricacion.buscarMateriasPrimas();
        pantallaCrearRutaFabricacion.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMateriaPrima(listaMatPrim));



    }

    public void buscarProducto() throws ExpertoExceptionRutaFabricacion {



        if (pantallaCrearRutaFabricacion.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
            articuloEncontradoFinal = (ProductoFinal) expertoRutaFabricacion.buscarArticulos(armarDTOArticulo(1));

            if (!articuloEncontradoFinal.getCodigo().isEmpty()) {
                pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());
                
            }

        } else if (pantallaCrearRutaFabricacion.getRadioBotonBuscaNombreProdFinal().isSelected()) {
            articuloEncontradoFinal = (ProductoFinal) expertoRutaFabricacion.buscarArticulos(armarDTOArticulo(2));

            if (!articuloEncontradoFinal.getNombre().isEmpty()) {
                pantallaCrearRutaFabricacion.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                pantallaCrearRutaFabricacion.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                pantallaCrearRutaFabricacion.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());
                
            }


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

}
