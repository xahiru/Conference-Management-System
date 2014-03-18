/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import entity.UsersGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xahiru
 */
@Stateless
public class UsersGroupFacade extends AbstractFacade<UsersGroup> {

    @PersistenceContext(unitName = "Conference_Management_SystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersGroupFacade() {
        super(UsersGroup.class);
    }

    public List<UsersGroup> getUserGroupbyName(String groupName) {

        return em.createNamedQuery("UsersGroup.findByGroupname").setParameter("groupname", groupName).getResultList();

    }
      public List<UsersGroup> getAllDistinctGroupNames() {

        return em.createNamedQuery("UsersGroup.findAllDistinctGroupNames").getResultList();

    }

}
