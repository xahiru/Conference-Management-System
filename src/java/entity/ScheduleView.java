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
@Table(name = "schedule_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScheduleView.findAll", query = "SELECT s FROM ScheduleView s"),
    @NamedQuery(name = "ScheduleView.findByIdtable1", query = "SELECT s FROM ScheduleView s WHERE s.idtable1 = :idtable1"),
    @NamedQuery(name = "ScheduleView.findByDate", query = "SELECT s FROM ScheduleView s WHERE s.date = :date"),
    @NamedQuery(name = "ScheduleView.findByStatus", query = "SELECT s FROM ScheduleView s WHERE s.status = :status")})
public class ScheduleView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtable1")
    private Integer idtable1;
    @Size(max = 45)
    @Column(name = "date")
    private String date;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "booking_booking_ref", referencedColumnName = "booking_ref")
    @ManyToOne(optional = false)
    private Booking bookingBookingRef;

    public ScheduleView() {
    }

    public ScheduleView(Integer idtable1) {
        this.idtable1 = idtable1;
    }

    public Integer getIdtable1() {
        return idtable1;
    }

    public void setIdtable1(Integer idtable1) {
        this.idtable1 = idtable1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Booking getBookingBookingRef() {
        return bookingBookingRef;
    }

    public void setBookingBookingRef(Booking bookingBookingRef) {
        this.bookingBookingRef = bookingBookingRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtable1 != null ? idtable1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScheduleView)) {
            return false;
        }
        ScheduleView other = (ScheduleView) object;
        if ((this.idtable1 == null && other.idtable1 != null) || (this.idtable1 != null && !this.idtable1.equals(other.idtable1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ScheduleView[ idtable1=" + idtable1 + " ]";
    }
    
}
