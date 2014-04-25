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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

//import javax.faces.application.FacesMessage;  
//import javax.faces.context.FacesContext;  
  
//import org.primefaces.event.FileUploadEvent;  
//import org.primefaces.model.UploadedFile; 
/**
 *
 * @author xahiru
 */
@Named("imageStreamer")
@SessionScoped
public class ImageStreamer {
    private Part file;  

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
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
    
    public String upload() throws IOException {  
         InputStream inputStream = file.getInputStream();          
        FileOutputStream outputStream = new FileOutputStream(getFilename(file));  
          
        byte[] buffer = new byte[4096];          
        int bytesRead = 0;  
        while(true) {                          
            bytesRead = inputStream.read(buffer);  
            if(bytesRead > 0) {  
                outputStream.write(buffer, 0, bytesRead);  
            }else {  
                break;  
            }                         
        }  
        outputStream.close();  
        inputStream.close();  
       
        return "success";  
    }  
  
    private static String getFilename(Part part) {  
        for (String cd : part.getHeader("content-disposition").split(";")) {  
            if (cd.trim().startsWith("filename")) {  
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");  
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }  
        }  
        return null;  
    }  
}
