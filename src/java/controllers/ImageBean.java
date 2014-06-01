/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import backingbeans.ParticipantFacade;
import entity.Participant;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Zahir
 */
@Named("imageBean")
@RequestScoped
public class ImageBean implements Serializable {
   private StreamedContent image;

//    @ManagedProperty("#{param.id}")
//    private String id;

    @EJB
    private ParticipantFacade service;

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            image = new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.

            System.out.println("Printing pariticipant object name");
              String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("p_id");

            System.out.println(id);
            System.out.println(service.find(Integer.valueOf(id)).getName());
            
            image = new DefaultStreamedContent(new ByteArrayInputStream(service.find(Integer.valueOf(id)).getPhoto()));
        }
    }

//    public void setId(String id) {
//        this.id = id;
//    }
    
//    public String getId(){
//        return id;
//    }

    public StreamedContent getImage() {
        return image;
    }
 public StreamedContent getBytesToStreamedContent() {
  String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("p_id");
  //Now get the car with the id
           return   image = new DefaultStreamedContent(new ByteArrayInputStream(service.find(Integer.parseInt(id)).getPhoto()));

}
}
