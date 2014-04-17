/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.PaginationHelper;
import entity.Booking;
import entity.Event;
import entity.Organizer;
import entity.Room;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.annotation.PostConstruct;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.faces.model.DataModel;
//import javax.faces.model.ListDataModel;
import javax.inject.Named;

/**
 *
 * @author xahiru
 */
@Named("visitorIndexController")
@SessionScoped
public class VisitorIndexController implements Serializable {

    List<Event> eventList = new ArrayList<Event>();
    PaginationHelper pagination;
//    private DataModel items = null;
    @EJB
    private backingbeans.EventFacade ef;
    @EJB
    private backingbeans.OrganizerFacade of;
    @EJB
    private backingbeans.BookingFacade bf;
    @EJB
    private backingbeans.RoomFacade rf;

    public VisitorIndexController() {
//        Calendar cal = Calendar.getInstance();
//        cal.getTime();

    }

    @PostConstruct
    private void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = dateFormat.format(cal.getTime());
        
        try {
            cal.setTime(dateFormat.parse(today));
//Date timeat12 = cal.setTime(dateFormat.parse(today));
        } catch (ParseException ex) {
            Logger.getLogger(VisitorIndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        cal.setTime(dateFormat.parse('15-4-2014'));
        Date startTime = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date endDate = cal.getTime();

        eventList = ef.getTodaysEvents(startTime, endDate);
        System.out.println(startTime);
        System.err.println(endDate);
        for (Event event : eventList) {
            System.out.println("Title "+event.getTitle()+" IN "+event.getRoomIdroom().getName()+"From: "+event.getBookingBookingRef().getStartTime()+" To: "+event.getBookingBookingRef().getEndTime());
        }
    }

/*
    public PaginationHelper getPagination() {
    if (pagination == null) {
    pagination = new PaginationHelper(10) {
    @Override
    public int getItemsCount() {
    return ef.count();
    }
    @Override
    public DataModel createPageDataModel() {
    Calendar cal = Calendar.getInstance();
    Date startTime = cal.getTime();
    cal.add(Calendar.DATE, 1);
    Date endDate = cal.getTime();
    return new ListDataModel(ef.getTodaysEvents(startTime,endDate));
    }
    };
    }
    return pagination;
    }
    public DataModel getItems() {
    if (items == null) {
    items = getPagination().createPageDataModel();
    }
    return items;
    }
     */
    public List<Event> getEventList() {
        return eventList;
    }
    
    

    public String getOrganizerName(Organizer r) {
//        Organizer r = (Organizer) of.find(Integer.valueOf(id));
        return r.getCompanyName();
    }
    
    public String getTime(Booking r){
        
        Date d = r.getStartTime();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int s = c.get(Calendar.HOUR);
        int sm = c.get(Calendar.MINUTE);
        d = r.getEndTime();
        c.setTime(d);
        int f = c.get(Calendar.HOUR);
        int fm = c.get(Calendar.MINUTE);
        String sTime = String.format("%02d:%02d", s, sm);
        String fTime = String.format("%02d:%02d", f, fm);
        
        return sTime+"-"+fTime;
    }

    public Date getStartTime(Booking r) {
//        Booking r = (Booking) bf.find(Integer.valueOf(id));
        return r.getStartTime();
    }

    public Date getEndTime(Booking r) {
//        Booking r = (Booking) bf.find(Integer.valueOf(id));
        return r.getEndTime();
    }

    public String getRoomname(Room r) {
//        Room r = (Room) rf.find(Integer.valueOf(id));
        return r.getName();
    }
    
    
    /*
     public Event getEvent(java.lang.Integer id) {
     return ef.find(id);
     }
     @FacesConverter(forClass = Event.class)
     public static class EventControllerConverter implements Converter {

     @Override
     public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
     if (value == null || value.length() == 0) {
     return null;
     }
     EventController controller = (EventController) facesContext.getApplication().getELResolver().
     getValue(facesContext.getELContext(), null, "eventController");
     return controller.getEvent(getKey(value));
     }

     java.lang.Integer getKey(String value) {
     java.lang.Integer key;
     key = Integer.valueOf(value);
     return key;
     }

     String getStringKey(java.lang.Integer value) {
     StringBuilder sb = new StringBuilder();
     sb.append(value);
     return sb.toString();
     }

     @Override
     public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
     if (object == null) {
     return null;
     }
     if (object instanceof Event) {
     Event o = (Event) object;
     return getStringKey(o.getIdevent());
     } else {
     throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Event.class.getName());
     }
     }

     }
     */
}
