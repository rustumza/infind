/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.CostoVariable;
import Entidades.MaestroDeArticulo;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoPuntoEquilibrioException;
import expertos.ExpertoPuntoEquillibrio;
import interfacesGraficas.ModeloTablas.ModeloTablaPuntoEquilibrio;
import interfacesGraficas.PantallaPuntoDeEquilibrio;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class ControladorPuntoEquilibrio {

    ControladorPantallaMadre controlador;
    PantallaPuntoDeEquilibrio pantallaPuntoEquil;
    ModeloTablaPuntoEquilibrio modeloTablaPuntoEqui;
    ExpertoPuntoEquillibrio expertoPuntoEquilibrio;
    MaestroDeArticulo productoEstandar;
    private List<MaestroDeArticulo> productoFinal;
    private NumberFormat numberFormat = NumberFormat.getNumberInstance();

    public ControladorPuntoEquilibrio(ControladorPantallaMadre control) {

        controlador = control;
        expertoPuntoEquilibrio = (ExpertoPuntoEquillibrio) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.PUNTO_EQUILIBRIO);
        numberFormat.setMaximumFractionDigits(2);
    }

    void iniciar() throws ExpertoPuntoEquilibrioException {
        pantallaPuntoEquil = new PantallaPuntoDeEquilibrio(controlador.getPantalla(), true, this);
        modeloTablaPuntoEqui = new ModeloTablaPuntoEquilibrio();
        pantallaPuntoEquil.getTablaPuntoEquilibrio().setModel(modeloTablaPuntoEqui);

////////////////////////////////// cargo los datos de los costos estandares y relaciones//////////////////////////////

        productoEstandar = expertoPuntoEquilibrio.buscarProductoEstandar();

        List<CostoVariable> costosVariablesProdEstandar = expertoPuntoEquilibrio.buscarCostosVariablesProdEstandar(productoEstandar);
        double costoTotalProdEstandar = 0.0;
        if (costosVariablesProdEstandar != null) {

            for (CostoVariable costoVariable : costosVariablesProdEstandar) {
                //sumo los costos
                costoTotalProdEstandar = costoTotalProdEstandar + costoVariable.getCosto();
                
                


            }

            pantallaPuntoEquil.getCampoCostoEstandar().setText(String.valueOf(numberFormat.format(costoTotalProdEstandar)));//seteo el valor del costo total calculado mas arriba
            pantallaPuntoEquil.getCostoEstandarDetergente().setText(String.valueOf(numberFormat.format(costoTotalProdEstandar)));
            pantallaPuntoEquil.getCostoEstandarEnjuague().setText(String.valueOf(numberFormat.format(costoTotalProdEstandar)));

        } else {
            JOptionPane.showMessageDialog(pantallaPuntoEquil, "No se han ingresado Costos Variables", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        }

        //busco los costos de todos los producto comparados con el estandar

        double costoTotalProdDesodorante = 0.0;
        double costoTotalProdEnjuague = 0.0;

        productoFinal = expertoPuntoEquilibrio.buscarProductoFinal();

        for (MaestroDeArticulo maestroDeArticulo : productoFinal) {

            if (maestroDeArticulo.getNombre().equals("Desodorante para pisos lavanda")) {
                List<CostoVariable> costosVariablesEncontrados = expertoPuntoEquilibrio.buscarCostosVariables(maestroDeArticulo);
                if (costosVariablesEncontrados != null) {
                    for (CostoVariable costoVariable : costosVariablesEncontrados) {
                        costoTotalProdDesodorante = costoTotalProdDesodorante + costoVariable.getCosto();
                    }

                    pantallaPuntoEquil.getCampoCostoDetergente().setText(String.valueOf(numberFormat.format(costoTotalProdDesodorante)));

                } else {
                    JOptionPane.showMessageDialog(pantallaPuntoEquil, "No se han ingresado Costos Variables", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (maestroDeArticulo.getNombre().equals("Enjuage para ropa imitacion vivere")) {
                List<CostoVariable> costosVariablesEncontrados = expertoPuntoEquilibrio.buscarCostosVariables(maestroDeArticulo);
                if (costosVariablesEncontrados != null) {
                    for (CostoVariable costoVariable : costosVariablesEncontrados) {

                        costoTotalProdEnjuague = costoTotalProdEnjuague + costoVariable.getCosto();

                    }
                    pantallaPuntoEquil.getCampoCostoEnjuague().setText(String.valueOf(numberFormat.format(costoTotalProdEnjuague)));

                } else {
                    JOptionPane.showMessageDialog(pantallaPuntoEquil, "No se han ingresado Costos Variables", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        double relacionDesodorante = costoTotalProdDesodorante / costoTotalProdEstandar;
        double relacionEnjuague = costoTotalProdEnjuague / costoTotalProdEstandar;
        pantallaPuntoEquil.getCampoRelacionDetergente().setText(String.valueOf(numberFormat.format(relacionDesodorante)));
        pantallaPuntoEquil.getCampoRelacionEnjuague().setText(String.valueOf(numberFormat.format(relacionEnjuague)));

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pantallaPuntoEquil.setLocationRelativeTo(null);
        pantallaPuntoEquil.setVisible(true);

    }

    public void buscarProducto() throws ExpertoPuntoEquilibrioException {

        //busco los historiales de demandas y costos del producto seleccinado por pantalla
        Object productoSeleccionado = pantallaPuntoEquil.getComboBoxProducto().getSelectedItem();
    }
}
