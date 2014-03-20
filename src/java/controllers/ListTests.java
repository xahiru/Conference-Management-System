/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;

import javax.inject.Named;

/**
 *
 * @author xahiru
 */
@Named("listTests")
@SessionScoped
public class ListTests implements Serializable{

    /**
     * Creates a new instance of ListTests
     */
    
    private DataModel items = null;
    
    public ListTests() {
    }
    
    
    
}
