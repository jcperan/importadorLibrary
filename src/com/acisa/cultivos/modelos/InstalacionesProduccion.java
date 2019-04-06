package com.acisa.cultivos.modelos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the InstalacionesProduccion database table.
 * 
 */
@Entity
@NamedQuery(name = "InstalacionesProduccion.findAll", query = "SELECT i FROM InstalacionesProduccion i")
public class InstalacionesProduccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private String nombre;

	public InstalacionesProduccion() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}