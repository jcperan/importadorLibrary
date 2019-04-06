package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

import com.acisa.cultivos.utilidades.Rutinas;

import java.util.Date;

/**
 * The persistent class for the VisitasProductosOcb database table.
 * 
 */
@Entity
@Table(name = "VisitasProductosOcb")
@NamedQuery(name = "VisitasProductosOcb.findAll", query = "SELECT v FROM VisitasProductosOcb v")
public class VisitasProductosOcb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private double dosis;

	private double dosisDeclarada;

	@Temporal(TemporalType.DATE)
	private Date fechaAplicacion;

	private int intervalo;

	private int operario;

	private int plazoSeguridad;

	private int productoOcb;

	private int visita;

	public VisitasProductosOcb() {
	}

	public VisitasProductosOcb(String registro) {
		String[] campos = registro.split(";");

		if (campos.length >= 3) {
			this.visita = Rutinas.Valor(campos[0]).intValue();
			this.productoOcb = Rutinas.Valor(campos[1]).intValue();
			this.dosis = Rutinas.Valor(campos[2]);
		}
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getDosis() {
		return this.dosis;
	}

	public void setDosis(double dosis) {
		this.dosis = dosis;
	}

	public double getDosisDeclarada() {
		return this.dosisDeclarada;
	}

	public void setDosisDeclarada(double dosisDeclarada) {
		this.dosisDeclarada = dosisDeclarada;
	}

	public Date getFechaAplicacion() {
		return this.fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public int getIntervalo() {
		return this.intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public int getOperario() {
		return this.operario;
	}

	public void setOperario(int operario) {
		this.operario = operario;
	}

	public int getPlazoSeguridad() {
		return this.plazoSeguridad;
	}

	public void setPlazoSeguridad(int plazoSeguridad) {
		this.plazoSeguridad = plazoSeguridad;
	}

	public int getProductoOcb() {
		return this.productoOcb;
	}

	public void setProductoOcb(int productoOcb) {
		this.productoOcb = productoOcb;
	}

	public int getVisita() {
		return this.visita;
	}

	public void setVisita(int visita) {
		this.visita = visita;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.getCodigo() + ";";
		resultado += this.getVisita() + ";";
		resultado += this.getProductoOcb() + ";";
		resultado += this.getDosis() + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}