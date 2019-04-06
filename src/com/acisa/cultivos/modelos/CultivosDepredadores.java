/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "CultivosDepredadores")
@XmlRootElement
public class CultivosDepredadores implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected CultivosDepredadoresPK cultivosDepredadoresPK;

	// bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name = "Cultivo", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Cultivos cultivoBean;

	// bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name = "Depredador", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Depredadores depredadoresBean;

	public CultivosDepredadores() {
	}

	public CultivosDepredadores(CultivosDepredadoresPK cultivosDepredadoresPK) {
		this.cultivosDepredadoresPK = cultivosDepredadoresPK;
	}

	public CultivosDepredadores(int cultivo, int depredador) {
		this.cultivosDepredadoresPK = new CultivosDepredadoresPK(cultivo, depredador);
	}

	public CultivosDepredadoresPK getCultivosDepredadoresPK() {
		return cultivosDepredadoresPK;
	}

	public void setCultivosDepredadoresPK(CultivosDepredadoresPK cultivosDepredadoresPK) {
		this.cultivosDepredadoresPK = cultivosDepredadoresPK;
	}

	public Cultivos getCultivoBean() {
		return cultivoBean;
	}

	public void setCultivoBean(Cultivos cultivoBean) {
		this.cultivoBean = cultivoBean;
	}

	public Depredadores getDepredadoresBean() {
		return depredadoresBean;
	}

	public void setDepredadoresBean(Depredadores depredadoresBean) {
		this.depredadoresBean = depredadoresBean;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cultivosDepredadoresPK != null ? cultivosDepredadoresPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CultivosDepredadores)) {
			return false;
		}
		CultivosDepredadores other = (CultivosDepredadores) object;
		if ((this.cultivosDepredadoresPK == null && other.cultivosDepredadoresPK != null)
				|| (this.cultivosDepredadoresPK != null
						&& !this.cultivosDepredadoresPK.equals(other.cultivosDepredadoresPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.CultivosDepredadores[ cultivosDepredadoresPK=" + cultivosDepredadoresPK
				+ " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.cultivosDepredadoresPK.getCultivo() + ";";
		resultado += this.cultivosDepredadoresPK.getDepredador() + ";";
		if (this.cultivoBean == null)
			resultado += ";";
		else
			resultado += this.getCultivoBean().getNombre() + ";";
		if (this.depredadoresBean == null)
			resultado += ";";
		else
			resultado += this.getDepredadoresBean().getNombre() + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
