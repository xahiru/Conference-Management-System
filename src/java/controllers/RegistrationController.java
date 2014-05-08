/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.util.JsfUtil;
import entity.Event;
import entity.Participant;
import entity.Users;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author xahiru
 */
@Named("registrationController")
@SessionScoped
public class RegistrationController implements Serializable{
    @EJB
    private backingbeans.ParticipantFacade participantFacade;
   @EJB
    private backingbeans.EventFacade eventFacade ;
   
   private String participantId;
   private Event event;

    public RegistrationController() {
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
   
     public SelectItem[] getItemsAvailableSelectOne() {
         Date date = new Date();
        List<Event> eventlList = eventFacade.getEventForRegistration(date) ;
         System.out.println(date);
        for (Event event : eventlList) {
            System.out.println("Title "+event.getTitle()+" IN "+event.getTblRoomroomId().getName()+"From: "+event.getBookingBookingRef().getStartTime()+" To: "+event.getBookingBookingRef().getEndTime());
        }
        return JsfUtil.getSelectItems(eventFacade.getEventForRegistration(date), true);
         
    }
     
     public String register(){
        
        Collection<Participant> pList = event.getParticipantCollection();
         String message = "Registration not successful";
         Participant participant = participantFacade.find(Integer.valueOf(getParticipantId()));
//         for (Iterator<Participant> it = pList.iterator(); it.hasNext();) {
//             Participant participant = it.next();
//             if (participant.getParticipantId().equals(participantId)){
//                 
//                 participant.setRegistrationstatus(true);
//                 participantFacade.edit(participant);
//                 
//                 message = "registraion successfull";
//                 break;
//             }else{
//                 message = "invalid Participant Id";
//             }
        
         if(pList.contains(participant)){
             
             participant.setRegistrationstatus(true);
             participantFacade.edit(participant);
             message = "registraion successfull";
         }else{
             message = "registraion unsuccessfull";
         }
         
//         for(Participant p: pList){
//             String a;
//             if (p.getParticipantId().equals(participantId)){
////                 
//                 p.setRegistrationstatus(true);
//                 participantFacade.edit(p);
//                 
//                 a = "registraion successfull";
//                 break;
//             }else{
//                 a = "invalid Participant Id";
//             }
//             message = a;
//             
//         }
//             
//         }
         System.out.println(message);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
            
         return message;
           
     }
   
}
