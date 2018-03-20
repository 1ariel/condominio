/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arb.condominio.controllers.DAO;

import com.arb.condominio.controllers.DatabaseController;
import com.arb.condominio.model.entity.Login;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ariel
 */
public class LoginDAO extends DatabaseController{
    
    public boolean GetLoginConfirmation(String name, String password){
            boolean status = false;
        try {
            connect();
            Login obj = new Login();
            obj.setPassword(password);
            obj.setUserName(name);
            
            long aux= dbConection.datastore.createQuery(obj.getClass())
                     .field("username").equal(obj.getUserName())
                     .field("password").equal(obj.getPassword()).count();
            
            if (aux ==1 ) {
                status = true;
            }
            
        } catch (Exception ex) {
            
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        disconnect();
        }
        
        return status;
    }
    
}
