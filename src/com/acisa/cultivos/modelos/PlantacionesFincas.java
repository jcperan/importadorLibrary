package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the plantacionesfincas database table.
 * 
 */
@Entity
@Table(name = "plantacionesfincas")
@NamedQuery(name = "Plantacionesfinca.findAll", query = "SELECT p FROM PlantacionesFincas p")
public class PlantacionesFincas implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlantacionesFincasPK id;

	public PlantacionesFincasPK getId() {
		return id;
	}

	public void setId(PlantacionesFincasPK id) {
		this.id = id;
	}

	public PlantacionesFincas() {
	}

	@ManyToOne
	@JoinColumn(name = "plantacion", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Plantaciones plantacionBean;

	public Plantaciones getPlantacionBean() {
		return plantacionBean;
	}

	public void setPlantacionBean(Plantaciones plantacionBean) {
		this.plantacionBean = plantacionBean;
	}

}