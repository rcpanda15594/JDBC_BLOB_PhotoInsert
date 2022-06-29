package com.nt.jdbc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoRetrieve {
	private static final String EMPALL_QUERY="SELECT (ENO,ENAME,ESALARY,PHOTO)FROM EMPALL WHERE ENO=?";
	public static void main(String[] args) {
		
		Scanner scn=null;
		int eno=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		OutputStream os=null;
		byte[] buffer=null;
		int bytesRead =0;
		try {
			scn=new Scanner(System.in);
		if(scn!=null) {
			System.out.println("Enter Emp no:");
			eno=scn.nextInt();
	    	}
		
	//register driver
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    
//Establish the connection
	   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger"); 

//create PreparedStatement
	   ps=con.prepareStatement(EMPALL_QUERY);
	   
	//set param value
	   if(ps!=null)
		   ps.setInt(1,eno);
	  
	   
//execute SQL Query
	   rs=ps.executeQuery();
	   
//process the resultSet(read the BLOB values)
	   if(rs.next()) {
		   is=rs.getBinaryStream(4);
	   }
//create outputStream for dest. file
	   os=new FileOutputStream("C:\\Users\\HCL\\Desktop\\New folder (3)\\R.jpg");
	   
//write buffer based logic to copy file content
	   buffer=new byte[4096];
	   while((bytesRead=is.read(buffer))!=-1) {
		   os.write(buffer, 0, bytesRead);
	   }//while
		}//try
	   catch(SQLException se) {
		   
		   System.out.println("Record insertion failed");
			se.printStackTrace();
			
		}
		catch(Exception e) {
			System.out.println("Record insertion failed");
			e.printStackTrace();
		
		}
		finally {
			
			//close jdbc objects
	try {
				
				
				if(rs!=null)
					rs.close();
				
			}catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				
				
				if(ps!=null)
					ps.close();
				
			}catch(SQLException se) {
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
				
             
	try {
				
				
				if(is!=null)
					is.close();
				
			}catch(Exception se) {
				se.printStackTrace();
			}//catch
		
	try {
		
		
		if(os!=null)
			os.close();
		
	}catch(Exception se) {
		se.printStackTrace();
	}//catch
	
             
		}//finally
	   
		
	}//method

}//class
