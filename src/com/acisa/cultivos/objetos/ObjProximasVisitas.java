package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.ProximasVisitas;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjProximasVisitas extends Persistencia {

	private ProximasVisitas proximaVisita = new ProximasVisitas();

	public ProximasVisitas getProximaVisita() {
		return proximaVisita;
	}

	public void setProximaVisita(ProximasVisitas proximaVisita) {
		this.proximaVisita = proximaVisita;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Proximas Visitas
	 *********************************************************************************************/
	private List<ProximasVisitas> consultaProximasVisitas = new ArrayList<ProximasVisitas>();

	public void setConsultaProximasVisitas(List<ProximasVisitas> consultaProximasVisitas) {
		this.consultaProximasVisitas = consultaProximasVisitas;
	}

	public List<ProximasVisitas> getConsultaProximasVisitas() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT p FROM ProximasVisitas p WHERE p.estado = 0 ";
			Query consulta = em.createQuery(sqlQuery);
			consultaProximasVisitas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Proximas Visitas", e);
		} finally {
			em.close();
		}
		return consultaProximasVisitas;
	}

	public Integer Buscar(Integer Id, Integer operario) {

		Integer resultado = 0;

		em = entityManager();
		try {
			String sqlQuery = "SELECT p FROM ProximasVisitas p WHERE p.codigo = :codigo AND p.operario = :operario ";
			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("codigo", Id);
			consulta.setParameter("operario", operario);
			List<ProximasVisitas> proximas = consulta.getResultList();
			if (!proximas.isEmpty())
				resultado = proximas.get(0).getId();
		} catch (Exception e) {
			resultado = 0;
		} finally {
			em.close();
		}

		return resultado;
	}

	public void Grabar(ProximasVisitas proxima) {

		super.Insertar(proxima);

	}

	public void Borrar(Integer Id) {

		ProximasVisitas pRegistro = (ProximasVisitas) super.Leer(ProximasVisitas.class, Id);
		em = entityManager();

		try {
			em.getTransaction().begin();
			ProximasVisitas aborrar = em.merge(pRegistro);
			em.remove(aborrar);
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("ERROR al Borrar", e);
			Rutinas.LogExcepcion(e);
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			if (em.isOpen()) {
				em.close();
			}
		}

	}

	public boolean esTerminada(Integer Id, Integer operario) {

		boolean resultado = false;

		em = entityManager();
		try {
			String sqlQuery = "SELECT p FROM ProximasVisitas p WHERE p.codigo = :codigo AND p.operario = :operario ";
			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("codigo", Id);
			consulta.setParameter("operario", operario);
			List<ProximasVisitas> proximas = consulta.getResultList();
			if (!proximas.isEmpty())
				if (proximas.get(0).getEstado() != 0)
					resultado = true;
		} catch (Exception e) {
			resultado = false;
		} finally {
			em.close();
		}

		return resultado;
	}

	public void Finalizar(Integer Id) {

		ProximasVisitas pRegistro = (ProximasVisitas) super.Leer(ProximasVisitas.class, Id);
		em = entityManager();

		try {
			em.getTransaction().begin();
			pRegistro.setEstado(1);
			em.merge(pRegistro);
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("ERROR al Finalizar Cita", e);
			Rutinas.LogExcepcion(e);
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			if (em.isOpen()) {
				em.close();
			}
		}

	}

}
