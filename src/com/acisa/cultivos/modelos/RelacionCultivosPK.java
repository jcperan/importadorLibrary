package com.acisa.cultivos.modelos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RelacionCultivos database table.
 * 
 */
@Embeddable
public class RelacionCultivosPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int cultivo;

	private int cultivoContenedor;

	public RelacionCultivosPK() {
	}

	public int getCultivo() {
		return this.cultivo;
	}

	public void setCultivo(int cultivo) {
		this.cultivo = cultivo;
	}

	public int getCultivoContenedor() {
		return this.cultivoContenedor;
	}

	public void setCultivoContenedor(int cultivoContenedor) {
		this.cultivoContenedor = cultivoContenedor;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RelacionCultivosPK)) {
			return false;
		}
		RelacionCultivosPK castOther = (RelacionCultivosPK) other;
		return (this.cultivo == castOther.cultivo) && (this.cultivoContenedor == castOther.cultivoContenedor);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cultivo;
		hash = hash * prime + this.cultivoContenedor;

		return hash;
	}
}