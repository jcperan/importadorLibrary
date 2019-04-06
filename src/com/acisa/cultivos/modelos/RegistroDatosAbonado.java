package com.acisa.cultivos.modelos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the RegistroDatosAbonado database table.
 * 
 */
@Entity
@Table(name = "RegistroDatosAbonado")
public class RegistroDatosAbonado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoRegistroAbonado;
	private String aplicacion;
	private int codigoAbonado;
	private String equipo;
	private double porcentaje;
	private String riego;

	// bi-directional many-to-one association to Cultivosplaga
	@OneToMany(mappedBy = "registroBean")
	private Set<DatosAbonado> datosAbonado;

	public RegistroDatosAbonado() {
	}

	public int getCodigoRegistroAbonado() {
		return this.codigoRegistroAbonado;
	}

	public void setCodigoRegistroAbonado(int codigoRegistroAbonado) {
		this.codigoRegistroAbonado = codigoRegistroAbonado;
	}

	public String getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public int getCodigoAbonado() {
		return this.codigoAbonado;
	}

	public void setCodigoAbonado(int codigoAbonado) {
		this.codigoAbonado = codigoAbonado;
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

}