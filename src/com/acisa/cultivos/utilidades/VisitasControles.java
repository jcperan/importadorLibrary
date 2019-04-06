package com.acisa.cultivos.utilidades;

public class VisitasControles {
	public VisitasControles(String pTexto) {
		String[] campos = pTexto.split(";");

		if (campos.length >= 3) {
			this.visita = Rutinas.Valor(campos[0]).intValue();
			this.plaga = Rutinas.Valor(campos[1].split("_")[1]).intValue();
			this.nivel = campos[2];
		}
	}

	private Integer visita;
	private Integer plaga;
	private String nivel;

	public Integer getVisita() {
		return visita;
	}

	public void setVisita(Integer visita) {
		this.visita = visita;
	}

	public Integer getPlaga() {
		return plaga;
	}

	public void setPlaga(Integer plaga) {
		this.plaga = plaga;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
}
