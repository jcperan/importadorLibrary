package com.acisa.cultivos.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the clientesequipos database table.
 * 
 */
@Entity
@NamedQuery(name = "ClientesEquipos.findAll", query = "SELECT c FROM ClientesEquipos c")
public class ClientesEquipos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;

	private Integer codigoCliente;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechaAdquisicion;

	@Temporal(TemporalType.DATE)
	private Date fechaUltimaInspeccion;

	private String NRoma;

	public ClientesEquipos() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaAdquisicion() {
		return this.fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Date getFechaUltimaInspeccion() {
		return this.fechaUltimaInspeccion;
	}

	public void setFechaUltimaInspeccion(Date fechaUltimaInspeccion) {
		this.fechaUltimaInspeccion = fechaUltimaInspeccion;
	}

	public String getNRoma() {
		return this.NRoma;
	}

	public void setNRoma(String NRoma) {
		this.NRoma = NRoma;
	}

}