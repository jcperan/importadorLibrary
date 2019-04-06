package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.AbonosFertilizantes;
import com.acisa.cultivos.modelos.PlantillaAbonosFertilizantes;
import com.acisa.cultivos.modelos.PlantillaDatosAbonado;
import com.acisa.cultivos.modelos.PlantillaRegistroDatosAbonado;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjAbonosFertilizantes extends Persistencia {

	/*********************************************************************************************
	 * Lista de Consulta de Clientes
	 *********************************************************************************************/
	private List<AbonosFertilizantes> listaAbonosFertilizantes = new ArrayList<AbonosFertilizantes>();

	public void setListaAbonosFertilizantes(List<AbonosFertilizantes> listaAbonosFertilizantes) {
		this.listaAbonosFertilizantes = listaAbonosFertilizantes;
	}

	public List<AbonosFertilizantes> getListaAbonosFertilizantes() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT a FROM AbonosFertilizantes a ";
			Query consulta = em.createQuery(sqlQuery);
			listaAbonosFertilizantes = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de listaAbonosFertilizantes", e);
		} finally {
			em.close();
		}
		return listaAbonosFertilizantes;
	}

	/*********************************************************************************************
	 * Lista Plantilla abonado
	 *********************************************************************************************/
	private List<PlantillaRegistroDatosAbonado> listaPlantillasAbonado = new ArrayList<PlantillaRegistroDatosAbonado>();

	public List<PlantillaRegistroDatosAbonado> getListaPlantillasAbonado() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT a FROM PlantillaRegistroDatosAbonado a ";
			Query consulta = em.createQuery(sqlQuery);
			listaPlantillasAbonado = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de PlantillaRegistroDatosAbonado", e);
		} finally {
			em.close();
		}
		return listaPlantillasAbonado;
	}

	public void setListaPlantillasAbonado(List<PlantillaRegistroDatosAbonado> listaPlantillasAbonado) {
		this.listaPlantillasAbonado = listaPlantillasAbonado;
	}

	/*********************************************************************************************
	 * Lista Plantilla detalle abonado
	 *********************************************************************************************/
	private List<PlantillaDatosAbonado> listaPlantillasDetalleAbonado = new ArrayList<PlantillaDatosAbonado>();

	public List<PlantillaDatosAbonado> getListaPlantillasDetalleAbonado() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT a FROM PlantillaDatosAbonado a ";
			Query consulta = em.createQuery(sqlQuery);
			listaPlantillasDetalleAbonado = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de listaPlantillasAbonado", e);
		} finally {
			em.close();
		}
		return listaPlantillasDetalleAbonado;
	}

	public void setListaPlantillasDetalleAbonado(List<PlantillaDatosAbonado> listaPlantillasAbonado) {
		this.listaPlantillasDetalleAbonado = listaPlantillasAbonado;
	}

	/*********************************************************************************************
	 * Lista Plantilla productos abonado
	 *********************************************************************************************/
	private List<PlantillaAbonosFertilizantes> listaPlantillasProductos = new ArrayList<PlantillaAbonosFertilizantes>();

	public List<PlantillaAbonosFertilizantes> getListaPlantillasProductos() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT a FROM PlantillaAbonosFertilizantes a ";
			Query consulta = em.createQuery(sqlQuery);
			listaPlantillasProductos = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de listaPlantillasProductos", e);
		} finally {
			em.close();
		}
		return listaPlantillasProductos;
	}

	public void setListaPlantillasProductos(List<PlantillaAbonosFertilizantes> listaPlantillasProductos) {
		this.listaPlantillasProductos = listaPlantillasProductos;
	}

}
