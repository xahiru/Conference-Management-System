/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Booking;
import entity.Event;
import entity.Room;
import java.io.Serializable;
//import java.sql.Date;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author xahiru
 */
@Named("testDateController")
@SessionScoped
public class TestDateController implements Serializable {

    private Date startDate;
    private Date endDate;
    private List<Booking> rangeBooking;
    private List<Event> eventlList;
    private Room room;
    @EJB
    private backingbeans.BookingFacade bf;
    @EJB
    private backingbeans.EventFacade ef;

    public TestDateController() {

    }

    public String printBooking() {
        rangeBooking = new ArrayList<Booking>();
        System.out.println(startDate);
        System.out.println(endDate);
          System.out.println(room.toString());
//        rangeBooking = bf.getAllBookingsInRange(startDate, endDate);
        
//        rangeBooking = bf.findAll();                                
//        System.out.println("PRINTING DATES");
//        for (Booking booking : rangeBooking) {
//            System.out.println("Start" + booking.getStartTime() + " end " + booking.getEndTime());
//        }
        
//        eventlList = ef.getEventByRoom(room);
        eventlList = ef.getEventByRoomAndTime(room, startDate, endDate);
        
        System.out.println("PRINTING Events");
        if (eventlList.isEmpty()) {
            System.out.println("Available");
        }
        for (Event event : eventlList) {
            System.out.println("Title "+event.getTitle()+" IN "+event.getRoomIdroom().getName()+"From: "+event.getBookingBookingRef().getStartTime()+" To: "+event.getBookingBookingRef().getEndTime());
        }

        return "/authusers/index";
    }
    
     public String printTodaysEvents() {
        
        System.out.println(startDate);
        System.out.println(endDate);
          System.out.println(room.toString());
//        rangeBooking = bf.getAllBookingsInRange(startDate, endDate);
        
//        rangeBooking = bf.findAll();                                
//        System.out.println("PRINTING DATES");
//        for (Booking booking : rangeBooking) {
//            System.out.println("Start" + booking.getStartTime() + " end " + booking.getEndTime());
//        }
        
//        eventlList = ef.getEventByRoom(room);
        eventlList = ef.getTodaysEvents( startDate, endDate);
        
        System.out.println("PRINTING Events");
        if (eventlList.isEmpty()) {
            System.out.println("Available");
        }
        for (Event event : eventlList) {
            System.out.println("Title "+event.getTitle()+" IN "+event.getRoomIdroom().getName()+"From: "+event.getBookingBookingRef().getStartTime()+" To: "+event.getBookingBookingRef().getEndTime());
        }

        return "/authusers/index";
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
