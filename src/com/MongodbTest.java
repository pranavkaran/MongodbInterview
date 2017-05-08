package com;

import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
 
public class MongodbTest {
	
    Mongo mongo = null;
	DB db = null;
	DBCollection table = null;
	
	MongodbTest(){
		mongo = new Mongo("localhost", 27017);
        db = mongo.getDB("textdb");
		table = db.getCollection("testcoll");
	} 
	
	public void cleanup() {
		DBCursor cursor = table.find();
		while (cursor.hasNext()) {
		    table.remove(cursor.next());
		}
	}
    
    public void InsertRecord(List<Model> mList) throws IllegalArgumentException, IllegalAccessException {
    	
    	for (Model m : mList){
    		Class<?> o = m.getClass();
    		BasicDBObject document = null;
    		Field[] fields = o.getFields();
    		
    		document = new BasicDBObject();
    		
    		for (Field f : fields){
    			String docKey = f.getName();
    		    String docObject = f.get(m).toString();
    		    
        		document.put(docKey, docObject);
    		}
    		table.insert(document);
    	}
    }
    
    public void DisplayRecord(){
    	BasicDBObject searchQuery = new BasicDBObject();
		DBCursor cursor = table.find(searchQuery);
		
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
    }
    
    public boolean SearchRecord(String fname, String lname){
    	BasicDBObject searchQuery = new BasicDBObject();
    	//BasicDBObject fields = new BasicDBObject();
    	//fields.put("name", s);
    	searchQuery.put("fname", fname);
    	searchQuery.put("lname", lname);
		DBCursor cursor = table.find(searchQuery);
		
		try {
			cursor.next();
			return true;
		} catch(Exception e){ 
			//System.out.println(e);
			return false;
		}
    }
    
    public void InsertInterviewRecord(List<InterviewModel> mList) throws IllegalArgumentException, IllegalAccessException {
    	
    	for (InterviewModel m : mList){
    		Class<?> o = m.getClass();
    		BasicDBObject document = null;
    		Field[] fields = o.getFields();
    		
    		document = new BasicDBObject();
    		
    		for (Field f : fields){
    			String docKey = f.getName();
    		    Object docObject = "";
    			if (f.getName() == "change") {
    				docObject = new BasicDBList();
    			} else {
    				docObject = f.get(m).toString();
    			}
    			
        		document.put(docKey, docObject);
    		}
    		if (!SearchRecord(m.fname, m.lname)){
    			table.insert(document);
    		}
    	}
    }
    
    public void UpdateInterviewRecord(List<Change> mList) throws IllegalArgumentException, IllegalAccessException {
    	
    	for (Change m : mList){
    		BasicDBObject searchQuery = new BasicDBObject();
        	searchQuery.put("email", m.oldvalue);
    		DBCursor cursor = table.find(searchQuery);
    		//BasicDBList list = (BasicDBList) cursor.next().get("change");
    		Object obj = cursor.next().get("change");
    		BasicDBList list = null;
    		if (obj instanceof BasicDBList) {
    			list = (BasicDBList) obj;
    		} else {
    			list = new BasicDBList();
    		}
    		
    		Class<?> o = m.getClass();
    		BasicDBObject document = null;
    		Field[] fields = o.getFields();
    		document = new BasicDBObject();
    		
    		for (Field f : fields){
    			String docKey = f.getName();
    		    String docObject = f.get(m).toString();
        		document.put(docKey, docObject);
    		}
    		if (!list.contains(document)){
    			list.add(document);
        		table.update(new BasicDBObject("email", m.oldvalue),
        				new BasicDBObject("$set", new BasicDBObject("change", list)));
        		table.update(new BasicDBObject("email", m.oldvalue),
        				new BasicDBObject("$set", new BasicDBObject("email", m.newvalue)));
    		}	
    	}
    }
    
    public void SearchDisplay(String fname, String lname){
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("fname", fname);
    	searchQuery.put("lname", lname);
		DBCursor cursor = table.find(searchQuery);
		System.out.println(cursor.next());
    }
}
