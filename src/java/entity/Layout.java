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
@Table(name = "layout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Layout.findAll", query = "SELECT l FROM Layout l"),
    @NamedQuery(name = "Layout.findByIdlayout", query = "SELECT l FROM Layout l WHERE l.idlayout = :idlayout"),
    @NamedQuery(name = "Layout.findByPhoto", query = "SELECT l FROM Layout l WHERE l.photo = :photo")})
public class Layout implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idlayout")
    private Integer idlayout;
    @Size(max = 255)
    @Column(name = "photo")
    private String photo;
    @JoinColumn(name = "room_idroom", referencedColumnName = "idroom")
    @ManyToOne(optional = false)
    private Room roomIdroom;

    public Layout() {
    }

    public Layout(Integer idlayout) {
        this.idlayout = idlayout;
    }

    public Integer getIdlayout() {
        return idlayout;
    }

    public void setIdlayout(Integer idlayout) {
        this.idlayout = idlayout;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Room getRoomIdroom() {
        return roomIdroom;
    }

    public void setRoomIdroom(Room roomIdroom) {
        this.roomIdroom = roomIdroom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlayout != null ? idlayout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Layout)) {
            return false;
        }
        Layout other = (Layout) object;
        if ((this.idlayout == null && other.idlayout != null) || (this.idlayout != null && !this.idlayout.equals(other.idlayout))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Layout[ idlayout=" + idlayout + " ]";
    }
    
}
