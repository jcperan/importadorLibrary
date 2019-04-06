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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Tratamientos")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tratamientos.findAll", query = "SELECT t FROM Tratamientos t") })
public class Tratamientos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Visita")
	private Integer visita;
	@Column(name = "FechaReceta")
	@Temporal(TemporalType.DATE)
	private Date fechaReceta;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "Caldo")
	private Double caldo;
	@Column(name = "FechaRecoleccion")
	@Temporal(TemporalType.DATE)
	private Date fechaRecoleccion;

	@OneToMany(mappedBy = "tratamientoBean")
	private Set<Tratamientosproductos> tratamientos;

	public Set<Tratamientosproductos> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(Set<Tratamientosproductos> tratamientos) {
		this.tratamientos = tratamientos;
	}

	@OneToOne(mappedBy = "tratamientoBean")
	private Operaciones operaciones;

	public Operaciones getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(Operaciones operaciones) {
		this.operaciones = operaciones;
	}

	@OneToOne
	@JoinColumn(name = "visita", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Visitas visitaBean;

	public Visitas getVisitaBean() {
		return visitaBean;
	}

	public void setVisitaBean(Visitas visitaBean) {
		this.visitaBean = visitaBean;
	}

	public Tratamientos() {
	}

	public Tratamientos(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getVisita() {
		return visita;
	}

	public void setVisita(Integer visita) {
		this.visita = visita;
	}

	public Date getFechaReceta() {
		return fechaReceta;
	}

	public void setFechaReceta(Date fechaReceta) {
		this.fechaReceta = fechaReceta;
	}

	public Double getCaldo() {
		return caldo;
	}

	public void setCaldo(Double caldo) {
		this.caldo = caldo;
	}

	public Date getFechaRecoleccion() {
		return fechaRecoleccion;
	}

	public void setFechaRecoleccion(Date fechaRecoleccion) {
		this.fechaRecoleccion = fechaRecoleccion;
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
		if (!(object instanceof Tratamientos)) {
			return false;
		}
		Tratamientos other = (Tratamientos) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Tratamientos[ codigo=" + codigo + " ]";
	}

}
