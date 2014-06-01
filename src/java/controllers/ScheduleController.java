/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.PaginationHelper;
import entity.Event;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Zahir
 */
@Named("scheduleController")
@SessionScoped
public class ScheduleController implements Serializable {

    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    List<Event> eventList = new ArrayList<Event>();
   
    @EJB
    private backingbeans.EventFacade ef;
    @EJB
    private backingbeans.OrganizerFacade of;
    @EJB
    private backingbeans.BookingFacade bf;
    @EJB
    private backingbeans.RoomFacade rf;


    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        
      eventList =  ef.findAll();
      if (!eventList.isEmpty()){
          
          for (Event event : eventList) {
              System.out.println("Printing schedule");
            System.out.println("Title " + event.getTitle() + " IN " + event.getTblRoomroomId().getName() + "From: " + event.getBookingBookingRef().getStartTime() + " To: " + event.getBookingBookingRef().getEndTime());
                eventModel.addEvent(new DefaultScheduleEvent(event.getTitle()+" IN " + event.getTblRoomroomId().getName(),  event.getBookingBookingRef().getStartTime(),  event.getBookingBookingRef().getEndTime()));

          }
        
    }
        
//        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party + Test", today1Pm(), today6Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
//        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("new one + ROOM 2", today1Pm(), today6Pm()));
        lazyEventModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }
        };
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);
        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);
        return t.getTime();
    }

    private Date previousDay11Pm() {

        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);
        return t.getTime();
    }

    private Date today1Pm() {
        
        Calendar t = (Calendar) today().clone();
        
        t.set(Calendar.AM_PM, Calendar.PM);
        
        t.set(Calendar.HOUR, 1);
        

        return t.getTime();
        
    }

    
private Date theDayAfter3Pm() {
        
        Calendar t = (Calendar) today().clone();
        
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2); 

        t.set(Calendar.AM_PM, Calendar.PM);
        
        t.set(Calendar.HOUR, 3);
        

        return t.getTime();
        
    }

    
private Date today6Pm() {
        
        Calendar t = (Calendar) today().clone();
        
        t.set(Calendar.AM_PM, Calendar.PM);
        
        t.set(Calendar.HOUR, 6);
        

        return t.getTime();
        
    }

    
private Date nextDay9Am() {
        
        Calendar t = (Calendar) today().clone();
        
        t.set(Calendar.AM_PM, Calendar.AM);
        
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        
        t.set(Calendar.HOUR, 9);
        
        return t.getTime();
        
    }

    
private Date nextDay11Am() {
        
        Calendar t = (Calendar) today().clone();
        
        t.set(Calendar.AM_PM, Calendar.AM);
        
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        
        t.set(Calendar.HOUR, 11);
        

        return t.getTime();
       
    }

    
private Date fourDaysLater3pm() {
        
        Calendar t = (Calendar) today().clone();
        
        t.set(Calendar.AM_PM, Calendar.PM);
        
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        
        t.set(Calendar.HOUR, 3);
        

        return t.getTime();
        
    }

    
public ScheduleEvent getEvent() {
        
        return event;
        
    }

    
public void setEvent(ScheduleEvent event) {
        
        this.event = event;
        
    }

    
public void addEvent(ActionEvent actionEvent) {
        
        if (event.getId() == null) {
            eventModel.addEvent(event);
        }
        else{
        eventModel.updateEvent(event);}
        

        event = new DefaultScheduleEvent();
        
    }

   public void onEventSelect(SelectEvent selectEvent) {
        
        event = (ScheduleEvent) selectEvent.getObject();
        
    }

   public void onDateSelect(SelectEvent selectEvent) {
   
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

    }


public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
