/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "tblParticipant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participant.findAll", query = "SELECT p FROM Participant p"),
    @NamedQuery(name = "Participant.findByName", query = "SELECT p FROM Participant p WHERE p.name = :name"),
    @NamedQuery(name = "Participant.findByRegistrationstatus", query = "SELECT p FROM Participant p WHERE p.registrationstatus = :registrationstatus"),
    @NamedQuery(name = "Participant.findByParticipantId", query = "SELECT p FROM Participant p WHERE p.participantId = :participantId")})
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registrationstatus")
    private boolean registrationstatus;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "participantId")
    private Integer participantId;
    @JoinColumn(name = "participantId", referencedColumnName = "roomcardId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Roomcard roomcard;
    @JoinColumn(name = "tblEvent_eventId", referencedColumnName = "eventId")
    @ManyToOne(optional = false)
    private Event tblEventeventId;

    public Participant() {
    }

    public Participant(Integer participantId) {
        this.participantId = participantId;
    }

    public Participant(Integer participantId, String name, boolean registrationstatus) {
        this.participantId = participantId;
        this.name = name;
        this.registrationstatus = registrationstatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean getRegistrationstatus() {
        return registrationstatus;
    }

    public void setRegistrationstatus(boolean registrationstatus) {
        this.registrationstatus = registrationstatus;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Roomcard getRoomcard() {
        return roomcard;
    }

    public void setRoomcard(Roomcard roomcard) {
        this.roomcard = roomcard;
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
        hash += (participantId != null ? participantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participant)) {
            return false;
        }
        Participant other = (Participant) object;
        if ((this.participantId == null && other.participantId != null) || (this.participantId != null && !this.participantId.equals(other.participantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Participant[ participantId=" + participantId + " ]";
    }
    
}
