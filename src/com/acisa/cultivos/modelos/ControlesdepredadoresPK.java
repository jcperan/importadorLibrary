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
 * @author Usuario
 */
@Embeddable
public class ControlesdepredadoresPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "Visita")
	private int visita;
	@Basic(optional = false)
	@Column(name = "Depredador")
	private int depredador;

	public ControlesdepredadoresPK() {
	}

	public ControlesdepredadoresPK(int visita, int depredador) {
		this.visita = visita;
		this.depredador = depredador;
	}

	public int getVisita() {
		return visita;
	}

	public void setVisita(int visita) {
		this.visita = visita;
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
		hash += (int) visita;
		hash += (int) depredador;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ControlesdepredadoresPK)) {
			return false;
		}
		ControlesdepredadoresPK other = (ControlesdepredadoresPK) object;
		if (this.visita != other.visita) {
			return false;
		}
		if (this.depredador != other.depredador) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.ControlesdepredadoresPK[ visita=" + visita + ", depredador=" + depredador
				+ " ]";
	}

}
