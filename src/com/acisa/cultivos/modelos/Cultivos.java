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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "Cultivos")
@XmlRootElement
public class Cultivos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Nombre")
	private String nombre;

	// bi-directional many-to-one association to Cultivosplaga
	@OneToMany(mappedBy = "cultivoBean")
	private Set<CultivosPlagas> cultivosPlagas;

	// bi-directional many-to-one association to UsosAutorizados
	@OneToMany(mappedBy = "cultivoBean")
	private Set<UsosAutorizados> usosAutorizados;

	@OneToMany(mappedBy = "cultivoBean")
	private Set<Plantaciones> plantaciones;

	public Set<Plantaciones> getPlantaciones() {
		return plantaciones;
	}

	public void setPlantaciones(Set<Plantaciones> plantaciones) {
		this.plantaciones = plantaciones;
	}

	public Cultivos() {
	}

	public Cultivos(Integer codigo) {
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

	public Set<CultivosPlagas> getCultivosplagas() {
		return cultivosPlagas;
	}

	public void setCultivosplagas(Set<CultivosPlagas> cultivosPlagas) {
		this.cultivosPlagas = cultivosPlagas;
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
		if (!(object instanceof Cultivos)) {
			return false;
		}
		Cultivos other = (Cultivos) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Cultivos[ codigo=" + codigo + " ]";
	}

	@XmlTransient
	public Set<CultivosPlagas> getCultivosPlagas() {
		return cultivosPlagas;
	}

	public void setCultivosPlagas(Set<CultivosPlagas> cultivosPlagas) {
		this.cultivosPlagas = cultivosPlagas;
	}

	@XmlTransient
	public Set<UsosAutorizados> getUsosAutorizados() {
		return usosAutorizados;
	}

	public void setUsosAutorizados(Set<UsosAutorizados> usosAutorizados) {
		this.usosAutorizados = usosAutorizados;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.nombre + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
