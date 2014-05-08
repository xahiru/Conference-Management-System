/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author xahiru
 */
//@ManagedBean
//@ViewScoped
public class UploadBean {

    private int filesUploaded = 0;

    //javax.servlet.http.Part (Servlet 3.0 API)
    private Part file;
    private String fileContent;

    /**
     * Just prints out file content
     */
    public void upload() {
        try {
            fileContent = new Scanner(file.getInputStream())
                    .useDelimiter("\\A").next();
            System.out.println(fileContent + " uploaded");
            filesUploaded++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getFilesUploaded() {
        return filesUploaded;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public String upload2() throws IOException {
        InputStream inputStream = file.getInputStream();
        String filename = getFilename(file);
        FileOutputStream outputStream = new FileOutputStream("/home/xahiru/Documents/thesis/photos/"+filename);

        byte[] buffer = new byte[4194304]; //4MB
        int bytesRead = 0;
        while (true) {
            bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            } else {
                break;
            }
        }
//        current.setPhoto(buffer);

        outputStream.close();
        inputStream.close();
        System.out.println("Succesful  hhhh  filename");

        FacesMessage msg = new FacesMessage("Succesful", filename + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

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