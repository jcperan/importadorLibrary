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
@Table(name = "Clientes")
@XmlRootElement
public class Clientes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
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
	@Column(name = "Telefono1")
	private String telefono1;
	@Column(name = "Telefono2")
	private String telefono2;
	@Column(name = "Movil")
	private String movil;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	// message="Correo electronico no valido")//if the field contains email address
	// consider using this annotation to enforce field validation
	@Column(name = "EMail")
	private String eMail;

	private String carnetAplicador;
	private String nif;
	private String nExplotacionNacional;
	private String nExplotacionAutonomico;
	private String nombreTitular;
	private String nifTitular;
	private String direccionTitular;
	private String poblacionTitular;
	private Integer codigoPostalTitular;
	private String provinciaTitular;
	private String tipoRepresentacion;
	private String telefonoTitular;
	private String emailTitular;

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "clienteBean")
	private Set<Plantaciones> plantaciones;

	public Set<Plantaciones> getPlantaciones() {
		return plantaciones;
	}

	public void setPlantaciones(Set<Plantaciones> plantaciones) {
		this.plantaciones = plantaciones;
	}

	@OneToMany(mappedBy = "clienteBean")
	private Set<ClientesPersonas> clientesPersonas;

	public Set<ClientesPersonas> getClientesPersonas() {
		return clientesPersonas;
	}

	public void setClientesPersonas(Set<ClientesPersonas> clientesPersonas) {
		this.clientesPersonas = clientesPersonas;
	}

	// bi-directional many-to-one association to Visitas
	@OneToMany(mappedBy = "clienteBean")
	private Set<ProximasVisitas> proximasVisitas;

	public Set<ProximasVisitas> getProximasVisitas() {
		return proximasVisitas;
	}

	public void setProximasVisitas(Set<ProximasVisitas> proximasVisitas) {
		this.proximasVisitas = proximasVisitas;
	}

	public Clientes() {
	}

	public Clientes(Integer codigo) {
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

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getCarnetAplicador() {
		return carnetAplicador;
	}

	public void setCarnetAplicador(String carnetAplicador) {
		this.carnetAplicador = carnetAplicador;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getnExplotacionNacional() {
		return nExplotacionNacional;
	}

	public void setnExplotacionNacional(String nExplotacionNacional) {
		this.nExplotacionNacional = nExplotacionNacional;
	}

	public String getnExplotacionAutonomico() {
		return nExplotacionAutonomico;
	}

	public void setnExplotacionAutonomico(String nExplotacionAutonomico) {
		this.nExplotacionAutonomico = nExplotacionAutonomico;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getNifTitular() {
		return nifTitular;
	}

	public void setNifTitular(String nifTitular) {
		this.nifTitular = nifTitular;
	}

	public String getDireccionTitular() {
		return direccionTitular;
	}

	public void setDireccionTitular(String direccionTitular) {
		this.direccionTitular = direccionTitular;
	}

	public String getPoblacionTitular() {
		return poblacionTitular;
	}

	public void setPoblacionTitular(String poblacionTitular) {
		this.poblacionTitular = poblacionTitular;
	}

	public Integer getCodigoPostalTitular() {
		return codigoPostalTitular;
	}

	public void setCodigoPostalTitular(Integer codigoPostalTitular) {
		this.codigoPostalTitular = codigoPostalTitular;
	}

	public String getProvinciaTitular() {
		return provinciaTitular;
	}

	public void setProvinciaTitular(String provinciaTitular) {
		this.provinciaTitular = provinciaTitular;
	}

	public String getTipoRepresentacion() {
		return tipoRepresentacion;
	}

	public void setTipoRepresentacion(String tipoRepresentacion) {
		this.tipoRepresentacion = tipoRepresentacion;
	}

	public String getTelefonoTitular() {
		return telefonoTitular;
	}

	public void setTelefonoTitular(String telefonoTitular) {
		this.telefonoTitular = telefonoTitular;
	}

	public String getEmailTitular() {
		return emailTitular;
	}

	public void setEmailTitular(String emailTitular) {
		this.emailTitular = emailTitular;
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
		if (!(object instanceof Clientes)) {
			return false;
		}
		Clientes other = (Clientes) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Clientes[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.nombre + ";";
		resultado += this.direccion + ";";
		resultado += this.codigoPostal + ";";
		resultado += this.poblacion + ";";
		resultado += this.provincia + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}
