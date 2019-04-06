package com.acisa.cultivos.objetos;

import com.acisa.cultivos.modelos.Plantaciones;

public class ObjPlantaciones extends Persistencia {

	private Plantaciones plantaciones = new Plantaciones();

	public Plantaciones getPlantaciones() {
		return plantaciones;
	}

	public void setPlantaciones(Plantaciones plantaciones) {
		this.plantaciones = plantaciones;
	}

	/**
	 * Obtener plantacion por c�digo de plantacion
	 * 
	 * @param numeroPlantacion
	 * @return
	 */
	public Plantaciones leer(Integer numeroPlantacion) {
		return (Plantaciones) super.Leer(Plantaciones.class, numeroPlantacion);
	}

}
