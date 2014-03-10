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
@Table(name = "rental_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RentalRequest.findAll", query = "SELECT r FROM RentalRequest r"),
    @NamedQuery(name = "RentalRequest.findByIdrentalRequest", query = "SELECT r FROM RentalRequest r WHERE r.idrentalRequest = :idrentalRequest"),
    @NamedQuery(name = "RentalRequest.findByRequestType", query = "SELECT r FROM RentalRequest r WHERE r.requestType = :requestType"),
    @NamedQuery(name = "RentalRequest.findByDescription", query = "SELECT r FROM RentalRequest r WHERE r.description = :description"),
    @NamedQuery(name = "RentalRequest.findByQauntity", query = "SELECT r FROM RentalRequest r WHERE r.qauntity = :qauntity")})
public class RentalRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrental_request")
    private Integer idrentalRequest;
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
    @ManyToMany(mappedBy = "rentalRequestCollection")
    private Collection<Equipment> equipmentCollection;
    @JoinColumn(name = "event_idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false)
    private Event eventIdevent;

    public RentalRequest() {
    }

    public RentalRequest(Integer idrentalRequest) {
        this.idrentalRequest = idrentalRequest;
    }

    public RentalRequest(Integer idrentalRequest, String requestType) {
        this.idrentalRequest = idrentalRequest;
        this.requestType = requestType;
    }

    public Integer getIdrentalRequest() {
        return idrentalRequest;
    }

    public void setIdrentalRequest(Integer idrentalRequest) {
        this.idrentalRequest = idrentalRequest;
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

    public Event getEventIdevent() {
        return eventIdevent;
    }

    public void setEventIdevent(Event eventIdevent) {
        this.eventIdevent = eventIdevent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrentalRequest != null ? idrentalRequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RentalRequest)) {
            return false;
        }
        RentalRequest other = (RentalRequest) object;
        if ((this.idrentalRequest == null && other.idrentalRequest != null) || (this.idrentalRequest != null && !this.idrentalRequest.equals(other.idrentalRequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RentalRequest[ idrentalRequest=" + idrentalRequest + " ]";
    }
    
}
