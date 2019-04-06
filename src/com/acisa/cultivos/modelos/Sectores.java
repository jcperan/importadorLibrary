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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Sectores")
@XmlRootElement
public class Sectores implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Finca")
	private Integer finca;
	@Column(name = "Nombre")
	private String nombre;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "Extension")
	private Double extension;
	@Column(name = "SistemaPlantacion")
	private Integer sistemaPlantacion;
	@Column(name = "SistemaRiego")
	private Integer sistemaRiego;
	@Column(name = "GPSLatitud")
	private Double gPSLatitud;
	@Column(name = "GPSLongitud")
	private Double gPSLongitud;
	@Column(name = "UTM")
	private String utm;
	@Column(name = "ReferenciaCatastral")
	private String referenciaCatastral;

	@OneToMany(mappedBy = "sectorBean")
	private Set<Plantaciones> plantaciones;

	public Set<Plantaciones> getPlantaciones() {
		return plantaciones;
	}

	public void setPlantaciones(Set<Plantaciones> plantaciones) {
		this.plantaciones = plantaciones;
	}

	@ManyToOne
	@JoinColumn(name = "finca", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Fincas fincaBean;

	public Fincas getFincaBean() {
		return fincaBean;
	}

	public void setFincaBean(Fincas fincaBean) {
		this.fincaBean = fincaBean;
	}

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "sectorBean")
	private Set<ProximasVisitas> proximasVisitas;

	public Set<ProximasVisitas> getProximasVisitas() {
		return proximasVisitas;
	}

	public void setProximasVisitas(Set<ProximasVisitas> proximasVisitas) {
		this.proximasVisitas = proximasVisitas;
	}

	public Sectores() {
	}

	public Sectores(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getFinca() {
		return finca;
	}

	public void setFinca(Integer finca) {
		this.finca = finca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getExtension() {
		return extension;
	}

	public void setExtension(Double extension) {
		this.extension = extension;
	}

	public Integer getSistemaPlantacion() {
		return sistemaPlantacion;
	}

	public void setSistemaPlantacion(Integer sistemaPlantacion) {
		this.sistemaPlantacion = sistemaPlantacion;
	}

	public Integer getSistemaRiego() {
		return sistemaRiego;
	}

	public void setSistemaRiego(Integer sistemaRiego) {
		this.sistemaRiego = sistemaRiego;
	}

	public Double getGPSLatitud() {
		return gPSLatitud;
	}

	public void setGPSLatitud(Double gPSLatitud) {
		this.gPSLatitud = gPSLatitud;
	}

	public Double getGPSLongitud() {
		return gPSLongitud;
	}

	public void setGPSLongitud(Double gPSLongitud) {
		this.gPSLongitud = gPSLongitud;
	}

	public String getUtm() {
		return utm;
	}

	public void setUtm(String utm) {
		this.utm = utm;
	}

	public String getReferenciaCatastral() {
		return referenciaCatastral;
	}

	public void setReferenciaCatastral(String referenciaCatastral) {
		this.referenciaCatastral = referenciaCatastral;
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
		if (!(object instanceof Sectores)) {
			return false;
		}
		Sectores other = (Sectores) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Sectores[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.finca + ";";
		resultado += this.nombre + ";";
		resultado += this.extension + ";";
		resultado += this.sistemaPlantacion + ";";
		resultado += this.sistemaRiego + ";";
		resultado += this.gPSLatitud + ";";
		resultado += this.gPSLongitud + ";";
		resultado += this.utm + ";";
		resultado += this.referenciaCatastral + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
