package com;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MysqlCon sqlObj = new MysqlCon();
		MongodbTest mongoObj = new MongodbTest();
		
		List<Model> mList = sqlObj.DataToModel();
		
		try {
			mongoObj.InsertRecord(mList);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//mongoObj.DisplayRecord();
		//mongoObj.SearchRecord("Steve");
	}

}
