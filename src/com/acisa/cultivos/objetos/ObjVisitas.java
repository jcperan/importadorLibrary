package com.acisa.cultivos.objetos;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.acisa.cultivos.modelos.Controlesdepredadores;
import com.acisa.cultivos.modelos.Controlesplagas;
import com.acisa.cultivos.modelos.DatosAbonado;
import com.acisa.cultivos.modelos.Operaciones;
import com.acisa.cultivos.modelos.Operacionesproductos;
import com.acisa.cultivos.modelos.RegistroDatosAbonado;
import com.acisa.cultivos.modelos.Tratamientos;
import com.acisa.cultivos.modelos.Tratamientosproductos;
import com.acisa.cultivos.modelos.Visitas;
import com.acisa.cultivos.modelos.VisitasAbonosFertilizante;
import com.acisa.cultivos.modelos.VisitasProductosOcb;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjVisitas extends Persistencia {

	private Visitas visitas = new Visitas();

	public Visitas getVisitas() {
		return visitas;
	}

	public void setVisitas(Visitas visitas) {
		this.visitas = visitas;
	}

	/**
	 * Graba los datos de una nueva visita
	 * 
	 * @param nuevaVisita
	 * @return
	 */
	public boolean grabarVisita(Visitas nuevaVisita) {
		return super.Insertar(nuevaVisita);
	}

	/**
	 * Obtener el numero de la �ltima visita grabada
	 * 
	 * @return
	 */
	public Integer ultimaVisita() {
		Integer resultado = 0;
		try {
			em = entityManager();
			resultado = (Integer) em.createQuery("select max(u.codigo) from Visitas u").getSingleResult();
		} catch (Exception e) {
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Graba los datos de Control/Paga efectuado
	 * 
	 * @param nuevoControlPlaga
	 * @return
	 */
	public boolean grabarControlPlaga(Controlesplagas nuevoControlPlaga) {
		return super.Insertar(nuevoControlPlaga);
	}

	/**
	 * Graba los datos de Control/Depredador efectuado
	 * 
	 * @param nuevoControlDepredador
	 * @return
	 */
	public boolean grabarControlDepredador(Controlesdepredadores nuevoControlDepredador) {
		return super.Insertar(nuevoControlDepredador);
	}

	/**
	 * Grabar los datos del tratamiento efectuado
	 * 
	 * @param nuevoTratamiento
	 * @return
	 */
	public boolean grabarTratamiento(Tratamientos nuevoTratamiento) {
		return super.Insertar(nuevoTratamiento);
	}

	/**
	 * Grabar los datos correspondientes a los productos de un tratamiento
	 * 
	 * @param nuevoTratamientosProductos
	 * @return
	 */
	public boolean grabarTratamientoProductos(Tratamientosproductos nuevoTratamientosProductos) {
		return super.Insertar(nuevoTratamientosProductos);
	}

	/**
	 * Obtener el numero de la �ltimo Tratamiento
	 * 
	 * @return
	 */
	public Integer ultimoTratamiento() {
		Integer resultado = 0;
		try {
			em = entityManager();
			resultado = (Integer) em.createQuery("select max(u.codigo) from Tratamientos u").getSingleResult();
		} catch (Exception e) {
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Grabar nueva operacion
	 * 
	 * @param nuevaOperacion
	 * @return
	 */
	public boolean grabarOperacion(Operaciones nuevaOperacion) {
		return super.Insertar(nuevaOperacion);
	}

	/**
	 * Grabar los datos correspondientes a los productos de un tratamiento
	 * 
	 * @param nuevoTratamientosProductos
	 * @return
	 */
	public boolean grabarOperacionProductos(Operacionesproductos nuevoOperacionProductos) {
		return super.Insertar(nuevoOperacionProductos);
	}

	/**
	 * Grabar los datos correspondientes a los OCB de un tratamiento
	 * 
	 * @param nuevoRegistro
	 * @return
	 */
	public boolean grabarProductosOCB(VisitasProductosOcb nuevoRegistro) {
		return super.Insertar(nuevoRegistro);
	}

	/**
	 * Grabar los datos correspondientes a los Abonos de un tratamiento
	 * 
	 * @param nuevoRegistro
	 * @return
	 */
	public boolean grabarAbonado(DatosAbonado nuevoRegistro) {
		return super.Insertar(nuevoRegistro);
	}

	/**
	 * Obtener el numero de la �ltima operacion grabada
	 * 
	 * @return
	 */
	public Integer ultimaAbonado() {
		Integer resultado = 0;
		try {
			em = entityManager();
			resultado = (Integer) em.createQuery("select max(u.codigoAbonado) from DatosAbonado u").getSingleResult();
		} catch (Exception e) {
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Grabar los datos correspondientes a los Abonos de un tratamiento
	 * 
	 * @param nuevoRegistro
	 * @return
	 */
	public boolean grabarAbonos(VisitasAbonosFertilizante nuevoRegistro) {
		return super.Insertar(nuevoRegistro);
	}

	/**
	 * Obtener el numero de la �ltima operacion grabada
	 * 
	 * @return
	 */
	public Integer ultimaOperacion() {
		Integer resultado = 0;
		try {
			em = entityManager();
			resultado = (Integer) em.createQuery("select max(u.codigo) from Operaciones u").getSingleResult();
		} catch (Exception e) {
			Rutinas.LogExcepcion(e);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void flush() {
		super.emf = null;
	}

	public int GeneraRegistroDatosAbonado(Integer numeroVisita, Integer numeroRegistro, String aplicacionTipo,
			String aplicacionTanque, String aplicacionRiego) {

		int resultado = 0;
		try {
			em = entityManager();
			// Graba un nuevo registro
			// -----------------------------------------------------------
			String sql = "select r.codigoRegistroAbonado from RegistroDatosAbonado r ";
			sql += "where r.codigoAbonado = :numeroRegistro   ";
			sql += "and   r.aplicacion    = :aplicacionTipo   ";
			sql += "and   r.equipo        = :aplicacionTanque ";
			sql += "and   r.riego         = :aplicacionRiego  ";
			Query query = em.createQuery(sql);
			query.setParameter("numeroRegistro", numeroRegistro);
			query.setParameter("aplicacionTipo", aplicacionTipo);
			query.setParameter("aplicacionTanque", aplicacionTanque);
			query.setParameter("aplicacionRiego", aplicacionRiego);
			resultado = (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			em.getTransaction().begin();
			RegistroDatosAbonado nuevoRegistro = new RegistroDatosAbonado();
			nuevoRegistro.setCodigoAbonado(numeroRegistro);
			nuevoRegistro.setAplicacion(aplicacionTipo);
			nuevoRegistro.setEquipo(aplicacionTanque);
			nuevoRegistro.setRiego(aplicacionRiego);
			em.persist(nuevoRegistro);
			em.getTransaction().commit();
			resultado = GeneraRegistroDatosAbonado(numeroVisita, numeroRegistro, aplicacionTipo, aplicacionTanque,
					aplicacionRiego);
		} catch (Exception e) {
			Rutinas.LogExcepcion(e);
		} finally {
			if (em.isOpen())
				em.close();
		}

		return resultado;
	}

}
