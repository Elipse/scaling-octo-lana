/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.db.search.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
@Table(name = "itembusq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itembusq.findAll", query = "SELECT i FROM Itembusq i"),
    @NamedQuery(name = "Itembusq.findByType", query = "SELECT i FROM Itembusq i WHERE i.itembusqPK.type = :type"),
    @NamedQuery(name = "Itembusq.findByIdItem", query = "SELECT i FROM Itembusq i WHERE i.itembusqPK.idItem = :idItem"),
    @NamedQuery(name = "Itembusq.findByPrioridad", query = "SELECT i FROM Itembusq i WHERE i.prioridad = :prioridad")})
public class Itembusq implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItembusqPK itembusqPK;
    @Lob
    @Column(name = "contexto")
    private String contexto;
    @Column(name = "prioridad")
    private Integer prioridad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itembusq", orphanRemoval = true)
    private List<ItemOrtograma> itemOrtogramaList;

    public Itembusq() {
    }

    public Itembusq(ItembusqPK itembusqPK) {
        this.itembusqPK = itembusqPK;
    }

    public Itembusq(String type, String idItem) {
        this.itembusqPK = new ItembusqPK(type, idItem);
    }

    public ItembusqPK getItembusqPK() {
        return itembusqPK;
    }

    public void setItembusqPK(ItembusqPK itembusqPK) {
        this.itembusqPK = itembusqPK;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    @XmlTransient
    public List<ItemOrtograma> getItemOrtogramaList() {
        return itemOrtogramaList;
    }

    public void setItemOrtogramaList(List<ItemOrtograma> itemOrtogramaList) {
        this.itemOrtogramaList = itemOrtogramaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itembusqPK != null ? itembusqPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itembusq)) {
            return false;
        }
        Itembusq other = (Itembusq) object;
        if ((this.itembusqPK == null && other.itembusqPK != null) || (this.itembusqPK != null && !this.itembusqPK.equals(other.itembusqPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.croer.db.search.entities.Itembusq[ itembusqPK=" + itembusqPK + " ]";
    }

}
