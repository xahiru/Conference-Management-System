/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Booking;
import java.io.Serializable;
//import java.sql.Date;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

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
    @EJB
    private backingbeans.BookingFacade bf;

    public TestDateController() {

    }

    public String printBooking() {
        rangeBooking = new ArrayList<Booking>();
        System.out.println(startDate);
        System.out.println(endDate);
        rangeBooking = bf.getAllBookingsInRange(startDate, endDate);
//        rangeBooking = bf.findAll();                                
        System.out.println("PRINTING DATES");
        for (Booking booking : rangeBooking) {
            System.out.println("Start"+booking.getStartTime()+" end "+booking.getEndTime());
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

}
