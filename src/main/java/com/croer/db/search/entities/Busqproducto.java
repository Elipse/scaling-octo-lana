/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.db.search.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elialva
 */
@Entity
@Table(name = "busqproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Busqproducto.findAll", query = "SELECT b FROM Busqproducto b"),
    @NamedQuery(name = "Busqproducto.findByIdBean", query = "SELECT b FROM Busqproducto b WHERE b.idBean = :idBean")})
public class Busqproducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idBean")
    private Integer idBean;
    @Lob
    @Column(name = "contexto")
    private String contexto;

    public Busqproducto() {
    }

    public Busqproducto(Integer idBean) {
        this.idBean = idBean;
    }

    public Integer getIdBean() {
        return idBean;
    }

    public void setIdBean(Integer idBean) {
        this.idBean = idBean;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBean != null ? idBean.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Busqproducto)) {
            return false;
        }
        Busqproducto other = (Busqproducto) object;
        if ((this.idBean == null && other.idBean != null) || (this.idBean != null && !this.idBean.equals(other.idBean))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.croer.db.search.entities.Busqproducto[ idBean=" + idBean + " ]";
    }
    
}
