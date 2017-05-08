package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import com.mysql.jdbc.PreparedStatement;

public class DataDump {
	
//	String csvFile = "/Users/pranavkaran/Downloads/HR_Data.csv";
//    BufferedReader br = null;
//    String line = "";
//    String cvsSplitBy = ",";
//	
//	Class.forName("com.mysql.jdbc.Driver");  
//	Connection con=DriverManager.getConnection(  
//			"jdbc:mysql://localhost:3306/user","root","root");  
//
//	//here sonoo is database name, root is username and password  
//	
//	// the mysql insert statement
//    String query = " insert into hr (fname, lname, email) values (?, ?, ?, ?, ?)";
//
//    try {
//
//        br = new BufferedReader(new FileReader(csvFile));
//        while ((line = br.readLine()) != null) {
//
//            // use comma as separator
//            String[] user = line.split(cvsSplitBy);
//
//            System.out.println("User [fname= " + user[3] + " , lname=" + user[4] + "]");
//            
//	         // create the mysql insert preparedstatement
//	         PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
//	         preparedStmt.setString (1, user[3]);
//	         preparedStmt.setString (2, user[4]);
//	         preparedStmt.setString (3, user[5]);
//	
//	         // execute the preparedstatement
//	         preparedStmt.execute();    
//
//        }
//        con.close();
//
//    } catch (FileNotFoundException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    } finally {
//        if (br != null) {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
