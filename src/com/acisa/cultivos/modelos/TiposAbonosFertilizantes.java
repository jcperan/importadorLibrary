package com.acisa.cultivos.modelos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the TiposAbonosFertilizantes database table.
 * 
 */
@Entity
@Table(name = "TiposAbonosFertilizantes")
@NamedQuery(name = "TiposAbonosFertilizantes.findAll", query = "SELECT t FROM TiposAbonosFertilizantes t")
public class TiposAbonosFertilizantes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;
	private String nombre;

	// bi-directional many-to-one association to Cultivosplaga
	@OneToMany(mappedBy = "tiposAbonosBean")
	private Set<AbonosFertilizantes> AbonosFertilizantes;

	public TiposAbonosFertilizantes() {
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