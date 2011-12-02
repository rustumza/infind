/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fabricas;

import expertos.Experto;
import expertos.ExpertoABMCostosFijos;
import expertos.ExpertoABMCostosVariables;
import expertos.ExpertoCentroDeTrabajo;
import expertos.ExpertoEditarRutaFabricacion;
import expertos.ExpertoMateriaPrima;
import expertos.ExpertoOperarios;
import expertos.ExpertoPuntoEquillibrio;
import expertos.ExpertoRutaDeFabricacion;

/**
 *
 * @author diego
 */
public class FabricaExpertos {

    private static FabricaExpertos instancia;

    public static FabricaExpertos getInstancia() {
        if (instancia == null) {
            instancia = new FabricaExpertos();
        }
        return instancia;
    }

    public enum expertos {

        CENTRO_DE_TRABAJO, OPERARIOS, MATERIAPRIMA, RUTA_FABRICACION, EDITAR_RUTA_FABRICACION, ABM_COSTOS_FIJOS,
        ABM_COSTOS_VARIABLES, PUNTO_EQUILIBRIO
    }

    public Experto getExperto(expertos expertoNombre) {
        switch (expertoNombre) {
            case CENTRO_DE_TRABAJO:
                return new ExpertoCentroDeTrabajo();
            case OPERARIOS:
                return new ExpertoOperarios();
            case MATERIAPRIMA:
                return new ExpertoMateriaPrima();
            case RUTA_FABRICACION:
                return new ExpertoRutaDeFabricacion();
            case EDITAR_RUTA_FABRICACION:
                return new ExpertoEditarRutaFabricacion();
            case ABM_COSTOS_FIJOS:
                return new ExpertoABMCostosFijos();
            case ABM_COSTOS_VARIABLES:
                return new ExpertoABMCostosVariables();
            case PUNTO_EQUILIBRIO:
                return new ExpertoPuntoEquillibrio();

                
            default:
                return null;

        }
    }
}
