package Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Deposit {

	public static void deposit(Long acc_no) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("\n===== DEPOSIT MONEY =====");

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
                
                System.out.print("Enter money to deposit: ");
                
                double money = sc.nextDouble();
                
                double total = balance + money;
                System.out.println("Total: " + total);
              
                String sql1 = "UPDATE accounts set balance = ? where acc_no = ?";

                PreparedStatement psr = conn.prepareStatement(sql1);
                
                psr.setDouble(1, total);
				psr.setLong(2, acc_no);

				int rs1 = psr.executeUpdate();
                
                if (rs1 > 0){
                    System.out.println("Deposit Successfully");
                    System.out.println("Deposited Amount:" + money);
                    System.out.println("Total Amount:" + total);
                      				
                } else {
                    System.out.println("Balanced amount failed.");
                }
                ps.close();
                conn.close();
            }
        }
                catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
	}
}
