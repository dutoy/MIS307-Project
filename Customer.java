/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis307project;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Isa Othman
 */
public class MIS307project {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        //Use database.properties
        if (args.length == 0) {
            System.out.println("Usage: java MIS307project propertiesFile");
            System.exit(0);
        }

        SimpleDataSource.init(args[0]);
        try (Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
            try {
                stat.execute("DROP TABLE Customer");
            } catch (SQLException e) {
                // get exception if table doesn't exist yet
                System.out.println("Database error");
                e.printStackTrace();
            }

            /*
             *CREATE TABLE FOR CUSTOMER
             */
            stat.execute("CREATE TABLE Customer"
                    + "(CustomerID NUMBER,"
                    + "CName VARCHAR(20), "
                    + "CPhone CHAR(10), "
                    + "CEmail VARCHAR(20)) "
                    + "CPoint NUMBER");

            /*
             *CREATE TABLE FOR CUSTOMER
             */
            stat.execute("CREATE TABLE Employee"
                    + "(EmployeeID NUMBER,"
                    + "EName VARCHAR)"
                    + "EPhone NUMBER");

            String inputFileName = "customer.txt";
            File inputFile = new File(inputFileName);
            Scanner in = new Scanner(inputFile);

            // COMPLETE THIS WHILE LOOP to insert all cars from the input text file
            while (in.hasNextLine()) {
                stat.execute("INSERT INTO Customer (CustomerID, CName, CPhone, CEmail, CPoint)");
            }

            // Main loop of the program. Complete this while loop.
            Scanner in2 = new Scanner(System.in);
            boolean continueProgram = true;
            while (continueProgram) {
                System.out.println("Select from the following options");
                System.out.println("(Q) Quit");
                System.out.println("(A) Add a car");
                System.out.println("(C) Calculate average");
                System.out.println("(W) Write the entire table to a text file");
                System.out.println("(P) Print the entire table");
                System.out.println("(M) Print a subset of the cars based on MPG");
                String select = in2.next();

                // Data to Modify CPoint
                // customer bought coffee
                if (select.equals("b")) {
                    
                }
                        

                // Add user to the database
                if (select.equals("A")) {
                    // Prompt user to input Name, Phone, email
                    String newName = in2.next();
                    double newPhone = in2.nextDouble();
                    String newEmail = in2.next();

                    // Execute Stat to add value into the Customer table
                    addCar(conn, newName, newPhone, newEmail);
                }

                // If its done for the day
                if (select.equals("W")) { // If user select W
                    // Prompt user to input Output file name
                    System.out.print("Output file name: ");
                    String output = in2.next();
                    File file = new File(inputFileName); // Old name
                    File file2 = new File(output); // New name
                    boolean success = file.renameTo(file2); // Rename file 
                }

                if (select.equals("P")) { // If user select P
                    // Print all products in Car table
                    printTable(conn);
                }

            }
        }
    }

    // If user input "A", add car
    public static void addCar(Connection conn, String newName, double newPhone, String newEmail) throws SQLException {
        try (PreparedStatement stat = conn.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?)")) {
            stat.setString(2, newName);
            stat.setString(3, newPhone);
            stat.setString(4, newEmail);
        }
        System.out.println();
    }

    // If user input "P", print the entire table
    public static void printTable(Connection conn) throws SQLException {
        try (Statement stat = conn.createStatement()) {
            ResultSet result = stat.executeQuery("SELECT * FROM Car");
            while (result.next()) {
                for (int i = 1; i <= 4; i++) {
                    System.out.print(result.getString(i) + "\t");
                }
                System.out.println();
            }
            result.close();
        }
    }

}
