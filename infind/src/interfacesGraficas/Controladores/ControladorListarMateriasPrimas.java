/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MateriaPrima;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoMateriaPrimaException;
import expertos.ExpertoMateriaPrima;
import interfacesGraficas.ModeloCombo.ModeloComboBoxMateriaPrima;
import interfacesGraficas.ModeloTablas.ModeloTablaListarMateriasPrimas;
import interfacesGraficas.PantallaListarMateriaPrima;
import interfacesGraficas.PantallaMadre;
import java.util.List;

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
    ExpertoMateriaPrima expertoMateriaPrima;

    ControladorListarMateriasPrimas(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();
        expertoMateriaPrima = (ExpertoMateriaPrima) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.MATERIASPRIMAS);
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
            materiaPrimaEncontrada = expertoMateriaPrima.buscarOperarios(armarDTOOperario(1));

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

            materiaPrimaEncontrada = expertoMateriaPrima.buscarOperarios(armarDTOOperario(2));

            modeloTablaListarMateriasPrimas.addAllRow(materiaPrimaEncontrada);

        }

    }

    public void tabla(Integer fila, Integer click) {

        materiaPrimaSeleccionada = modeloTablaListarMateriasPrimas.buscarMateriaPrima(pantallaListarMateriaPrima.getTablaMateriasPrimasEncontradas().getValueAt(fila, 1).toString());

        if (click == 2) {

            cargarOperario(materiaPrimaSeleccionada);

        }

    }

    public DTOOperario armarDTOOperario(Integer caso) {

        DTOOperario nuevoDto = new DTOOperario();

        switch (caso) {
            case 1:

                if (!pantallaListarMateriaPrima.getCampoBuscaCodigo().getText().equals("")) {
                    nuevoDto.setCodigoOperario(pantallaListarMateriaPrima.getCampoBuscaCodigo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaListarMateriaPrima.getCampoBuscaNombre().getText().equals("")) {
                    nuevoDto.setNombreOperario(pantallaListarMateriaPrima.getCampoBuscaNombre().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void guardarOperario() {
        if (materiaPrimaSeleccionada == null) {
            materiaPrimaSeleccionada = new MateriaPrima();

        }

        materiaPrimaSeleccionada.setApellido(pantallaCrearOperario.getCampoApellido().getText());
        materiaPrimaSeleccionada.setCodigoOperario(pantallaCrearOperario.getCampoCodigo().getText());
        materiaPrimaSeleccionada.setCorreoElectronico(pantallaCrearOperario.getCampoCorreo().getText());
        materiaPrimaSeleccionada.setDireccion(pantallaCrearOperario.getCampoDireccion().getText());
        materiaPrimaSeleccionada.setDni(Integer.valueOf(pantallaCrearOperario.getCampoDni().getText()));
        materiaPrimaSeleccionada.setEliminado(Boolean.FALSE);
        materiaPrimaSeleccionada.setNombre(pantallaCrearOperario.getCampoNombre().getText());
        materiaPrimaSeleccionada.setTelefono(Integer.valueOf(pantallaCrearOperario.getCampoTelefono().getText()));
        materiaPrimaSeleccionada.setTipoOperario(((ModeloComboBoxTipoOperador) pantallaCrearOperario.getComboPuesto().getModel()).getPuestoSeleccionado());

        try {
            expertoMateriaPrima.guardar(materiaPrimaSeleccionada);
            JOptionPane.showMessageDialog(pantallaCrearOperario, "Operario Guardado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarPantallaNuevoOperador();
            materiaPrimaSeleccionada = null;
            pantallaCrearOperario.dispose();

        } catch (ExpertoOperarioException ex) {
            Logger.getLogger(ControladorOperarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarOperario(MateriaPrima materiaPrima) {

        pantallaListarMateriaPrima.getCampoCodigo().setText(materiaPrima.getCodigoOperario());
        pantallaListarMateriaPrima.getCampoDescripcion().setText(materiaPrima.getApellido());
        pantallaListarMateriaPrima.getCampoLoteEstandar().setText(materiaPrima.getCorreoElectronico());
        pantallaListarMateriaPrima.getCampoUbicacion().setText(Integer.toString(materiaPrima.getDni()));
        pantallaListarMateriaPrima.getCampoPrecioBase().setText(materiaPrima.getDireccion());
        pantallaListarMateriaPrima.getCampoNombre().setText(materiaPrima.getNombre());
        pantallaListarMateriaPrima.getCampoCostoEstandar().setText(Integer.toString(materiaPrima.getTelefono()));

    }

    public void actualizarMateriaPrima() throws ExpertoMateriaPrimaException {


        if (operarioEncontrado == null) {
            return;

        }
        materiaPrimaSeleccionada = operarioEncontrado.get(0);

        materiaPrimaSeleccionada.setApellido(pantallaListarMateriaPrima.getCampoDescripcion().getText());
        materiaPrimaSeleccionada.setCodigoOperario(pantallaListarMateriaPrima.getCampoCodigo().getText());
        materiaPrimaSeleccionada.setCorreoElectronico(pantallaListarMateriaPrima.getCampoLoteEstandar().getText());
        materiaPrimaSeleccionada.setDireccion(pantallaListarMateriaPrima.getCampoPrecioBase().getText());
        materiaPrimaSeleccionada.setDni(Integer.valueOf(pantallaListarMateriaPrima.getCampoUbicacion().getText()));
        materiaPrimaSeleccionada.setEliminado(Boolean.FALSE);
        materiaPrimaSeleccionada.setNombre(pantallaListarMateriaPrima.getCampoNombre().getText());
        materiaPrimaSeleccionada.setTelefono(Integer.valueOf(pantallaListarMateriaPrima.getCampoCostoEstandar().getText()));
        materiaPrimaSeleccionada.setTipoOperario(((ModeloComboBoxTipoOperadorActualizado) pantallaListarMateriaPrima.getComboEditarCategoria().getModel()).getPuestoSeleccionado());

        try {
            expertoMateriaPrima.guardar(materiaPrimaSeleccionada);
            JOptionPane.showMessageDialog(pantallaCrearOperario, "Operario Guardado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarPantallaEditarOperador();
            materiaPrimaSeleccionada = null;
            pantallaListarMateriaPrima.dispose();

        } catch (ExpertoOperarioException ex) {
            Logger.getLogger(ControladorOperarios.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    public void eliminarOperario() {
    }

    public void limpiarPantallaNuevoOperador() {

        pantallaCrearOperario.getCampoApellido().setText("");
        pantallaCrearOperario.getCampoCodigo().setText("");
        pantallaCrearOperario.getCampoCorreo().setText("");
        pantallaCrearOperario.getCampoDireccion().setText("");
        pantallaCrearOperario.getCampoDni().setText("");
        pantallaCrearOperario.getCampoNombre().setText("");
        pantallaCrearOperario.getCampoTelefono().setText("");
        pantallaCrearOperario.getComboPuesto().setModel(modeloComboMateriaPrima);


    }

    public void limpiarPantallaEditarOperador() {

        pantallaListarMateriaPrima.getCampoDescripcion().setText("");
        pantallaListarMateriaPrima.getCampoCodigo().setText("");
        pantallaListarMateriaPrima.getCampoLoteEstandar().setText("");
        pantallaListarMateriaPrima.getCampoPrecioBase().setText("");
        pantallaListarMateriaPrima.getCampoUbicacion().setText("");
        pantallaListarMateriaPrima.getCampoNombre().setText("");
        pantallaListarMateriaPrima.getCampoCostoEstandar().setText("");
        pantallaListarMateriaPrima.getComboEditarCategoria().setModel(modeloComboTipoOperadorActualizado);
        modeloTablaListarMateriasPrimas.clear();
        pantallaListarMateriaPrima.getCampoBuscaCodigo().setText("");
        pantallaListarMateriaPrima.getCampoBuscaNombre().setText("");



    }
}
