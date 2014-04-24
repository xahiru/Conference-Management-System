/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "tblOrganizer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizer.findAll", query = "SELECT o FROM Organizer o"),
    @NamedQuery(name = "Organizer.findByOrganizerId", query = "SELECT o FROM Organizer o WHERE o.organizerId = :organizerId"),
    @NamedQuery(name = "Organizer.findByCompanyName", query = "SELECT o FROM Organizer o WHERE o.companyName = :companyName"),
    @NamedQuery(name = "Organizer.findByContactPersonName", query = "SELECT o FROM Organizer o WHERE o.contactPersonName = :contactPersonName"),
    @NamedQuery(name = "Organizer.findByContactNumber", query = "SELECT o FROM Organizer o WHERE o.contactNumber = :contactNumber"),
    @NamedQuery(name = "Organizer.findByEmail", query = "SELECT o FROM Organizer o WHERE o.email = :email")})
public class Organizer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizerId")
    private Integer organizerId;
    @Size(max = 45)
    @Column(name = "company_name")
    private String companyName;
    @Size(max = 45)
    @Column(name = "contact_person_name")
    private String contactPersonName;
    @Size(max = 45)
    @Column(name = "contact_number")
    private String contactNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblOrganizerorganizerId")
    private Collection<Event> eventCollection;

    public Organizer() {
    }

    public Organizer(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizerId != null ? organizerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organizer)) {
            return false;
        }
        Organizer other = (Organizer) object;
        if ((this.organizerId == null && other.organizerId != null) || (this.organizerId != null && !this.organizerId.equals(other.organizerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Organizer[ organizerId=" + organizerId + " ]";
    }
    
}
