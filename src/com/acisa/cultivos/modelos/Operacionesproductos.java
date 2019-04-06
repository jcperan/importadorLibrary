/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "OperacionesProductos")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Operacionesproductos.findAll", query = "SELECT o FROM Operacionesproductos o") })
public class Operacionesproductos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Operacion")
	private Integer operacion;
	@Column(name = "Producto")
	private Integer producto;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "CantidadEntregada")
	private Double cantidadEntregada;

	// bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name = "producto", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Productos productoBean;

	public Productos getProductoBean() {
		return productoBean;
	}

	public void setProductoBean(Productos productoBean) {
		this.productoBean = productoBean;
	}

	public Operacionesproductos() {
	}

	public Operacionesproductos(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getOperacion() {
		return operacion;
	}

	public void setOperacion(Integer operacion) {
		this.operacion = operacion;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Double getCantidadEntregada() {
		return cantidadEntregada;
	}

	public void setCantidadEntregada(Double cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
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
		if (!(object instanceof Operacionesproductos)) {
			return false;
		}
		Operacionesproductos other = (Operacionesproductos) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Operacionesproductos[ codigo=" + codigo + " ]";
	}

}
