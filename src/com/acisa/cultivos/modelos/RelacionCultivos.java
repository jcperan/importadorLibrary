package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the RelacionCultivos database table.
 * 
 */
@Entity
@Table(name = "RelacionCultivos")
@NamedQuery(name = "RelacionCultivos.findAll", query = "SELECT r FROM RelacionCultivos r")
public class RelacionCultivos implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RelacionCultivosPK id;

	public RelacionCultivos() {
	}

	public RelacionCultivosPK getId() {
		return this.id;
	}

	public void setId(RelacionCultivosPK id) {
		this.id = id;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.getId().getCultivo() + ";";
		resultado += this.getId().getCultivoContenedor() + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}