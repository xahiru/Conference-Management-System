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
@Table(name = "tblLayout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Layout.findAll", query = "SELECT l FROM Layout l"),
    @NamedQuery(name = "Layout.findByLayoutId", query = "SELECT l FROM Layout l WHERE l.layoutId = :layoutId")})
public class Layout implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "layoutId")
    private Integer layoutId;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @JoinColumn(name = "room_roomId", referencedColumnName = "roomId")
    @ManyToOne(optional = false)
    private Room roomroomId;

    public Layout() {
    }

    public Layout(Integer layoutId) {
        this.layoutId = layoutId;
    }

    public Integer getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Integer layoutId) {
        this.layoutId = layoutId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Room getRoomroomId() {
        return roomroomId;
    }

    public void setRoomroomId(Room roomroomId) {
        this.roomroomId = roomroomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (layoutId != null ? layoutId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Layout)) {
            return false;
        }
        Layout other = (Layout) object;
        if ((this.layoutId == null && other.layoutId != null) || (this.layoutId != null && !this.layoutId.equals(other.layoutId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Layout[ layoutId=" + layoutId + " ]";
    }
    
}
