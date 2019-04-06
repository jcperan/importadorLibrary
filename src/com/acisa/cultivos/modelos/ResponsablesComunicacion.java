package com.acisa.cultivos.modelos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ResponsablesComunicacion database table.
 * 
 */
@Entity
@NamedQuery(name = "ResponsablesComunicacion.findAll", query = "SELECT r FROM ResponsablesComunicacion r")
public class ResponsablesComunicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private String nombre;

	public ResponsablesComunicacion() {
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