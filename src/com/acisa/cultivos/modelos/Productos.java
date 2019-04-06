/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlTransient;

import com.acisa.cultivos.utilidades.Configuracion;
import com.acisa.cultivos.utilidades.Rutinas;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "Productos")
@XmlRootElement
public class Productos implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "NumeroRegistro")
	private Integer numeroRegistro;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "MateriaActiva")
	private String materiaActiva;
	@Column(name = "Titular")
	private Integer titular;
	@Column(name = "Fabricante")
	private Integer fabricante;
	@Column(name = "FechaInscripcion")
	@Temporal(TemporalType.DATE)
	private Date fechaInscripcion;
	@Column(name = "FechaCaducidad")
	@Temporal(TemporalType.DATE)
	private Date fechaCaducidad;
	@Column(name = "Presentacion")
	private String presentacion;
	@Column(name = "Preparado")
	private String preparado;
	@Column(name = "Funcion")
	private String funcion;
	@Column(name = "Ambitos")
	private String ambitos;
	@Column(name = "Orden")
	private String orden;

	private Integer tablet;

	private String RegMagrama;
	@Column(name = "FechaActualizacion")
	@Temporal(TemporalType.DATE)
	private Date fechaActualizacion;

	// bi-directional many-to-one association to Cultivosplaga
	@OneToMany(mappedBy = "productoBean")
	private Set<UsosAutorizados> usosAutorizados;

	// bi-directional many-to-one association to Cultivosplaga
	@OneToMany(mappedBy = "productoBean")
	private Set<Operacionesproductos> operacionesProductos;

	public Set<Operacionesproductos> getOperacionesProductos() {
		return operacionesProductos;
	}

	public void setOperacionesProductos(Set<Operacionesproductos> operacionesProductos) {
		this.operacionesProductos = operacionesProductos;
	}

	// bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name = "Fabricante", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Fabricantes fabricanteBean;

	// bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name = "titular", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
	private Titulares titularBean;

	// bi-directional many-to-one association to Cultivosplaga
	@OneToMany(mappedBy = "productoBean")
	private Set<Tratamientosproductos> tratamientos;

	public Set<Tratamientosproductos> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(Set<Tratamientosproductos> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public Productos() {
	}

	public Productos(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(Integer numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMateriaActiva() {
		return materiaActiva;
	}

	public void setMateriaActiva(String materiaActiva) {
		this.materiaActiva = materiaActiva;
	}

	public Integer getTitular() {
		return titular;
	}

	public void setTitular(Integer titular) {
		this.titular = titular;
	}

	public Integer getFabricante() {
		return fabricante;
	}

	public void setFabricante(Integer fabricante) {
		this.fabricante = fabricante;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codigo != null ? codigo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Productos)) {
			return false;
		}
		Productos other = (Productos) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Productos[ codigo=" + codigo + " ]";
	}

	public String getPreparado() {
		return preparado;
	}

	public void setPreparado(String preparado) {
		this.preparado = preparado;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getAmbitos() {
		return ambitos;
	}

	public void setAmbitos(String ambitos) {
		this.ambitos = ambitos;
	}

	@XmlTransient
	public Set<UsosAutorizados> getUsosAutorizados() {
		return usosAutorizados;
	}

	public void setUsosAutorizados(Set<UsosAutorizados> usosAutorizados) {
		this.usosAutorizados = usosAutorizados;
	}

	public Fabricantes getFabricanteBean() {
		return fabricanteBean;
	}

	public void setFabricanteBean(Fabricantes fabricanteBean) {
		this.fabricanteBean = fabricanteBean;
	}

	public Titulares getTitularBean() {
		return titularBean;
	}

	public void setTitularBean(Titulares titularBean) {
		this.titularBean = titularBean;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public Integer getTablet() {
		return tablet;
	}

	public void setTablet(Integer tablet) {
		this.tablet = tablet;
	}

	public String getRegMagrama() {
		return RegMagrama;
	}

	public void setRegMagrama(String regMagrama) {
		RegMagrama = regMagrama;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.numeroRegistro + ";";
		resultado += this.nombre + ";";
		resultado += this.materiaActiva + ";";
		if (this.titularBean == null)
			resultado += ';';
		else
			resultado += this.getTitularBean().getNombre() + ";";
		if (this.fabricanteBean == null)
			resultado += ';';
		else
			resultado += this.fabricante + ";";
		if (this.fechaInscripcion == null) {
			resultado += ";";
		} else {
			resultado += Rutinas.FORMATO_FECHA(this.fechaInscripcion, "dd-MM-yyyy") + ";";
		}
		if (this.fechaCaducidad == null) {
			resultado += ";";
		} else {
			resultado += Rutinas.FORMATO_FECHA(this.fechaCaducidad, "dd-MM-yyyy") + ";";
		}
		// resultado += this.presentacion + ";";
		resultado += this.preparado + ";";
		resultado += this.funcion + ";";
		resultado += this.ambitos + ";";
		// Calcular numero de aplicaciones del producto
		resultado += Rutinas.FORMATO_NUMERO(Rutinas.CStr(this.getTratamientos().size()), "00000") + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
