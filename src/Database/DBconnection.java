package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	public static void main(String[] args) {
		try {
    		
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Kumar@1234");
	        
	        if (conn != null) {
	            System.out.println("Connection Established");
	        } else {
	            System.out.println("Connection Failed");
	        }
	    	} catch(Exception e) {
	    	e.printStackTrace();
	    	}
		
	}
}