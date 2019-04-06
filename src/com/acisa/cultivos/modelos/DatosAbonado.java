package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

import com.acisa.cultivos.utilidades.Rutinas;

/**
 * The persistent class for the DatosAbonado database table.
 * 
 */
@Entity
@Table(name = "DatosAbonado")
public class DatosAbonado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoAbonado;
	private double ce;
	private String descripcion;
	private double ph;
	private double tiempo;
	private int visita;
	private double volumen;
	private String IntervaloRiegos;

	// bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name = "codigoAbonado", referencedColumnName = "codigoAbonado", nullable = false, insertable = false, updatable = false)
	private RegistroDatosAbonado registroBean;

	public DatosAbonado() {
	}

	public DatosAbonado(int visita, String descripcion, double volumen, double tiempo, double ph, double ce) {
		this.visita = visita;
		this.descripcion = descripcion;
		this.volumen = volumen;
		this.tiempo = tiempo;
		this.ph = ph;
		this.ce = ce;
	}

	public DatosAbonado(String registro) {
		String[] campos = registro.split(";");

		if (campos.length >= 5) {
			this.visita = Rutinas.Valor(campos[0]).intValue();
			this.codigoAbonado = Rutinas.Valor(campos[1]).intValue();
			this.descripcion = campos[2];
			this.IntervaloRiegos = campos[3];
			this.volumen = Rutinas.Valor(campos[4]);
			this.tiempo = Rutinas.Valor(campos[5]);
			this.ph = Rutinas.Valor(campos[6]);
			this.ce = Rutinas.Valor(campos[7]);
		}

	}

	public int getCodigoAbonado() {
		return this.codigoAbonado;
	}

	public void setCodigoAbonado(int codigoAbonado) {
		this.codigoAbonado = codigoAbonado;
	}

	public double getCe() {
		return this.ce;
	}

	public void setCe(double ce) {
		this.ce = ce;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPh() {
		return this.ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
	}

	public double getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public int getVisita() {
		return this.visita;
	}

	public void setVisita(int visita) {
		this.visita = visita;
	}

	public double getVolumen() {
		return this.volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public String getIntervaloRiegos() {
		return IntervaloRiegos;
	}

	public void setIntervaloRiegos(String intervaloRiegos) {
		IntervaloRiegos = intervaloRiegos;
	}

}