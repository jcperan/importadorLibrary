package com.acisa.cultivos.utilidades;

public class VisitasProductos {

	public VisitasProductos(String pTexto) {
		String[] campos = pTexto.split(";");

		if (campos.length >= 5) {
			this.visita = Rutinas.Valor(campos[0]).intValue();
			this.producto = Rutinas.Valor(campos[1]).intValue();
			this.dosis = Rutinas.Valor(campos[2]);
			this.plazoSeguridad = Rutinas.Valor(campos[3]).intValue();
			this.intervalo = Rutinas.Valor(campos[4]).intValue();
			this.tipoAplicacion = campos[5];
			this.dosisDeclarada = Rutinas.Valor(campos[6]);
		}
	}

	private Integer visita;
	private Integer producto;
	private Double dosis;
	private Integer plazoSeguridad;
	private Integer intervalo;
	private Double dosisDeclarada;
	private String tipoAplicacion;

	public Integer getVisita() {
		return visita;
	}

	public void setVisita(Integer visita) {
		this.visita = visita;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Double getDosis() {
		return dosis;
	}

	public void setDosis(Double dosis) {
		this.dosis = dosis;
	}

	public Integer getPlazoSeguridad() {
		return plazoSeguridad;
	}

	public void setPlazoSeguridad(Integer plazoSeguridad) {
		this.plazoSeguridad = plazoSeguridad;
	}

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public Double getDosisDeclarada() {
		return dosisDeclarada;
	}

	public void setDosisDeclarada(Double dosisDeclarada) {
		this.dosisDeclarada = dosisDeclarada;
	}

	public String getTipoAplicacion() {
		return tipoAplicacion;
	}

	public void setTipoAplicacion(String tipoAplicacion) {
		this.tipoAplicacion = tipoAplicacion;
	}
}
