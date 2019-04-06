package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the plantacionesfincas database table.
 * 
 */
@Embeddable
public class PlantacionesFincasPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private int finca;

	private int plantacion;

	public PlantacionesFincasPK() {
	}

	public int getFinca() {
		return this.finca;
	}

	public void setFinca(int finca) {
		this.finca = finca;
	}

	public int getPlantacion() {
		return this.plantacion;
	}

	public void setPlantacion(int plantacion) {
		this.plantacion = plantacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + finca;
		result = prime * result + plantacion;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantacionesFincasPK other = (PlantacionesFincasPK) obj;
		if (finca != other.finca)
			return false;
		if (plantacion != other.plantacion)
			return false;
		return true;
	}

}