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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "contents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contents.findAll", query = "SELECT c FROM Contents c"),
    @NamedQuery(name = "Contents.findByIdcontents", query = "SELECT c FROM Contents c WHERE c.idcontents = :idcontents"),
    @NamedQuery(name = "Contents.findByPaticipantNotes", query = "SELECT c FROM Contents c WHERE c.paticipantNotes = :paticipantNotes")})
public class Contents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontents")
    private Integer idcontents;
    @Size(max = 255)
    @Column(name = "paticipant_notes")
    private String paticipantNotes;
    @JoinColumn(name = "event_idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false)
    private Event eventIdevent;

    public Contents() {
    }

    public Contents(Integer idcontents) {
        this.idcontents = idcontents;
    }

    public Integer getIdcontents() {
        return idcontents;
    }

    public void setIdcontents(Integer idcontents) {
        this.idcontents = idcontents;
    }

    public String getPaticipantNotes() {
        return paticipantNotes;
    }

    public void setPaticipantNotes(String paticipantNotes) {
        this.paticipantNotes = paticipantNotes;
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
        hash += (idcontents != null ? idcontents.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contents)) {
            return false;
        }
        Contents other = (Contents) object;
        if ((this.idcontents == null && other.idcontents != null) || (this.idcontents != null && !this.idcontents.equals(other.idcontents))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Contents[ idcontents=" + idcontents + " ]";
    }
    
}
