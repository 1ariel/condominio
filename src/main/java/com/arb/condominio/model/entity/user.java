/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arb.condominio.model.entity;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
/**
 *
 * @author ariel
 */
@Entity("user")
@Indexes(
    @Index(value = "name", fields = @Field("name"))
)
public class user {
    public user(){};
    @Id
    private ObjectId id;
    
    @Reference
    private Login login;
    
    private String name;
    private String lastName1;
    private String lastName2;
    private String houseNumber;
    
    
}
