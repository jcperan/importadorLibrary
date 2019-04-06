package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.acisa.cultivos.utilidades.Rutinas;

import java.util.Date;

/**
 * The persistent class for the ProximasVisitas database table.
 * 
 */
@Entity
@Table(name = "ProximasVisitas")
public class ProximasVisitas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Integer codigo;

	private int operario;
	private int estado;

	@Temporal(TemporalType.DATE)
	private Date fechaAplicacion;

	@Temporal(TemporalType.DATE)
	private Date fechaSiguiente;

	@Temporal(TemporalType.DATE)
	private Date fechaVisita;

	private int cliente;
	private int finca;
	private int sector;
	private int plantacion;

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

	@ManyToOne
	@JoinColumn(name = "sector", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Plantaciones plantacionBean;

	public Plantaciones getPlantacionBean() {
		return plantacionBean;
	}

	public void setPlantacionBean(Plantaciones plantacionBean) {
		this.plantacionBean = plantacionBean;
	}

	public ProximasVisitas() {
	}

	public ProximasVisitas(String registro) {
		String[] campos = registro.split(";");

		if (campos.length >= 12) {
			Date fecha;

			this.codigo = Rutinas.Valor(campos[11]).intValue();
			this.operario = Rutinas.Valor(campos[12]).intValue();
			this.cliente = Rutinas.Valor(campos[3]).intValue();
			this.finca = Rutinas.Valor(campos[5]).intValue();
			this.sector = Rutinas.Valor(campos[7]).intValue();
			this.plantacion = Rutinas.Valor(campos[9]).intValue();
			fecha = Rutinas.Fecha(campos[0], "yyyy-MM-dd");
			if (fecha != null) {
				this.fechaVisita = fecha;
			}
			fecha = Rutinas.Fecha(campos[2], "yyyy-MM-dd");
			if (fecha != null) {
				this.fechaAplicacion = fecha;
			}
			fecha = Rutinas.Fecha(campos[1], "yyyy-MM-dd");
			if (fecha != null) {
				this.fechaSiguiente = fecha;
			}
			this.estado = Rutinas.Valor(campos[10]).intValue();

		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCliente() {
		return this.cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaAplicacion() {
		return this.fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Date getFechaSiguiente() {
		return this.fechaSiguiente;
	}

	public void setFechaSiguiente(Date fechaSiguiente) {
		this.fechaSiguiente = fechaSiguiente;
	}

	public Date getFechaVisita() {
		return this.fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	public int getFinca() {
		return this.finca;
	}

	public void setFinca(int finca) {
		this.finca = finca;
	}

	public int getOperario() {
		return this.operario;
	}

	public void setOperario(int operario) {
		this.operario = operario;
	}

	public int getPlantacion() {
		return this.plantacion;
	}

	public void setPlantacion(int plantacion) {
		this.plantacion = plantacion;
	}

	public int getSector() {
		return this.sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
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
		if (!(object instanceof ProximasVisitas)) {
			return false;
		}
		ProximasVisitas other = (ProximasVisitas) object;
		if ((this.codigo == null && other.codigo != null)
				|| (this.codigo != null && !this.codigo.equals(other.codigo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.acisa.cultivos.modelos.ProximasVisitas[ id=" + id + " ]";
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.operario + ";";
		resultado += this.cliente + ";";
		if (this.clienteBean == null)
			resultado += ";";
		else
			resultado += this.getClienteBean().getNombre() + ";";
		resultado += this.finca + ";";
		if (this.fincaBean == null)
			resultado += ";";
		else
			resultado += this.getFincaBean().getNombre() + ";";
		resultado += this.sector + ";";
		if (this.sectorBean == null)
			resultado += ";";
		else
			resultado += this.getSectorBean().getNombre() + ";";
		resultado += this.plantacion + ";";
		resultado += Rutinas.FORMATO_FECHA(this.fechaVisita, "yyyy-MM-dd") + ";";
		if (Rutinas.FORMATO_FECHA(this.fechaAplicacion, "yyyy-MM-dd").equals("0002-11-30")) {
			resultado += "0000-00-00" + ";";
		} else {
			resultado += Rutinas.FORMATO_FECHA(this.fechaAplicacion, "yyyy-MM-dd") + ";";
		}
		resultado += Rutinas.FORMATO_FECHA(this.fechaSiguiente, "yyyy-MM-dd") + ";";
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}