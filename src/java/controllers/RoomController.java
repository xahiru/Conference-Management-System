package controllers;

import entity.Room;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import backingbeans.RoomFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

import org.primefaces.event.FileUploadEvent;
//import org.primefaces.model.UploadedFile;

@Named("roomController")
@SessionScoped
public class RoomController implements Serializable {

//    private Part uploadedFile;
    private Room current;
    private DataModel items = null;
    private Part file;
    @EJB
    private backingbeans.RoomFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
//     private UploadedFile uploadedFile;
     private List<String> images;

    public RoomController() {
         images = new ArrayList<String>();

        for (int i = 1; i <= 4; i++) {
            images.add("room" + i + ".jpg");
        }
    }

  public List<String> getImages() {
        return images;
    }
    public Room getSelected() {
        if (current == null) {
            current = new Room();
            selectedItemIndex = -1;
        }
        return current;
    }

    private RoomFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

//    public void handleFileUpload(FileUploadEvent event) {
//
//        InputStream input;
//        FacesMessage msg;
//        try {
//            input = event.getFile().getInputstream();
//            byte[] image = IOUtils.toByteArray(input);
//
//            current.setFloorPlan(image);
//
//            msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//
//        } catch (IOException ex) {
//            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
//            msg = new FacesMessage("file upload Failed.");
//
//        }
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//
//    }
    public String prepareList() {
        recreateModel();
        return "/room/List";
    }

    public String prepareView() {
        current = (Room) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/room/View";
    }

    public String prepareCreate() {
        current = new Room();
        selectedItemIndex = -1;
        return "/room/Create";
    }

    public String create() {

        try {
             upload();
            getFacade().create(current);
            JsfUtil.addSuccessMessage("RoomCreated");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Room) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("RoomUpdated");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String destroy() {
        current = (Room) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/room/List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/room/View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/room/List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("RoomDeleted");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Room getRoom(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Room.class)
    public static class RoomControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RoomController controller = (RoomController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "roomController");
            return controller.getRoom(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Room) {
                Room o = (Room) object;
                return getStringKey(o.getRoomId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Room.class.getName());
            }
        }

    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

//     private String destination = "/NetBeansProjects/Conference Management System/pictures/";
//
//    public void uploadPicture(FileUploadEvent event) throws IOException {
//        try {
//            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
//            FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            // Do what you want with the file       
//        } catch (IOException e) {
//            throw new IOException(e);
//        }
//    }
//
//    public void copyFile(String fileName, InputStream in) {
//        try {
//            // write the inputStream to a FileOutputStream
//            OutputStream out = new FileOutputStream(new File(destination + fileName));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//            while ((read = in.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            in.close();
//            out.flush();
//            out.close();
//            System.out.println("New file created!");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public UploadedFile getUploadedFile() {
//        return uploadedFile;
//    }
//
//    public void setUploadedFile(UploadedFile uploadedFile) {
//        this.uploadedFile = uploadedFile;
//        
//         current.setFloorPlan(uploadedFile.getContents());
//         
//         FacesMessage msg = new FacesMessage("Succesful is uploaded.");  
//        FacesContext.getCurrentInstance().addMessage(null, msg);  
//    }
//    public void handleFileUpload(FileUploadEvent event) {  
//        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
//        FacesContext.getCurrentInstance().addMessage(null, msg);  
//    } 
    public String upload() throws IOException {
        InputStream inputStream = file.getInputStream();
        String filename = getFilename(file);
        FileOutputStream outputStream = new FileOutputStream(filename);

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
        current.setFloorPlan(buffer);

        outputStream.close();
        inputStream.close();

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
