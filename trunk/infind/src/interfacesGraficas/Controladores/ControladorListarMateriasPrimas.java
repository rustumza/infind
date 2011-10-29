/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOMateriaPrima;
import Entidades.MateriaPrima;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoMateriaPrimaException;
import expertos.ExpertoListarMateriaPrima;
import expertos.ExpertoMateriaPrima;
import interfacesGraficas.ModeloCombo.ModeloComboBoxMateriaPrima;
import interfacesGraficas.ModeloTablas.ModeloTablaListarMateriasPrimas;
import interfacesGraficas.PantallaListarMateriaPrima;
import interfacesGraficas.PantallaMadre;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorListarMateriasPrimas {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaMadre pantallaMadre;
    PantallaListarMateriaPrima pantallaListarMateriaPrima;
    ModeloTablaListarMateriasPrimas modeloTablaListarMateriasPrimas;
    MateriaPrima materiaPrimaSeleccionada;
    List<MateriaPrima> materiaPrimaEncontrada;
    ModeloComboBoxMateriaPrima modeloComboMateriaPrima;
    ExpertoListarMateriaPrima expertoListarMateriaPrima;

    ControladorListarMateriasPrimas(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();
        expertoListarMateriaPrima = (ExpertoListarMateriaPrima) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.LISTARMATERIASPRIMAS);
    }

    public void editarMateriaPrima() {
        pantallaListarMateriaPrima = new PantallaListarMateriaPrima(pantallaMadre, true, this);
        modeloTablaListarMateriasPrimas = new ModeloTablaListarMateriasPrimas();
        pantallaListarMateriaPrima.getTablaMateriasPrimasEncontradas().setModel(modeloTablaListarMateriasPrimas);
        pantallaListarMateriaPrima.getComboEditarCategoria().setModel(modeloComboMateriaPrima);

        pantallaListarMateriaPrima.setVisible(true);
        pantallaListarMateriaPrima.setLocationRelativeTo(null);

    }

    public void buscarMateriaPrima() throws ExpertoMateriaPrimaException {


        if (!modeloTablaListarMateriasPrimas.getListaElementos().isEmpty()) {
            modeloTablaListarMateriasPrimas.clear();
        }

        if (pantallaListarMateriaPrima.getRadioBotonCodigo().isSelected()) {
            materiaPrimaEncontrada = expertoListarMateriaPrima.buscarMateriaPrima(armarDTOMateriaPrima(1));

            if (!materiaPrimaEncontrada.get(0).getCodigo().isEmpty()) {
                pantallaListarMateriaPrima.getCampoCodigo().setText(materiaPrimaEncontrada.get(0).getCodigo());
                pantallaListarMateriaPrima.getCampoDescripcion().setText(materiaPrimaEncontrada.get(0).getDescripcion());
                pantallaListarMateriaPrima.getCampoLoteEstandar().setText(Integer.toString(materiaPrimaEncontrada.get(0).getTamanioLoteEstandar()));
                pantallaListarMateriaPrima.getCampoUbicacion().setText(materiaPrimaEncontrada.get(0).getUbicacionEnAlmacen());
                pantallaListarMateriaPrima.getCampoPrecioBase().setText(Double.toString(materiaPrimaEncontrada.get(0).getPrecioBase()));
                pantallaListarMateriaPrima.getCampoNombre().setText(materiaPrimaEncontrada.get(0).getNombre());
                pantallaListarMateriaPrima.getCampoCostoEstandar().setText(Double.toString(materiaPrimaEncontrada.get(0).getCostoEstandar()));
                pantallaListarMateriaPrima.getComboEditarCategoria().setSelectedItem(materiaPrimaEncontrada.get(0).getCategoria());

            }

        } else if (pantallaListarMateriaPrima.getRadioBotonNombre().isSelected()) {

            materiaPrimaEncontrada = expertoListarMateriaPrima.buscarMateriaPrima(armarDTOMateriaPrima(2));

            modeloTablaListarMateriasPrimas.addAllRow(materiaPrimaEncontrada);

        }

    }

    public void tabla(Integer fila, Integer click) {

        materiaPrimaSeleccionada = modeloTablaListarMateriasPrimas.buscarMateriaPrima(pantallaListarMateriaPrima.getTablaMateriasPrimasEncontradas().getValueAt(fila, 1).toString());

        if (click == 2) {

            cargarMateriaPrima(materiaPrimaSeleccionada);

        }

    }

    public DTOMateriaPrima armarDTOMateriaPrima(Integer caso) {

        DTOMateriaPrima nuevoDto = new DTOMateriaPrima();

        switch (caso) {
            case 1:

                if (!pantallaListarMateriaPrima.getCampoBuscaCodigo().getText().equals("")) {
                    nuevoDto.setCodigoMateriaPrima(pantallaListarMateriaPrima.getCampoBuscaCodigo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaListarMateriaPrima.getCampoBuscaNombre().getText().equals("")) {
                    nuevoDto.setNombreMateriaPrima(pantallaListarMateriaPrima.getCampoBuscaNombre().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void guardarMateriaPrima() {
        if (materiaPrimaSeleccionada == null) {
            materiaPrimaSeleccionada = new MateriaPrima();

        }

        materiaPrimaSeleccionada.setDescripcion(pantallaListarMateriaPrima.getCampoDescripcion().getText());
        materiaPrimaSeleccionada.setCodigo(pantallaListarMateriaPrima.getCampoCodigo().getText());
        materiaPrimaSeleccionada.setCostoEstandar(Integer.valueOf(pantallaListarMateriaPrima.getCampoCostoEstandar().getText()));
        materiaPrimaSeleccionada.setUbicacionEnAlmacen(pantallaListarMateriaPrima.getCampoUbicacion().getText());
        materiaPrimaSeleccionada.setPrecioBase(Integer.valueOf(pantallaListarMateriaPrima.getCampoPrecioBase().getText()));
        materiaPrimaSeleccionada.setNombre(pantallaListarMateriaPrima.getCampoNombre().getText());
        materiaPrimaSeleccionada.setEliminado(Boolean.FALSE);
        materiaPrimaSeleccionada.setTamanioLoteEstandar(Integer.valueOf(pantallaListarMateriaPrima.getCampoLoteEstandar().getText()));
        materiaPrimaSeleccionada.setCategoria(((String)pantallaListarMateriaPrima.getComboEditarCategoria().getModel().getSelectedItem()).charAt(0));

        try {
            expertoListarMateriaPrima.guardarMateriaPrima(materiaPrimaSeleccionada);
            JOptionPane.showMessageDialog(pantallaListarMateriaPrima, "Materia Prima Guardada Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarPantallaNuevaMateriaPrima();
            materiaPrimaSeleccionada = null;
            pantallaListarMateriaPrima.dispose();

        } catch (ExpertoMateriaPrimaException ex) {
            Logger.getLogger(ControladorListarMateriasPrimas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarMateriaPrima(MateriaPrima materiaPrima) {

        pantallaListarMateriaPrima.getCampoCodigo().setText(materiaPrima.getCodigo());
        pantallaListarMateriaPrima.getCampoDescripcion().setText(materiaPrima.getDescripcion());
        pantallaListarMateriaPrima.getCampoLoteEstandar().setText(Integer.toString(materiaPrima.getTamanioLoteEstandar()));
        pantallaListarMateriaPrima.getCampoUbicacion().setText(materiaPrima.getUbicacionEnAlmacen());
        pantallaListarMateriaPrima.getCampoPrecioBase().setText(Double.toString(materiaPrima.getPrecioBase()));
        pantallaListarMateriaPrima.getCampoNombre().setText(materiaPrima.getNombre());
        pantallaListarMateriaPrima.getCampoCostoEstandar().setText(Double.toString(materiaPrima.getCostoEstandar()));

    }

    public void actualizarMateriaPrima() throws ExpertoMateriaPrimaException {


        if (materiaPrimaEncontrada == null) {
            return;

        }
        materiaPrimaSeleccionada = materiaPrimaEncontrada.get(0);

        materiaPrimaSeleccionada.setDescripcion(pantallaListarMateriaPrima.getCampoDescripcion().getText());
        materiaPrimaSeleccionada.setCodigo(pantallaListarMateriaPrima.getCampoCodigo().getText());
        materiaPrimaSeleccionada.setCostoEstandar(Integer.valueOf(pantallaListarMateriaPrima.getCampoLoteEstandar().getText()));
        materiaPrimaSeleccionada.setPrecioBase(Integer.valueOf(pantallaListarMateriaPrima.getCampoPrecioBase().getText()));
        materiaPrimaSeleccionada.setUbicacionEnAlmacen(pantallaListarMateriaPrima.getCampoUbicacion().getText());
        materiaPrimaSeleccionada.setEliminado(Boolean.FALSE);
        materiaPrimaSeleccionada.setNombre(pantallaListarMateriaPrima.getCampoNombre().getText());
        materiaPrimaSeleccionada.setTamanioLoteEstandar(Integer.valueOf(pantallaListarMateriaPrima.getCampoCostoEstandar().getText()));
        materiaPrimaSeleccionada.setCategoria( ((String)pantallaListarMateriaPrima.getComboEditarCategoria().getModel().getSelectedItem()).charAt(0));

        try {
            expertoListarMateriaPrima.guardarMateriaPrima(materiaPrimaSeleccionada);
            JOptionPane.showMessageDialog(pantallaListarMateriaPrima, "Materia Prima Guardada Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarPantallaEditarMateriaPrima();
            materiaPrimaSeleccionada = null;
            pantallaListarMateriaPrima.dispose();

        } catch (ExpertoMateriaPrimaException ex) {
            Logger.getLogger(ControladorListarMateriasPrimas.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    public void eliminarMateriaPrima() {
    }

    public void limpiarPantallaNuevaMateriaPrima() {

        pantallaListarMateriaPrima.getCampoLoteEstandar().setText("");
        pantallaListarMateriaPrima.getCampoCodigo().setText("");
        pantallaListarMateriaPrima.getCampoCostoEstandar().setText("");
        pantallaListarMateriaPrima.getCampoDescripcion().setText("");
        pantallaListarMateriaPrima.getCampoPrecioBase().setText("");
        pantallaListarMateriaPrima.getCampoNombre().setText("");
        pantallaListarMateriaPrima.getCampoUbicacion().setText("");
        pantallaListarMateriaPrima.getComboEditarCategoria().setModel(modeloComboMateriaPrima);


    }

    public void limpiarPantallaEditarMateriaPrima() {

        pantallaListarMateriaPrima.getCampoDescripcion().setText("");
        pantallaListarMateriaPrima.getCampoCodigo().setText("");
        pantallaListarMateriaPrima.getCampoLoteEstandar().setText("");
        pantallaListarMateriaPrima.getCampoPrecioBase().setText("");
        pantallaListarMateriaPrima.getCampoUbicacion().setText("");
        pantallaListarMateriaPrima.getCampoNombre().setText("");
        pantallaListarMateriaPrima.getCampoCostoEstandar().setText("");
        pantallaListarMateriaPrima.getComboEditarCategoria().setModel(modeloComboMateriaPrima);
        modeloTablaListarMateriasPrimas.clear();
        pantallaListarMateriaPrima.getCampoBuscaCodigo().setText("");
        pantallaListarMateriaPrima.getCampoBuscaNombre().setText("");
    }
}
