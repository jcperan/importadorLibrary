package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.UsosAutorizados;
import com.acisa.cultivos.utilidades.Configuracion;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjUsosAutorizados extends Persistencia {

	private UsosAutorizados usoAutorizado = new UsosAutorizados();

	public UsosAutorizados getUsoAutorizado() {
		return usoAutorizado;
	}

	public void setUsoAutorizado(UsosAutorizados usoAutorizado) {
		this.usoAutorizado = usoAutorizado;
	}

	public UsosAutorizados buscarUsos(int producto, int cultivo, int plaga) {

		UsosAutorizados resultado = null;
		em = entityManager();
		try {
			Query q = em.createQuery(
					"select u from UsosAutorizados u where u.producto = :producto and u.cultivo = :cultivo and u.plaga = :plaga ");
			q.setParameter("producto", producto);
			q.setParameter("cultivo", cultivo);
			q.setParameter("plaga", plaga);
			resultado = (UsosAutorizados) q.getSingleResult();
		} catch (Exception e) {
			// Rutinas.MuestraMensaje("CULTIVOS : Error al buscar Uso Autorizado", e);
		}

		return resultado;
	}

	public List<UsosAutorizados> getListaUsosAutorizadosHtml() {

		List<UsosAutorizados> resultado = new ArrayList<UsosAutorizados>();
		em = entityManager();
		try {
			/*
			 * Query p = em.createQuery("select p from Plantaciones p order by p.cultivo");
			 * List<Plantaciones> listaPlantaciones = p.getResultList(); Integer anterior =
			 * 0; resultado.clear(); for (Plantaciones plantacion : listaPlantaciones) { if
			 * (anterior.intValue() != plantacion.getCultivo().intValue() ) {
			 * anterior=plantacion.getCultivo(); Query q =
			 * em.createQuery("select u from UsosAutorizados u where u.cultivo = :cultivo");
			 * q.setParameter("cultivo", plantacion.getCultivo()); List<UsosAutorizados>
			 * listaUsos = q.getResultList(); resultado.addAll(listaUsos); } }
			 */
			// Query q = em.createQuery("select u from UsosAutorizados u ");

			String sql = "select u from UsosAutorizados u ";
			if (Configuracion.getInstance().getProperty("tablet").equals("si"))
				sql += "where u.productoBean.tablet = 1";
			Query q = em.createQuery(sql);
			resultado = q.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista html de Usos Autorizados", e);
		}

		return resultado;
	}

	public void GrabarUsoAutorizado(UsosAutorizados nuevoUso) {
		em = entityManager();
		try {
			em.getTransaction().begin();
			em.persist(nuevoUso);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error Grabacion Nuevo Uso Autorizado", e);
		} finally {
			em.close();
		}
	}

	public void ActualizarUsoAutorizado(UsosAutorizados actual) {
		em = entityManager();
		try {
			em.getTransaction().begin();
			UsosAutorizados anterior = em.find(UsosAutorizados.class, actual.getCodigo());
			anterior.setAutorizado(actual.getAutorizado());
			anterior.setCultivo(actual.getCultivo());
			anterior.setDosificacion(actual.getDosificacion());
			anterior.setDosis(actual.getDosis());
			anterior.setObservaciones(actual.getObservaciones());
			anterior.setPlaga(actual.getPlaga());
			anterior.setPlazoSeguridad(actual.getPlazoSeguridad());
			anterior.setProducto(actual.getProducto());
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error Actualizacion Uso Autorizado", e);
		} finally {
			em.close();
		}
	}

	public void ActualizarUsoAutorizado(int codigo, String observaciones) {
		em = entityManager();
		try {
			em.getTransaction().begin();
			UsosAutorizados anterior = em.find(UsosAutorizados.class, codigo);
			anterior.setObservaciones(observaciones);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error Actualizacion Observaciones en Uso Autorizado", e);
		} finally {
			em.close();
		}
	}

	public void EliminarUsos(int producto) {
		em = entityManager();
		try {
			em.getTransaction().begin();

			String sql = "delete from UsosAutorizados u where u.autorizado = 1 and u.producto = :producto ";
			Query q = em.createQuery(sql);
			q.setParameter("producto", producto);
			q.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error en Borrado de Usos Autorizados", e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

}
