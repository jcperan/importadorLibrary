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
public class CultivosPlagasPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "Cultivo")
	private int cultivo;
	@Basic(optional = false)
	@Column(name = "Plaga")
	private int plaga;

	public CultivosPlagasPK() {
	}

	public CultivosPlagasPK(int cultivo, int plaga) {
		this.cultivo = cultivo;
		this.plaga = plaga;
	}

	public int getCultivo() {
		return cultivo;
	}

	public void setCultivo(int cultivo) {
		this.cultivo = cultivo;
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
		hash += (int) cultivo;
		hash += (int) plaga;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CultivosPlagasPK)) {
			return false;
		}
		CultivosPlagasPK other = (CultivosPlagasPK) object;
		if (this.cultivo != other.cultivo) {
			return false;
		}
		if (this.plaga != other.plaga) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.CultivosPlagasPK[ cultivo=" + cultivo + ", plaga=" + plaga + " ]";
	}

}
