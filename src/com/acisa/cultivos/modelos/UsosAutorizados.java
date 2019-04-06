/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.modelos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.acisa.cultivos.utilidades.Configuracion;
import com.acisa.cultivos.utilidades.Rutinas;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "UsosAutorizados")
@XmlRootElement
public class UsosAutorizados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;

    @Column(name = "Dosificacion")
    private String dosificacion;

    @Lob
    @Column(name = "Observaciones")
    private String observaciones;

    @Column(name = "Producto")
    private Integer producto;
    @Column(name = "Cultivo")
    private Integer cultivo;
    @Column(name = "Plaga")
    private Integer plaga;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Column(name = "Dosis")
    private Double dosis;
    @Column(name = "PlazoSeguridad")
    private Integer plazoSeguridad;
    @Column(name = "LMR")
    private Double lmr;
    @Column(name = "Autorizado")
    private Boolean autorizado;

    // bi-directional many-to-one association to Cultivo
    @ManyToOne
    @JoinColumn(name = "Cultivo", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
    private Cultivos cultivoBean;

    // bi-directional many-to-one association to Plaga
    @ManyToOne
    @JoinColumn(name = "Plaga", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
    private Plagas plagasBean;

    // bi-directional many-to-one association to Producto
    @ManyToOne
    @JoinColumn(name = "Producto", referencedColumnName = "Codigo", nullable = false, insertable = false, updatable = false)
    private Productos productoBean;

    public UsosAutorizados() {
    }

    public UsosAutorizados(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Integer getCultivo() {
        return cultivo;
    }

    public void setCultivo(Integer cultivo) {
        this.cultivo = cultivo;
    }

    public Integer getPlaga() {
        return plaga;
    }

    public void setPlaga(Integer plaga) {
        this.plaga = plaga;
    }

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }

    public Integer getPlazoSeguridad() {
        return plazoSeguridad;
    }

    public void setPlazoSeguridad(Integer plazoSeguridad) {
        this.plazoSeguridad = plazoSeguridad;
    }

    public Double getLmr() {
        return lmr;
    }

    public void setLmr(Double lmr) {
        this.lmr = lmr;
    }

    public Boolean getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Boolean autorizado) {
        this.autorizado = autorizado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsosAutorizados)) {
            return false;
        }
        UsosAutorizados other = (UsosAutorizados) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acisa.cultivos.modelos.UsosAutorizados[ codigo=" + codigo + " ]";
    }

    public String getDosificacion() {
        return dosificacion;
    }

    public void setDosificacion(String dosificacion) {
        this.dosificacion = dosificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Cultivos getCultivoBean() {
        return cultivoBean;
    }

    public void setCultivoBean(Cultivos cultivoBean) {
        this.cultivoBean = cultivoBean;
    }

    public Plagas getPlagaBean() {
        return plagasBean;
    }

    public void setPlagaBean(Plagas plagaBean) {
        this.plagasBean = plagaBean;
    }

    public Productos getProductoBean() {
        return productoBean;
    }

    public void setProductoBean(Productos productoBean) {
        this.productoBean = productoBean;
    }

    @Transient
    private String toWebDB = "";

    public String getToWebDB() {
        String resultado = "";
        resultado += this.codigo + ";";
        resultado += this.producto + ";";
        // resultado += this.cultivo + ";";
        if (this.cultivoBean != null) {
            resultado += this.cultivoBean.getNombre() + ";";
        } else {
            resultado += this.cultivo + ";";
        }
        // resultado += this.plaga + ";";
        if (this.plagasBean != null) {
            resultado += this.plagasBean.getNombre() + ";";
        } else {
            resultado += this.plaga + ";";
        }
        // resultado += this.dosis + ";";
        resultado += this.plazoSeguridad + ";";
        // resultado += this.lmr + ";";
        // resultado += this.autorizado + ";";
        resultado += this.dosificacion + ";";
        if (Configuracion.getInstance().getProperty("tablet") == "si") {
            resultado += this.observaciones + ";";
        } else {
            resultado += ";";
        }
        // Calcular numero de aplicaciones del producto
        String cuentaUsos = "0";
        if (this.productoBean != null) {
            if (this.productoBean.getTratamientos() != null) {
                cuentaUsos = Rutinas.CStr(this.productoBean.getTratamientos().size());
            }
        }
        resultado += cuentaUsos + ";";
        resultado += this.cultivo + "_" + this.plaga + "_" + Rutinas.FORMATO_NUMERO(cuentaUsos, "00000") + ";";
        resultado += "\n";
        return resultado;
    }

    public void setToWebDB(String toWebDB) {
        this.toWebDB = toWebDB;
    }

}
