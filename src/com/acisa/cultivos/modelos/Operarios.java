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
@Table(name = "Operarios")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Operarios.findAll", query = "SELECT o FROM Operarios o") })
public class Operarios implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Codigo")
	private Integer codigo;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Clave")
	private String clave;
	@Column(name = "Nivel")
	private String nivel;
	@Column(name = "Login")
	private String login;

	@OneToMany(mappedBy = "operarioBean")
	private Set<Operaciones> operacion;

	public Set<Operaciones> getOperacion() {
		return operacion;
	}

	public void setOperacion(Set<Operaciones> operacion) {
		this.operacion = operacion;
	}

	public Operarios() {
	}

	public Operarios(Integer codigo) {
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
		if (!(object instanceof Operarios)) {
			return false;
		}
		Operarios other = (Operarios) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.Operarios[ codigo=" + codigo + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";

		resultado += this.codigo + ";";
		resultado += this.nombre + ";";
		resultado += this.clave + ";";
		resultado += this.nivel + ";";
		resultado += this.login + ";";
		resultado += "\n";

		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}
}
