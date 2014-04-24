/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import entity.Advertisement;
import entity.Room;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author xahiru
 */
@Named("imageStreamer")
@ApplicationScoped
public class ImageStreamer {
    
    
     @EJB
    private backingbeans.RoomFacade service;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
          
            Room image =  service.find(Long.valueOf(imageId));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getFloorPlan()));
        }
    }
    
    public void uploadPicture(FileUploadEvent event) throws IOException {
        
        System.out.println("Hello");
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
         FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
