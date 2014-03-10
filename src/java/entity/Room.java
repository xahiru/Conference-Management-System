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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findByIdroom", query = "SELECT r FROM Room r WHERE r.idroom = :idroom"),
    @NamedQuery(name = "Room.findByNumber", query = "SELECT r FROM Room r WHERE r.number = :number"),
    @NamedQuery(name = "Room.findByName", query = "SELECT r FROM Room r WHERE r.name = :name"),
    @NamedQuery(name = "Room.findByArea", query = "SELECT r FROM Room r WHERE r.area = :area"),
    @NamedQuery(name = "Room.findByCapacity", query = "SELECT r FROM Room r WHERE r.capacity = :capacity"),
    @NamedQuery(name = "Room.findByType", query = "SELECT r FROM Room r WHERE r.type = :type"),
    @NamedQuery(name = "Room.findByFurnitureMobility", query = "SELECT r FROM Room r WHERE r.furnitureMobility = :furnitureMobility"),
    @NamedQuery(name = "Room.findByFurnitureType", query = "SELECT r FROM Room r WHERE r.furnitureType = :furnitureType"),
    @NamedQuery(name = "Room.findByOrientation", query = "SELECT r FROM Room r WHERE r.orientation = :orientation"),
    @NamedQuery(name = "Room.findByFloorPlan", query = "SELECT r FROM Room r WHERE r.floorPlan = :floorPlan")})
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idroom")
    private Integer idroom;
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
    @Size(max = 255)
    @Column(name = "floor_plan")
    private String floorPlan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomIdroom")
    private Collection<Event> eventCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomIdroom")
    private Collection<Layout> layoutCollection;

    public Room() {
    }

    public Room(Integer idroom) {
        this.idroom = idroom;
    }

    public Room(Integer idroom, int number) {
        this.idroom = idroom;
        this.number = number;
    }

    public Integer getIdroom() {
        return idroom;
    }

    public void setIdroom(Integer idroom) {
        this.idroom = idroom;
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

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }

    @XmlTransient
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }

    @XmlTransient
    public Collection<Layout> getLayoutCollection() {
        return layoutCollection;
    }

    public void setLayoutCollection(Collection<Layout> layoutCollection) {
        this.layoutCollection = layoutCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idroom != null ? idroom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.idroom == null && other.idroom != null) || (this.idroom != null && !this.idroom.equals(other.idroom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Room[ idroom=" + idroom + " ]";
    }
    
}
