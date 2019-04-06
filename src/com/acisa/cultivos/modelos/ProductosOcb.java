package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the ProductosOcb database table.
 * 
 */
@Entity
@Table(name = "ProductosOcb")
@NamedQuery(name = "ProductosOcb.findAll", query = "SELECT p FROM ProductosOcb p")
public class ProductosOcb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;
	private int depredador;
	private int fabricanteOCB;

	@Temporal(TemporalType.DATE)
	private Date fechaEntradaComunicacion;

	@Temporal(TemporalType.DATE)
	private Date fechaInscripcionRegistro;

	private int instalacionProduccion;

	private String nombre;
	private String numeroRegistro;

	private int responsableComercializacion;

	private int responsableComunicacion;

	@ManyToOne
	@JoinColumn(name = "fabricanteOCB", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private FabricantesOCB fabricanteBean;

	public FabricantesOCB getFabricanteBean() {
		return fabricanteBean;
	}

	public void setFabricanteBean(FabricantesOCB fabricanteBean) {
		this.fabricanteBean = fabricanteBean;
	}

	@ManyToOne
	@JoinColumn(name = "depredador", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
	private Depredadores depredadoresBean;

	public Depredadores getDepredadoresBean() {
		return depredadoresBean;
	}

	public void setDepredadoresBean(Depredadores depredadoresBean) {
		this.depredadoresBean = depredadoresBean;
	}

	public ProductosOcb() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDepredador() {
		return this.depredador;
	}

	public void setDepredador(int depredador) {
		this.depredador = depredador;
	}

	public int getFabricanteOCB() {
		return this.fabricanteOCB;
	}

	public void setFabricanteOCB(int fabricanteOCB) {
		this.fabricanteOCB = fabricanteOCB;
	}

	public Date getFechaEntradaComunicacion() {
		return this.fechaEntradaComunicacion;
	}

	public void setFechaEntradaComunicacion(Date fechaEntradaComunicacion) {
		this.fechaEntradaComunicacion = fechaEntradaComunicacion;
	}

	public Date getFechaInscripcionRegistro() {
		return this.fechaInscripcionRegistro;
	}

	public void setFechaInscripcionRegistro(Date fechaInscripcionRegistro) {
		this.fechaInscripcionRegistro = fechaInscripcionRegistro;
	}

	public int getInstalacionProduccion() {
		return this.instalacionProduccion;
	}

	public void setInstalacionProduccion(int instalacionProduccion) {
		this.instalacionProduccion = instalacionProduccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroRegistro() {
		return this.numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public int getResponsableComercializacion() {
		return this.responsableComercializacion;
	}

	public void setResponsableComercializacion(int responsableComercializacion) {
		this.responsableComercializacion = responsableComercializacion;
	}

	public int getResponsableComunicacion() {
		return this.responsableComunicacion;
	}

	public void setResponsableComunicacion(int responsableComunicacion) {
		this.responsableComunicacion = responsableComunicacion;
	}

	@Transient
	private String toWebDB = "";

	public String getToWebDB() {
		String resultado = "";
		resultado += this.codigo + ";";
		resultado += this.numeroRegistro + ";";
		resultado += this.nombre + ";";
		if (this.getDepredadoresBean() == null) {
			resultado += ";";
		} else {
			resultado += this.getDepredadoresBean().getNombre() + ";";
		}
		if (this.getFabricanteBean() == null) {
			resultado += ";";
		} else {
			resultado += this.getFabricanteBean().getNombre() + ";";
		}
		resultado += "\n";
		return resultado;
	}

	public void setToWebDB(String toWebDB) {
		this.toWebDB = toWebDB;
	}

}