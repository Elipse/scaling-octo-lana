/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.db.business.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elialva
 */
@Entity
@Table(name = "producto_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoLog.findAll", query = "SELECT p FROM ProductoLog p"),
    @NamedQuery(name = "ProductoLog.findByIdProducto", query = "SELECT p FROM ProductoLog p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "ProductoLog.findByDescripcion", query = "SELECT p FROM ProductoLog p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProductoLog.findByCantidad", query = "SELECT p FROM ProductoLog p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "ProductoLog.findByPrecio", query = "SELECT p FROM ProductoLog p WHERE p.precio = :precio"),
    @NamedQuery(name = "ProductoLog.findByMarcaidMarca", query = "SELECT p FROM ProductoLog p WHERE p.marcaidMarca = :marcaidMarca"),
    @NamedQuery(name = "ProductoLog.findByUnidadidUnidad", query = "SELECT p FROM ProductoLog p WHERE p.unidadidUnidad = :unidadidUnidad"),
    @NamedQuery(name = "ProductoLog.findById", query = "SELECT p FROM ProductoLog p WHERE p.productoLogPK.id = :id"),
    @NamedQuery(name = "ProductoLog.findByLogDate", query = "SELECT p FROM ProductoLog p WHERE p.productoLogPK.logDate = :logDate"),
    @NamedQuery(name = "ProductoLog.findByActionType", query = "SELECT p FROM ProductoLog p WHERE p.actionType = :actionType")})
public class ProductoLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductoLogPK productoLogPK;
    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @Column(name = "Marca_idMarca")
    private int marcaidMarca;
    @Basic(optional = false)
    @Column(name = "Unidad_idUnidad")
    private int unidadidUnidad;
    @Basic(optional = false)
    @Column(name = "actionType")
    private String actionType;

    public ProductoLog() {
    }

    public ProductoLog(ProductoLogPK productoLogPK) {
        this.productoLogPK = productoLogPK;
    }

    public ProductoLog(ProductoLogPK productoLogPK, int idProducto, int marcaidMarca, int unidadidUnidad, String actionType) {
        this.productoLogPK = productoLogPK;
        this.idProducto = idProducto;
        this.marcaidMarca = marcaidMarca;
        this.unidadidUnidad = unidadidUnidad;
        this.actionType = actionType;
    }

    public ProductoLog(int id, Date logDate) {
        this.productoLogPK = new ProductoLogPK(id, logDate);
    }

    public ProductoLogPK getProductoLogPK() {
        return productoLogPK;
    }

    public void setProductoLogPK(ProductoLogPK productoLogPK) {
        this.productoLogPK = productoLogPK;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getMarcaidMarca() {
        return marcaidMarca;
    }

    public void setMarcaidMarca(int marcaidMarca) {
        this.marcaidMarca = marcaidMarca;
    }

    public int getUnidadidUnidad() {
        return unidadidUnidad;
    }

    public void setUnidadidUnidad(int unidadidUnidad) {
        this.unidadidUnidad = unidadidUnidad;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoLogPK != null ? productoLogPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoLog)) {
            return false;
        }
        ProductoLog other = (ProductoLog) object;
        if ((this.productoLogPK == null && other.productoLogPK != null) || (this.productoLogPK != null && !this.productoLogPK.equals(other.productoLogPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.croer.db.business.entities.ProductoLog[ productoLogPK=" + productoLogPK + " ]";
    }
    
}
