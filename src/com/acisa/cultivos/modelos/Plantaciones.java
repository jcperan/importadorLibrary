/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.acisa.cultivos.utilidades.Rutinas;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Plantaciones")
@XmlRootElement
public class Plantaciones implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Cliente")
	private Integer cliente;
	@Column(name = "Finca")
	private Integer finca;
	@Column(name = "Sector")
	private Integer sector;
	@Column(name = "Cultivo")
	private Integer cultivo;
	@Column(name = "Variedad")
	private Integer variedad;
	@Column(name = "FechaInicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Column(name = "Ciclo")
	private Integer ciclo;
	@Column(name = "FechaRecoleccion")
	@Temporal(TemporalType.DATE)
	private Date fechaRecoleccion;

	private String nombre;

	@ManyToOne
	@JoinColumn(name = "cultivo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Cultivos cultivoBean;

	public Cultivos getCultivoBean() {
		return cultivoBean;
	}

	public void setCultivoBean(Cultivos cultivoBean) {
		this.cultivoBean = cultivoBean;
	}

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "plantacionBean")
	private Set<Visitas> visitas;

	public Set<Visitas> getVisitas() {
		return visitas;
	}

	public void setVisitas(Set<Visitas> visitas) {
		this.visitas = visitas;
	}

	@ManyToOne
	@JoinColumn(name = "variedad", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Variedades variedadBean;

	public Variedades getVariedadBean() {
		return variedadBean;
	}

	public void setVariedadBean(Variedades variedadBean) {
		this.variedadBean = variedadBean;
	}

	@ManyToOne
	@JoinColumn(name = "cliente", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Clientes clienteBean;

	public Clientes getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(Clientes clienteBean) {
		this.clienteBean = clienteBean;
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

	@ManyToOne
	@JoinColumn(name = "sector", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Sectores sectorBean;

	public Sectores getSectorBean() {
		return sectorBean;
	}

	public void setSectorBean(Sectores sectorBean) {
		this.sectorBean = sectorBean;
	}

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "plantacionBean")
	private Set<ProximasVisitas> proximasVisitas;

	public Set<ProximasVisitas> getProximasVisitas() {
		return proximasVisitas;
	}

	public void setProximasVisitas(Set<ProximasVisitas> proximasVisitas) {
		this.proximasVisitas = proximasVisitas;
	}

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "plantacionBean")
	private Set<PlantacionesFincas> plantacionesFincas;

	public Set<PlantacionesFincas> getPlantacionesFincas() {
		return plantacionesFincas;
	}

	public void setPlantacionesFincas(Set<PlantacionesFincas> plantacionesFincas) {
		this.plantacionesFincas = plantacionesFincas;
	}

	public Plantaciones() {

	}

	public Plantaciones(Integer codigo) {
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

	public Integer getFinca() {
		return finca;
	}

	public void setFinca(Integer finca) {
		this.finca = finca;
	}

	public Integer getSector() {
		return sector;
	}

	public void setSector(Integer sector) {
		this.sector = sector;
	}

	public Integer getCultivo() {
		return cultivo;
	}

	public void setCultivo(Integer cultivo) {
		this.cultivo = cultivo;
	}

	public Integer getVariedad() {
		return variedad;
	}

	public void setVariedad(Integer variedad) {
		this.variedad = variedad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Date getFechaRecoleccion() {
		return fechaRecoleccion;
	}

	public void setFechaRecoleccion(Date fechaRecoleccion) {
		this.fechaRecoleccion = fechaRecoleccion;
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
		if (!(object instanceof Plantaciones)) {
			return false;
		}
		Plantaciones other = (Plantaciones) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Plantaciones[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {

		Iterator<PlantacionesFincas> it = this.getPlantacionesFincas().iterator();
		int codigoFinca = 0;

		if (it.hasNext()) {
			PlantacionesFincas pf = it.next();
			codigoFinca = pf.getId().getFinca();
		}

		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.cliente + ";";
		// resultado += this.finca + ";";
		resultado += codigoFinca + ";";
		resultado += this.sector + ";";
		resultado += this.cultivo + ";";
		resultado += this.variedad + ";";
		resultado += Rutinas.FORMATO_FECHA(this.fechaInicio, "yyyy-MM-dd") + ";";
		resultado += this.ciclo + ";";
		resultado += Rutinas.FORMATO_FECHA(this.fechaRecoleccion, "yyyy-MM-dd") + ";";
		if (this.cultivoBean == null)
			resultado += ";";
		else
			resultado += this.getCultivoBean().getNombre() + ";";
		if (this.variedadBean == null)
			resultado += ";";
		else
			resultado += this.getVariedadBean().getNombre() + ";";
		resultado += this.nombre + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
