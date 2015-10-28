package com.redmart.assignment.complaints;

import org.mongodb.morphia.*;
import org.mongodb.morphia.query.*;

import com.mongodb.MongoClient;

public class MongoSingleton {

	private final static Morphia morphia = new Morphia();
	private final static Datastore datastore = morphia.createDatastore(new MongoClient(), "redmart");
	
	public static Datastore getMongoDS (){
	  return datastore;
	}
}