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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "roomcard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roomcard.findAll", query = "SELECT r FROM Roomcard r"),
    @NamedQuery(name = "Roomcard.findByIdtable1", query = "SELECT r FROM Roomcard r WHERE r.idtable1 = :idtable1"),
    @NamedQuery(name = "Roomcard.findByRoomNumber", query = "SELECT r FROM Roomcard r WHERE r.roomNumber = :roomNumber"),
    @NamedQuery(name = "Roomcard.findByCardNumber", query = "SELECT r FROM Roomcard r WHERE r.cardNumber = :cardNumber")})
public class Roomcard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtable1")
    private Integer idtable1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "room_number")
    private String roomNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "card_number")
    private String cardNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomcardIdtable1")
    private Collection<Participant> participantCollection;

    public Roomcard() {
    }

    public Roomcard(Integer idtable1) {
        this.idtable1 = idtable1;
    }

    public Roomcard(Integer idtable1, String roomNumber, String cardNumber) {
        this.idtable1 = idtable1;
        this.roomNumber = roomNumber;
        this.cardNumber = cardNumber;
    }

    public Integer getIdtable1() {
        return idtable1;
    }

    public void setIdtable1(Integer idtable1) {
        this.idtable1 = idtable1;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @XmlTransient
    public Collection<Participant> getParticipantCollection() {
        return participantCollection;
    }

    public void setParticipantCollection(Collection<Participant> participantCollection) {
        this.participantCollection = participantCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtable1 != null ? idtable1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roomcard)) {
            return false;
        }
        Roomcard other = (Roomcard) object;
        if ((this.idtable1 == null && other.idtable1 != null) || (this.idtable1 != null && !this.idtable1.equals(other.idtable1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Roomcard[ idtable1=" + idtable1 + " ]";
    }
    
}
