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
@Table(name = "ControlesDepredadores")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Controlesdepredadores.findAll", query = "SELECT c FROM Controlesdepredadores c") })
public class Controlesdepredadores implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ControlesdepredadoresPK controlesdepredadoresPK;
	@Column(name = "Nivel")
	private String nivel;
	private Double porcentaje;

	@ManyToOne
	@JoinColumn(name = "depredador", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Depredadores depredadorBean;

	public Depredadores getDepredadorBean() {
		return depredadorBean;
	}

	public void setDepredadorBean(Depredadores depredadorBean) {
		this.depredadorBean = depredadorBean;
	}

	public Controlesdepredadores() {
	}

	public Controlesdepredadores(ControlesdepredadoresPK controlesdepredadoresPK) {
		this.controlesdepredadoresPK = controlesdepredadoresPK;
	}

	public Controlesdepredadores(int visita, int depredador) {
		this.controlesdepredadoresPK = new ControlesdepredadoresPK(visita, depredador);
	}

	public ControlesdepredadoresPK getControlesdepredadoresPK() {
		return controlesdepredadoresPK;
	}

	public void setControlesdepredadoresPK(ControlesdepredadoresPK controlesdepredadoresPK) {
		this.controlesdepredadoresPK = controlesdepredadoresPK;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (controlesdepredadoresPK != null ? controlesdepredadoresPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Controlesdepredadores)) {
			return false;
		}
		Controlesdepredadores other = (Controlesdepredadores) object;
		if ((this.controlesdepredadoresPK == null && other.controlesdepredadoresPK != null)
				|| (this.controlesdepredadoresPK != null
						&& !this.controlesdepredadoresPK.equals(other.controlesdepredadoresPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Controlesdepredadores[ controlesdepredadoresPK=" + controlesdepredadoresPK
				+ " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.getControlesdepredadoresPK().getVisita() + ";";
		resultado += this.getControlesdepredadoresPK().getDepredador() + ";";
		resultado += this.getNivel() + ";";
		if (this.depredadorBean == null)
			resultado += ';';
		else
			resultado += this.getDepredadorBean().getNombre() + ';';
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
