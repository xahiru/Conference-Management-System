/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import backingbeans.UsersFacade;
import entity.Booking;
import entity.Organizer;
import entity.Room;
import entity.Users;
import java.io.Serializable;
import java.security.Principal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author xahiru
 */
@Named("reservationController")
@SessionScoped
public class ReservationController implements Serializable {

  
    private String requestedPName;

    public String getRequestedPName() {
        return requestedPName ;
    }

    public void setRequestedPName(String requestedPName) {
        this.requestedPName = requestedPName;
    }

    private Users user;
    private Booking booking;
    private Organizer organizer;
    private Room room;
    
    @EJB
    private backingbeans.UsersFacade usersFacade;
    @EJB
    private backingbeans.BookingFacade bookingFacade;
    @EJB
    private backingbeans.OrganizerFacade organizerFacade;
    @EJB
    private backingbeans.RoomFacade roomFacade;
   // private int selectedItemIndex;

    public ReservationController() {
        user = new Users();
        booking = new Booking();
        organizer = new Organizer();
        room = new Room();
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
        
        user = usersFacade.findUsersbyName(requestedPName);
        
        booking.setUsersIduser(user);
        
        
        bookingFacade.create(booking);
        
        

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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

   
}
