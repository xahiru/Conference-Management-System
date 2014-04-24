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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "tblContent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Content.findAll", query = "SELECT c FROM Content c"),
    @NamedQuery(name = "Content.findByContentsId", query = "SELECT c FROM Content c WHERE c.contentsId = :contentsId")})
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contentsId")
    private Integer contentsId;
    @Lob
    @Column(name = "paticipant_notes")
    private byte[] paticipantNotes;
    @JoinColumn(name = "tblEvent_eventId", referencedColumnName = "eventId")
    @ManyToOne(optional = false)
    private Event tblEventeventId;

    public Content() {
    }

    public Content(Integer contentsId) {
        this.contentsId = contentsId;
    }

    public Integer getContentsId() {
        return contentsId;
    }

    public void setContentsId(Integer contentsId) {
        this.contentsId = contentsId;
    }

    public byte[] getPaticipantNotes() {
        return paticipantNotes;
    }

    public void setPaticipantNotes(byte[] paticipantNotes) {
        this.paticipantNotes = paticipantNotes;
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
        hash += (contentsId != null ? contentsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Content)) {
            return false;
        }
        Content other = (Content) object;
        if ((this.contentsId == null && other.contentsId != null) || (this.contentsId != null && !this.contentsId.equals(other.contentsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Content[ contentsId=" + contentsId + " ]";
    }
    
}
