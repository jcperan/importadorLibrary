package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.Productos;
import com.acisa.cultivos.utilidades.Configuracion;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjProductos extends Persistencia {

	private Productos producto = new Productos();

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	private String busqueda = "";

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	private Integer val(String cadena) {

		Integer resultado = 0;
		try {
			resultado = Integer.valueOf(cadena);
		} catch (Exception e) {

		}
		return resultado;

	}

	/*
	 * *****************************************************************************
	 * ************** Lista de consulta de datos de productos fitosanitarios
	 *******************************************************************************************/
	private List<Productos> listaProductos = new ArrayList<Productos>();

	public void setListaProductos(List<Productos> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<Productos> getListaProductos() {

		em = entityManager();
		try {
			String sql = "select p from Productos p where p.numeroRegistro = :codigo or p.nombre like :nombre";
			Query q = em.createQuery(sql);
			q.setParameter("codigo", val(busqueda));
			q.setParameter("nombre", "%" + busqueda + "%");
			listaProductos = q.getResultList();
		} catch (Exception e) {
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}

		return listaProductos;
	}

	/*
	 * *****************************************************************************
	 * ************** Lista de datos a exportar al tablet
	 *******************************************************************************************/
	private List<Productos> productosTablet = new ArrayList<Productos>();

	public void setProductosTablet(List<Productos> productosTablet) {
		this.productosTablet = productosTablet;
	}

	public List<Productos> getProductosTablet() {

		em = entityManager();
		try {
			String sql = "select p from Productos p ";
			if (Configuracion.getInstance().getProperty("tablet").equals("si"))
				sql += "where p.tablet = 1 or p.numeroRegistro < 0";
			Query q = em.createQuery(sql);
			this.productosTablet = q.getResultList();
		} catch (Exception e) {
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}

		return productosTablet;
	}

}
