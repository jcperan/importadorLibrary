package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the PlantillaDatosAbonado database table.
 * 
 */
@Entity
@Table(name = "PlantillaDatosAbonado")
public class PlantillaDatosAbonado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoPlantillaAbonado;
	private String nombre;
	private double volumen;
	private int tiempo;
	private double ce;
	private double ph;

	// bi-directional many-to-one association to PlantillaRegistroDatosAbonado
	@OneToMany(mappedBy = "plantillaDatosAbonado")
	private List<PlantillaRegistroDatosAbonado> plantillaRegistroDatosAbonados;

	public PlantillaDatosAbonado() {
	}

	public int getCodigoPlantillaAbonado() {
		return this.codigoPlantillaAbonado;
	}

	public void setCodigoPlantillaAbonado(int codigoPlantillaAbonado) {
		this.codigoPlantillaAbonado = codigoPlantillaAbonado;
	}

	public double getCe() {
		return this.ce;
	}

	public void setCe(double ce) {
		this.ce = ce;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPh() {
		return this.ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
	}

	public int getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public double getVolumen() {
		return this.volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public List<PlantillaRegistroDatosAbonado> getPlantillaRegistroDatosAbonados() {
		return this.plantillaRegistroDatosAbonados;
	}

	public void setPlantillaRegistroDatosAbonados(List<PlantillaRegistroDatosAbonado> plantillaRegistroDatosAbonados) {
		this.plantillaRegistroDatosAbonados = plantillaRegistroDatosAbonados;
	}

	public PlantillaRegistroDatosAbonado addPlantillaRegistroDatosAbonado(
			PlantillaRegistroDatosAbonado plantillaRegistroDatosAbonado) {
		getPlantillaRegistroDatosAbonados().add(plantillaRegistroDatosAbonado);
		plantillaRegistroDatosAbonado.setPlantillaDatosAbonado(this);

		return plantillaRegistroDatosAbonado;
	}

	public PlantillaRegistroDatosAbonado removePlantillaRegistroDatosAbonado(
			PlantillaRegistroDatosAbonado plantillaRegistroDatosAbonado) {
		getPlantillaRegistroDatosAbonados().remove(plantillaRegistroDatosAbonado);
		plantillaRegistroDatosAbonado.setPlantillaDatosAbonado(null);

		return plantillaRegistroDatosAbonado;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigoPlantillaAbonado + ";";
		resultado += this.nombre + ";";
		resultado += this.volumen + ";";
		resultado += this.tiempo + ";";
		resultado += this.ph + ";";
		resultado += this.ce + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}