/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.importacion;


import com.acisa.cultivos.objetos.Persistencia;
import com.acisa.cultivos.modelos.Productos;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author jcperan
 */
public class ProductoFitosanitario extends Persistencia {
    
    Productos producto;
    
    public void Generar(Integer numeroRegistro) {

        em = entityManager();
        try {
            Query consulta = em.createQuery("SELECT p FROM Productos p where p.numeroRegistro = :numero ");
            consulta.setParameter("numero", numeroRegistro);
            consulta.setMaxResults(1);
            producto = new Productos();
            try {
                producto = (Productos) consulta.getSingleResult();
            } catch (Exception e) {
                producto = new Productos();
                producto.setNumeroRegistro(numeroRegistro);
                em.getTransaction().begin();
                em.persist(producto);
                em.flush();
                em.getTransaction().commit();
            }

            em.getTransaction().begin();
            if (numeroRegistro < 9999) {
                producto.setRegMagrama("ES-" + String.format("%1$05d", numeroRegistro));
            } else {
                producto.setRegMagrama(numeroRegistro.toString());
            }
            producto.setFechaActualizacion(new Date());
            
        } catch (Exception ex) {
            System.out.println(new Date() + " - ERROR AL GENERAR NUEVO PRODUCTO - " + ex.getMessage());
        }
    }
    
}
