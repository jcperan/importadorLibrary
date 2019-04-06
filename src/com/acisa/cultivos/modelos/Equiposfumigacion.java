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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "EquiposFumigacion")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Equiposfumigacion.findAll", query = "SELECT e FROM Equiposfumigacion e") })
public class Equiposfumigacion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "TipoFumigacion")
	private String tipoFumigacion;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "Capacidad")
	private Double capacidad;

	// bi-directional many-to-one association to Cultivosplaga
	@OneToMany(mappedBy = "equipoBean")
	private Set<Operaciones> equipos;

	public Set<Operaciones> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Operaciones> equipos) {
		this.equipos = equipos;
	}

	public Equiposfumigacion() {
	}

	public Equiposfumigacion(Integer codigo) {
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

	public String getTipoFumigacion() {
		return tipoFumigacion;
	}

	public void setTipoFumigacion(String tipoFumigacion) {
		this.tipoFumigacion = tipoFumigacion;
	}

	public Double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
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
		if (!(object instanceof Equiposfumigacion)) {
			return false;
		}
		Equiposfumigacion other = (Equiposfumigacion) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Equiposfumigacion[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.nombre + ";";
		resultado += this.tipoFumigacion + ";";
		resultado += this.capacidad + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
