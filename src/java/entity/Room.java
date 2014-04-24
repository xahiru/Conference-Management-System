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
import javax.persistence.Lob;
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
@Table(name = "tblRoom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findByRoomId", query = "SELECT r FROM Room r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "Room.findByNumber", query = "SELECT r FROM Room r WHERE r.number = :number"),
    @NamedQuery(name = "Room.findByName", query = "SELECT r FROM Room r WHERE r.name = :name"),
    @NamedQuery(name = "Room.findByArea", query = "SELECT r FROM Room r WHERE r.area = :area"),
    @NamedQuery(name = "Room.findByCapacity", query = "SELECT r FROM Room r WHERE r.capacity = :capacity"),
    @NamedQuery(name = "Room.findByType", query = "SELECT r FROM Room r WHERE r.type = :type"),
    @NamedQuery(name = "Room.findByFurnitureMobility", query = "SELECT r FROM Room r WHERE r.furnitureMobility = :furnitureMobility"),
    @NamedQuery(name = "Room.findByFurnitureType", query = "SELECT r FROM Room r WHERE r.furnitureType = :furnitureType"),
    @NamedQuery(name = "Room.findByOrientation", query = "SELECT r FROM Room r WHERE r.orientation = :orientation")})
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomId")
    private Integer roomId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private int number;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "area")
    private Integer area;
    @Column(name = "capacity")
    private Integer capacity;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "furniture_mobility")
    private String furnitureMobility;
    @Size(max = 45)
    @Column(name = "furniture_type")
    private String furnitureType;
    @Size(max = 45)
    @Column(name = "orientation")
    private String orientation;
    @Lob
    @Column(name = "floor_plan")
    private byte[] floorPlan;

    public Room() {
    }

    public Room(Integer roomId) {
        this.roomId = roomId;
    }

    public Room(Integer roomId, int number) {
        this.roomId = roomId;
        this.number = number;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFurnitureMobility() {
        return furnitureMobility;
    }

    public void setFurnitureMobility(String furnitureMobility) {
        this.furnitureMobility = furnitureMobility;
    }

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public byte[] getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(byte[] floorPlan) {
        this.floorPlan = floorPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }
    
}
