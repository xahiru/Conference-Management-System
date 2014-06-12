/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backingbeans;

import entity.RentalRequest;
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
public class RentalRequestFacade extends AbstractFacade<RentalRequest> {
    @PersistenceContext(unitName = "Conference_Management_SystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RentalRequestFacade() {
        super(RentalRequest.class);
    }
    public List<RentalRequest> findUserSpecificRentalRequest(int[] range, Users user) {

        javax.persistence.Query q = em.createNamedQuery("RentalRequest.findUserSpecificRentalRequest").setParameter("user", user);

        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
}
