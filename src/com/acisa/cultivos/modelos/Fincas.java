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

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "Fincas")
@XmlRootElement
public class Fincas implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Cliente")
	private Integer cliente;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Direccion")
	private String direccion;
	@Column(name = "CodigoPostal")
	private Integer codigoPostal;
	@Column(name = "Poblacion")
	private String poblacion;
	@Column(name = "Provincia")
	private String provincia;
	@Column(name = "CodigoFinca")
	private String codigoFinca;

	// bi-directional many-to-one association to Cultivosplaga
	@OneToMany(mappedBy = "fincaBean")
	private Set<Sectores> sectores;

	public Set<Sectores> getSectores() {
		return sectores;
	}

	public void setSectores(Set<Sectores> sectores) {
		this.sectores = sectores;
	}

	@OneToMany(mappedBy = "fincaBean")
	private Set<Plantaciones> plantaciones;

	public Set<Plantaciones> getPlantaciones() {
		return plantaciones;
	}

	public void setPlantaciones(Set<Plantaciones> plantaciones) {
		this.plantaciones = plantaciones;
	}

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "fincaBean")
	private Set<ProximasVisitas> proximasVisitas;

	public Set<ProximasVisitas> getProximasVisitas() {
		return proximasVisitas;
	}

	public void setProximasVisitas(Set<ProximasVisitas> proximasVisitas) {
		this.proximasVisitas = proximasVisitas;
	}

	public Fincas() {
	}

	public Fincas(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodigoFinca() {
		return codigoFinca;
	}

	public void setCodigoFinca(String codigoFinca) {
		this.codigoFinca = codigoFinca;
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
		if (!(object instanceof Fincas)) {
			return false;
		}
		Fincas other = (Fincas) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Fincas[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.cliente + ";";
		resultado += this.nombre + ";";
		resultado += this.direccion + ";";
		resultado += this.codigoPostal + ";";
		resultado += this.poblacion + ";";
		resultado += this.provincia + ";";
		resultado += this.codigoFinca + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
