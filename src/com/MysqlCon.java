package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement; 


public class MysqlCon{  
	
	public List<Model> DataToModel(){
		List<Model> mList = new ArrayList<Model>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/user","root","root");  

			//here sonoo is database name, root is username and password  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from emp");  
			while(rs.next())  {
				Model model = new Model();
				model.id = rs.getInt("id");
				model.name = rs.getString("name");
				model.manager = rs.getString("manager");
				model.manager_id = rs.getInt("manager_id");
				model.dob = rs.getDate("birth");
				mList.add(model);	
			}
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}  
		
		return mList;
	}
	
	public void dataDump(){
		try{  
			
			String csvFile = "/Users/pranavkaran/Downloads/HR_Data1.csv";
		    BufferedReader br = null;
		    String line = "";
		    String cvsSplitBy = ",";
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/emp","root","root");  

			//here sonoo is database name, root is username and password  

		    String query = " insert into hr (fname, lname, email) values (?, ?, ?)";

			
		    br = new BufferedReader(new FileReader(csvFile));
	        while ((line = br.readLine()) != null) {

	            // use comma as separator
	            String[] user = line.split(cvsSplitBy);

	            System.out.println("User [fname= " + user[3] + " , lname=" + user[4] + "]");
	            
		         // create the mysql insert preparedstatement
		         PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		         preparedStmt.setString (1, user[3]);
		         preparedStmt.setString (2, user[4]);
		         preparedStmt.setString (3, user[5]);
		
		         // execute the preparedstatement
		         try {
		        	 preparedStmt.execute();    
		         } catch(Exception e){ 
		 			System.out.println(e);
		 		 }  
	        }
	        br.close();
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}  
	}
	
	public List<InterviewModel> InterviewModelData(){
		List<InterviewModel> mList = new ArrayList<InterviewModel>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/emp","root","root");  

			//here sonoo is database name, root is username and password  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from hr");  
			while(rs.next())  {
				InterviewModel model = new InterviewModel();
				model.fname = rs.getString("fname");
				model.lname = rs.getString("lname");
				model.email = rs.getString("email");
				model.change = null;
				mList.add(model);	
			}
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}  
		
		return mList;
	}
	
	
	public List<Change> InterviewChangeData(){
		List<Change> mList = new ArrayList<Change>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/emp","root","root");  

			//here sonoo is database name, root is username and password  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from hr_log");  
			while(rs.next())  {
				Change model = new Change();
				model.feild = rs.getString("feild");
				model.oldvalue = rs.getString("oldemail");
				model.newvalue = rs.getString("newemail");
				model.timeStamp = rs.getDate("changedate");
				mList.add(model);	
			}
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}  
		
		return mList;
	}
	
	public void InterviewCleanData(){
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/emp","root","root");  

			//here sonoo is database name, root is username and password  

			Statement stmt=con.createStatement();  
			stmt.executeUpdate("delete from hr;");  
			stmt.executeUpdate("delete from hr_log;"); 
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}
	}
	
	public void InterviewUpdateData(List<String> list){
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/emp","root","root");  

			//here sonoo is database name, root is username and password  

			Statement stmt=con.createStatement();  
			for (String string : list) {
				stmt.executeUpdate(string);  
			}
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}
	}
} 