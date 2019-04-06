/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.acisa.cultivos.utilidades.Rutinas;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Visitas")
@XmlRootElement
public class Visitas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Plantacion")
	private Integer plantacion;
	@Column(name = "Fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(name = "DiasSiguiente")
	private Integer diasSiguiente;
	@Column(name = "FechaSiguiente")
	@Temporal(TemporalType.DATE)
	private Date fechaSiguiente;
	@Column(name = "Fase")
	private Integer fase;
	@Column(name = "Muestreo")
	private Integer muestreo;

	@ManyToOne
	@JoinColumn(name = "plantacion", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Plantaciones plantacionBean;

	public Plantaciones getPlantacionBean() {
		return plantacionBean;
	}

	public void setPlantacionBean(Plantaciones plantacionBean) {
		this.plantacionBean = plantacionBean;
	}

	@ManyToOne
	@JoinColumn(name = "fase", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Fases faseBean;

	public Fases getFaseBean() {
		return faseBean;
	}

	public void setFaseBean(Fases faseBean) {
		this.faseBean = faseBean;
	}

	@ManyToOne
	@JoinColumn(name = "muestreo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Muestreos muestreoBean;

	public Muestreos getMuestreoBean() {
		return muestreoBean;
	}

	public void setMuestreoBean(Muestreos muestreoBean) {
		this.muestreoBean = muestreoBean;
	}

	@OneToOne
	@JoinColumn(name = "codigo", referencedColumnName = "visita", nullable = false, insertable = false, updatable = false)
	private Tratamientos tratamientoBean;

	public Tratamientos getTratamientoBean() {
		return tratamientoBean;
	}

	public void setTratamientoBean(Tratamientos tratamientoBean) {
		this.tratamientoBean = tratamientoBean;
	}

	public Visitas() {
	}

	public Visitas(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getPlantacion() {
		return plantacion;
	}

	public void setPlantacion(Integer plantacion) {
		this.plantacion = plantacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getDiasSiguiente() {
		return diasSiguiente;
	}

	public void setDiasSiguiente(Integer diasSiguiente) {
		this.diasSiguiente = diasSiguiente;
	}

	public Date getFechaSiguiente() {
		return fechaSiguiente;
	}

	public void setFechaSiguiente(Date fechaSiguiente) {
		this.fechaSiguiente = fechaSiguiente;
	}

	public Integer getFase() {
		return fase;
	}

	public void setFase(Integer fase) {
		this.fase = fase;
	}

	public Integer getMuestreo() {
		return muestreo;
	}

	public void setMuestreo(Integer muestreo) {
		this.muestreo = muestreo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codigo != null ? codigo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Visitas)) {
			return false;
		}
		Visitas other = (Visitas) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Visitas[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.plantacion + ";";
		resultado += Rutinas.FORMATO_FECHA(this.fecha, "yyyy-MM-dd") + ";";
		resultado += this.diasSiguiente + ";";
		resultado += Rutinas.FORMATO_FECHA(this.fechaSiguiente, "yyyy-MM-dd") + ";";
		resultado += this.fase + ";";
		resultado += this.muestreo + ";";
		if (this.faseBean == null)
			resultado += ";";
		else
			resultado += this.getFaseBean().getNombre() + ";";
		if (this.muestreoBean == null)
			resultado += ";";
		else
			resultado += this.getMuestreoBean().getNombre() + ";";
		resultado += this.plantacion + "_" + Rutinas.FORMATO_FECHA(this.fecha, "yyyy-MM-dd") + ";";

		try {
			Operaciones operacion = this.tratamientoBean.getOperaciones();
			if (operacion == null)
				resultado += ";";
			else
				resultado += Rutinas.FORMATO_FECHA(operacion.getFecha(), "yyyy-MM-dd") + ";";
			if (operacion == null)
				resultado += ";";
			else
				resultado += operacion.getObservaciones() + ";";
			if (operacion == null)
				resultado += ";";
			else
				resultado += operacion.getNumeroEquipos().toString() + ";";

		} catch (Exception e) {
			resultado += ";;;";
		}

		try {
			Tratamientos tratamiento = this.getTratamientoBean();
			if (tratamiento == null)
				resultado += ";";
			else
				resultado += tratamiento.getCaldo().toString() + ";";
		} catch (Exception e) {
			resultado += ";";
		}

		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
