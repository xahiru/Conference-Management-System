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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "participant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participant.findAll", query = "SELECT p FROM Participant p"),
    @NamedQuery(name = "Participant.findByIdparticipant", query = "SELECT p FROM Participant p WHERE p.idparticipant = :idparticipant"),
    @NamedQuery(name = "Participant.findByName", query = "SELECT p FROM Participant p WHERE p.name = :name"),
    @NamedQuery(name = "Participant.findByPhoto", query = "SELECT p FROM Participant p WHERE p.photo = :photo")})
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparticipant")
    private Integer idparticipant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "photo")
    private String photo;
    @JoinColumn(name = "roomcard_idtable1", referencedColumnName = "idtable1")
    @ManyToOne(optional = false)
    private Roomcard roomcardIdtable1;
    @JoinColumn(name = "event_idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false)
    private Event eventIdevent;

    public Participant() {
    }

    public Participant(Integer idparticipant) {
        this.idparticipant = idparticipant;
    }

    public Participant(Integer idparticipant, String name) {
        this.idparticipant = idparticipant;
        this.name = name;
    }

    public Integer getIdparticipant() {
        return idparticipant;
    }

    public void setIdparticipant(Integer idparticipant) {
        this.idparticipant = idparticipant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Roomcard getRoomcardIdtable1() {
        return roomcardIdtable1;
    }

    public void setRoomcardIdtable1(Roomcard roomcardIdtable1) {
        this.roomcardIdtable1 = roomcardIdtable1;
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
        hash += (idparticipant != null ? idparticipant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participant)) {
            return false;
        }
        Participant other = (Participant) object;
        if ((this.idparticipant == null && other.idparticipant != null) || (this.idparticipant != null && !this.idparticipant.equals(other.idparticipant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Participant[ idparticipant=" + idparticipant + " ]";
    }
    
}
