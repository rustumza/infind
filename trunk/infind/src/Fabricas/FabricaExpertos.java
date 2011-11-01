/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fabricas;

import expertos.Experto;
import expertos.ExpertoCentroDeTrabajo;
import expertos.ExpertoMateriaPrima;
import expertos.ExpertoOperarios;
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

        CENTRO_DE_TRABAJO, OPERARIOS, MATERIAPRIMA, RUTA_FABRICACION
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

            default:
                return null;

        }
    }
}
