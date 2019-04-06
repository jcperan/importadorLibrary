package com.acisa.cultivos.utilidades;

public class VisitasControlesDepredadores {
	public VisitasControlesDepredadores(String pTexto) {
		String[] campos = pTexto.split(";");

		if (campos.length >= 3) {
			this.visita = Rutinas.Valor(campos[0]).intValue();
			this.depredador = Rutinas.Valor(campos[1]).intValue();
			this.nivel = campos[2];
			this.porcentaje = Rutinas.Valor(campos[3]);
		}
	}

	private Integer visita;
	private Integer depredador;
	private String nivel;
	private Double porcentaje;

	public Integer getVisita() {
		return visita;
	}

	public void setVisita(Integer visita) {
		this.visita = visita;
	}

	public Integer getDepredador() {
		return depredador;
	}

	public void setDepredador(Integer depredador) {
		this.depredador = depredador;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
}
