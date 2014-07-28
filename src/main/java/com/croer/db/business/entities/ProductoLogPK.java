/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.db.business.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author elialva
 */
@Embeddable
public class ProductoLogPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "logDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDate;

    public ProductoLogPK() {
    }

    public ProductoLogPK(int id, Date logDate) {
        this.id = id;
        this.logDate = logDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (logDate != null ? logDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoLogPK)) {
            return false;
        }
        ProductoLogPK other = (ProductoLogPK) object;
        if (this.id != other.id) {
            return false;
        }
        if ((this.logDate == null && other.logDate != null) || (this.logDate != null && !this.logDate.equals(other.logDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.croer.db.business.entities.ProductoLogPK[ id=" + id + ", logDate=" + logDate + " ]";
    }
    
}
