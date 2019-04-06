package com.acisa.cultivos.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.Clientes;
import com.acisa.cultivos.modelos.ClientesPersonas;
import com.acisa.cultivos.modelos.Controlesdepredadores;
import com.acisa.cultivos.modelos.Controlesplagas;
import com.acisa.cultivos.modelos.Fincas;
import com.acisa.cultivos.modelos.Operaciones;
import com.acisa.cultivos.modelos.Operacionesproductos;
import com.acisa.cultivos.modelos.Plantaciones;
import com.acisa.cultivos.modelos.Sectores;
import com.acisa.cultivos.modelos.Tratamientos;
import com.acisa.cultivos.modelos.Tratamientosproductos;
import com.acisa.cultivos.modelos.Visitas;
import com.acisa.cultivos.modelos.VisitasAbonosFertilizante;
import com.acisa.cultivos.modelos.VisitasProductosOcb;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjClientes extends Persistencia {

	// <editor-fold desc="Valores de uso general - Propiedades"
	// defaultstate="collapsed">

	/**
	 * Datos de la Consulta actual
	 */
	private String txtBuscarCliente = "";

	public String getTxtBuscarCliente() {
		return txtBuscarCliente;
	}

	public void setTxtBuscarCliente(String txtBuscarCliente) {
		this.txtBuscarCliente = txtBuscarCliente;
	}

	private String txtBuscarFinca = "";

	public String getTxtBuscarFinca() {
		return txtBuscarFinca;
	}

	public void setTxtBuscarFinca(String txtBuscarFinca) {
		this.txtBuscarFinca = txtBuscarFinca;
	}

	private String txtBuscarSector = "";

	public String getTxtBuscarSector() {
		return txtBuscarSector;
	}

	public void setTxtBuscarSector(String txtBuscarSector) {
		this.txtBuscarSector = txtBuscarSector;
	}

	/*
	 * Codigo de Cliente que se busca
	 */
	private Integer codigoCliente = 0;

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	private Integer codigoFinca = 0;

	public Integer getCodigoFinca() {
		return codigoFinca;
	}

	public void setCodigoFinca(Integer codigoFinca) {
		this.codigoFinca = codigoFinca;
	}

	private Integer codigoSector = 0;

	public Integer getCodigoSector() {
		return codigoSector;
	}

	public void setCodigoSector(Integer codigoSector) {
		this.codigoSector = codigoSector;
	}

	private Integer codigoPlantacion = 0;

	public Integer getCodigoPlantacion() {
		return codigoPlantacion;
	}

	public void setCodigoPlantacion(Integer codigoPlantacion) {
		this.codigoPlantacion = codigoPlantacion;
	}

	private Integer codigoVisita = 0;

	public Integer getCodigoVisita() {
		return codigoVisita;
	}

	public void setCodigoVisita(Integer codigoVisita) {
		this.codigoVisita = codigoVisita;
	}

	private Integer codigoTratamiento = 0;

	public Integer getCodigoTratamiento() {
		return codigoTratamiento;
	}

	public void setCodigoTratamiento(Integer codigoTratamiento) {
		this.codigoTratamiento = codigoTratamiento;
	}

	private Integer codigoOperacion = 0;

	public Integer getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(Integer codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * Cliente en curso actual
	 */
	private Clientes clienteActual = new Clientes();

	public Clientes getClienteActual() {
		return clienteActual;
	}

	public void setClienteActual(Clientes clienteActual) {
		this.clienteActual = clienteActual;
	}

	private Fincas fincaActual = new Fincas();

	public Fincas getFincaActual() {
		return fincaActual;
	}

	public void setFincaActual(Fincas fincaActual) {
		this.fincaActual = fincaActual;
	}

	private Sectores sectorActual = new Sectores();

	public Sectores getSectorActual() {
		return sectorActual;
	}

	public void setSectorActual(Sectores sectorActual) {
		this.sectorActual = sectorActual;
	}

	private Plantaciones plantacionActual = new Plantaciones();

	public Plantaciones getPlantacionActual() {
		return plantacionActual;
	}

	public void setPlantacionActual(Plantaciones plantacionActual) {
		this.plantacionActual = plantacionActual;
	}

	private Visitas visitaActual = new Visitas();

	public Visitas getVisitaActual() {
		return visitaActual;
	}

	public void setVisitaActual(Visitas visitaActual) {
		this.visitaActual = visitaActual;
	}

	private Tratamientos tratamientoActual = new Tratamientos();

	public Tratamientos getTratamientoActual() {
		return tratamientoActual;
	}

	public void setTratamientoActual(Tratamientos tratamientoActual) {
		this.tratamientoActual = tratamientoActual;
	}

	private Operaciones operacionActual = new Operaciones();

	public Operaciones getOperacionActual() {
		return operacionActual;
	}

	public void setOperacionActual(Operaciones operacionActual) {
		this.operacionActual = operacionActual;
	}

	// </editor-fold>

	public String volverClientes() {
		clienteActual = new Clientes();
		return "clientes.xhtml";
	}

	public String volverFincas() {
		fincaActual = new Fincas();
		return "fincas.xhtml";
	}

	public String volverSector() {
		sectorActual = new Sectores();
		return "sectores.xhtml";
	}

	public String volverPlantacion() {
		return "plantaciones.xhtml";
	}

	// <editor-fold desc="Acceso datos Clientes" defaultstate="collapsed">

	/*********************************************************************************************
	 * Lista de Consulta de Clientes
	 *********************************************************************************************/
	private List<Clientes> consultaClientes = new ArrayList<Clientes>();

	public void setConsultaClientes(List<Clientes> consultaClientes) {
		this.consultaClientes = consultaClientes;
	}

	public List<Clientes> getConsultaClientes() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT c FROM Clientes c WHERE c.nombre LIKE :nombre ORDER BY c.nombre ";
			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("nombre", "%" + txtBuscarCliente + "%");
			consultaClientes = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Clientes", e);
		} finally {
			em.close();
		}
		return consultaClientes;
	}

	public void cmdBuscarClientes() {
		this.getConsultaClientes();
	}

	/*********************************************************************************************
	 * Lista de Consulta de Fincas
	 *********************************************************************************************/
	private List<Fincas> consultaFincas = new ArrayList<Fincas>();

	public void setConsultaFincas(List<Fincas> consultaFincas) {
		this.consultaFincas = consultaFincas;
	}

	public List<Fincas> getConsultaFincas() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Fincas f WHERE f.nombre LIKE :nombre ";
			if (codigoCliente != 0)
				sqlQuery = sqlQuery + "AND f.cliente = :cliente ";
			sqlQuery = sqlQuery + "ORDER BY f.nombre ";
			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("nombre", "%" + txtBuscarFinca + "%");
			if (codigoCliente != 0)
				consulta.setParameter("cliente", codigoCliente);
			consultaFincas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Fincas", e);
		} finally {
			em.close();
		}
		return consultaFincas;
	}

	// </editor-fold>
	// <editor-fold desc="Acceso datos Sectores" defaultstate="collapsed">

	/*********************************************************************************************
	 * Lista de Consulta de Sectores
	 *********************************************************************************************/
	private List<Sectores> consultaSectores = new ArrayList<Sectores>();

	public void setConsultaSectores(List<Sectores> consultaSectores) {
		this.consultaSectores = consultaSectores;
	}

	public List<Sectores> getConsultaSectores() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Sectores f WHERE f.nombre LIKE :nombre ";
			if (codigoCliente != 0)
				sqlQuery = sqlQuery + "AND f.fincaBean.cliente = :cliente ";
			if (codigoFinca != 0)
				sqlQuery = sqlQuery + "AND f.finca = :finca ";
			sqlQuery = sqlQuery + "ORDER BY f.nombre ";
			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("nombre", "%" + txtBuscarSector + "%");
			if (codigoCliente != 0)
				consulta.setParameter("cliente", codigoCliente);
			if (codigoFinca != 0)
				consulta.setParameter("finca", codigoFinca);
			consultaSectores = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Sectores", e);
		} finally {
			em.close();
		}
		return consultaSectores;
	}

	// </editor-fold>
	// <editor-fold desc="Acceso datos Plantaciones" defaultstate="collapsed">

	/*********************************************************************************************
	 * Lista de Consulta de Plantaciones
	 *********************************************************************************************/
	private List<Plantaciones> consultaPlantaciones = new ArrayList<Plantaciones>();

	public void setConsultaPlantaciones(List<Plantaciones> consultaPlantaciones) {
		this.consultaPlantaciones = consultaPlantaciones;
	}

	public List<Plantaciones> getConsultaPlantaciones() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Plantaciones f WHERE 1=1 ";
			if (codigoCliente != 0)
				sqlQuery = sqlQuery + "AND f.cliente = :cliente ";
			if (codigoFinca != 0)
				sqlQuery = sqlQuery + "AND f.finca = :finca ";
			if (codigoSector != 0)
				sqlQuery = sqlQuery + "AND f.sector = :sector ";
			sqlQuery = sqlQuery + "ORDER BY f.fechaInicio DESC ";
			Query consulta = em.createQuery(sqlQuery);
			if (codigoCliente != 0)
				consulta.setParameter("cliente", codigoCliente);
			if (codigoFinca != 0)
				consulta.setParameter("finca", codigoFinca);
			if (codigoSector != 0)
				consulta.setParameter("sector", codigoSector);
			consultaPlantaciones = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Plantaciones", e);
		} finally {
			em.close();
		}
		return consultaPlantaciones;
	}

	// </editor-fold>
	// <editor-fold desc="Acceso datos Visitas" defaultstate="collapsed">

	/*********************************************************************************************
	 * Lista de Consulta de Visitas
	 *********************************************************************************************/
	private List<Visitas> consultaVisitas = new ArrayList<Visitas>();

	public void setConsultaVisitas(List<Visitas> consultaVisitas) {
		this.consultaVisitas = consultaVisitas;
	}

	public List<Visitas> getConsultaVisitas() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Visitas f WHERE 1=1 ";
			if (codigoCliente != 0)
				sqlQuery = sqlQuery + "AND f.plantacionBean.sectorBean.fincaBean.cliente = :cliente ";
			if (codigoFinca != 0)
				sqlQuery = sqlQuery + "AND f.plantacionBean.sectorBean.fincaBean.codigo = :finca ";
			if (codigoSector != 0)
				sqlQuery = sqlQuery + "AND f.plantacionBean.sectorBean.codigo = :sector ";
			if (codigoPlantacion != 0)
				sqlQuery = sqlQuery + "AND f.plantacionBean.codigo = :plantacion ";
			sqlQuery = sqlQuery + "ORDER BY f.fecha DESC ";
			Query consulta = em.createQuery(sqlQuery);
			if (codigoCliente != 0)
				consulta.setParameter("cliente", codigoCliente);
			if (codigoFinca != 0)
				consulta.setParameter("finca", codigoFinca);
			if (codigoSector != 0)
				consulta.setParameter("sector", codigoSector);
			if (codigoPlantacion != 0)
				consulta.setParameter("plantacion", codigoPlantacion);
			consultaVisitas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Visitas", e);
		} finally {
			em.close();
		}
		return consultaVisitas;
	}

	public void cmdBuscarVisitas() {
		this.getConsultaVisitas();
	}

	/*********************************************************************************************
	 * Lista de Consulta de Visitas
	 *********************************************************************************************/
	private List<Tratamientosproductos> consultaProductosVisitas = new ArrayList<Tratamientosproductos>();

	public void setConsultaProductosVisitas(List<Tratamientosproductos> consultaProductosVisitas) {
		this.consultaProductosVisitas = consultaProductosVisitas;
	}

	public List<Tratamientosproductos> getConsultaProductosVisitas() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT p FROM Tratamientosproductos p ";
			Query consulta = em.createQuery(sqlQuery);
			consultaProductosVisitas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista ProductosVisitas", e);
		} finally {
			em.close();
		}
		return consultaProductosVisitas;
	}

	// </editor-fold>
	// <editor-fold desc="Acceso datos ControlesPlagas" defaultstate="collapsed">

	/*********************************************************************************************
	 * Lista de Consulta de Controles
	 *********************************************************************************************/
	private List<Controlesplagas> consultaControlesplagas = new ArrayList<Controlesplagas>();

	public void setConsultaControlesplagas(List<Controlesplagas> consultaControlesplagas) {
		this.consultaControlesplagas = consultaControlesplagas;
	}

	public List<Controlesplagas> getConsultaControlesplagas() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Controlesplagas f WHERE f.controlesplagasPK.visita = :visita ";
			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("visita", codigoVisita);
			consultaControlesplagas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Controles", e);
		} finally {
			em.close();
		}
		return consultaControlesplagas;
	}

	// </editor-fold>
	// <editor-fold desc="Acceso datos Productos Tratamientos"
	// defaultstate="collapsed">

	/*********************************************************************************************
	 * Lista de Consulta de Controles
	 *********************************************************************************************/
	private List<Tratamientosproductos> consultaTratamientosProductos = new ArrayList<Tratamientosproductos>();

	public void setConsultaTratamientosProductos(List<Tratamientosproductos> consultaTratamientosProductos) {
		this.consultaTratamientosProductos = consultaTratamientosProductos;
	}

	public List<Tratamientosproductos> getConsultaTratamientosProductos() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Tratamientosproductos f WHERE f.tratamiento = :tratamiento ";
			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("tratamiento", codigoTratamiento);
			consultaTratamientosProductos = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Tratamientos/Productos", e);
		} finally {
			em.close();
		}
		return consultaTratamientosProductos;
	}

	// </editor-fold>
	// <editor-fold desc="Acceso datos Operaciones" defaultstate="collapsed">

	/*********************************************************************************************
	 * Lista de Consulta de Controles
	 *********************************************************************************************/
	private List<Operacionesproductos> consultaOperacionesProductos = new ArrayList<Operacionesproductos>();

	public void setConsultaOperacionesProductos(List<Operacionesproductos> consultaOperacionesProductos) {
		this.consultaOperacionesProductos = consultaOperacionesProductos;
	}

	public List<Operacionesproductos> getConsultaOperacionesProductos() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Operacionesproductos f WHERE f.operacion = :operacion ";
			Query consulta = em.createQuery(sqlQuery);
			consulta.setParameter("operacion", codigoOperacion);
			consultaOperacionesProductos = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Operaciones/Productos", e);
		} finally {
			em.close();
		}
		return consultaOperacionesProductos;
	}

	// </editor-fold>

	/*********************************************************************************************
	 * Lista de Informe de Visitas
	 *********************************************************************************************/
	private List<Visitas> informeVisitas = new ArrayList<Visitas>();

	public void setInformeVisitas(List<Visitas> informeVisitas) {
		this.informeVisitas = informeVisitas;
	}

	public List<Visitas> getInformeVisitas() {
		return informeVisitas;
	}

	public void GeneraInformeVisitas(Integer codigoCultivo, Integer CodigoMotivo) {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Visitas f WHERE 1=1 ";
			if (codigoCliente != 0)
				sqlQuery = sqlQuery + "AND f.plantacionBean.sectorBean.fincaBean.cliente = :cliente ";
			if (codigoFinca != 0)
				sqlQuery = sqlQuery + "AND f.plantacionBean.sectorBean.fincaBean.codigo = :finca ";
			if (codigoSector != 0)
				sqlQuery = sqlQuery + "AND f.plantacionBean.sectorBean.codigo = :sector ";
			if (codigoPlantacion != 0)
				sqlQuery = sqlQuery + "AND f.plantacionBean.codigo = :plantacion ";
			sqlQuery = sqlQuery + "ORDER BY f.fecha DESC ";
			Query consulta = em.createQuery(sqlQuery);
			if (codigoCliente != 0)
				consulta.setParameter("cliente", codigoCliente);
			if (codigoFinca != 0)
				consulta.setParameter("finca", codigoFinca);
			if (codigoSector != 0)
				consulta.setParameter("sector", codigoSector);
			if (codigoPlantacion != 0)
				consulta.setParameter("plantacion", codigoPlantacion);
			informeVisitas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Visitas", e);
		} finally {
			em.close();
		}
	}

	private List<Controlesplagas> consultaTratamientosPlagas = new ArrayList<Controlesplagas>();

	public List<Controlesplagas> getConsultaTratamientosPlagas() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Controlesplagas f ";
			Query consulta = em.createQuery(sqlQuery);
			consultaTratamientosPlagas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de TratamientosPlagas", e);
		} finally {
			em.close();
		}
		return consultaTratamientosPlagas;
	}

	private List<Controlesdepredadores> consultaTratamientosDepredadores = new ArrayList<Controlesdepredadores>();

	public List<Controlesdepredadores> getConsultaTratamientosDepredadores() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM Controlesdepredadores f ";
			Query consulta = em.createQuery(sqlQuery);
			consultaTratamientosDepredadores = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de TratamientosDepredadores", e);
		} finally {
			em.close();
		}
		return consultaTratamientosDepredadores;
	}

	private List<VisitasAbonosFertilizante> consultaTratamientosAbonos = new ArrayList<VisitasAbonosFertilizante>();

	public List<VisitasAbonosFertilizante> getConsultaTratamientosAbonos() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM VisitasAbonosFertilizante f ";
			Query consulta = em.createQuery(sqlQuery);
			consultaTratamientosAbonos = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de TratamientosPlagas", e);
		} finally {
			em.close();
		}
		return consultaTratamientosAbonos;
	}

	private List<VisitasProductosOcb> consultaTratamientosOCB = new ArrayList<VisitasProductosOcb>();

	public List<VisitasProductosOcb> getConsultaTratamientosOCB() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT f FROM VisitasProductosOcb f ";
			Query consulta = em.createQuery(sqlQuery);
			consultaTratamientosOCB = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de TratamientosPlagas", e);
		} finally {
			em.close();
		}
		return consultaTratamientosOCB;
	}

	/*********************************************************************************************
	 * Lista de Consulta de Personas
	 *********************************************************************************************/
	private List<ClientesPersonas> listaPersonas = new ArrayList<ClientesPersonas>();

	public void setListaPersonas(List<ClientesPersonas> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public List<ClientesPersonas> getListaPersonas() {
		em = entityManager();
		try {
			String sqlQuery = "SELECT p FROM ClientesPersonas p ";
			if (codigoCliente != 0)
				sqlQuery = sqlQuery + "WHERE f.cliente = :cliente ";
			sqlQuery = sqlQuery + "ORDER BY f.nombre ";
			Query consulta = em.createQuery(sqlQuery);
			if (codigoCliente != 0)
				consulta.setParameter("cliente", codigoCliente);
			listaPersonas = consulta.getResultList();
		} catch (Exception e) {
			Rutinas.MuestraMensaje("CULTIVOS : Error al obtener lista de Personas", e);
		} finally {
			em.close();
		}
		return listaPersonas;
	}

}
