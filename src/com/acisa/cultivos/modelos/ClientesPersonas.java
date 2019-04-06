package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the clientespersonas database table.
 * 
 */
@Entity
public class ClientesPersonas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;
	private String asesor;
	private Integer codigoCliente;
	private String nif;
	private String nombre;
	private String NRopo;
	private String tipoCarneBasico;
	private String tipoCarneCualificado;
	private String tipoCarneFumig;
	private String tipoCarnePiloto;

	@ManyToOne
	@JoinColumn(name = "codigoCliente", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Clientes clienteBean;

	public Clientes getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(Clientes clienteBean) {
		this.clienteBean = clienteBean;
	}

	public ClientesPersonas() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getAsesor() {
		return this.asesor;
	}

	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}

	public Integer getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNRopo() {
		return this.NRopo;
	}

	public void setNRopo(String NRopo) {
		this.NRopo = NRopo;
	}

	public String getTipoCarneBasico() {
		return this.tipoCarneBasico;
	}

	public void setTipoCarneBasico(String tipoCarneBasico) {
		this.tipoCarneBasico = tipoCarneBasico;
	}

	public String getTipoCarneCualificado() {
		return this.tipoCarneCualificado;
	}

	public void setTipoCarneCualificado(String tipoCarneCualificado) {
		this.tipoCarneCualificado = tipoCarneCualificado;
	}

	public String getTipoCarneFumig() {
		return this.tipoCarneFumig;
	}

	public void setTipoCarneFumig(String tipoCarneFumig) {
		this.tipoCarneFumig = tipoCarneFumig;
	}

	public String getTipoCarnePiloto() {
		return this.tipoCarnePiloto;
	}

	public void setTipoCarnePiloto(String tipoCarnePiloto) {
		this.tipoCarnePiloto = tipoCarnePiloto;
	}

}