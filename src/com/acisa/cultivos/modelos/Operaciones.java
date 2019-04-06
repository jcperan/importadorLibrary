/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;
import java.util.Date;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Operaciones")
@XmlRootElement
public class Operaciones implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Tratamiento")
	private Integer tratamiento;
	@Column(name = "Fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(name = "Equipo")
	private Integer equipo;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "NumeroEquipos")
	private Double numeroEquipos;
	@Column(name = "VelocidadAvance")
	private Double velocidadAvance;
	@Column(name = "TipoBoquilla")
	private String tipoBoquilla;
	@Column(name = "Presion")
	private Double presion;
	@Column(name = "Climatologia")
	private Integer climatologia;
	@Column(name = "Operario")
	private Integer operario;
	@Column(name = "Observaciones")
	private String observaciones;
	@Column(name = "Encargado")
	private String encargado;
	@Column(name = "Aplicador")
	private String aplicador;
	@Column(name = "Confirmada")
	private Boolean confirmada;

	@OneToOne
	@JoinColumn(name = "tratamiento", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Tratamientos tratamientoBean;

	public Tratamientos getTratamientoBean() {
		return tratamientoBean;
	}

	public void setTratamientoBean(Tratamientos tratamientoBean) {
		this.tratamientoBean = tratamientoBean;
	}

	@ManyToOne
	@JoinColumn(name = "equipo", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Equiposfumigacion equipoBean;

	public Equiposfumigacion getEquipoBean() {
		return equipoBean;
	}

	public void setEquipoBean(Equiposfumigacion equipoBean) {
		this.equipoBean = equipoBean;
	}

	@ManyToOne
	@JoinColumn(name = "climatologia", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Climatologias climatologiaBean;

	public Climatologias getClimatologiaBean() {
		return climatologiaBean;
	}

	public void setClimatologiaBean(Climatologias climatologiaBean) {
		this.climatologiaBean = climatologiaBean;
	}

	@ManyToOne
	@JoinColumn(name = "operario", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Operarios operarioBean;

	public Operarios getOperarioBean() {
		return operarioBean;
	}

	public void setOperarioBean(Operarios operarioBean) {
		this.operarioBean = operarioBean;
	}

	public Operaciones() {
	}

	public Operaciones(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Integer tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getEquipo() {
		return equipo;
	}

	public void setEquipo(Integer equipo) {
		this.equipo = equipo;
	}

	public Double getNumeroEquipos() {
		return numeroEquipos;
	}

	public void setNumeroEquipos(Double numeroEquipos) {
		this.numeroEquipos = numeroEquipos;
	}

	public Double getVelocidadAvance() {
		return velocidadAvance;
	}

	public void setVelocidadAvance(Double velocidadAvance) {
		this.velocidadAvance = velocidadAvance;
	}

	public String getTipoBoquilla() {
		return tipoBoquilla;
	}

	public void setTipoBoquilla(String tipoBoquilla) {
		this.tipoBoquilla = tipoBoquilla;
	}

	public Double getPresion() {
		return presion;
	}

	public void setPresion(Double presion) {
		this.presion = presion;
	}

	public Integer getClimatologia() {
		return climatologia;
	}

	public void setClimatologia(Integer climatologia) {
		this.climatologia = climatologia;
	}

	public Integer getOperario() {
		return operario;
	}

	public void setOperario(Integer operario) {
		this.operario = operario;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	public String getAplicador() {
		return aplicador;
	}

	public void setAplicador(String aplicador) {
		this.aplicador = aplicador;
	}

	public Boolean getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(Boolean confirmada) {
		this.confirmada = confirmada;
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
		if (!(object instanceof Operaciones)) {
			return false;
		}
		Operaciones other = (Operaciones) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Operaciones[ codigo=" + codigo + " ]";
	}

}
