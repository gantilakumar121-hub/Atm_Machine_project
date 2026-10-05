package Login;
import ATM.ATM_Menu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ATM.ATM_Menu;

public class Login {
	
	public void startLogin() {
		
		System.out.println("\n===== WELCOME TO ATM MACHINE =====\n");
		
Scanner scanner = new Scanner(System.in);
		
		int acc_no;
		int pin;
		
		System.out.println("Enter Account Number");
		acc_no = scanner.nextInt();
		
		System.out.println("Enter password");
		pin = scanner.nextInt();
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Kumar@1234");
			
			String sql = " select * from accounts where acc_no = ? AND pin = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, acc_no);
			ps.setInt(2, pin);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("Login Successful");
				ATM_Menu.showMenu(acc_no);
			} else {
				System.out.println("Invalid credentials");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}