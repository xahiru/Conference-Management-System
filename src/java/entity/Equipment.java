/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "equipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e"),
    @NamedQuery(name = "Equipment.findByIdequipment", query = "SELECT e FROM Equipment e WHERE e.idequipment = :idequipment"),
    @NamedQuery(name = "Equipment.findByName", query = "SELECT e FROM Equipment e WHERE e.name = :name")})
public class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idequipment")
    private Integer idequipment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "equipment_has_rental_request", joinColumns = {
        @JoinColumn(name = "equipment_idequipment", referencedColumnName = "idequipment")}, inverseJoinColumns = {
        @JoinColumn(name = "rental_request_idrental_request", referencedColumnName = "idrental_request")})
    @ManyToMany
    private Collection<RentalRequest> rentalRequestCollection;

    public Equipment() {
    }

    public Equipment(Integer idequipment) {
        this.idequipment = idequipment;
    }

    public Equipment(Integer idequipment, String name) {
        this.idequipment = idequipment;
        this.name = name;
    }

    public Integer getIdequipment() {
        return idequipment;
    }

    public void setIdequipment(Integer idequipment) {
        this.idequipment = idequipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<RentalRequest> getRentalRequestCollection() {
        return rentalRequestCollection;
    }

    public void setRentalRequestCollection(Collection<RentalRequest> rentalRequestCollection) {
        this.rentalRequestCollection = rentalRequestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipment != null ? idequipment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.idequipment == null && other.idequipment != null) || (this.idequipment != null && !this.idequipment.equals(other.idequipment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Equipment[ idequipment=" + idequipment + " ]";
    }
    
}
