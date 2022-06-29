package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoInsert {

	private static final String EMPALL_QUERY="INSERT INTO EMPALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
	
	//read user data
		Scanner scn=null;
		int eno=0;
		String ename=null, photoPath=null;
		float esalary=0.0f;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		InputStream is=null;
		long length=0;
		int result=0;
		try {
			scn=new Scanner(System.in);
			if(scn!=null)
				System.out.println("Enter Emp No:");
			    eno=scn.nextInt();
			    System.out.println("Enter Emp Name:");
			    ename=scn.next();
			    System.out.println("Enter Emp Salary:");
			    esalary=scn.nextFloat();
			    System.out.println("Enter Emp Photo:");
			    photoPath=scn.next();
			    
	//register driver
			    Class.forName("oracle.jdbc.driver.OracleDriver");
			    
	//Establish the connection
			   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger"); 

			   
 // locate the file
			  file=new File("C:\\Users\\HCL\\Desktop\\New folder\\R.jpg");
			  
//create InputStream by locating file based on photopath
		is=new FileInputStream(file);
		length=file.length();
 //create prepared statement
			   if(con!=null)
				   ps=con.prepareStatement(EMPALL_QUERY);
	  
//set values to query params
			   ps.setInt(1,eno);
			   ps.setString(2,ename);
			   ps.setFloat(3,esalary);
			   ps.setBinaryStream(4,is,file.length());
//execute the SQL Query
			   if(ps!=null)
				   result=ps.executeUpdate();
			   
//process the result set
			   if(result!=0)
				   System.out.println("Record not inserted..");
			   else
				   System.out.println("Record inserted..");
			   
			   
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record insertion failed");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Record insertion failed");
		}
		finally {
			
			//close jdbc objects
						
						try {
							
							if(ps!=null)
								ps.close();
							
						}catch(Exception se) {
							se.printStackTrace();
						}//catch
						
						
			            try {
							
							if(con!=null)
								con.close();
							
						}catch(Exception se) {
							se.printStackTrace();
						}//catch
						
						
			             try {
							
							if(scn!=null)
								scn.close();
							
						}catch(Exception se) {
							se.printStackTrace();
						}//catch
			             
					}//finally
	
	}//method

}//class
