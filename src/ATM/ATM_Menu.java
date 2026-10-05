package ATM;

import java.util.Scanner;

import Operations.Change_PIN;
import Operations.Check_Balance;
import Operations.Deposit;
import Operations.Mini_Statement;
import Operations.Transfer;
import Operations.Withdraw;

public class ATM_Menu {

    public static void showMenu(long acc_no) {

        Scanner sc = new Scanner(System.in);

        while(true ) {
        	
        
        System.out.println("\n===== ATM MACHINE OPERATIONS =====\n");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Mini Statement");
        System.out.println("6. Change PIN");
        System.out.println("7. Exit");

        System.out.println("\n===== ENTER YOUR CHOICE =====");
        int choice = sc.nextInt();

        switch (choice) {

        case 1:
            Check_Balance balance = new Check_Balance();
            balance.check_balance(acc_no);
            break;

        case 2:
            Deposit deposit = new Deposit();
            deposit.deposit(acc_no);
            break;

        case 3:
            Withdraw withdraw = new Withdraw();
            withdraw.withdraw(acc_no);
            break;

        case 4:
            Transfer transfer = new Transfer();
            transfer.transfer(acc_no);
            break;

        case 5:
            Mini_Statement mini = new Mini_Statement();
            mini.mini(acc_no);
            break;

        case 6:
            Change_PIN pin = new Change_PIN();
            pin.changePin(acc_no);
            break;

        case 7:
            System.out.println("Thank you for using the ATM.");
            break;

        default:
            System.out.println("Invalid choice.");
        }
    }
   }
}