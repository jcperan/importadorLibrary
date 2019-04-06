package com.acisa.cultivos.utilidades;

import java.util.Date;

public class VisitasDatos {

	public VisitasDatos(String pTexto) {
		String[] campos = pTexto.split(";");

		if (campos.length >= 19) {

			this.visita = Rutinas.Valor(campos[0]).intValue();
			this.plantacion = Rutinas.Valor(campos[1]).intValue();

			Date fecha = Rutinas.Fecha(campos[2], "yyyy-MM-dd");
			if (fecha != null) {
				this.fecha = fecha;
			}

			this.diasSiguiente = Rutinas.Valor(campos[3]).intValue();

			Date fechaSiguiente = Rutinas.Fecha(campos[4], "yyyy-MM-dd");
			if (fechaSiguiente != null) {
				this.fechaSiguiente = fechaSiguiente;
			}

			this.fase = Rutinas.Valor(campos[5]).intValue();
			this.muestreo = Rutinas.Valor(campos[6]).intValue();
			this.operario = Rutinas.Valor(campos[7]).intValue();

			Date aplicacion = Rutinas.Fecha(campos[8], "yyyy-MM-dd");
			if (aplicacion != null) {
				this.aplicacion = aplicacion;
			}

			this.equipoFumigacion = Rutinas.Valor(campos[9]).intValue();
			this.caldo = Rutinas.Valor(campos[10]);
			this.numeroEquipos = Rutinas.Valor(campos[11]);
			this.velocidad = Rutinas.Valor(campos[12]);
			this.presion = Rutinas.Valor(campos[13]);
			this.tipoBoquilla = campos[14];
			this.climatologia = Rutinas.Valor(campos[15]).intValue();
			this.observaciones = campos[16];
			this.encargado = campos[17];
			this.aplicador = campos[18];
		}
	}

	private Integer visita;
	private Integer plantacion;
	private Date fecha;
	private Integer diasSiguiente;
	private Date fechaSiguiente;
	private Integer fase;
	private Integer muestreo;
	private Integer operario;
	private Date aplicacion;
	private Integer equipoFumigacion;
	private Double caldo;
	private Double numeroEquipos;
	private Double velocidad;
	private Double presion;
	private String tipoBoquilla;
	private Integer climatologia;
	private String observaciones;
	private String encargado;
	private String aplicador;

	public Integer getVisita() {
		return visita;
	}

	public void setVisita(Integer visita) {
		this.visita = visita;
	}

	public Integer getPlantacion() {
		return plantacion;
	}

	public void setPlantacion(Integer plantacion) {
		this.plantacion = plantacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getDiasSiguiente() {
		return diasSiguiente;
	}

	public void setDiasSiguiente(Integer diasSiguiente) {
		this.diasSiguiente = diasSiguiente;
	}

	public Date getFechaSiguiente() {
		return fechaSiguiente;
	}

	public void setFechaSiguiente(Date fechaSiguiente) {
		this.fechaSiguiente = fechaSiguiente;
	}

	public Integer getFase() {
		return fase;
	}

	public void setFase(Integer fase) {
		this.fase = fase;
	}

	public Integer getMuestreo() {
		return muestreo;
	}

	public void setMuestreo(Integer muestreo) {
		this.muestreo = muestreo;
	}

	public Integer getOperario() {
		return operario;
	}

	public void setOperario(Integer operario) {
		this.operario = operario;
	}

	public Date getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Date aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Integer getEquipoFumigacion() {
		return equipoFumigacion;
	}

	public void setEquipoFumigacion(Integer equipoFumigacion) {
		this.equipoFumigacion = equipoFumigacion;
	}

	public Double getCaldo() {
		return caldo;
	}

	public void setCaldo(Double caldo) {
		this.caldo = caldo;
	}

	public Double getNumeroEquipos() {
		return numeroEquipos;
	}

	public void setNumeroEquipos(Double numeroEquipos) {
		this.numeroEquipos = numeroEquipos;
	}

	public Double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Double velocidad) {
		this.velocidad = velocidad;
	}

	public Double getPresion() {
		return presion;
	}

	public void setPresion(Double presion) {
		this.presion = presion;
	}

	public String getTipoBoquilla() {
		return tipoBoquilla;
	}

	public void setTipoBoquilla(String tipoBoquilla) {
		this.tipoBoquilla = tipoBoquilla;
	}

	public Integer getClimatologia() {
		return climatologia;
	}

	public void setClimatologia(Integer climatologia) {
		this.climatologia = climatologia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	public String getAplicador() {
		return aplicador;
	}

	public void setAplicador(String aplicador) {
		this.aplicador = aplicador;
	}
}
