package Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Change_PIN {

	public static void changePin (long acc_no) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("\n===== CHANGE PIN =====");
		 
		 System.out.println("Enter OLD PIN: ");
		 int old_pin = sc.nextInt();
		 
		 System.out.println("Enter NEW PIN: ");
		 int new_pin = sc.nextInt();
		 
           try {

               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Kumar@1234");
               
               String sql = "UPDATE accounts set pin = ? where acc_no = ? AND pin = ?";

               PreparedStatement ps = conn.prepareStatement(sql);
               
               ps.setInt(1, new_pin);
				ps.setLong(2, acc_no);
				ps.setInt(3, old_pin);

				int rs = ps.executeUpdate();
               
               if (rs > 0){
                   System.out.println("PIN Changed Successfully");
              
   				
               } else {
                   System.out.println("OLD PIN is Incorrect.");
               }
               ps.close();
               conn.close();
               
           } catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}	
	}
}
