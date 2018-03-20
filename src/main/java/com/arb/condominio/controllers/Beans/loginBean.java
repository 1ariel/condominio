/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arb.condominio.controllers.Beans;

import com.arb.condominio.classHelpers.CookieHelper;
import com.arb.condominio.controllers.DAO.LoginDAO;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;



/**
 *
 * @author ariel
 */

@Named
@SessionScoped
public class loginBean implements Serializable {

    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
    }
    
    private String username;
    private String password;
    private Boolean logged = false;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String ValidateUser(){
        LoginDAO con = new LoginDAO();
          
       if (con.GetLoginConfirmation(username, password)==true) {
        //   HttpSession session = req.getSession(false);
          FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        this.password="";
        session.setAttribute("loginWithSession", this);
    
                 logged=true;    
                 return "protected/Homepage.xhtml?faces-redirect=true";

        }
        return "";
        
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }
    
}
