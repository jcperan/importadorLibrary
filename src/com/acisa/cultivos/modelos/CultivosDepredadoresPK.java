/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author usuario
 */
@Embeddable
public class CultivosDepredadoresPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "Cultivo")
	private int cultivo;
	@Basic(optional = false)
	@Column(name = "Depredador")
	private int depredador;

	public CultivosDepredadoresPK() {
	}

	public CultivosDepredadoresPK(int cultivo, int depredador) {
		this.cultivo = cultivo;
		this.depredador = depredador;
	}

	public int getCultivo() {
		return cultivo;
	}

	public void setCultivo(int cultivo) {
		this.cultivo = cultivo;
	}

	public int getDepredador() {
		return depredador;
	}

	public void setDepredador(int depredador) {
		this.depredador = depredador;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) cultivo;
		hash += (int) depredador;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CultivosDepredadoresPK)) {
			return false;
		}
		CultivosDepredadoresPK other = (CultivosDepredadoresPK) object;
		if (this.cultivo != other.cultivo) {
			return false;
		}
		if (this.depredador != other.depredador) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.CultivosDepredadoresPK[ cultivo=" + cultivo + ", depredador=" + depredador
				+ " ]";
	}

}
