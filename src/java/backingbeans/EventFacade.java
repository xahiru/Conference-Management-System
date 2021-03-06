/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import entity.Event;
import entity.Participant;
import entity.Room;
import entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xahiru
 */
@Stateless
public class EventFacade extends AbstractFacade<Event> {

    @PersistenceContext(unitName = "Conference_Management_SystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventFacade() {
        super(Event.class);
    }

    public List<Event> getEventByRoom(Room room) {
        return em.createNamedQuery("Event.findByRoom").setParameter("room", room).getResultList();
    }

    public List<Event> getEventByRoomAndTime(Room room, Date startTtime, Date endTime) {
        return em.createNamedQuery("Event.findEventsByRoomInTimeRange").setParameter("room", room).setParameter("startTime", startTtime).setParameter("endTime", endTime).getResultList();
    }

    public List<Event> getMyEvents(Users user) {
        return em.createNamedQuery("Event.findMyEvents").setParameter("user", user).getResultList();
    }

    public List<Event> getTodaysEvents(Date startTtime, Date endTime) {
          return em.createNamedQuery("Event.findTodaysEvent").setParameter("startTime", startTtime).setParameter("endTime", endTime).getResultList();
        
    }
    
     public List<Event> getEventForRegistration(Date startTtime) {
          return em.createNamedQuery("Event.findEventByOpenRegistration").setParameter("startTime", startTtime).getResultList();
        
    }
    
    
    public List<Event> findUserSpecificRange(int[] range,Users user ) {
       
        javax.persistence.Query q = em.createNamedQuery("Event.findMyEvents").setParameter("user", user);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
}
