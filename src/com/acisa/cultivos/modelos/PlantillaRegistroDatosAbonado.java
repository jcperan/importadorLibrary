package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the PlantillaRegistroDatosAbonado database table.
 * 
 */
@Entity
@Table(name = "PlantillaRegistroDatosAbonado")
public class PlantillaRegistroDatosAbonado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoPlantillaRegistroAbonado;
	private String aplicacion;
	private String equipo;
	private String riego;
	private double porcentaje;

	// bi-directional many-to-one association to PlantillaDatosAbonado
	@ManyToOne
	@JoinColumn(name = "CodigoPlantillaAbonado")
	private PlantillaDatosAbonado plantillaDatosAbonado;

	public PlantillaRegistroDatosAbonado() {
	}

	public int getCodigoPlantillaRegistroAbonado() {
		return this.codigoPlantillaRegistroAbonado;
	}

	public void setCodigoPlantillaRegistroAbonado(int codigoPlantillaRegistroAbonado) {
		this.codigoPlantillaRegistroAbonado = codigoPlantillaRegistroAbonado;
	}

	public String getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getEquipo() {
		return this.equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public double getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getRiego() {
		return this.riego;
	}

	public void setRiego(String riego) {
		this.riego = riego;
	}

	public PlantillaDatosAbonado getPlantillaDatosAbonado() {
		return this.plantillaDatosAbonado;
	}

	public void setPlantillaDatosAbonado(PlantillaDatosAbonado plantillaDatosAbonado) {
		this.plantillaDatosAbonado = plantillaDatosAbonado;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigoPlantillaRegistroAbonado + ";";
		resultado += this.plantillaDatosAbonado.getCodigoPlantillaAbonado() + ";";
		resultado += this.aplicacion + ";";
		resultado += this.equipo + ";";
		resultado += this.riego + ";";
		resultado += this.porcentaje + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}