/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backingbeans;

import entity.Layout;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xahiru
 */
@Stateless
public class LayoutFacade extends AbstractFacade<Layout> {
    @PersistenceContext(unitName = "Conference_Management_SystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LayoutFacade() {
        super(Layout.class);
    }
    
}
