* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author danielalanis Revised by Isa Othman
 */
public class TransactionInfo {

    public static void CustomerName() throws SQLException {

        // Danny wrote code on 11/7/18 (look at progess report for description of class)
        DailyReport DailyReport = new DailyReport();
        RewardAmount RewardAmount = new RewardAmount();
        Item Item = new Item();
        LoyaltyProgram LoyaltyProgram = new LoyaltyProgram();

        // Scanner
        Scanner in = new Scanner(System.in);

        // value
        String OrderID = "";   //To DailyReport SQL
        String ItemName = "";  //To DailyReport SQL
        String ItemPrice = ""; //To DailyReport SQL
        String Quantity = "";  //To DailyReport SQL
        String Total = "";     //To DailyReport SQL
        String CName;     //To find Customer from Customer SQL
        String CPhone;    //To find Customer from Customer SQL
        String CEmail;    //To find Customer from Customer SQL
        boolean verify = false; // To verify if user have account

        // Loyalty Card?
        System.out.println("Do you have Loyalty Card? (Y/N)");
        String choice = in.next();

        // If Yes
        if (choice.equals("Y")) {

            // Loop
            boolean loop = true;
            while (loop == true) {
                // Prompt user to either find by name, phone, or email
                System.out.println("Find by: (1)Name, (2)Phone, (3)Email");
                choice = in.next();

                // Find user by Name
                if (choice.equals("1")) {
                    System.out.println("Input Name: ");
                    String name = in.next();
                    try (ResultSet result = stat.executeQuery("SELECT * FROM Customer WHERE CName "
                            + "LIKE '" + name + "'")) {
                        if(result.equals(name)){
                            //points for the loyalty program
                            int point = Integer.parseInt(name);
                            if(point>=100){
                                System.out.println("You are eligible for a free coffee! You have "+point+" points.");
                                System.out.println("Hello");
                            }
                            else{
                                System.out.println("You have "+point+" points.");
                                System.out.println("Hello");
                            }
                        }
                        System.out.println("Hello");
                        CName = name;
                        verify = true;
                        loop = false;
                    }
                } 

                // Find user by Phone
                else if (choice.equals("2")) {
                    System.out.println("Input Phone Number: ");
                    String phone = in.next();
                    try (ResultSet result = stat.executeQuery("SELECT * FROM Customer WHERE CPhone "
                            + "LIKE '" + phone + "'")) {
                        System.out.println("Hello");
                        CPhone = phone;
                        verify = true;
                        loop = false;
                    }
                } 

                // Find user by Email
                else if (choice.equals("3")) {
                    System.out.println("Input Email: ");
                    String email = in.next();
                    try (ResultSet result = stat.executeQuery("SELECT * FROM Customer WHERE CEmail "
                            + "LIKE '" + email + "'")) {
                        System.out.println("Hello");
                        CEmail = email;
                        verify = true;
                        loop = false;
                    }
                }
            } // End loop
        }

        // If No
        if (choice.equals("N")) {
            // Proceed, let value null.
            CName = null;
            CPhone = null;
            CEmail = null;
        }

        // Next: We take order from the Customer (use Item)
        System.out.println("Would you like to be a member? Y/N");
        choice = in.next();

        // If Yes, add Customer to Customer Dabatase, and update value
        if (choice.equals("Y")) {
            // A loop to ensure Customer input the correct data
            boolean loop = true;
            String choice2;
            while (loop == true) {
                System.out.println("Name: ");
                CName = in.next();
                System.out.println("Phone: ");
                CPhone = in.next();
                System.out.println("Email: ");
                CEmail = in.next();
                System.out.println("Is this correct?: Y/N");
                choice2 = in.next();
                if (choice2.equals("Y")) {
                    RewardAmount.add(CName, CPhone, CEmail);
                    System.out.println("Registration complete");
                    verify = true;
                    loop = false;
                } else {
                    // continue the loop?
                    System.out.println("Would you like to try again? Y/N");
                    choice2 = in.next();
                    if(choice2.equals("Y")) {
                        //continue the loop
                    }
                    else {
                        loop = false;
                        System.out.println("Registeration cancelled");
                    }
                }
            }
        }

        // Next: Customer takes the order
       int itemCounter = 0;
       boolean transaction = true;
       
       while(transaction == true ) {
    	   
        Scanner pick  = new Scanner(System.in);
        Scanner amount = new Scanner(System.in);
        
        System.out.println("What would you like to order? 1, 2, 3, or 4? ");
        String pick1;
        pick1 = pick.next();
        if(pick1.equals(1)){
        	System.out.println("How many would you like? ");
        	amount.nextInt();
        	int num = amount.nextInt();
        	itemCounter = itemCounter + num;
        	
        } else{
        	String pick2;
        	pick2 = pick.next();
        	if(pick2.equals(2));
        	System.out.println("How many would you like? ");
        	amount.nextInt();
        	int num = amount.nextInt();
        	itemCounter = itemCounter + num;
        	if(transaction = true);
        } else {
        	String pick3;
        	pick3 = pick.next();
        	if(pick3.equals(3));
        	System.out.println("How many would you like? ");
        	amount.nextInt();
        	int num = amount.nextInt();
        	itemCounter = itemCounter + num;
       } else {
       	String pick4;
       	pick4 = pick.next();
       	if(pick4.equals(4));
       	System.out.println("How many would you like? ");
       	amount.nextInt();
       	int num = amount.nextInt();
       	itemCounter = itemCounter + num;
      }
        System.out.println("Is that all? Y/N ");
        String decision;
       decision = pick.next();
        if(decision.equals(Y));
        then(transaction = false);
        
      
        // Next: If user have Account, update CPoint (use RewardAmount)
        if(verify==true) {
            
        }
        // Finally, we add data into DailyReport (use DailyReport SQL)
        String query = "INSERT INTO DailyReport VALUES (" + OrderID + "," + ItemName + "," + ItemPrice
                + "," + Quantity + "," + Total + ")";
        stat.execute(query);

        System.out.println("Thank you");
    }

    // End
}
