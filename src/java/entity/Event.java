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
@Table(name = "event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByIdevent", query = "SELECT e FROM Event e WHERE e.idevent = :idevent"),
    @NamedQuery(name = "Event.findByTitle", query = "SELECT e FROM Event e WHERE e.title = :title"),
    @NamedQuery(name = "Event.findByDescription", query = "SELECT e FROM Event e WHERE e.description = :description"),
    @NamedQuery(name = "Event.findByNumberOfParticipants", query = "SELECT e FROM Event e WHERE e.numberOfParticipants = :numberOfParticipants")})
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevent")
    private Integer idevent;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventIdevent")
    private Collection<RentalRequest> rentalRequestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventIdevent")
    private Collection<Contents> contentsCollection;
    @JoinColumn(name = "booking_booking_ref", referencedColumnName = "booking_ref")
    @ManyToOne(optional = false)
    private Booking bookingBookingRef;
    @JoinColumn(name = "organizer_idorganizer", referencedColumnName = "idorganizer")
    @ManyToOne(optional = false)
    private Organizer organizerIdorganizer;
    @JoinColumn(name = "room_idroom", referencedColumnName = "idroom")
    @ManyToOne(optional = false)
    private Room roomIdroom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventIdevent")
    private Collection<Participant> participantCollection;

    public Event() {
    }

    public Event(Integer idevent) {
        this.idevent = idevent;
    }

    public Event(Integer idevent, String title) {
        this.idevent = idevent;
        this.title = title;
    }

    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
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

    @XmlTransient
    public Collection<RentalRequest> getRentalRequestCollection() {
        return rentalRequestCollection;
    }

    public void setRentalRequestCollection(Collection<RentalRequest> rentalRequestCollection) {
        this.rentalRequestCollection = rentalRequestCollection;
    }

    @XmlTransient
    public Collection<Contents> getContentsCollection() {
        return contentsCollection;
    }

    public void setContentsCollection(Collection<Contents> contentsCollection) {
        this.contentsCollection = contentsCollection;
    }

    public Booking getBookingBookingRef() {
        return bookingBookingRef;
    }

    public void setBookingBookingRef(Booking bookingBookingRef) {
        this.bookingBookingRef = bookingBookingRef;
    }

    public Organizer getOrganizerIdorganizer() {
        return organizerIdorganizer;
    }

    public void setOrganizerIdorganizer(Organizer organizerIdorganizer) {
        this.organizerIdorganizer = organizerIdorganizer;
    }

    public Room getRoomIdroom() {
        return roomIdroom;
    }

    public void setRoomIdroom(Room roomIdroom) {
        this.roomIdroom = roomIdroom;
    }

    @XmlTransient
    public Collection<Participant> getParticipantCollection() {
        return participantCollection;
    }

    public void setParticipantCollection(Collection<Participant> participantCollection) {
        this.participantCollection = participantCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevent != null ? idevent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.idevent == null && other.idevent != null) || (this.idevent != null && !this.idevent.equals(other.idevent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Event[ idevent=" + idevent + " ]";
    }
    
}
