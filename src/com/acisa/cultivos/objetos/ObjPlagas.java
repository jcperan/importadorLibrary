package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.Plagas;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjPlagas extends Persistencia {

	private String txtBuscar = "";

	public String getTxtBuscar() {
		return txtBuscar;
	}

	public void setTxtBuscar(String txtBuscar) {
		this.txtBuscar = txtBuscar;
	}

	public Plagas buscarPlaga(String nombre) {

		Plagas resultado = null;
		em = entityManager();
		try {
			Query q = em.createQuery("select p from Plagas p where p.nombre = :nombre ");
			q.setParameter("nombre", nombre);
			resultado = (Plagas) q.getSingleResult();
		} catch (Exception e) {
			// Rutinas.MuestraMensaje("CULTIVOS : Error al buscar Plaga", e);
		}

		return resultado;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Plagas
	 *********************************************************************************************/
	private List<Plagas> consultaPlagas = new ArrayList<Plagas>();

	public void setConsultaPlagas(List<Plagas> consultaPlagas) {
		this.consultaPlagas = consultaPlagas;
	}

	@SuppressWarnings("unchecked")
	public List<Plagas> getConsultaPlagas() {
		em = entityManager();
		try {
			Query consulta = em.createQuery("select p from Plagas p where p.nombre like :nombre order by p.nombre");
			consulta.setParameter("nombre", "%" + txtBuscar + "%");
			consultaPlagas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Plagas", e);
		} finally {
			em.close();
		}
		return consultaPlagas;
	}

}
