package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the plantacionessectores database table.
 * 
 */
@Embeddable
public class PlantacionesSectoresPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private int plantacion;
	private int sector;

	public PlantacionesSectoresPK() {
	}

	public int getPlantacion() {
		return this.plantacion;
	}

	public void setPlantacion(int plantacion) {
		this.plantacion = plantacion;
	}

	public int getSector() {
		return this.sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + plantacion;
		result = prime * result + sector;
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
		PlantacionesSectoresPK other = (PlantacionesSectoresPK) obj;
		if (plantacion != other.plantacion)
			return false;
		if (sector != other.sector)
			return false;
		return true;
	}

}