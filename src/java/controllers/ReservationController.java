/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Booking;
import entity.Event;
import entity.Organizer;
import entity.Room;

import entity.Users;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
    private Room room;
    private String currentDate;
    private String endDate;
//    private String endTimeHour;
//    private String endTimeMin;

    //Booking
    private Date startTime;
    private Date endTime;

//    Event
    private String title;
    private String description;
    private Integer numberOfParticipants;
    private boolean openresgistration;

//    Oragnization
    private String companyName;
    private String contactPersonName;
    private String contactNumber;
    private String email;

    @EJB
    private backingbeans.UsersFacade usersFacade;
    @EJB
    private backingbeans.BookingFacade bookingFacade;
    @EJB
    private backingbeans.OrganizerFacade organizerFacade;
    @EJB
    private backingbeans.EventFacade eventFacade;
    @EJB
    private backingbeans.RoomFacade roomFacade;
    // private int selectedItemIndex;

    public ReservationController() {

        requestedPName = controllers.util.JsfUtil.getLoggedinUsername();

    }

    public String reserveEvent() {

//        if (!requestedPName.equals("Anonymous")) {
//          //  user = usersFacade.findUsersbyName(requestedPName);
//        } else {
//
//        }      
        if (isRoomAvailable()) {
            /*
             Getting the loggedin user from DB
             */ user = usersFacade.findUsersbyName(requestedPName);

            booking = new Booking();
            booking.setStartTime(startTime);
            booking.setEndTime(endTime);
            booking.setTblUseruserId(user);
            bookingFacade.create(booking);

            organizer = new Organizer();
            organizer.setCompanyName(companyName);
            organizer.setContactPersonName(contactPersonName);
            organizer.setContactNumber(contactNumber);
            organizer.setEmail(email);
            organizerFacade.create(organizer);

            event = new Event();
            event.setBookingBookingRef(booking);
            event.setTitle(title);
            event.setTblRoomroomId(room);
            event.setDescription(description);
            event.setNumberOfParticipants(numberOfParticipants);
            event.setTblOrganizerorganizerId(organizer);
            event.setOpenresgistration(openresgistration);

            eventFacade.create(event);

            return "/authusers/EventList";
        }//the success page
        return "/authusers/CreateBooking";
    }

//    public Booking createBooking(){
//        
//        user = usersFacade.findUsersbyName(requestedPName);
//
//        booking = new Booking();
//        booking.setStartTime(startTime);
//        booking.setEndTime(endTime);
//        booking.setUsersIduser(user);
//        bookingFacade.create(booking);
//        
//        return booking;
//               
//    }
    public boolean isRoomAvailable() {
        List<Event> evntList = eventFacade.getEventByRoomAndTime(room, startTime, endTime);
        if (evntList.isEmpty()) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("available"));
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room is not available for the selected time, please select a different time"));
            return false;
        }

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

    public boolean isOpenresgistration() {
        return openresgistration;
    }

    public void setOpenresgistration(boolean openresgistration) {
        this.openresgistration = openresgistration;
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public String getEndDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(booking.getStartTime());
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
