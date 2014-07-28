/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.db.search.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elialva
 */
@Entity
@Table(name = "_simigrama")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Simigrama.findAll", query = "SELECT s FROM Simigrama s"),
    @NamedQuery(name = "Simigrama.findBySimigrama", query = "SELECT s FROM Simigrama s WHERE s.simigrama = :simigrama"),
    @NamedQuery(name = "Simigrama.findByNumegrama", query = "SELECT s FROM Simigrama s WHERE s.numegrama = :numegrama")})
public class Simigrama implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "simigrama")
    private String simigrama;
    @Basic(optional = false)
    @Column(name = "numegrama")
    private String numegrama;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "simigrama1")
    private List<Alineacion> alineacionList;

    public Simigrama() {
    }

    public Simigrama(String simigrama) {
        this.simigrama = simigrama;
    }

    public Simigrama(String simigrama, String numegrama) {
        this.simigrama = simigrama;
        this.numegrama = numegrama;
    }

    public String getSimigrama() {
        return simigrama;
    }

    public void setSimigrama(String simigrama) {
        this.simigrama = simigrama;
    }

    public String getNumegrama() {
        return numegrama;
    }

    public void setNumegrama(String numegrama) {
        this.numegrama = numegrama;
    }

    @XmlTransient
    public List<Alineacion> getAlineacionList() {
        return alineacionList;
    }

    public void setAlineacionList(List<Alineacion> alineacionList) {
        this.alineacionList = alineacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (simigrama != null ? simigrama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Simigrama)) {
            return false;
        }
        Simigrama other = (Simigrama) object;
        if ((this.simigrama == null && other.simigrama != null) || (this.simigrama != null && !this.simigrama.equals(other.simigrama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.croer.db.search.entities.Simigrama[ simigrama=" + simigrama + " ]";
    }
    
}
