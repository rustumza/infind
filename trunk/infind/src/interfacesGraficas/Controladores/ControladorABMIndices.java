/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Indices;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCostosFijosException;
import expertos.ExpertoABMIndices;
import interfacesGraficas.PantallaABMIndices;
import javax.swing.JOptionPane;

/**
 *
 * @author edu
 */
public class ControladorABMIndices {

    PantallaABMIndices pantallaABMIndices;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoABMIndices expertoABMIndice;
    Indices indice;
    Indices indices;

    public ControladorABMIndices(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMIndice = (ExpertoABMIndices) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_INDICE);

    }

    void iniciar() {
        pantallaABMIndices = new PantallaABMIndices(controladorPantMadre.getPantalla(), true, this);
        limpiarPantalla();
        pantallaABMIndices.setLocationRelativeTo(null);
        pantallaABMIndices.setVisible(true);

    }

    public void guardarIndices() {

        if (indice == null) {
            indice = new Indices();

        }
        indice.setCapitalesPropios(Double.valueOf(pantallaABMIndices.getCampoCapitalesPropios().getText()));
        indice.setCarteraClientes(Double.valueOf(pantallaABMIndices.getCampoCarteraCliente().getText()));
        indice.setComposicionVentas(Double.valueOf(pantallaABMIndices.getCampoCOmposicionVentas().getText()));
        indice.setCredito(Double.valueOf(pantallaABMIndices.getCampoCredito().getText()));
        indice.setDeLasInversiones(Double.valueOf(pantallaABMIndices.getCampoInversiones().getText()));
        indice.setDeLasVentas(Double.valueOf(pantallaABMIndices.getCampoDeLasVentas().getText()));
        indice.setEficienciaVendedor(Double.valueOf(pantallaABMIndices.getCampoEficienciaVendedor().getText()));
        indice.setGradoDependencia(Double.valueOf(pantallaABMIndices.getCampoGradoDependencia().getText()));
        indice.setIndiceLiquides(Double.valueOf(pantallaABMIndices.getCampoIndiceLiquidez().getText()));
        indice.setNivelAusentismo(Double.valueOf(pantallaABMIndices.getCampoNivelAusentismo().getText()));
        indice.setNivelCredito(Double.valueOf(pantallaABMIndices.getCampoNivelCredito().getText()));
        indice.setNivelDescuento(Double.valueOf(pantallaABMIndices.getCampoNivelDescuento().getText()));
        indice.setNivelRechazos(Double.valueOf(pantallaABMIndices.getCampoNivelRechazos().getText()));
        indice.setNivelRotacion(Double.valueOf(pantallaABMIndices.getCampoNivelRotacion().getText()));
        indice.setPlanSugerencias(Double.valueOf(pantallaABMIndices.getCampoPlanSugerencia().getText()));
        indice.setProporcionCuotas(Double.valueOf(pantallaABMIndices.getCampoProporCuotoas().getText()));
        indice.setQueSeDebe(Double.valueOf(pantallaABMIndices.getCampoQueSeDebe().getText()));
        indice.setRotacionExterna(Double.valueOf(pantallaABMIndices.getCampoRotacionExterna().getText()));
        indice.setRotacionInterna(Double.valueOf(pantallaABMIndices.getCampoRotacionInterna().getText()));
        indice.setStocks(Double.valueOf(pantallaABMIndices.getCampoStocks().getText()));
        indice.setUsoHP(Double.valueOf(pantallaABMIndices.getCampoUsoHP().getText()));
        indice.setUtilidadActivoTotal(Double.valueOf(pantallaABMIndices.getCampoUtilidadActivoTotal().getText()));
        indice.setVolumenCompra(Double.valueOf(pantallaABMIndices.getCampoVolumenCompra().getText()));

        expertoABMIndice.guardarIndices(indice);
        JOptionPane.showMessageDialog(pantallaABMIndices, "Datos guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        limpiarPantalla();
        deshabilitarCampos();
      //  nuevoIndice = null;

    }

    public void crearIndices() {

        pantallaABMIndices.getCampoCOmposicionVentas().setEnabled(true);
        pantallaABMIndices.getCampoCapitalesPropios().setEnabled(true);
        pantallaABMIndices.getCampoCarteraCliente().setEnabled(true);
        pantallaABMIndices.getCampoCredito().setEnabled(true);
        pantallaABMIndices.getCampoDeLasVentas().setEnabled(true);
        pantallaABMIndices.getCampoEficienciaVendedor().setEnabled(true);
        pantallaABMIndices.getCampoGradoDependencia().setEnabled(true);
        pantallaABMIndices.getCampoIndiceLiquidez().setEnabled(true);
        pantallaABMIndices.getCampoInversiones().setEnabled(true);
        pantallaABMIndices.getCampoNivelAusentismo().setEnabled(true);
        pantallaABMIndices.getCampoNivelCredito().setEnabled(true);
        pantallaABMIndices.getCampoNivelDescuento().setEnabled(true);
        pantallaABMIndices.getCampoNivelRechazos().setEnabled(true);
        pantallaABMIndices.getCampoNivelRotacion().setEnabled(true);
        pantallaABMIndices.getCampoPlanSugerencia().setEnabled(true);
        pantallaABMIndices.getCampoProporCuotoas().setEnabled(true);
        pantallaABMIndices.getCampoQueSeDebe().setEnabled(true);
        pantallaABMIndices.getCampoRotacionExterna().setEnabled(true);
        pantallaABMIndices.getCampoRotacionInterna().setEnabled(true);
        pantallaABMIndices.getCampoStocks().setEnabled(true);
        pantallaABMIndices.getCampoUsoHP().setEnabled(true);
        pantallaABMIndices.getCampoUtilidadActivoTotal().setEnabled(true);
        pantallaABMIndices.getCampoVolumenCompra().setEnabled(true);


    }

    public void editarIndices() throws ExpertoCostosFijosException {
        indices = expertoABMIndice.buscarIndices();
        if (indices == null) {
            JOptionPane.showMessageDialog(pantallaABMIndices, "No existen datos cargados", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

        } else {
            pantallaABMIndices.getBotonGuardar().setEnabled(false);
            pantallaABMIndices.getCampoCOmposicionVentas().setText(String.valueOf(indices.getComposicionVentas()));
            pantallaABMIndices.getCampoCapitalesPropios().setText(String.valueOf(indices.getCapitalesPropios()));
            pantallaABMIndices.getCampoCarteraCliente().setText(String.valueOf(indices.getCarteraClientes()));
            pantallaABMIndices.getCampoCredito().setText(String.valueOf(indices.getCredito()));
            pantallaABMIndices.getCampoDeLasVentas().setText(String.valueOf(indices.getDeLasVentas()));
            pantallaABMIndices.getCampoEficienciaVendedor().setText(String.valueOf(indices.getEficienciaVendedor()));
            pantallaABMIndices.getCampoGradoDependencia().setText(String.valueOf(indices.getGradoDependencia()));
            pantallaABMIndices.getCampoIndiceLiquidez().setText(String.valueOf(indices.getIndiceLiquides()));
            pantallaABMIndices.getCampoInversiones().setText(String.valueOf(indices.getDeLasInversiones()));
            pantallaABMIndices.getCampoNivelAusentismo().setText(String.valueOf(indices.getNivelAusentismo()));
            pantallaABMIndices.getCampoNivelCredito().setText(String.valueOf(indices.getNivelCredito()));
            pantallaABMIndices.getCampoNivelDescuento().setText(String.valueOf(indices.getNivelDescuento()));
            pantallaABMIndices.getCampoNivelRechazos().setText(String.valueOf(indices.getNivelRechazos()));
            pantallaABMIndices.getCampoNivelRotacion().setText(String.valueOf(indices.getNivelRotacion()));
            pantallaABMIndices.getCampoPlanSugerencia().setText(String.valueOf(indices.getPlanSugerencias()));
            pantallaABMIndices.getCampoProporCuotoas().setText(String.valueOf(indices.getProporcionCuotas()));
            pantallaABMIndices.getCampoQueSeDebe().setText(String.valueOf(indices.getQueSeDebe()));
            pantallaABMIndices.getCampoRotacionExterna().setText(String.valueOf(indices.getRotacionExterna()));
            pantallaABMIndices.getCampoRotacionInterna().setText(String.valueOf(indices.getRotacionInterna()));
            pantallaABMIndices.getCampoStocks().setText(String.valueOf(indices.getStocks()));
            pantallaABMIndices.getCampoUsoHP().setText(String.valueOf(indices.getUsoHP()));
            pantallaABMIndices.getCampoUtilidadActivoTotal().setText(String.valueOf(indices.getUtilidadActivoTotal()));
            pantallaABMIndices.getCampoVolumenCompra().setText(String.valueOf(indices.getVolumenCompra()));

            pantallaABMIndices.getCampoCOmposicionVentas().setEnabled(true);
            pantallaABMIndices.getCampoCapitalesPropios().setEnabled(true);
            pantallaABMIndices.getCampoCarteraCliente().setEnabled(true);
            pantallaABMIndices.getCampoCredito().setEnabled(true);
            pantallaABMIndices.getCampoDeLasVentas().setEnabled(true);
            pantallaABMIndices.getCampoEficienciaVendedor().setEnabled(true);
            pantallaABMIndices.getCampoGradoDependencia().setEnabled(true);
            pantallaABMIndices.getCampoIndiceLiquidez().setEnabled(true);
            pantallaABMIndices.getCampoInversiones().setEnabled(true);
            pantallaABMIndices.getCampoNivelAusentismo().setEnabled(true);
            pantallaABMIndices.getCampoNivelCredito().setEnabled(true);
            pantallaABMIndices.getCampoNivelDescuento().setEnabled(true);
            pantallaABMIndices.getCampoNivelRechazos().setEnabled(true);
            pantallaABMIndices.getCampoNivelRotacion().setEnabled(true);
            pantallaABMIndices.getCampoPlanSugerencia().setEnabled(true);
            pantallaABMIndices.getCampoProporCuotoas().setEnabled(true);
            pantallaABMIndices.getCampoQueSeDebe().setEnabled(true);
            pantallaABMIndices.getCampoRotacionExterna().setEnabled(true);
            pantallaABMIndices.getCampoRotacionInterna().setEnabled(true);
            pantallaABMIndices.getCampoStocks().setEnabled(true);
            pantallaABMIndices.getCampoUsoHP().setEnabled(true);
            pantallaABMIndices.getCampoUtilidadActivoTotal().setEnabled(true);
            pantallaABMIndices.getCampoVolumenCompra().setEnabled(true);
            
            

        }
    }

    public void limpiarPantalla() {

        pantallaABMIndices.getCampoCOmposicionVentas().setText("");
        pantallaABMIndices.getCampoCapitalesPropios().setText("");
        pantallaABMIndices.getCampoCarteraCliente().setText("");
        pantallaABMIndices.getCampoCredito().setText("");
        pantallaABMIndices.getCampoDeLasVentas().setText("");
        pantallaABMIndices.getCampoEficienciaVendedor().setText("");
        pantallaABMIndices.getCampoGradoDependencia().setText("");
        pantallaABMIndices.getCampoIndiceLiquidez().setText("");
        pantallaABMIndices.getCampoInversiones().setText("");
        pantallaABMIndices.getCampoNivelAusentismo().setText("");
        pantallaABMIndices.getCampoNivelCredito().setText("");
        pantallaABMIndices.getCampoNivelDescuento().setText("");
        pantallaABMIndices.getCampoNivelRechazos().setText("");
        pantallaABMIndices.getCampoNivelRotacion().setText("");
        pantallaABMIndices.getCampoPlanSugerencia().setText("");
        pantallaABMIndices.getCampoProporCuotoas().setText("");
        pantallaABMIndices.getCampoQueSeDebe().setText("");
        pantallaABMIndices.getCampoRotacionExterna().setText("");
        pantallaABMIndices.getCampoRotacionInterna().setText("");
        pantallaABMIndices.getCampoStocks().setText("");
        pantallaABMIndices.getCampoUsoHP().setText("");
        pantallaABMIndices.getCampoUtilidadActivoTotal().setText("");
        pantallaABMIndices.getCampoVolumenCompra().setText("");

    }

    public void deshabilitarCampos() {

        pantallaABMIndices.getCampoCOmposicionVentas().setEnabled(false);
        pantallaABMIndices.getCampoCapitalesPropios().setEnabled(false);
        pantallaABMIndices.getCampoCarteraCliente().setEnabled(false);
        pantallaABMIndices.getCampoCredito().setEnabled(false);
        pantallaABMIndices.getCampoDeLasVentas().setEnabled(false);
        pantallaABMIndices.getCampoEficienciaVendedor().setEnabled(false);
        pantallaABMIndices.getCampoGradoDependencia().setEnabled(false);
        pantallaABMIndices.getCampoIndiceLiquidez().setEnabled(false);
        pantallaABMIndices.getCampoInversiones().setEnabled(false);
        pantallaABMIndices.getCampoNivelAusentismo().setEnabled(false);
        pantallaABMIndices.getCampoNivelCredito().setEnabled(false);
        pantallaABMIndices.getCampoNivelDescuento().setEnabled(false);
        pantallaABMIndices.getCampoNivelRechazos().setEnabled(false);
        pantallaABMIndices.getCampoNivelRotacion().setEnabled(false);
        pantallaABMIndices.getCampoPlanSugerencia().setEnabled(false);
        pantallaABMIndices.getCampoProporCuotoas().setEnabled(false);
        pantallaABMIndices.getCampoQueSeDebe().setEnabled(false);
        pantallaABMIndices.getCampoRotacionExterna().setEnabled(false);
        pantallaABMIndices.getCampoRotacionInterna().setEnabled(false);
        pantallaABMIndices.getCampoStocks().setEnabled(false);
        pantallaABMIndices.getCampoUsoHP().setEnabled(false);
        pantallaABMIndices.getCampoUtilidadActivoTotal().setEnabled(false);
        pantallaABMIndices.getCampoVolumenCompra().setEnabled(false);
    }

    public void guardarIndicesEditados() {
        if (indices == null) {
            indices = new Indices();

        }
        
        indices.setCapitalesPropios(Double.valueOf(pantallaABMIndices.getCampoCapitalesPropios().getText()));
        indices.setCarteraClientes(Double.valueOf(pantallaABMIndices.getCampoCarteraCliente().getText()));
        indices.setComposicionVentas(Double.valueOf(pantallaABMIndices.getCampoCOmposicionVentas().getText()));
        indices.setCredito(Double.valueOf(pantallaABMIndices.getCampoCredito().getText()));
        indices.setDeLasInversiones(Double.valueOf(pantallaABMIndices.getCampoInversiones().getText()));
        indices.setDeLasVentas(Double.valueOf(pantallaABMIndices.getCampoDeLasVentas().getText()));
        indices.setEficienciaVendedor(Double.valueOf(pantallaABMIndices.getCampoEficienciaVendedor().getText()));
        indices.setGradoDependencia(Double.valueOf(pantallaABMIndices.getCampoGradoDependencia().getText()));
        indices.setIndiceLiquides(Double.valueOf(pantallaABMIndices.getCampoIndiceLiquidez().getText()));
        indices.setNivelAusentismo(Double.valueOf(pantallaABMIndices.getCampoNivelAusentismo().getText()));
        indices.setNivelCredito(Double.valueOf(pantallaABMIndices.getCampoNivelCredito().getText()));
        indices.setNivelDescuento(Double.valueOf(pantallaABMIndices.getCampoNivelDescuento().getText()));
        indices.setNivelRechazos(Double.valueOf(pantallaABMIndices.getCampoNivelRechazos().getText()));
        indices.setNivelRotacion(Double.valueOf(pantallaABMIndices.getCampoNivelRotacion().getText()));
        indices.setPlanSugerencias(Double.valueOf(pantallaABMIndices.getCampoPlanSugerencia().getText()));
        indices.setProporcionCuotas(Double.valueOf(pantallaABMIndices.getCampoProporCuotoas().getText()));
        indices.setQueSeDebe(Double.valueOf(pantallaABMIndices.getCampoQueSeDebe().getText()));
        indices.setRotacionExterna(Double.valueOf(pantallaABMIndices.getCampoRotacionExterna().getText()));
        indices.setRotacionInterna(Double.valueOf(pantallaABMIndices.getCampoRotacionInterna().getText()));
        indices.setStocks(Double.valueOf(pantallaABMIndices.getCampoStocks().getText()));
        indices.setUsoHP(Double.valueOf(pantallaABMIndices.getCampoUsoHP().getText()));
        indices.setUtilidadActivoTotal(Double.valueOf(pantallaABMIndices.getCampoUtilidadActivoTotal().getText()));
        indices.setVolumenCompra(Double.valueOf(pantallaABMIndices.getCampoVolumenCompra().getText()));

        expertoABMIndice.guardarIndices(indices);
        JOptionPane.showMessageDialog(pantallaABMIndices, "Datos guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        limpiarPantalla();
        deshabilitarCampos();
        pantallaABMIndices.getBotonGuardar().setEnabled(true);
      //  nuevoIndice = null;

    }
}
