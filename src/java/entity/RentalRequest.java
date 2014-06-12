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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tblRentalRequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RentalRequest.findAll", query = "SELECT r FROM RentalRequest r"),
    @NamedQuery(name = "RentalRequest.findUserSpecificRentalRequest", query = "SELECT r FROM RentalRequest r WHERE r.tblEventeventId.bookingBookingRef.tblUseruserId = :user"),
    @NamedQuery(name = "RentalRequest.findByRentalrequestId", query = "SELECT r FROM RentalRequest r WHERE r.rentalrequestId = :rentalrequestId"),
    @NamedQuery(name = "RentalRequest.findByRequestType", query = "SELECT r FROM RentalRequest r WHERE r.requestType = :requestType"),
    @NamedQuery(name = "RentalRequest.findByDescription", query = "SELECT r FROM RentalRequest r WHERE r.description = :description"),
    @NamedQuery(name = "RentalRequest.findByQauntity", query = "SELECT r FROM RentalRequest r WHERE r.qauntity = :qauntity")})
public class RentalRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rentalrequestId")
    private Integer rentalrequestId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "request_type")
    private String requestType;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Column(name = "qauntity")
    private Integer qauntity;
    @JoinTable(name = "tblRentalRequest_has_tblEquipment", joinColumns = {
        @JoinColumn(name = "tblRentalRequest_rentalrequestId", referencedColumnName = "rentalrequestId")}, inverseJoinColumns = {
        @JoinColumn(name = "tblEquipment_equipmentId", referencedColumnName = "equipmentId")})
    @ManyToMany
    private Collection<Equipment> equipmentCollection;
    @JoinColumn(name = "tblEvent_eventId", referencedColumnName = "eventId")
    @ManyToOne(optional = false)
    private Event tblEventeventId;

    public RentalRequest() {
    }

    public RentalRequest(Integer rentalrequestId) {
        this.rentalrequestId = rentalrequestId;
    }

    public RentalRequest(Integer rentalrequestId, String requestType) {
        this.rentalrequestId = rentalrequestId;
        this.requestType = requestType;
    }

    public Integer getRentalrequestId() {
        return rentalrequestId;
    }

    public void setRentalrequestId(Integer rentalrequestId) {
        this.rentalrequestId = rentalrequestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQauntity() {
        return qauntity;
    }

    public void setQauntity(Integer qauntity) {
        this.qauntity = qauntity;
    }

    @XmlTransient
    public Collection<Equipment> getEquipmentCollection() {
        return equipmentCollection;
    }

    public void setEquipmentCollection(Collection<Equipment> equipmentCollection) {
        this.equipmentCollection = equipmentCollection;
    }

    public Event getTblEventeventId() {
        return tblEventeventId;
    }

    public void setTblEventeventId(Event tblEventeventId) {
        this.tblEventeventId = tblEventeventId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentalrequestId != null ? rentalrequestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RentalRequest)) {
            return false;
        }
        RentalRequest other = (RentalRequest) object;
        if ((this.rentalrequestId == null && other.rentalrequestId != null) || (this.rentalrequestId != null && !this.rentalrequestId.equals(other.rentalrequestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RentalRequest[ rentalrequestId=" + rentalrequestId + " ]";
    }

}
