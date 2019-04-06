package com.acisa.cultivos.objetos;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.Operaciones;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjOperaciones extends Persistencia {

	//// Mantenimiento

	public Operaciones Leer(Integer pClave) {
		return (Operaciones) super.Leer(Operaciones.class, pClave);
	}

	public boolean Actualizar(Operaciones pRegistro) {
		return super.Actualizar(pRegistro);
	}

	//// Funciones

	public Operaciones LeerOperacion(Integer pCodigo) {
		Operaciones resultado = null;

		em = entityManager();

		try {
			String sqlQuery = "SELECT t FROM Operaciones t WHERE t.codigo = :codigo ";

			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("codigo", pCodigo);
			consulta.setMaxResults(1);

			resultado = (Operaciones) consulta.getSingleResult();

		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al leer operacion", e);
		} finally {
			em.close();
		}

		return resultado;
	}

	public void clear() {
		super.emf = null;
	}

}
