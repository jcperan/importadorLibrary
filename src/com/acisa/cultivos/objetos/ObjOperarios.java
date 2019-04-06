package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.acisa.cultivos.modelos.Operarios;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjOperarios extends Persistencia {
	public Operarios Login(String pUsuario, String pPassword) {
		Operarios resultado = null;

		em = entityManager();
		try {
			Query q = em.createQuery("select p from Operarios p where p.login = :usuario and p.clave = :password ");
			q.setParameter("usuario", pUsuario);
			q.setParameter("password", pPassword);

			try {
				resultado = (Operarios) q.getSingleResult();
			} catch (NoResultException e) {
			}

		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al buscar Operario", e);
		}

		return resultado;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Operarios
	 *********************************************************************************************/
	private List<Operarios> consultaOperarios = new ArrayList<Operarios>();

	public void setConsultaOperarios(List<Operarios> consultaOperarios) {
		this.consultaOperarios = consultaOperarios;
	}

	@SuppressWarnings("unchecked")
	public List<Operarios> getConsultaOperarios() {
		em = entityManager();

		try {
			Query consulta = em.createQuery("SELECT c FROM Operarios c ORDER BY c.nombre");
			this.consultaOperarios = consulta.getResultList();

		} catch (Exception e) {
			Rutinas.MuestraMensaje("Operarios : Error al obtener lista de Operarios", e);
		} finally {
			em.close();
		}

		return consultaOperarios;
	}
}
