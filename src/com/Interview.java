package com;

import java.util.ArrayList;
import java.util.List;

public class Interview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MysqlCon sqlObj = new MysqlCon();
		MongodbTest mongoObj = new MongodbTest();
		cleanup(sqlObj, mongoObj);
		dataDump(sqlObj, mongoObj);
		update(sqlObj, mongoObj);
		dataChange(sqlObj, mongoObj);
		test(sqlObj, mongoObj);
	}
	
	public static void dataDump(MysqlCon sqlObj, MongodbTest mongoObj){
		sqlObj.dataDump();
		
		List<InterviewModel> mList = sqlObj.InterviewModelData();
		
		try {
			mongoObj.InsertInterviewRecord(mList);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mongoObj.DisplayRecord();
	}
	
	public static void dataChange(MysqlCon sqlObj, MongodbTest mongoObj){
		List<Change> mChangeList = sqlObj.InterviewChangeData();
		
		try {
			mongoObj.UpdateInterviewRecord(mChangeList);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mongoObj.DisplayRecord();
	}

	public static void cleanup(MysqlCon sqlObj, MongodbTest mongoObj){
		sqlObj.InterviewCleanData();
		mongoObj.cleanup();
	}
	
	public static void update(MysqlCon sqlObj, MongodbTest mongoObj){
		List<String> list = new ArrayList<String>();
		list.add("Update hr SET email = 'testShawn@anycompany.com' where fname = 'Shawn' and lname = 'Keith';");
	    list.add("Update hr SET email = 'testShawn1@anycompany.com' where fname = 'Shawn' and lname = 'Keith';");
		list.add("Update hr SET email = 'testShawn2@anycompany.com' where fname = 'Shawn' and lname = 'Keith';");
		list.add("Update hr SET email = 'testShawn3@anycompany.com' where fname = 'Shawn' and lname = 'Keith';");
		list.add("Update hr SET email = 'testVictor@anycompany.com' where fname = 'Victor' and lname = 'Gallegos';");
		list.add("Update hr SET email = 'testKathleen@anycompany.com' where fname = 'Kathleen' and lname = 'Allison';");
		list.add("Update hr SET email = 'testDouglas@anycompany.com' where fname = 'Douglas' and lname = 'Sharp';");
		list.add("Update hr SET email = 'testWilliam@anycompany.com' where fname = 'William' and lname = 'Skinner';");
		sqlObj.InterviewUpdateData(list);
	}
	public static void test(MysqlCon sqlObj, MongodbTest mongoObj){
		String[][] arr = new String[5][2];
		arr[0][0] = "Shawn";
		arr[0][1] = "Keith";
		arr[1][0] = "Victor";
		arr[1][1] = "Gallegos";
		arr[2][0] = "Kathleen";
		arr[2][1] = "Allison";
		arr[3][0] = "Douglas";
		arr[3][1] = "Sharp";
		arr[4][0] = "William";
		arr[4][1] = "Skinner";
		System.out.println("\n------------------------------------------------------------\n");
		for(int i = 0; i < 5; i++){
			mongoObj.SearchDisplay(arr[i][0], arr[i][1]);
		}
	}
}
