package com.redmart.assignment.complaints;

import org.mongodb.morphia.*;
import org.mongodb.morphia.query.*;

import com.mongodb.*;
import java.util.*;

public class MongoSingleton {

	private final static Morphia morphia = new Morphia();
	private static Datastore datastore = null;
	
	public static synchronized Datastore getMongoDS() {
        if (datastore == null) {
			MongoClientURI connectionString=new MongoClientURI("mongodb://redmart:redmart@ds045714.mongolab.com:45714/heroku_t6jxc42d");
			MongoClient mongoClient=new MongoClient(connectionString);
			Morphia morphia=new Morphia();
			// morphia.mapPackage("com.redmart.assignment.complaints");
			datastore=morphia.createDatastore(mongoClient,"heroku_t6jxc42d");	
        }
        return datastore;
    }	
}