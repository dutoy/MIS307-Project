
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author danielalanis
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

        // Loyalty Card?
        System.out.println("Do you have Loyalty Card? (Y/N)");
        String choice = in.next();

        // If Yes
        if (choice.equals("Y")) {

            // Prompt user to either find by name, phone, or email
            System.out.println("Find by: (1)Name, (2)Phone, (3)Email");
            choice = in.next();

            // Find user by Name
            if (choice.equals("1")) {
                System.out.println("Input Name: ");
                String name = in.next();
                try (ResultSet result = stat.executeQuery("SELECT * FROM Customer WHERE CName "
                        + "LIKE '" + name + "'")) {
                    System.out.println("Hello");
                }
            }

            // Find user by Phone
            else if (choice.equals("2")) {
                System.out.println("Input Phone Number: ");
                String phone = in.next();
                try (ResultSet result = stat.executeQuery("SELECT * FROM Customer WHERE CPhone "
                        + "LIKE '" + phone + "'")) {
                    System.out.println("Hello");
                }
            } 

            // Find user by Email
            else if (choice.equals("3")) {
                System.out.println("Input Email: ");
                String email = in.next();
                try (ResultSet result = stat.executeQuery("SELECT * FROM Customer WHERE CEmail "
                        + "LIKE '" + email + "'")) {
                    System.out.println("Hello");
                }
            } // End YES loop
        }

        // If No
        if (choice.equals("N")) {
            // Proceed, let value null.
            CName = null;
            CPhone = null;
            CEmail = null; 
        } 
        
        // Next: We take order from the Customer (use Item)
        ...
        
        // Next (a): If user have Reward, update CPoint (use RewardAmount)
        // Next (b): If user interested in being a customer (use RewardAmount)
        
        // Finally, we add data into DailyReport (use DailyReport SQL)
        String query = "INSERT INTO DailyReport VALUES (" + OrderID + "," + ItemName + "," + ItemPrice
                + "," + Quantity + "," + Total + ")";
        stat.execute(query);
        
        System.out.println("Thank you");
    }

    // End
}
