package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.Climatologias;
import com.acisa.cultivos.modelos.Cultivos;
import com.acisa.cultivos.modelos.CultivosDepredadores;
import com.acisa.cultivos.modelos.CultivosPlagas;
import com.acisa.cultivos.modelos.CultivosPlagasPK;
import com.acisa.cultivos.modelos.Depredadores;
import com.acisa.cultivos.modelos.Equiposfumigacion;
import com.acisa.cultivos.modelos.Fases;
import com.acisa.cultivos.modelos.Muestreos;
import com.acisa.cultivos.modelos.Plagas;
import com.acisa.cultivos.modelos.RelacionCultivos;
import com.acisa.cultivos.modelos.Variedades;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjCultivos extends Persistencia {

	private String txtBuscar = "";

	public String getTxtBuscar() {
		return txtBuscar;
	}

	public void setTxtBuscar(String txtBuscar) {
		this.txtBuscar = txtBuscar;
	}

	public Cultivos buscarCultivos(String nombre) {

		Cultivos resultado = null;
		em = entityManager();
		try {
			Query q = em.createQuery("select c from Cultivos c where c.nombre = :nombre ");
			q.setParameter("nombre", nombre);
			resultado = (Cultivos) q.getSingleResult();
		} catch (Exception e) {
			// Rutinas.MuestraMensaje("CULTIVOS : Error al buscar Cultivo", e);
		}

		return resultado;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Cultivos
	 *********************************************************************************************/
	private List<Cultivos> consultaCultivos = new ArrayList<Cultivos>();

	public void setConsultaCultivos(List<Cultivos> consultaCultivos) {
		this.consultaCultivos = consultaCultivos;
	}

	@SuppressWarnings("unchecked")
	public List<Cultivos> getConsultaCultivos() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM Cultivos c where c.nombre like :nombre ORDER BY c.nombre");
			consulta.setParameter("nombre", "%" + txtBuscar + "%");
			consultaCultivos = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Cultivos", e);
		} finally {
			em.close();
		}

		return consultaCultivos;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Relacion Cultivos
	 *********************************************************************************************/
	private List<RelacionCultivos> consultaRelacionCultivos = new ArrayList<RelacionCultivos>();

	public void setConsultaRelacionCultivos(List<RelacionCultivos> consultaRelacionCultivos) {
		this.consultaRelacionCultivos = consultaRelacionCultivos;
	}

	@SuppressWarnings("unchecked")
	public List<RelacionCultivos> getConsultaRelacionCultivos() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM RelacionCultivos c");
			consultaRelacionCultivos = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista RelacionCultivos", e);
		} finally {
			em.close();
		}

		return consultaRelacionCultivos;
	}

	private CultivosPlagas cultivosPlaga = new CultivosPlagas();

	public CultivosPlagas getCultivosPlaga() {
		return cultivosPlaga;
	}

	public void setCultivosPlaga(CultivosPlagas cultivosPlaga) {
		this.cultivosPlaga = cultivosPlaga;
	}

	public boolean ObtenerCultivoPlaga(int cultivo, int plaga) {
		boolean resultado = false;
		em = entityManager();
		try {
			CultivosPlagasPK clave = new CultivosPlagasPK();
			clave.setCultivo(cultivo);
			clave.setPlaga(plaga);
			cultivosPlaga = em.find(CultivosPlagas.class, clave);
			if (cultivosPlaga != null) {
				resultado = true;
			}
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error lectura Cultivo/Plaga", e);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void GrabarCultivoPlaga(int cultivo, int plaga) {
		em = entityManager();
		try {
			em.getTransaction().begin();
			CultivosPlagasPK clave = new CultivosPlagasPK();
			clave.setCultivo(cultivo);
			clave.setPlaga(plaga);
			cultivosPlaga = new CultivosPlagas();
			cultivosPlaga.setCultivosPlagasPK(clave);
			em.persist(cultivosPlaga);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error Grabacion Cultivo/Plaga", e);
		} finally {
			em.close();
		}
	}

	public void GrabarCultivo(Cultivos nuevoCultivo) {
		em = entityManager();
		try {
			em.getTransaction().begin();
			em.persist(nuevoCultivo);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error Grabacion Nuevo Cultivo", e);
		} finally {
			em.close();
		}
	}

	public void GrabarPlaga(Plagas nuevaPlaga) {
		em = entityManager();
		try {
			em.getTransaction().begin();
			em.persist(nuevaPlaga);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error Grabacion Nueva Plaga", e);
		} finally {
			em.close();
		}
	}

	/*********************************************************************************************
	 * Lista de Consulta de Variedades
	 *********************************************************************************************/
	private List<Variedades> consultaVariedades = new ArrayList<Variedades>();

	public void setConsultaVariedades(List<Variedades> consultaVariedades) {
		this.consultaVariedades = consultaVariedades;
	}

	@SuppressWarnings("unchecked")
	public List<Variedades> getConsultaVariedades() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM Variedades c ");
			consultaVariedades = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Variedades", e);
		} finally {
			em.close();
		}

		return consultaVariedades;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Fases
	 *********************************************************************************************/
	private List<Fases> consultaFases = new ArrayList<Fases>();

	public void setConsultaFases(List<Fases> consultaFases) {
		this.consultaFases = consultaFases;
	}

	@SuppressWarnings("unchecked")
	public List<Fases> getConsultaFases() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM Fases c ");
			consultaFases = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Fases", e);
		} finally {
			em.close();
		}

		return consultaFases;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Muestreos
	 *********************************************************************************************/
	private List<Muestreos> consultaMuestreos = new ArrayList<Muestreos>();

	public void setConsultaMuestreos(List<Muestreos> consultaMuestreos) {
		this.consultaMuestreos = consultaMuestreos;
	}

	@SuppressWarnings("unchecked")
	public List<Muestreos> getConsultaMuestreos() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM Muestreos c ");
			consultaMuestreos = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Muestreos", e);
		} finally {
			em.close();
		}

		return consultaMuestreos;
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
			Query consulta = em.createQuery("SELECT c FROM Plagas c ");
			consultaPlagas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Plagas", e);
		} finally {
			em.close();
		}

		return consultaPlagas;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Depredadores
	 *********************************************************************************************/
	private List<Depredadores> consultaDepredadores = new ArrayList<Depredadores>();

	public void setConsultaDepredadores(List<Depredadores> consultaDepredadores) {
		this.consultaDepredadores = consultaDepredadores;
	}

	@SuppressWarnings("unchecked")
	public List<Depredadores> getConsultaDepredadores() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM Depredadores c ");
			consultaDepredadores = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Depredadores", e);
		} finally {
			em.close();
		}

		return consultaDepredadores;
	}

	/*********************************************************************************************
	 * Lista de Consulta de CultivosPlagas
	 *********************************************************************************************/
	private List<CultivosPlagas> consultaCultivosPlagas = new ArrayList<CultivosPlagas>();

	public void setConsultaCultivosPlagas(List<CultivosPlagas> consultaCultivosPlagas) {
		this.consultaCultivosPlagas = consultaCultivosPlagas;
	}

	@SuppressWarnings("unchecked")
	public List<CultivosPlagas> getConsultaCultivosPlagas() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM CultivosPlagas c ");
			consultaCultivosPlagas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de CultivosPlagas", e);
		} finally {
			em.close();
		}

		return consultaCultivosPlagas;
	}

	/*********************************************************************************************
	 * Lista de Consulta de CultivosDepredadores
	 *********************************************************************************************/
	private List<CultivosDepredadores> consultaCultivosDepredadores = new ArrayList<CultivosDepredadores>();

	public void setConsultaCultivosDepredadores(List<CultivosDepredadores> consultaCultivosDepredadores) {
		this.consultaCultivosDepredadores = consultaCultivosDepredadores;
	}

	@SuppressWarnings("unchecked")
	public List<CultivosDepredadores> getConsultaCultivosDepredadores() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM CultivosDepredadores c ");
			consultaCultivosDepredadores = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de CultivosDepredadores", e);
		} finally {
			em.close();
		}

		return consultaCultivosDepredadores;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Equipos Fumigacion
	 *********************************************************************************************/
	private List<Equiposfumigacion> consultaEquiposFumigacion = new ArrayList<Equiposfumigacion>();

	public void setConsultaEquiposFumigacion(List<Equiposfumigacion> consultaEquiposFumigacion) {
		this.consultaEquiposFumigacion = consultaEquiposFumigacion;
	}

	@SuppressWarnings("unchecked")
	public List<Equiposfumigacion> getConsultaEquiposFumigacion() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM Equiposfumigacion c ");
			consultaEquiposFumigacion = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Equiposfumigacion", e);
		} finally {
			em.close();
		}

		return consultaEquiposFumigacion;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Climatologias
	 *********************************************************************************************/
	private List<Climatologias> consultaClimatologias = new ArrayList<Climatologias>();

	public void setConsultaClimatologias(List<Climatologias> consultaClimatologias) {
		this.consultaClimatologias = consultaClimatologias;
	}

	@SuppressWarnings("unchecked")
	public List<Climatologias> getConsultaClimatologias() {

		em = entityManager();
		try {
			Query consulta = em.createQuery("SELECT c FROM Climatologias c ");
			consultaClimatologias = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Climatologias", e);
		} finally {
			em.close();
		}

		return consultaClimatologias;
	}

}
