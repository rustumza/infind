/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Parametros;
import Fabricas.FabricaExpertos;
import expertos.ExpertoParametros;
import interfacesGraficas.PantallaParametros;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorParametros {

    PantallaParametros pantallaParametros;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoParametros expertoParametros;
    Parametros nuevoParametros = null;

    public ControladorParametros(ControladorPantallaMadre controlador) {
        controladorPantMadre = controlador;
        expertoParametros = (ExpertoParametros) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.PARAMETROS);
    }

    public void iniciar() {
        pantallaParametros = new PantallaParametros(controladorPantMadre.getPantalla(), false, this);
        pantallaParametros.setLocationRelativeTo(null);
        pantallaParametros.setVisible(true);
    }

    public void guardar() {
        nuevoParametros = new Parametros();
        nuevoParametros.setAlfa(Double.valueOf(pantallaParametros.getjComboBoxAlfa().getSelectedItem().toString()));
        nuevoParametros.setBeta(Double.valueOf(pantallaParametros.getjComboBoxBeta().getSelectedItem().toString()));
        nuevoParametros.setGama(Double.valueOf(pantallaParametros.getjComboBoxGama().getSelectedItem().toString()));
        nuevoParametros.setPeriodosAPredecir(Integer.valueOf(pantallaParametros.getComboBoxPeriodos().getSelectedItem().toString()));
        nuevoParametros.setErrorAceptable(Double.valueOf(pantallaParametros.getjComboBoxError().getSelectedItem().toString()));
        nuevoParametros.setMetodo(pantallaParametros.getjComboBoxMetodo().getSelectedItem().toString());
        expertoParametros.guardarParametros(nuevoParametros);
        JOptionPane.showMessageDialog(pantallaParametros, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
    }
}
