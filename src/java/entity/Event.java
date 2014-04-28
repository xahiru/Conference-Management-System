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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tblEvent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByEventId", query = "SELECT e FROM Event e WHERE e.eventId = :eventId"),
    @NamedQuery(name = "Event.findByTitle", query = "SELECT e FROM Event e WHERE e.title = :title"),
    @NamedQuery(name = "Event.findByDescription", query = "SELECT e FROM Event e WHERE e.description = :description"),
    @NamedQuery(name = "Event.findTodaysEvent", query = "SELECT e FROM Event e WHERE  (e.bookingBookingRef.startTime <= :startTime  AND e.bookingBookingRef.endTime >= :endTime) OR (e.bookingBookingRef.startTime >= :startTime  AND e.bookingBookingRef.endTime <= :endTime) "),
    @NamedQuery(name = "Event.findByRoom", query = "SELECT e FROM Event e WHERE e.tblRoomroomId = :room"),
    @NamedQuery(name = "Event.findMyEvents", query = "SELECT e FROM Event e WHERE e.bookingBookingRef.tblUseruserId = :user"),
    @NamedQuery(name = "Event.findEventsByRoomInTimeRange", query = "SELECT e FROM Event e WHERE e.tblRoomroomId = :room AND ((e.bookingBookingRef.startTime <= :startTime  AND e.bookingBookingRef.endTime >= :endTime) OR (e.bookingBookingRef.startTime >= :startTime  AND e.bookingBookingRef.endTime <= :endTime)) "),
    @NamedQuery(name = "Event.findByNumberOfParticipants", query = "SELECT e FROM Event e WHERE e.numberOfParticipants = :numberOfParticipants")})
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eventId")
    private Integer eventId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Column(name = "number_of_participants")
    private Integer numberOfParticipants;
    @JoinColumn(name = "tblRoom_roomId", referencedColumnName = "roomId")
    @ManyToOne(optional = false)
    private Room tblRoomroomId;
    @JoinColumn(name = "tblOrganizer_organizerId", referencedColumnName = "organizerId")
    @ManyToOne(optional = false)
    private Organizer tblOrganizerorganizerId;
    @JoinColumn(name = "booking_booking_ref", referencedColumnName = "bookingId")
    @ManyToOne(optional = false)
    private Booking bookingBookingRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblEventeventId")
    private Collection<Content> contentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblEventeventId")
    private Collection<Participant> participantCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblEventeventId")
    private Collection<RentalRequest> rentalRequestCollection;

    public Event() {
    }

    public Event(Integer eventId) {
        this.eventId = eventId;
    }

    public Event(Integer eventId, String title) {
        this.eventId = eventId;
        this.title = title;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Room getTblRoomroomId() {
        return tblRoomroomId;
    }

    public void setTblRoomroomId(Room tblRoomroomId) {
        this.tblRoomroomId = tblRoomroomId;
    }

    public Organizer getTblOrganizerorganizerId() {
        return tblOrganizerorganizerId;
    }

    public void setTblOrganizerorganizerId(Organizer tblOrganizerorganizerId) {
        this.tblOrganizerorganizerId = tblOrganizerorganizerId;
    }

    public Booking getBookingBookingRef() {
        return bookingBookingRef;
    }

    public void setBookingBookingRef(Booking bookingBookingRef) {
        this.bookingBookingRef = bookingBookingRef;
    }

    @XmlTransient
    public Collection<Content> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(Collection<Content> contentCollection) {
        this.contentCollection = contentCollection;
    }

    @XmlTransient
    public Collection<Participant> getParticipantCollection() {
        return participantCollection;
    }

    public void setParticipantCollection(Collection<Participant> participantCollection) {
        this.participantCollection = participantCollection;
    }

    @XmlTransient
    public Collection<RentalRequest> getRentalRequestCollection() {
        return rentalRequestCollection;
    }

    public void setRentalRequestCollection(Collection<RentalRequest> rentalRequestCollection) {
        this.rentalRequestCollection = rentalRequestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }
    
}
