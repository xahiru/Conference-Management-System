/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;


import entity.Users;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xahiru
 */
@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    /**
     * Creates a new instance of LoginController
     */  
    public LoginController() {
    }
    
    public String logout(){
          FacesContext context = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
          session.invalidate();
          return "/visitors/index";
   }
   
}
