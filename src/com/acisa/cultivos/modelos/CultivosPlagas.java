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

import com.acisa.cultivos.utilidades.Rutinas;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "CultivosPlagas")
@XmlRootElement
public class CultivosPlagas implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected CultivosPlagasPK cultivosPlagasPK;

	// bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name = "Cultivo", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Cultivos cultivoBean;

	// bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name = "Plaga", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Plagas plagasBean;

	public CultivosPlagas() {
	}

	public CultivosPlagas(CultivosPlagasPK cultivosPlagasPK) {
		this.cultivosPlagasPK = cultivosPlagasPK;
	}

	public CultivosPlagas(int cultivo, int plaga) {
		this.cultivosPlagasPK = new CultivosPlagasPK(cultivo, plaga);
	}

	public CultivosPlagasPK getCultivosPlagasPK() {
		return cultivosPlagasPK;
	}

	public void setCultivosPlagasPK(CultivosPlagasPK cultivosPlagasPK) {
		this.cultivosPlagasPK = cultivosPlagasPK;
	}

	public Cultivos getCultivoBean() {
		return cultivoBean;
	}

	public void setCultivoBean(Cultivos cultivoBean) {
		this.cultivoBean = cultivoBean;
	}

	public Plagas getPlagasBean() {
		return plagasBean;
	}

	public void setPlagasBean(Plagas plagasBean) {
		this.plagasBean = plagasBean;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cultivosPlagasPK != null ? cultivosPlagasPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CultivosPlagas)) {
			return false;
		}
		CultivosPlagas other = (CultivosPlagas) object;
		if ((this.cultivosPlagasPK == null && other.cultivosPlagasPK != null)
				|| (this.cultivosPlagasPK != null && !this.cultivosPlagasPK.equals(other.cultivosPlagasPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.CultivosPlagas[ cultivosPlagasPK=" + cultivosPlagasPK + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.cultivosPlagasPK.getCultivo() + ";";
		resultado += this.cultivosPlagasPK.getPlaga() + ";";
		if (this.cultivoBean == null)
			resultado += ";";
		else
			resultado += this.getCultivoBean().getNombre() + ";";
		if (this.plagasBean == null)
			resultado += ";";
		else
			resultado += this.getPlagasBean().getNombre() + ";";

		// Calcular numero de veces tratada la plaga
		String cuentaPlagas = "0";
		if (this.plagasBean != null) {
			if (this.plagasBean.getControles() != null) {
				cuentaPlagas = Rutinas.CStr(this.plagasBean.getControles().size());
			}
		}
		resultado += cuentaPlagas + ";";
		resultado += this.cultivosPlagasPK.getCultivo() + "_" + Rutinas.FORMATO_NUMERO(cuentaPlagas, "0000");
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
