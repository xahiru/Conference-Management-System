/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import entity.Event;
import entity.Participant;
import entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xahiru
 */
@Stateless
public class ParticipantFacade extends AbstractFacade<Participant> {

    @PersistenceContext(unitName = "Conference_Management_SystemPU")
    private EntityManager em;
    private Class<Participant> p;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipantFacade() {
        super(Participant.class);
    }

    /*
    This method will return the list of all participants entere by a specific user
    in most cases the logged in user.
    */
    public List<Participant> findRangeForSpecificUser(int[] range, Users user) {

        javax.persistence.Query q = em.createNamedQuery("Participant.findByNameByUser").setParameter("user", user);

        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

//    public List<Participant> findParticipantRangebyEvent(int[] range, Event event) {
//
//        javax.persistence.Query q = em.createNamedQuery("Participant.findByEvent").setParameter("event", event);
//
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }

}
