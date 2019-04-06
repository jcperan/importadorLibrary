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
public class ControlesplagasPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "Visita")
	private int visita;
	@Basic(optional = false)
	@Column(name = "Plaga")
	private int plaga;

	public ControlesplagasPK() {
	}

	public ControlesplagasPK(int visita, int plaga) {
		this.visita = visita;
		this.plaga = plaga;
	}

	public int getVisita() {
		return visita;
	}

	public void setVisita(int visita) {
		this.visita = visita;
	}

	public int getPlaga() {
		return plaga;
	}

	public void setPlaga(int plaga) {
		this.plaga = plaga;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) visita;
		hash += (int) plaga;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ControlesplagasPK)) {
			return false;
		}
		ControlesplagasPK other = (ControlesplagasPK) object;
		if (this.visita != other.visita) {
			return false;
		}
		if (this.plaga != other.plaga) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.ControlesplagasPK[ visita=" + visita + ", plaga=" + plaga + " ]";
	}

}
