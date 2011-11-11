/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOArticulo;
import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.Herramientas;
import Entidades.MaestroDeArticulo;
import Entidades.Maquina;
import Entidades.MateriaPrima;
import Entidades.ProductoFinal;
import Entidades.ProductoIntermedio;
import Entidades.ProductoTipoIQE;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoExceptionRutaFabricacion;
import expertos.ExpertoEditarRutaFabricacion;
import interfacesGraficas.ModeloComboYListas.ModeloJListListaMateriaPrima;
import interfacesGraficas.ModeloComboYListas.ModeloJListaMaquinas;
import interfacesGraficas.ModeloComboYListas.ModeloJlistaHerramientas;
import interfacesGraficas.ModeloTablas.ModeloTablaEtapaRutaAgregada;
import interfacesGraficas.PantallaEditarRutaDeFabricacion;
import interfacesGraficas.PantallaMadre;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author eduardo
 */
public class ControladorEditarRutaDeFabricacion {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaMadre pantallaMadre;
    ExpertoEditarRutaFabricacion expertoEditarRuta;
    PantallaEditarRutaDeFabricacion pantallaEditarRuta;
    private ModeloTablaEtapaRutaAgregada modeloTAblaEtapaAgregada;
    ProductoFinal articuloEncontradoFinal;
    ProductoIntermedio articuloEncontradoIntermedio;
    ProductoTipoIQE articuloEncontradoIQE;
    EtapaDeRutaDeFabricacion etapaSeleccionado;
    EtapaDeRutaDeFabricacion etapaAEliminar;

    public ControladorEditarRutaDeFabricacion(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();
    }

    void editarRuta() {
        expertoEditarRuta = (ExpertoEditarRutaFabricacion) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.EDITAR_RUTA_FABRICACION);

        pantallaEditarRuta = new PantallaEditarRutaDeFabricacion(pantallaMadre, false, this);
        modeloTAblaEtapaAgregada = new ModeloTablaEtapaRutaAgregada();
        pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
        //pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setDefaultRenderer(Object.class, new RenderTablaListaEtapasRutaFabricacion());
        List<MateriaPrima> listaMatPrim = expertoEditarRuta.buscarMateriasPrimas();
        List<Herramientas> listaHerramientas = expertoEditarRuta.buscarHerramientas();
        List<Maquina> listaMaquinas = expertoEditarRuta.buscarMaquinas();

        pantallaEditarRuta.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMateriaPrima(listaMatPrim));
        pantallaEditarRuta.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMateriaPrima(new ArrayList<MateriaPrima>()));
        pantallaEditarRuta.getListaHerramientasAgregadas().setModel(new ModeloJlistaHerramientas(new ArrayList<Herramientas>()));
        pantallaEditarRuta.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(listaHerramientas));
        pantallaEditarRuta.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(listaMaquinas));
        pantallaEditarRuta.getListaMaquinasAgregadas().setModel(new ModeloJListaMaquinas(new ArrayList<Maquina>()));
        pantallaEditarRuta.setVisible(true);
        pantallaEditarRuta.setLocationRelativeTo(null);

    }
    
    
    public void tabla(Integer fila, Integer click) {

        etapaSeleccionado = modeloTAblaEtapaAgregada.buscarEtapasRutas(pantallaEditarRuta.getTablaEtapasAgregadas().getValueAt(fila, 1).toString());

        if (click == 2) {

            cargarEtapaSeleccionada(etapaSeleccionado);

        }

    }


    public void guardarRutaDeFabricacion() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void agregarMatriaPrima() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void sacarMateriaPrima() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void buscarCentro() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void agregarMaquinas() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void sacarMaquina() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void agregarHerramientas() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void sacarHerramientas() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void buscarProducto() throws ExpertoExceptionRutaFabricacion {
        Object selectedItem = pantallaEditarRuta.getComboBoxTipoProducto().getSelectedItem();

        if (selectedItem.toString() == "Producto Final") {

            if (pantallaEditarRuta.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoFinal = expertoEditarRuta.buscarProductoFinal(armarDTOArticulo(1));

                if (!articuloEncontradoFinal.getCodigo().isEmpty()) {
                    pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                    pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                    pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());
                    modeloTAblaEtapaAgregada.addAllRow(articuloEncontradoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion());
                    pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);

                    
                }

            } else if (pantallaEditarRuta.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoFinal = (ProductoFinal) expertoEditarRuta.buscarProductoFinal(armarDTOArticulo(2));

                if (!articuloEncontradoFinal.getNombre().isEmpty()) {
                    pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                    pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                    pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());

                }


            }



        } else if (selectedItem.toString() == "Producto Intermedio") {

            if (pantallaEditarRuta.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoIntermedio = expertoEditarRuta.buscarProductoIntermedio(armarDTOArticulo(1));

                if (!articuloEncontradoIntermedio.getCodigo().isEmpty()) {
                    pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoIntermedio.getCodigo());
                    pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoIntermedio.getDescripcion());
                    pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoIntermedio.getNombre());

                }

            } else if (pantallaEditarRuta.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoIntermedio = expertoEditarRuta.buscarProductoIntermedio(armarDTOArticulo(2));

                if (!articuloEncontradoIntermedio.getNombre().isEmpty()) {
                    pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoIntermedio.getCodigo());
                    pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoIntermedio.getDescripcion());
                    pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoIntermedio.getNombre());

                }


            }



        } else if (selectedItem.toString() == "Producto Tipo IQE") {

            if (pantallaEditarRuta.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoIQE = expertoEditarRuta.buscarProductoIQE(armarDTOArticulo(1));

                if (!articuloEncontradoIQE.getCodigo().isEmpty()) {
                    pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoIQE.getCodigo());
                    pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoIQE.getDescripcion());
                    pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoIQE.getNombre());

                }

            } else if (pantallaEditarRuta.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoIQE = expertoEditarRuta.buscarProductoIQE(armarDTOArticulo(2));

                if (!articuloEncontradoIQE.getNombre().isEmpty()) {
                    pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoIQE.getCodigo());
                    pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoIQE.getDescripcion());
                    pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoIQE.getNombre());

                }


            }



        }



    }

    public DTOArticulo armarDTOArticulo(Integer caso) {

        DTOArticulo nuevoDto = new DTOArticulo();

        switch (caso) {
            case 1:

                if (!pantallaEditarRuta.getCampoBuscaCodigoProdFinal().getText().equals("")) {
                    nuevoDto.setCodigoArticulo(pantallaEditarRuta.getCampoBuscaCodigoProdFinal().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaEditarRuta.getCampoBuscaNombreProdFinal().getText().equals("")) {
                    nuevoDto.setNombreArticulo(pantallaEditarRuta.getCampoBuscaNombreProdFinal().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void guardarEtapa() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    

     public void eliminarEtapa() {


        int r = pantallaEditarRuta.getTablaEtapasAgregadas().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaEditarRuta, "Debe seleccionar una Etapa a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        } else {
            Object valorEtapa = pantallaEditarRuta.getTablaEtapasAgregadas().getModel().getValueAt(r, 1);
            etapaAEliminar = expertoEditarRuta.buscarEtapasAEliminar(valorEtapa, etapaSeleccionado.getMaestroRutaFabricacionList());
            expertoEditarRuta.eliminarEtapa(etapaAEliminar);
            modeloTAblaEtapaAgregada.removeRow(r);
            modeloTAblaEtapaAgregada.fireTableDataChanged();

        }
    }

    private void cargarEtapaSeleccionada(EtapaDeRutaDeFabricacion etapaSeleccionado) {
        
        pantallaEditarRuta.getCampoCodigoCentroEncontrado().setText(etapaSeleccionado.getMaestroCentroTrabajo().getCodigo());
        pantallaEditarRuta.getCampoNombreCentroEncontrado().setText(etapaSeleccionado.getMaestroCentroTrabajo().getNombreCentro());
        pantallaEditarRuta.getCampoDescripcionCentroEncontrado().setText(etapaSeleccionado.getMaestroCentroTrabajo().getDescripcion());
        pantallaEditarRuta.getCampoNombreEtapaRuta().setText(etapaSeleccionado.getNombreEtapa());
        pantallaEditarRuta.getCampoNumeroEtapaRuta().setText(String.valueOf(etapaSeleccionado.getNroEtapa()));
        pantallaEditarRuta.getCampoTpoTotalEtapaRuta().setText(String.valueOf(etapaSeleccionado.getTiempoDeTrabajoTotal()));
        pantallaEditarRuta.getCampoTpoOperarioEtapaRuta().setText(String.valueOf(etapaSeleccionado.getTiempoDeTrabajoDeOperarios()));
        pantallaEditarRuta.getCampoTpoMaquinaEtapaRuta().setText(String.valueOf(etapaSeleccionado.getTiempoDeTrabajoDeMaquinas()));
        pantallaEditarRuta.getCampoNroOperariosEtapaRuta().setText(String.valueOf(etapaSeleccionado.getCantidadDeOperarios()));
        List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArtEnEtapaFabList = etapaSeleccionado.getDetallesArtEnEtapaFabList();
        List<MateriaPrima> listaAAgregar = null;
        
        for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArtEnEtapaFabList) {
            MateriaPrima maestroArticulo = (MateriaPrima) detalleDeArticuloEnEtapaDeFabricacion.getMaestroArticulo();
            listaAAgregar.add(maestroArticulo);
        }
        
        pantallaEditarRuta.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMateriaPrima(listaAAgregar));
        
    }
    
    
    
    
    
}
