/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arb.condominio.model.database;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author ariel
 */
public class MongoDatabase {
    final Morphia morphia = new Morphia();
     public Datastore datastore ;
     String databaseName = "condominio";
     MongoClient mongoClient ;
     String serverURL;
     
    public MongoDatabase(){
        
    serverURL="localhost:27017";
    }
    
    public void Connect(){    
    mongoClient = new MongoClient(serverURL);
    datastore = morphia.createDatastore(mongoClient, databaseName);
    datastore.ensureIndexes();    
    }
    
    public void Disconnect(){
    try{
       mongoClient.close();
     
    }
    catch(Exception e){
    
    }
    finally{        
      mongoClient=null;
      datastore=null;
    }
    }
}
