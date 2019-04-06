/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "ControlesPlagas")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Controlesplagas.findAll", query = "SELECT c FROM Controlesplagas c") })
public class Controlesplagas implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ControlesplagasPK controlesplagasPK;
	@Column(name = "Nivel")
	private String nivel;

	@ManyToOne
	@JoinColumn(name = "plaga", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Plagas plagaBean;

	public Plagas getPlagaBean() {
		return plagaBean;
	}

	public void setPlagaBean(Plagas plagaBean) {
		this.plagaBean = plagaBean;
	}

	public Controlesplagas() {
	}

	public Controlesplagas(ControlesplagasPK controlesplagasPK) {
		this.controlesplagasPK = controlesplagasPK;
	}

	public Controlesplagas(int visita, int plaga) {
		this.controlesplagasPK = new ControlesplagasPK(visita, plaga);
	}

	public ControlesplagasPK getControlesplagasPK() {
		return controlesplagasPK;
	}

	public void setControlesplagasPK(ControlesplagasPK controlesplagasPK) {
		this.controlesplagasPK = controlesplagasPK;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (controlesplagasPK != null ? controlesplagasPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Controlesplagas)) {
			return false;
		}
		Controlesplagas other = (Controlesplagas) object;
		if ((this.controlesplagasPK == null && other.controlesplagasPK != null)
				|| (this.controlesplagasPK != null && !this.controlesplagasPK.equals(other.controlesplagasPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Controlesplagas[ controlesplagasPK=" + controlesplagasPK + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.getControlesplagasPK().getVisita() + ";";
		resultado += this.getControlesplagasPK().getPlaga() + ";";
		resultado += this.getNivel() + ";";
		if (this.plagaBean == null)
			resultado += ';';
		else
			resultado += this.getPlagaBean().getNombre() + ';';
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
