package Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Check_Balance {
	public static void check_balance(Long acc_no) {
		
		System.out.println("\n===== CHECK BALANCE =====\n");

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Kumar@1234");
            
            String sql = "SELECT balance FROM accounts WHERE acc_no = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            
			ps.setLong(1, acc_no);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {

                double balance = rs.getDouble("balance");

                System.out.println("Account Number : " + acc_no);
                System.out.println("Available Balance : ₹" + balance);
               
            } else {
                System.out.println("Account not found.");
            }
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}