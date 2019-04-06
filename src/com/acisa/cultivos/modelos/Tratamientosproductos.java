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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "TratamientosProductos")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tratamientosproductos.findAll", query = "SELECT t FROM Tratamientosproductos t") })
public class Tratamientosproductos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Tratamiento")
	private Integer tratamiento;
	@Column(name = "Producto")
	private Integer producto;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "Dosis")
	private Double dosis;
	@Column(name = "Operario")
	private Integer operario;
	@Column(name = "FechaAplicacion")
	@Temporal(TemporalType.DATE)
	private Date fechaAplicacion;
	@Column(name = "PlazoSeguridad")
	private Integer plazoSeguridad;
	@Column(name = "Intervalo")
	private Integer intervalo;
	private Double dosisDeclarada;
	private String Aplicacion;

	@ManyToOne
	@JoinColumn(name = "tratamiento", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Tratamientos tratamientoBean;

	public Tratamientos gettratamientoBean() {
		return tratamientoBean;
	}

	public void setTratamientoBean(Tratamientos tratamientoBean) {
		this.tratamientoBean = tratamientoBean;
	}

	@ManyToOne
	@JoinColumn(name = "producto", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Productos productoBean;

	public Productos getProductoBean() {
		return productoBean;
	}

	public void setProductoBean(Productos productoBean) {
		this.productoBean = productoBean;
	}

	public Tratamientosproductos() {
	}

	public Tratamientosproductos(Integer codigo) {
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

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Double getDosis() {
		return dosis;
	}

	public void setDosis(Double dosis) {
		this.dosis = dosis;
	}

	public Integer getOperario() {
		return operario;
	}

	public void setOperario(Integer operario) {
		this.operario = operario;
	}

	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Integer getPlazoSeguridad() {
		return plazoSeguridad;
	}

	public void setPlazoSeguridad(Integer plazoSeguridad) {
		this.plazoSeguridad = plazoSeguridad;
	}

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public Double getDosisDeclarada() {
		return dosisDeclarada;
	}

	public void setDosisDeclarada(Double dosisDeclarada) {
		this.dosisDeclarada = dosisDeclarada;
	}

	public String getAplicacion() {
		return Aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		Aplicacion = aplicacion;
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
		if (!(object instanceof Tratamientosproductos)) {
			return false;
		}
		Tratamientosproductos other = (Tratamientosproductos) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Tratamientosproductos[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		if (this.gettratamientoBean() == null)
			resultado += ";";
		else
			resultado += this.gettratamientoBean().getVisita() + ";";
		resultado += this.producto + ";";
		resultado += this.dosis + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
