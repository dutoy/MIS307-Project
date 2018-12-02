/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isa Othman (update 12/02)
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {

    public static void main(String[] args) throws Exception {

        //Use database.properties
        if (args.length == 0) {
            System.out.println("Usage: java Customer propertiesFile");
            System.exit(0);
        }

        SimpleDataSource.init(args[0]);
        try (Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
            try {
                stat.execute("DROP TABLE Customer");
            } catch (SQLException e) {
                // get exception if table doesn't exist yet
            }

            // CREATE TABLE for Customer
            stat.execute("CREATE TABLE Customer (CustomerID VARCHAR(10), "
                    + "CName VARCHAR (20), "
                    + "CPhone VARCHAR(20), "
                    + "CEmail VARCHAR(20), "
                    + "CPoint VARCHAR(10))");

            // Read txt file, insert into table Customer
            String inputFileName = "customer.txt";
            File inputFile = new File(inputFileName);
            Scanner in = new Scanner(inputFile);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                Scanner lineScanner = new Scanner(line);

                String CustomerID = "'" + lineScanner.next() + "'";
                String CName = "'" + lineScanner.next() + "'";
                String CPhone = "'" + lineScanner.next() + "'";
                String CEmail = "'" + lineScanner.next() + "'";
                String CPoint = "'" + lineScanner.next() + "'";
                String query = "INSERT INTO Customer VALUES (" + CustomerID + "," + CName + ","
                        + CPhone + "," + CEmail + "," + CPoint + ")";
                stat.execute(query);
                lineScanner.close();
            }

            /*
            
             CREATE TABLE FOR EMPLOYEE
             
            stat.execute("CREATE TABLE Employee"
                    + "(EmployeeID NUMBER,"
                    + "EName VARCHAR)"
                    + "EPhone NUMBER");
             */

 /*
            String inputFileName = "customer.txt";
            File inputFile = new File(inputFileName);
            Scanner in = new Scanner(inputFile);

            // COMPLETE THIS WHILE LOOP to insert all cars from the input text file
            while (in.hasNextLine()) {
                stat.execute("INSERT INTO Customer (CustomerID, CName, CPhone, CEmail, CPoint)");
            }
             */
 
            // Testing: Data is in the Database
            try (ResultSet result = stat.executeQuery("SELECT * FROM Customer")) {
                System.out.printf("%-15s%-15s%-15s%-15s%6s\n", "CustomerID", "CName", "CPhone",
                        "CEmail", "CPoint");
                while (result.next()) {
                    System.out.printf("%-15s", result.getString("CustomerID"));
                    System.out.printf("%-15s", result.getString("CName"));
                    System.out.printf("%-15s", result.getString("CPhone"));
                    System.out.printf("%-15s", result.getString("CEmail"));
                    System.out.printf("%6s\n", result.getString("CPoint"));
                }
            }
        }
    }
}
