/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Booking;
import entity.Event;
import entity.Organizer;

import entity.Users;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
//import org.primefaces.event.DateSelectEvent;

/**
 *
 * @author xahiru
 */
@Named("reservationController")
@RequestScoped
public class ReservationController implements Serializable {

    private String requestedPName;

    public String getRequestedPName() {
        return requestedPName;
    }

    public void setRequestedPName(String requestedPName) {
        this.requestedPName = requestedPName;
    }

    private Users user;
    private Booking booking;
    private Organizer organizer;
    private Event event;
   

    @EJB
    private backingbeans.UsersFacade usersFacade;
    @EJB
    private backingbeans.BookingFacade bookingFacade;
    @EJB
    private backingbeans.OrganizerFacade organizerFacade;
    @EJB
    private backingbeans.EventFacade eventFacade;
    // private int selectedItemIndex;

    public ReservationController() {
        user = new Users();
        booking = new Booking();
        organizer = new Organizer();
        event = new Event();

        try {
            requestedPName = getLoggedinUsername();

        } catch (NullPointerException e) {
            requestedPName = "Anon user";
        }

    }

    private String getLoggedinUsername() {

        Principal userPrincipal;
        userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return userPrincipal.getName();

    }

    public String reserveEvent() {

//        if (!requestedPName.equals("Anonymous")) {
//          //  user = usersFacade.findUsersbyName(requestedPName);
//        } else {
//
//        }
        /*
         Getting the loggedin user from DB
         */
        user = usersFacade.findUsersbyName(requestedPName);

        bookingFacade.create(booking);

        booking.setUsersIduser(user);

        event.setBookingBookingRef(booking);

        organizerFacade.create(organizer);

        event.setOrganizerIdorganizer(organizer);

        eventFacade.create(event);

        return "index"; //the success page

    }
    
   
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


}
