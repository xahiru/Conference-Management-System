/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backingbeans;

import entity.Roomcard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xahiru
 */
@Stateless
public class RoomcardFacade extends AbstractFacade<Roomcard> {
    @PersistenceContext(unitName = "Conference_Management_SystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomcardFacade() {
        super(Roomcard.class);
    }
    
}
