package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the PlantillaAbonosFertilizantes database table.
 * 
 */
@Entity
@Table(name = "PlantillaAbonosFertilizantes")
public class PlantillaAbonosFertilizantes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private int abonoFertilizante;

	private int codigoPlantillaRegistroAbonado;

	private double dosis;

	public PlantillaAbonosFertilizantes() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getAbonoFertilizante() {
		return this.abonoFertilizante;
	}

	public void setAbonoFertilizante(int abonoFertilizante) {
		this.abonoFertilizante = abonoFertilizante;
	}

	public int getCodigoPlantillaRegistroAbonado() {
		return this.codigoPlantillaRegistroAbonado;
	}

	public void setCodigoPlantillaRegistroAbonado(int codigoPlantillaRegistroAbonado) {
		this.codigoPlantillaRegistroAbonado = codigoPlantillaRegistroAbonado;
	}

	public double getDosis() {
		return this.dosis;
	}

	public void setDosis(double dosis) {
		this.dosis = dosis;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.codigoPlantillaRegistroAbonado + ";";
		resultado += this.abonoFertilizante + ";";
		resultado += this.dosis + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}