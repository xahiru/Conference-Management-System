/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import backingbeans.EventFacade;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import entity.Booking;
import entity.Event;
import entity.Organizer;
import entity.Room;
import entity.Users;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import javax.inject.Named;

/**
 *
 * @author xahiru
 */
@Named("eventlistController")
@SessionScoped
public class EventlistController implements Serializable {

    private Event current;
    private DataModel items = null;
    @EJB
    private backingbeans.EventFacade ejbFacade;
    @EJB
    private backingbeans.BookingFacade bookingFacade;
    @EJB
    private backingbeans.OrganizerFacade organizerFacade;
    @EJB
    private backingbeans.UsersFacade usersFacade;
    @EJB
    private backingbeans.RoomFacade roomFacade;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    public EventlistController() {
    }

    public Event getSelected() {
        if (current == null) {
            current = new Event();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EventFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
              
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getEventsByMe());
                }
            };
        }
        return pagination;
    }
    
  public List<Event> getEventsByMe(){
//      final List<Event> ev;
       Users  u = usersFacade.findUsersbyName(controllers.util.JsfUtil.getLoggedinUsername());
       if(JsfUtil.isCoordinator(u))
       {return getFacade().findAll();
       }else{
           return getFacade().getMyEvents(u);
       } 
  }
    public String prepareList() {
        recreateModel();
        return "List";

    }

    public String getRoomName(String id) {
        Room r = (Room) roomFacade.find(Integer.valueOf(id));
        return r.getName();
//        return id;
    }

    public String getTime(String id) {
        Booking b = (Booking) bookingFacade.find(Integer.valueOf(id));
        Date d = b.getStartTime();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int s = c.get(Calendar.HOUR);
        int sm = c.get(Calendar.MINUTE);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        d = b.getEndTime();
        c.setTime(d);
        int f = c.get(Calendar.HOUR);
        int fm = c.get(Calendar.MINUTE);
         int dayf = c.get(Calendar.DAY_OF_MONTH);
        int monthf = c.get(Calendar.MONTH);
        int yearf = c.get(Calendar.YEAR);
        String sTime = String.format("%02d-%02d-%04d %02d:%02d",month,day,year, s, sm);
        String fTime = String.format("%02d-%02d-%04d %02d:%02d",monthf,dayf,yearf, f, fm);

        return sTime + "-" + fTime;
    }

    public String getOrganizerName(String id) {
        Organizer r = (Organizer) organizerFacade.find(Integer.valueOf(id));
        return r.getCompanyName();
    }

    public Date getStartTime(String id) {
        Booking r = (Booking) bookingFacade.find(Integer.valueOf(id));
        return r.getStartTime();
    }

    public Date getEndTime(String id) {
        Booking r = (Booking) bookingFacade.find(Integer.valueOf(id));
        return r.getEndTime();
    }

    public String getBookedBy(String id) {
        Booking r = (Booking) bookingFacade.find(Integer.valueOf(id));
//        Users r = (Users) usersFacade.find(Integer.valueOf(id));
        return r.getTblUseruserId().getUsername();
//        return getStringValue(r.getUsersIduser().getUsername());
    }

    public String getStringValue(String id) {

        return id;
    }

    public String prepareView() {
      //  System.out.println("pressedview");
        current = (Event) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Event();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage("EventCreated");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,"PersistenceErrorOccured");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Event) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            if(isRoomAvailable()){
            getFacade().edit(current);
            bookingFacade.edit(current.getBookingBookingRef());
            organizerFacade.edit(current.getTblOrganizerorganizerId());
            JsfUtil.addSuccessMessage("EventUpdated");
            }
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String destroy() {
        current = (Event) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
//            bookingFacade.remove(current.getBookingBookingRef());
//            organizerFacade.remove(current.getTblOrganizerorganizerId());
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("EventDeleted");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getEventsByMe(), true);
    }

    public Event getEvent(java.lang.Integer id) {
        return ejbFacade.find(id);
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
                return getStringKey(o.getEventId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Event.class.getName());
            }
        }

    }
    
    public boolean isRoomAvailable() {
        List<Event> evntList = getFacade().getEventByRoomAndTime(current.getTblRoomroomId(), current.getBookingBookingRef().getStartTime(), current.getBookingBookingRef().getEndTime());
      
        if (evntList.isEmpty()) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("available"));
            return true;
        } else {
            for (Event event : evntList) {
                if(event.getEventId() == current.getEventId())
                    return true;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room is not available for the selected time, please select a different time"));
            return false;
        }

    }

}
