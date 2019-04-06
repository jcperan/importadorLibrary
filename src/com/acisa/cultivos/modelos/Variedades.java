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
@Table(name = "Variedades")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Variedades.findAll", query = "SELECT v FROM Variedades v") })
public class Variedades implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Cultivo")
	private Integer cultivo;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Ciclo")
	private Integer ciclo;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "PesoMedio")
	private Double pesoMedio;
	@Column(name = "PorcentajeMerma")
	private Double porcentajeMerma;

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "variedadBean")
	private Set<Plantaciones> plantaciones;

	public Set<Plantaciones> getPlantaciones() {
		return plantaciones;
	}

	public void setPlantaciones(Set<Plantaciones> plantaciones) {
		this.plantaciones = plantaciones;
	}

	public Variedades() {
	}

	public Variedades(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCultivo() {
		return cultivo;
	}

	public void setCultivo(Integer cultivo) {
		this.cultivo = cultivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Double getPesoMedio() {
		return pesoMedio;
	}

	public void setPesoMedio(Double pesoMedio) {
		this.pesoMedio = pesoMedio;
	}

	public Double getPorcentajeMerma() {
		return porcentajeMerma;
	}

	public void setPorcentajeMerma(Double porcentajeMerma) {
		this.porcentajeMerma = porcentajeMerma;
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
		if (!(object instanceof Variedades)) {
			return false;
		}
		Variedades other = (Variedades) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Variedades[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.cultivo + ";";
		resultado += this.nombre + ";";
		resultado += this.ciclo + ";";
		resultado += this.pesoMedio + ";";
		resultado += this.porcentajeMerma + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
