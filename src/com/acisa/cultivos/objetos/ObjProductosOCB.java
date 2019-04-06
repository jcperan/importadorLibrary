package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.ProductosOcb;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjProductosOCB extends Persistencia {

	/*********************************************************************************************
	 * Lista de Consulta de Productos OCB
	 *********************************************************************************************/
	private List<ProductosOcb> listaProductosOCB = new ArrayList<ProductosOcb>();

	public void setListaProductosOCB(List<ProductosOcb> listaProductosOCB) {
		this.listaProductosOCB = listaProductosOCB;
	}

	public List<ProductosOcb> getListaProductosOCB() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT p FROM ProductosOcb p ";
			Query consulta = em.createQuery(sqlQuery);
			listaProductosOCB = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de listaProductosOCB", e);
		} finally {
			em.close();
		}
		return listaProductosOCB;
	}

}
