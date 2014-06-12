/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backingbeans;

import entity.Content;
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
public class ContentsFacade extends AbstractFacade<Content> {
    @PersistenceContext(unitName = "Conference_Management_SystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContentsFacade() {
        super(Content.class);
    }
    
     public List<Content> findUserSpecificContent(int[] range, Users user) {

        javax.persistence.Query q = em.createNamedQuery("Content.findUserSpecificContent").setParameter("user", user);

        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
}
