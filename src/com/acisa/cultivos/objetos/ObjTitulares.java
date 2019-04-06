package com.acisa.cultivos.objetos;

import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.Titulares;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjTitulares extends Persistencia {

	public Titulares buscarTitular(String nombre) {

		Titulares resultado = null;
		em = entityManager();
		try {
			Query q = em.createQuery("select p from Titulares p where p.nombre like :nombre ");
			q.setParameter("nombre", nombre);
                        q.setMaxResults(1);
			resultado = (Titulares) q.getSingleResult();
		} catch (Exception e) {
			// Rutinas.MuestraMensaje("CULTIVOS : Error al buscar Titular", e);
		}

		return resultado;
	}

	public void GrabarTitular(Titulares nuevoTitular) {
		em = entityManager();
		try {
			em.getTransaction().begin();
			em.persist(nuevoTitular);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error Grabaci�n Nuevo Titular", e);
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Titulares> getConsultaTitulares() {

		List<Titulares> resultado = null;
		em = entityManager();
		try {
			Query q = em.createQuery("select p from Titulares p ");
			resultado = q.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al buscar lista Titulares", e);
		}

		return resultado;
	}

}
