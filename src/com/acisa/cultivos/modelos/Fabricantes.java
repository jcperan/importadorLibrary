/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "Fabricantes")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Fabricantes.findAll", query = "SELECT f FROM Fabricantes f"),
		@NamedQuery(name = "Fabricantes.findByCodigo", query = "SELECT f FROM Fabricantes f WHERE f.codigo = :codigo"),
		@NamedQuery(name = "Fabricantes.findByNombre", query = "SELECT f FROM Fabricantes f WHERE f.nombre = :nombre") })
public class Fabricantes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Nombre")
	private String nombre;

	@OneToMany(mappedBy = "fabricanteBean")
	private Set<Productos> productos;

	public Fabricantes() {
	}

	public Fabricantes(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		if (!(object instanceof Fabricantes)) {
			return false;
		}
		Fabricantes other = (Fabricantes) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Fabricantes[ codigo=" + codigo + " ]";
	}

	@XmlTransient
	public Set<Productos> getProductos() {
		return productos;
	}

	public void setProductos(Set<Productos> productos) {
		this.productos = productos;
	}

}
