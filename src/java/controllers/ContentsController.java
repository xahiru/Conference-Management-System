package controllers;

import entity.Content;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import backingbeans.ContentsFacade;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.ResourceBundle;
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

@Named("contentsController")
@SessionScoped
public class ContentsController implements Serializable {

    private Content current;
    private DataModel items = null;
//    private String fname;
    
    private Part file;
    @EJB
    private backingbeans.ContentsFacade ejbFacade;
//    @EJB
//    private backingbeans.EventFacade eventFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ContentsController() {
    }

    public Content getSelected() {
        if (current == null) {
            current = new Content();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ContentsFacade getFacade() {
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

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Content) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Content();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            upload();
            getFacade().create(current);
            JsfUtil.addSuccessMessage("ContentsCreated");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Content) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            upload();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("ContentsUpdated");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,"PersistenceErrorOccured");
            return null;
        }
    }

    public String destroy() {
        current = (Content) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("ContentsDeleted");
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

    public Content getContents(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Content.class)
    public static class ContentsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContentsController controller = (ContentsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contentsController");
            return controller.getContents(getKey(value));
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
            if (object instanceof Content) {
                Content o = (Content) object;
                return getStringKey(o.getContentsId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Content.class.getName());
            }
        }

    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String upload() throws IOException {
        InputStream inputStream = file.getInputStream();
        String filename = getFilename(file);
        current.setFilename(filename);
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
        current.setPaticipantNotes(buffer);

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
    
//      public  String getEventName(String id){
//      
//      return eventFacade.find(Integer.valueOf(id)).getTitle();
////      return "hell";
//  }
//
//    public String getFname() {
////        fname =getFilename(current.getPaticipantNotes())
//        return fname;
//    }
//
//    public void setFname(String fname) {
//        this.fname = fname;
//    }
//      
}
