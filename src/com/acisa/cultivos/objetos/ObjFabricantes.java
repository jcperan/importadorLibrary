package com.acisa.cultivos.objetos;

import javax.persistence.Query;

import com.acisa.cultivos.modelos.Fabricantes;
import com.acisa.cultivos.utilidades.Rutinas;

public class ObjFabricantes extends Persistencia {

    public Fabricantes buscarFabricante(String nombre) {

        Fabricantes resultado = null;
        em = entityManager();
        try {
            Query q = em.createQuery("select p from Fabricantes p where p.nombre = :nombre ");
            q.setParameter("nombre", nombre);
            resultado = (Fabricantes) q.getSingleResult();
        } catch (Exception e) {
            // Rutinas.MuestraMensaje("CULTIVOS : Error al buscar Fabricante", e);
        }

        return resultado;
    }

    public void GrabarFabricante(Fabricantes nuevoFabricante) {
        em = entityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoFabricante);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            Rutinas.MuestraMensaje("CULTIVOS : Error Grabacion Nuevo Fabricante", e);
        } finally {
            em.close();
        }
    }

}
