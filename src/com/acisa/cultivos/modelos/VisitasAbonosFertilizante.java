package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

import com.acisa.cultivos.utilidades.Rutinas;

import java.util.Date;

/**
 * The persistent class for the VisitasAbonosFertilizantes database table.
 * 
 */
@Entity
@Table(name = "VisitasAbonosFertilizantes")
@NamedQuery(name = "VisitasAbonosFertilizante.findAll", query = "SELECT v FROM VisitasAbonosFertilizante v")
public class VisitasAbonosFertilizante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private int abonoFertilizante;
	private double dosis;
	private double dosisDeclarada;
	@Temporal(TemporalType.DATE)
	private Date fechaAplicacion;
	private int intervalo;
	private int operario;
	private int plazoSeguridad;
	private int visita;

	private String aplicacionTipo;
	private String aplicacionTanque;
	private String aplicacionRiego;

	private int codigoRegistroAbonado;

	public VisitasAbonosFertilizante() {
	}

	public VisitasAbonosFertilizante(String registro) {
		String[] campos = registro.split(";");

		if (campos.length >= 6) {
			this.visita = Rutinas.Valor(campos[0]).intValue();
			this.codigoRegistroAbonado = Rutinas.Valor(campos[1]).intValue();
			this.abonoFertilizante = Rutinas.Valor(campos[2]).intValue();
			this.dosis = Rutinas.Valor(campos[3]);
			this.aplicacionTipo = campos[4];
			this.aplicacionTanque = campos[5];
			this.aplicacionRiego = campos[6];
		}
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

	public int getVisita() {
		return this.visita;
	}

	public void setVisita(int visita) {
		this.visita = visita;
	}

	public String getAplicacionTipo() {
		return aplicacionTipo;
	}

	public void setAplicacionTipo(String aplicacionTipo) {
		this.aplicacionTipo = aplicacionTipo;
	}

	public String getAplicacionTanque() {
		return aplicacionTanque;
	}

	public void setAplicacionTanque(String aplicacionTanque) {
		this.aplicacionTanque = aplicacionTanque;
	}

	public String getAplicacionRiego() {
		return aplicacionRiego;
	}

	public void setAplicacionRiego(String aplicacionRiego) {
		this.aplicacionRiego = aplicacionRiego;
	}

	public int getCodigoRegistroAbonado() {
		return this.codigoRegistroAbonado;
	}

	public void setCodigoRegistroAbonado(int codigoRegistroAbonado) {
		this.codigoRegistroAbonado = codigoRegistroAbonado;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.getCodigo() + ";";
		resultado += this.getVisita() + ";";
		resultado += this.getAbonoFertilizante() + ";";
		resultado += this.getDosis() + ";";
		resultado += this.getAplicacionTipo() + ";";
		resultado += this.getAplicacionTanque() + ";";
		resultado += this.getAplicacionRiego() + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}