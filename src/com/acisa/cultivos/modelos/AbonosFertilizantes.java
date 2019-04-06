package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the AbonosFertilizantes database table.
 * 
 */
@Entity
@Table(name = "AbonosFertilizantes")
public class AbonosFertilizantes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;
	private String aplicacion;
	private String composicion;
	private String nombre;
	private String numeroRegistro;
	private String rangoDosis;
	private int tipo;
	private String unidadMedida;

	@ManyToOne
	@JoinColumn(name = "tipo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private TiposAbonosFertilizantes tiposAbonosBean;

	public TiposAbonosFertilizantes getTiposAbonosBean() {
		return tiposAbonosBean;
	}

	public void setTiposAbonosBean(TiposAbonosFertilizantes tiposAbonosBean) {
		this.tiposAbonosBean = tiposAbonosBean;
	}

	public AbonosFertilizantes() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getComposicion() {
		return this.composicion;
	}

	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroRegistro() {
		return this.numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getRangoDosis() {
		return this.rangoDosis;
	}

	public void setRangoDosis(String rangoDosis) {
		this.rangoDosis = rangoDosis;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.numeroRegistro + ";";
		resultado += this.nombre + ";";
		resultado += this.composicion + ";";
		resultado += this.aplicacion + ";";
		resultado += this.rangoDosis + ";";
		resultado += this.unidadMedida + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}