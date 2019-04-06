package com.acisa.cultivos.modelos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the clientesasesores database table.
 * 
 */
@Entity
@NamedQuery(name = "ClientesAsesores.findAll", query = "SELECT c FROM ClientesAsesores c")
public class ClientesAsesores implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private int codigoCliente;

	private String NIdentificacion;

	private String nif;

	private String nombre;

	private String tipoExplotacion;

	public ClientesAsesores() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNIdentificacion() {
		return this.NIdentificacion;
	}

	public void setNIdentificacion(String NIdentificacion) {
		this.NIdentificacion = NIdentificacion;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoExplotacion() {
		return this.tipoExplotacion;
	}

	public void setTipoExplotacion(String tipoExplotacion) {
		this.tipoExplotacion = tipoExplotacion;
	}

}