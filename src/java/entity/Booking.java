/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "booking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findAllBookingInRange", query = "SELECT b FROM Booking b WHERE b.startTime >= :startTime  AND b.endTime <= :endTime"),
    @NamedQuery(name = "Booking.findByBookingRef", query = "SELECT b FROM Booking b WHERE b.bookingRef = :bookingRef"),
    @NamedQuery(name = "Booking.findByStartTime", query = "SELECT b FROM Booking b WHERE b.startTime = :startTime"),
    @NamedQuery(name = "Booking.findByEndTime", query = "SELECT b FROM Booking b WHERE b.endTime = :endTime")})
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "booking_ref")
    private Integer bookingRef;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @JoinColumn(name = "users_iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private Users usersIduser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingBookingRef")
    private Collection<Event> eventCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingBookingRef")
    private Collection<ScheduleView> scheduleViewCollection;

    public Booking() {
    }

    public Booking(Integer bookingRef) {
        this.bookingRef = bookingRef;
    }

    public Booking(Integer bookingRef, Date startTime, Date endTime) {
        this.bookingRef = bookingRef;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(Integer bookingRef) {
        this.bookingRef = bookingRef;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Users getUsersIduser() {
        return usersIduser;
    }

    public void setUsersIduser(Users usersIduser) {
        this.usersIduser = usersIduser;
    }

    @XmlTransient
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }

    @XmlTransient
    public Collection<ScheduleView> getScheduleViewCollection() {
        return scheduleViewCollection;
    }

    public void setScheduleViewCollection(Collection<ScheduleView> scheduleViewCollection) {
        this.scheduleViewCollection = scheduleViewCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingRef != null ? bookingRef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingRef == null && other.bookingRef != null) || (this.bookingRef != null && !this.bookingRef.equals(other.bookingRef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Booking[ bookingRef=" + bookingRef + " ]";
    }

}
