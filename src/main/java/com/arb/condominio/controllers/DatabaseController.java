/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arb.condominio.controllers;

import com.arb.condominio.model.database.MongoDatabase;
import com.sun.media.jfxmedia.logging.Logger;
import java.util.List;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;



/**
 *
 * @author ariel
 */
public class DatabaseController {
    protected MongoDatabase dbConection;
    
    public DatabaseController()
    {
        dbConection= new MongoDatabase();
    }
    
    protected void connect () throws Exception{
        dbConection.Connect();
    }
    protected void disconnect(){ dbConection.Disconnect();}
    
    protected Object SaveObject(Object obj){
         Object id = null;
       try{ dbConection.Connect();
        id = dbConection.datastore.save(obj).getId();   }
       catch(Exception e){ 
       Logger.logMsg(0, e.getMessage());
       }
       finally{
        dbConection.Disconnect();}
    return id;
    }
    
    public List<Object> GetAllData(Object obj){
        List<Object> resutl = null;  
        
        try{ dbConection.Connect();
          
       	Query userQueryDS = dbConection.datastore.createQuery(obj.getClass());
		resutl=userQueryDS.asList();
          }
       catch(Exception e){ 
       Logger.logMsg(0, e.getMessage());
       }
       finally{
        dbConection.Disconnect();}
        
        return resutl;
    }
    
    //public Object GetDataFromID(){}
    
    
}
