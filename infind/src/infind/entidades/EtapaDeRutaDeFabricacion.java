package infind.entidades;



/**
 * @author rustu
 * @version 1.0
 * @created 10-Oct-2011 05:53:20 p.m.
 */
public class EtapaDeRutaDeFabricacion {

	private int cantidadDeOperarios;
	private int nroEtapa;
	/**
	 * en minutos
	 */
	private int tiempoDeTrabajoDeMaquinas;
	/**
	 * en minutus
	 */
	private int tiempoDeTrabajoDeOperarios;
	/**
	 * en minutos
	 */
	private int tiempoDeTrabajoTotal;
	public MaestroDeCentroDeTrabajo m_MaestroDeCentroDeTrabajo;
	public detalleDeArticuloEnEtapaDeFabricacion m_detalleDeArticuloEnEtapaDeFabricacion;

	public EtapaDeRutaDeFabricacion(){

	}


}
