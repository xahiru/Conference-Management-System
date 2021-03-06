package controllers;

import entity.Participant;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import backingbeans.ParticipantFacade;
import entity.Event;
import entity.Roomcard;
import entity.Users;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.List;
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
import org.apache.commons.io.IOUtils;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
//import org.primefaces.model.UploadedFile;

@Named("participantController")
@SessionScoped
public class ParticipantController implements Serializable {

//    private Part uploadedFile;
//    private Roomcard currentCard;
    private Participant current;
    private DataModel items = null;
    private boolean regstat = false;
    private StreamedContent pPhoto;
    private Part file;
    @EJB
    private backingbeans.ParticipantFacade ejbFacade;
    @EJB
    private backingbeans.RoomcardFacade roomFacade;
    @EJB
    private backingbeans.UsersFacade usersFacade;
    @EJB
    private backingbeans.EventFacade eventFacade;
//     @EJB
//    private backingbeans.RoomcardFacade ejbRoomCardFacade;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ParticipantController() {
    }

    public Participant getSelected() {
        if (current == null) {
            current = new Participant();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ParticipantFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {

//        final List<Participant> ev;
        final Users u = usersFacade.findUsersbyName(controllers.util.JsfUtil.getLoggedinUsername());

        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    if (JsfUtil.isCoordinator(u)) {
                        return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));

                    } else {
                        return new ListDataModel(getFacade().findRangeForSpecificUser(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, u));
                    }

                }
            };
        }
        return pagination;
    }


    public String prepareList() {
        recreateModel();
        return "/participant/List";
    }

    public String prepareView() {
        current = (Participant) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/participant/View";
    }

    public String prepareCreate() {
        current = new Participant();
        selectedItemIndex = -1;
        return "/participant/Create";
    }

    public String create() {
        try {
           // ejbRoomCardFacade.create(currentCard);
            //  current.setRoomcard(currentCard);
            current.setRegistrationstatus(regstat);
            getFacade().create(current);
            JsfUtil.addSuccessMessage("Participant added. Participant ID is"+current.getParticipantId()+"\n Please provide the Pariticipant ID to the participant, if the event is open for registration\n so that participant can register themsevlves");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Participant) getItems().getRowData();
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
        current = (Participant) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/participant/List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/participant/View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/participant/List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("ParticipantDeleted");
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

    public Participant getParticipant(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Participant.class)
    public static class ParticipantControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ParticipantController controller = (ParticipantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "participantController");
            return controller.getParticipant(getKey(value));
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
            if (object instanceof Participant) {
                Participant o = (Participant) object;
                return getStringKey(o.getParticipantId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Participant.class.getName());
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
        current.setPhoto(buffer);

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

    public boolean isRegstat() {
        return regstat;
    }

    public void setRegstat(boolean regstat) {
        this.regstat = regstat;
    }

    public StreamedContent getpPhoto() {

        InputStream is = new ByteArrayInputStream((byte[]) current.getPhoto());
        pPhoto = new DefaultStreamedContent(is, "image/png");

        return pPhoto;
    }

    public void setpPhoto(StreamedContent pPhoto) {
        this.pPhoto = pPhoto;
    }

}
