/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "tblRoomcard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roomcard.findAll", query = "SELECT r FROM Roomcard r"),
    @NamedQuery(name = "Roomcard.findByRoomcardId", query = "SELECT r FROM Roomcard r WHERE r.roomcardId = :roomcardId"),
    @NamedQuery(name = "Roomcard.findByRoomNumber", query = "SELECT r FROM Roomcard r WHERE r.roomNumber = :roomNumber"),
    @NamedQuery(name = "Roomcard.findByCardNumber", query = "SELECT r FROM Roomcard r WHERE r.cardNumber = :cardNumber")})
public class Roomcard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomcardId")
    private Integer roomcardId;
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "roomcard")
    private Participant participant;

    public Roomcard() {
    }

    public Roomcard(Integer roomcardId) {
        this.roomcardId = roomcardId;
    }

    public Roomcard(Integer roomcardId, String roomNumber, String cardNumber) {
        this.roomcardId = roomcardId;
        this.roomNumber = roomNumber;
        this.cardNumber = cardNumber;
    }

    public Integer getRoomcardId() {
        return roomcardId;
    }

    public void setRoomcardId(Integer roomcardId) {
        this.roomcardId = roomcardId;
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

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomcardId != null ? roomcardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roomcard)) {
            return false;
        }
        Roomcard other = (Roomcard) object;
        if ((this.roomcardId == null && other.roomcardId != null) || (this.roomcardId != null && !this.roomcardId.equals(other.roomcardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Roomcard[ roomcardId=" + roomcardId + " ]";
    }
    
}
