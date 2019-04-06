/**
 * 
 */
package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.acisa.cultivos.utilidades.Configuracion;
import com.acisa.cultivos.utilidades.Rutinas;

public class Persistencia {

	public Persistencia() {

	}

	/*********************************************************************************************
	 * Objetos de la Persistencia de Datos
	 *********************************************************************************************/
	@PersistenceUnit
	protected EntityManagerFactory emf;

	@PersistenceContext
	protected EntityManager em;

	protected EntityManager entityManager() {

		if (emf == null) {
			Map<String, String> properties = new HashMap<String, String>();

			properties.put("javax.persistence.jdbc.driver", Configuracion.getInstance().getProperty("jdbcClass"));
			properties.put("javax.persistence.jdbc.url", Configuracion.getInstance().getProperty("jdbcUrl"));
			properties.put("javax.persistence.jdbc.user", Configuracion.getInstance().getProperty("jdbcUser"));
			properties.put("javax.persistence.jdbc.password", Configuracion.getInstance().getProperty("jdbcPassword"));
			properties.put("eclipselink.cache.shared.default", "false");
			properties.put("eclipselink.cache.size.default", "0");
			properties.put("eclipselink.cache.type.default", "None");

			emf = Persistence.createEntityManagerFactory("webCultivosPU", properties);
			// emf = Persistence.createEntityManagerFactory("webCultivosPU");
		}

		return emf.createEntityManager();
	}

	protected Object Leer(Class<?> pClase, Object pClave) {
		Object resultado = null;

		try {
			em = entityManager();

			resultado = em.find(pClase, pClave);

		} catch (Exception e) {
			Rutinas.MuestraMensaje("ERROR al Leer.", e);
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}

		return resultado;
	}

	protected boolean Insertar(Object pRegistro) {
		return this.Grabar(pRegistro, false);
	}

	protected boolean Actualizar(Object pRegistro) {
		return this.Grabar(pRegistro, true);
	}

	private boolean Grabar(Object pRegistro, boolean pActualizar) {
		boolean resultado = false;

		em = entityManager();

		try {
			em.getTransaction().begin();

			if (pActualizar) {
				em.merge(pRegistro);
			} else {
				em.persist(pRegistro);
			}

			em.getTransaction().commit();

			resultado = true;

		} catch (Exception e) {
			Rutinas.MuestraMensaje("ERROR al Grabar", e);
			Rutinas.LogExcepcion(e);
			resultado = false;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			if (em.isOpen()) {
				em.close();
			}
		}

		return resultado;
	}

	protected boolean Borrar(Object pRegistro) {
		boolean resultado = false;

		em = entityManager();

		try {
			em.getTransaction().begin();
			em.remove(pRegistro);
			em.getTransaction().commit();
			resultado = true;
		} catch (Exception e) {
			Rutinas.MuestraMensaje("ERROR al Borrar", e);
			Rutinas.LogExcepcion(e);
			resultado = false;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			if (em.isOpen()) {
				em.close();
			}
		}
		return resultado;

	}

	protected List<?> EjecutarSQL(String pSql) {
		List<?> resultado = null;

		try {
			em = entityManager();

			Query q = em.createQuery(pSql);

			resultado = q.getResultList();

		} catch (Exception e) {
			Rutinas.MuestraMensaje("ERROR al EjecutarSQL.", e);
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}

		return resultado;
	}

	protected List<?> EjecutarSQL(String pSql, ArrayList<Object[]> pParametros) {
		List<?> resultado = null;

		try {
			em = entityManager();

			Query q = em.createQuery(pSql);

			// Añadir parametros
			for (Object[] parametro : pParametros) {
				q.setParameter(parametro[0].toString(), parametro[1]);
			}

			resultado = q.getResultList();

		} catch (Exception e) {
			Rutinas.MuestraMensaje("ERROR al EjecutarSQL.", e);
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}

		return resultado;
	}

}
