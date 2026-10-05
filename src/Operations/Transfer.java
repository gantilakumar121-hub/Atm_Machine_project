package Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Transfer {

	public static void transfer(Long acc_no) {
		// TODO Auto-generated method stub

		System.out.println("\n===== TRANSFER MONEY =====");

	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Kumar@1234");

	        Scanner scanner = new Scanner(System.in);

	        
	        System.out.print("Enter receiver account number: ");
	        int receiverAccNo = scanner.nextInt();

	        System.out.print("Enter amount to transfer: ");
	        double amount = scanner.nextDouble();

	        if (amount <= 0) {
	            System.out.println("Invalid amount!");
	        }

	        conn.setAutoCommit(false);

	    
	        String senderSql = "SELECT balance FROM accounts WHERE acc_no = ?";

	        PreparedStatement senderPs = conn.prepareStatement(senderSql);

	        senderPs.setLong(1, acc_no);

	        ResultSet senderRs = senderPs.executeQuery();

	        if (!senderRs.next()) {
	            System.out.println("Sender account not found!");
	            conn.rollback();
	           
	        }

	        double senderBalance = senderRs.getDouble("balance");
	        
	        if (senderBalance < amount) {
	            System.out.println("Insufficient balance!");
	            System.out.println("Available Balance : " + senderBalance);

	            conn.rollback();
	            
	        }

	     
	        String receiverSql =
	                "SELECT acc_no FROM accounts WHERE acc_no = ?";

	        PreparedStatement receiverPs =conn.prepareStatement(receiverSql);

	        receiverPs.setInt(1, receiverAccNo);

	        ResultSet receiverRs = receiverPs.executeQuery();

	        if (!receiverRs.next()) {
	            System.out.println("Receiver account not found!");

	            conn.rollback();
	          
	        }

	        // Prevent transferring to same account
	        if (acc_no == receiverAccNo) {
	            System.out.println("You cannot transfer money to the same account!");

	            conn.rollback();
	            
	        }


	        String debitSql = "UPDATE accounts SET balance = balance - ? WHERE acc_no = ?";

	        PreparedStatement debitPs = conn.prepareStatement(debitSql);

	        debitPs.setDouble(1, amount);
	        debitPs.setLong(2, acc_no);
	        debitPs.executeUpdate();

	        String creditSql = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ?";

	        PreparedStatement creditPs = conn.prepareStatement(creditSql);

	        creditPs.setDouble(1, amount);
	        creditPs.setInt(2, receiverAccNo);

	        creditPs.executeUpdate();
	        conn.commit();

	        System.out.println("\n===== TRANSFER SUCCESSFUL =====");
	        System.out.println("From Account : " + acc_no);
	        System.out.println("To Account   : " + receiverAccNo);
	        System.out.println("Amount       : " + amount);
	        System.out.println("Old Balance  : " + senderBalance);
	        System.out.println("New Balance  : " + (senderBalance - amount));
			
	        senderRs.close();
	        senderPs.close();

	        receiverRs.close();
	        receiverPs.close();

	        debitPs.close();
	        creditPs.close();

	        conn.close();

	    } catch (SQLException e) {
	        System.out.println("Transfer failed!");
	        e.printStackTrace();
	    }		
	}
}
