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

import com.acisa.cultivos.utilidades.Rutinas;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Fases")
@XmlRootElement
public class Fases implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Variedad")
	private Integer variedad;
	@Column(name = "Orden")
	private Integer orden;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Porcentaje")
	private Integer porcentaje;

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "faseBean")
	private Set<Visitas> visitas;

	public Set<Visitas> getVisitas() {
		return visitas;
	}

	public void setVisitas(Set<Visitas> visitas) {
		this.visitas = visitas;
	}

	public Fases() {
	}

	public Fases(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getVariedad() {
		return variedad;
	}

	public void setVariedad(Integer variedad) {
		this.variedad = variedad;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
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
		if (!(object instanceof Fases)) {
			return false;
		}
		Fases other = (Fases) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Fases[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.variedad + ";";
		resultado += this.orden + ";";
		resultado += this.nombre + ";";
		resultado += this.porcentaje + ";";
		resultado += this.variedad + "_" + Rutinas.FORMATO_NUMERO(this.orden, "00");
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
