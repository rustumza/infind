/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOArticulo;
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
import expertos.ExpertoEditarRutaFabricacion;
import interfacesGraficas.ModeloComboYListas.ModeloJListListaMaestroDeArticulo;
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
    MaestroDeCentroDeTrabajo centroEncontrado;
    EtapaDeRutaDeFabricacion etapasTabla = new EtapaDeRutaDeFabricacion();

    public ControladorEditarRutaDeFabricacion(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();
    }

    void editarRuta() {
        expertoEditarRuta = (ExpertoEditarRutaFabricacion) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.EDITAR_RUTA_FABRICACION);

        pantallaEditarRuta = new PantallaEditarRutaDeFabricacion(pantallaMadre, false, this);
        modeloTAblaEtapaAgregada = new ModeloTablaEtapaRutaAgregada();
        pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
        pantallaEditarRuta.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo());
        pantallaEditarRuta.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMaestroDeArticulo(new ArrayList<MaestroDeArticulo>()));
        pantallaEditarRuta.getListaHerramientasAgregadas().setModel(new ModeloJlistaHerramientas(new ArrayList<Herramientas>()));
        pantallaEditarRuta.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(new ArrayList<Herramientas>()));
        pantallaEditarRuta.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(new ArrayList<Maquina>()));
        pantallaEditarRuta.getListaMaquinasAgregadas().setModel(new ModeloJListaMaquinas(new ArrayList<Maquina>()));
        pantallaEditarRuta.setVisible(true);
        pantallaEditarRuta.setLocationRelativeTo(null);

    }

    public void tabla(Integer fila, Integer click) {


        etapaSeleccionado = modeloTAblaEtapaAgregada.buscarEtapasRutas(pantallaEditarRuta.getTablaEtapasAgregadas().getValueAt(fila, 1).toString());
        Object selectedItem = pantallaEditarRuta.getComboBoxTipoProducto().getSelectedItem();
        if (selectedItem.toString() == "Producto Final") {

            if (etapaSeleccionado.isTipoIQE()) {
                bloquearCampos();
                cargarEtapaNoEditable(etapaSeleccionado);
            } else {
                desbloquearCampos();
                cargarEtapaNoEditable(etapaSeleccionado);
            }


        } else if (selectedItem.toString() == "Producto Intermedio") {
            if (etapaSeleccionado.isTipoIQE()) {
                bloquearCampos();
                cargarEtapaNoEditable(etapaSeleccionado);
            } else {
                desbloquearCampos();
                cargarEtapaNoEditable(etapaSeleccionado);
            }
            
        } else {// si es tipo iqe,
            
            if (etapaSeleccionado.isTipoIQE()) {
                desbloquearCampos();
                cargarEtapaNoEditable(etapaSeleccionado);
            } else {
                //desbloquearCampos();
                //cargarEtapaEditable(etapaSeleccionado);
            }
            
        }
        //si es prod final o intermedio, y selecciono las etapas del tipo iqe, las tiene qe mosstrar con los compos deshabilitados , sino habilitaods
        //si es producto tipoiqe las tiene que mostrar habilitados


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


        if (pantallaEditarRuta.getRadioBotonBuscaCodigoCentro().isSelected()) {
            //centroEncontrado = expertoEditarRuta.buscarCentros(armarDTOCentro(1));

            if (!centroEncontrado.getCodigo().isEmpty()) {
                pantallaEditarRuta.getCampoCodigoCentroEncontrado().setText(centroEncontrado.getCodigo());
                pantallaEditarRuta.getCampoDescripcionCentroEncontrado().setText(centroEncontrado.getDescripcion());
                pantallaEditarRuta.getCampoNombreCentroEncontrado().setText(centroEncontrado.getNombreCentro());
                List<Herramientas> herramientas = centroEncontrado.getHerramientas();
                List<Maquina> maquinas = centroEncontrado.getMaquinas();
                List<Operario> operario = centroEncontrado.getOperario();
                List<Herramientas> listaHerramientas = new ArrayList<Herramientas>();
                for (Herramientas listaherramientas : herramientas) {
                    listaHerramientas.add(listaherramientas);
                }
                pantallaEditarRuta.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(listaHerramientas));
                pantallaEditarRuta.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(maquinas));




            }

        } else if (pantallaEditarRuta.getRadioBotonBuscaNombreCentro().isSelected()) {
            //centroEncontrado = expertoRutaFabricacion.buscarCentros(armarDTOCentro(2));

            if (!centroEncontrado.getNombreCentro().isEmpty()) {
                pantallaEditarRuta.getCampoCodigoCentroEncontrado().setText(centroEncontrado.getCodigo());
                pantallaEditarRuta.getCampoDescripcionCentroEncontrado().setText(centroEncontrado.getDescripcion());
                pantallaEditarRuta.getCampoNombreCentroEncontrado().setText(centroEncontrado.getNombreCentro());
                List<Herramientas> herramientas = centroEncontrado.getHerramientas();
                List<Maquina> maquinas = centroEncontrado.getMaquinas();
                List<Operario> operario = centroEncontrado.getOperario();
                pantallaEditarRuta.getListaHerramientasCargadas().setModel(new ModeloJlistaHerramientas(herramientas));
                pantallaEditarRuta.getListaMaquiansCargadas().setModel(new ModeloJListaMaquinas(maquinas));

            }


        }


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
        List<MaestroDeArticulo> listaDeLosProductos = new ArrayList<MaestroDeArticulo>();



        if (selectedItem.toString() == "Producto Final") {

            if (pantallaEditarRuta.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoFinal = expertoEditarRuta.buscarProductoFinal(armarDTOArticulo(1));
                if (!articuloEncontradoFinal.getCodigo().isEmpty()) {
                    if (articuloEncontradoFinal.getProductoTipoIQE() != null) {
                        MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                        if (maestroRutaFabricacion == null) {

                            JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + " '" + " NO tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                        } else {

                            //son las etapas de la ruta de fabricacion del producto final
                            List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionFinal = maestroRutaFabricacion.getEtapaRutaFabricacion();

                            for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionFinal) {

                                modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada   



                            }

                            pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);

                            pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                            pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                            pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());
                            pantallaEditarRuta.getBotonGuardarEtapaEditada().setEnabled(true);
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
                            List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = articuloEncontradoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                            if (etapaRutaFabricacionTipoIQE != null) {
                                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                    if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                        modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    
                                        pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);

                                    } /*else {
                                    
                                    modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                    pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    }*/

                                }


                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + "' " + " no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                        //lanzar excepcion
                    }

                }

            } else if (pantallaEditarRuta.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoFinal = (ProductoFinal) expertoEditarRuta.buscarProductoFinal(armarDTOArticulo(2));

                if (!articuloEncontradoFinal.getNombre().isEmpty()) {
                    if (articuloEncontradoFinal.getProductoTipoIQE() != null) {
                        MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                        if (maestroRutaFabricacion == null) {

                            JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + " '" + " NO tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                        } else {

                            pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoFinal.getCodigo());
                            pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoFinal.getDescripcion());
                            pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoFinal.getNombre());
                            pantallaEditarRuta.getBotonGuardarEtapaEditada().setEnabled(true);
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
                            List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = articuloEncontradoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                            if (etapaRutaFabricacionTipoIQE != null) {
                                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                    if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                        modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    
                                        pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    } /*else {
                                    modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                    pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    }*/

                                }

                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Final: " + " '" + articuloEncontradoFinal.getNombre() + " '" + " no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                        //lanzar excepcion
                    }
                }
            }
            pantallaEditarRuta.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));

        } else if (selectedItem.toString() == "Producto Intermedio") {

            if (pantallaEditarRuta.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoIntermedio = expertoEditarRuta.buscarProductoIntermedio(armarDTOArticulo(1));

                if (!articuloEncontradoIntermedio.getCodigo().isEmpty()) {
                    if (articuloEncontradoIntermedio.getProductoTipoIQE() != null) {
                        MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                        if (maestroRutaFabricacion == null) {

                            JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Intermedio: " + " '" + articuloEncontradoIntermedio.getNombre() + " '" + " NO tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                        } else {

                            pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoIntermedio.getCodigo());
                            pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoIntermedio.getDescripcion());
                            pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoIntermedio.getNombre());
                            pantallaEditarRuta.getBotonGuardarEtapaEditada().setEnabled(true);
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


                            List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = articuloEncontradoIntermedio.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                            if (etapaRutaFabricacionTipoIQE != null) {
                                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                    if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                        modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    
                                        pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    }/* else {
                                    modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                    pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    }*/

                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Intermedio: " + " '" + articuloEncontradoIntermedio.getNombre() + "' " + " no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                    }


                }

            } else if (pantallaEditarRuta.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoIntermedio = expertoEditarRuta.buscarProductoIntermedio(armarDTOArticulo(2));

                if (!articuloEncontradoIntermedio.getNombre().isEmpty()) {
                    if (articuloEncontradoIntermedio.getProductoTipoIQE() != null) {
                        MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoIntermedio.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                        if (maestroRutaFabricacion == null) {

                            JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Intermedio: " + " '" + articuloEncontradoIntermedio.getNombre() + " '" + " NO tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                        } else {

                            pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoIntermedio.getCodigo());
                            pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoIntermedio.getDescripcion());
                            pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoIntermedio.getNombre());
                            pantallaEditarRuta.getBotonGuardarEtapaEditada().setEnabled(true);
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

                            List<EtapaDeRutaDeFabricacion> etapaRutaFabricacionTipoIQE = articuloEncontradoIntermedio.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                            if (etapaRutaFabricacionTipoIQE != null) {
                                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacionTipoIQE) {
                                    if (etapaDeRutaDeFabricacion.isTipoIQE()) {
                                        modeloTAblaEtapaAgregada.addRow(etapaDeRutaDeFabricacion);//tengo que poner la fila deshabilitada    
                                        pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    } /*else {
                                    modeloTAblaEtapaAgregada.addAllRow(etapaRutaFabricacionTipoIQE);
                                    pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
                                    }*/

                                }

                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Intermedio: " + " '" + articuloEncontradoIntermedio.getNombre() + "' " + " no tiene un Producto TipoIQE asociado", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                    }

                }


            }

            pantallaEditarRuta.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));

        } else if (selectedItem.toString() == "Producto Tipo IQE") {

            if (pantallaEditarRuta.getRadioBotonBuscaCodigoProdFinal().isSelected()) {
                articuloEncontradoIQE = expertoEditarRuta.buscarProductoIQE(armarDTOArticulo(1));

                if (!articuloEncontradoIQE.getCodigo().isEmpty()) {
                    MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                    if (maestroRutaFabricacion == null) {

                        JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Tipo IQE: " + " '" + articuloEncontradoIQE.getNombre() + " '" + " NO tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = maestroRutaFabricacion.getEtapaRutaFabricacion();

                        for (int i = 0; i < etapaRutaFabricacion.size(); i++) {

                            etapasTabla = etapaRutaFabricacion.get(i);
                            modeloTAblaEtapaAgregada.addRow(etapasTabla);

                        }
                        pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);

                        pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoIQE.getCodigo());
                        pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoIQE.getDescripcion());
                        pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoIQE.getNombre());
                        pantallaEditarRuta.getBotonGuardarEtapaEditada().setEnabled(true);


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
            } else if (pantallaEditarRuta.getRadioBotonBuscaNombreProdFinal().isSelected()) {
                articuloEncontradoIQE = expertoEditarRuta.buscarProductoIQE(armarDTOArticulo(2));

                if (!articuloEncontradoFinal.getNombre().isEmpty()) {
                    MaestroDeRutaDeFabricacion maestroRutaFabricacion = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion();
                    if (maestroRutaFabricacion == null) {

                        JOptionPane.showMessageDialog(pantallaEditarRuta, "El Producto Tipo IQE: " + " '" + articuloEncontradoIQE.getNombre() + " '" + " NO tiene una Ruta de Fabricación", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        if (etapasTabla == null) {
                            etapasTabla = new EtapaDeRutaDeFabricacion();
                        }
                        etapasTabla = (EtapaDeRutaDeFabricacion) maestroRutaFabricacion.getEtapaRutaFabricacion();

                        modeloTAblaEtapaAgregada.addRow(etapasTabla);//tengo que poner la fila deshabilitada    
                        pantallaEditarRuta.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);

                        pantallaEditarRuta.getCampoCodigoProdFinal().setText(articuloEncontradoIQE.getCodigo());
                        pantallaEditarRuta.getCampoDescripProdFInal().setText(articuloEncontradoIQE.getDescripcion());
                        pantallaEditarRuta.getCampoNombreProdFinal().setText(articuloEncontradoIQE.getNombre());
                        pantallaEditarRuta.getBotonGuardarEtapaEditada().setEnabled(true);
                        List< DetalleEstructuraDeProducto> materiasPrimasProdIQE = articuloEncontradoIQE.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();
                        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : materiasPrimasProdIQE) {

                            //si es materia prima
                            if (detalleEstructuraDeProducto.getTipo().equals("Materia Prima")) {
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

            pantallaEditarRuta.getListaMateriasPrimasCargadas().setModel(new ModeloJListListaMaestroDeArticulo(listaDeLosProductos));
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

    private void cargarEtapaNoEditable(EtapaDeRutaDeFabricacion etapaSeleccionado) {

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
        List<MaestroDeArticulo> listaAAgregar = new ArrayList<MaestroDeArticulo>();

        for (DetalleDeArticuloEnEtapaDeFabricacion detalleDeArticuloEnEtapaDeFabricacion : detallesArtEnEtapaFabList) {
            MaestroDeArticulo maestroArticulo = (MaestroDeArticulo) detalleDeArticuloEnEtapaDeFabricacion.getMaestroArticulo();
            listaAAgregar.add(maestroArticulo);
        }

        pantallaEditarRuta.getListaMateriasPrimasAgregadas().setModel(new ModeloJListListaMaestroDeArticulo(listaAAgregar));

    }

    public void desbloquearCampos() {
        pantallaEditarRuta.getCampoNumeroEtapaRuta().setEnabled(true);
        pantallaEditarRuta.getCampoNombreEtapaRuta().setEnabled(true);
        pantallaEditarRuta.getCampoNroOperariosEtapaRuta().setEnabled(true);
        pantallaEditarRuta.getCampoTpoMaquinaEtapaRuta().setEnabled(true);
        pantallaEditarRuta.getCampoTpoOperarioEtapaRuta().setEnabled(true);
        pantallaEditarRuta.getCampoTpoTotalEtapaRuta().setEnabled(true);
        pantallaEditarRuta.getBotonAgregaHerramientas().setEnabled(true);
        pantallaEditarRuta.getBotonAgregaMaquinas().setEnabled(true);
        pantallaEditarRuta.getBotonAgregaMateriaPrima().setEnabled(true);
        pantallaEditarRuta.getBotonQuitaHerramientas().setEnabled(true);
        pantallaEditarRuta.getBotonQuitaMaquinas().setEnabled(true);
        pantallaEditarRuta.getBotonQuitaMateriaPrima().setEnabled(true);
        pantallaEditarRuta.getBotonGuardarEtapaEditada().setEnabled(true);
    }

    private void cargarEtapaEditable(EtapaDeRutaDeFabricacion etapaSeleccionado) {

        pantallaEditarRuta.getCampoCodigoCentroEncontrado().setText("");
        pantallaEditarRuta.getCampoNombreCentroEncontrado().setText("");
        pantallaEditarRuta.getCampoDescripcionCentroEncontrado().setText("");
        pantallaEditarRuta.getCampoNombreEtapaRuta().setText("");
        pantallaEditarRuta.getCampoNumeroEtapaRuta().setText("");
        pantallaEditarRuta.getCampoTpoTotalEtapaRuta().setText("");
        pantallaEditarRuta.getCampoTpoOperarioEtapaRuta().setText("");
        pantallaEditarRuta.getCampoTpoMaquinaEtapaRuta().setText("");
        pantallaEditarRuta.getCampoNroOperariosEtapaRuta().setText("");

    }

    public void bloquearCampos() {
        pantallaEditarRuta.getCampoNumeroEtapaRuta().setEnabled(false);
        pantallaEditarRuta.getCampoNombreEtapaRuta().setEnabled(false);
        pantallaEditarRuta.getCampoNroOperariosEtapaRuta().setEnabled(false);
        pantallaEditarRuta.getCampoTpoMaquinaEtapaRuta().setEnabled(false);
        pantallaEditarRuta.getCampoTpoOperarioEtapaRuta().setEnabled(false);
        pantallaEditarRuta.getCampoTpoTotalEtapaRuta().setEnabled(false);
        pantallaEditarRuta.getBotonAgregaHerramientas().setEnabled(false);
        pantallaEditarRuta.getBotonAgregaMaquinas().setEnabled(false);
        pantallaEditarRuta.getBotonAgregaMateriaPrima().setEnabled(false);
        pantallaEditarRuta.getBotonQuitaHerramientas().setEnabled(false);
        pantallaEditarRuta.getBotonQuitaMaquinas().setEnabled(false);
        pantallaEditarRuta.getBotonQuitaMateriaPrima().setEnabled(false);
        pantallaEditarRuta.getBotonGuardarEtapaEditada().setEnabled(false);
    }
}
