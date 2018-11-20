/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis307project;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
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
 * Responsible for lines 96-240
 * More detailed explanations for code are on our progress report document.
 */
public class MIS307project {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        /*
        @author Dustin Toy
        Responsible for lines 31-73
        */
        if (args.length == 0)
	      {
	         System.out.println("Usage: DailyReport propertiesFile");
	         System.exit(0);
	      }

	      SimpleDataSource.init(args[0]);
	      try(Connection conn = SimpleDataSource.getConnection();Statement stat = conn.createStatement()) 
	      {
	         try
	         {
	            stat.execute("DROP TABLE DailyReport");
	         }
	         catch(SQLException e)
	         {
	         
	     }
		 stat.execute("CREATE TABLE DailyReport (OrderID VARCHAR(20),"
	               + "EmployeeID VARCHAR(20), " + "ItemName VARCHAR(20), " + "ItemPrice DECIMAL(8,2), " + "Quantity VARCHAR(20), " +  "Total VARCHAR(20)");
	
		 String inputFileName = "dailyreport.txt";
		 File inputFile = new File(inputFileName);
	     Scanner in = new Scanner(inputFile);
	     
	     while (in.hasNextLine())
	     {
	    	 String input = in.nextLine();
	    	 String[] information = input.split("		");
	    	 	String orderId = information[1];
	    	 	String employeeId = information[2];
	    	 	String itemName = information[3];
	    	 	Double itemPrice = double.parseDouble(information[4]);
	    	 	String quantity = information[5];
	    	 	String total = information[6];
	    	 	
	    	 	try(PreparedStatement prepared = conn.prepareStatement("Insert INTO Daily Report (?, ?, ?, ?, ?, ?)")){
	    	 		prepared.setString(1, orderId); 
	    	 		prepared.setString(2, employeeId);
	    	 		prepared.setString(3, itemName);
	    	 		prepared.setDouble(4, itemPrice);
	    	 		prepared.setString(5, quantity);
	    	 		prepared.setString(6, total);
	    	 		prepared.executeUpdate();
        /*
                                @author Daniel Alanis 11/7/18
                                Responsible for lines 78-93
                                */
        String name;
        Double phone;
        String email;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Customer Name:");
        name = in.next();
        
        System.out.println("Enter Customer Phone #: ");
        phone = in.nextDouble();
        
        System.out.println("Enter Customer Email: ");
        email = in.next();
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
                        

                if (select.equals("A")) { // If user select Add a car
                    // Prompt user to input Manufacturer, Model, Effeciency, and Price
                    String newManufacturer = in2.next();
                    String newModel = in2.next();
                    double newEffeciency = in2.nextDouble();
                    double newPrice = in2.nextDouble();

                    // Execute Stat to add value into the Car table
                    addCar(conn, newManufacturer, newModel, newEffeciency, newPrice);
                }

                if (select.equals("C")) { // If user select C
                    // Calculate average Efficiency
                    averageMPG(conn);
                }

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

                if (select.equals("M")) { // If user select M
                    // Prompt user to input Upper bound MPG
                    System.out.print("Upper bound on efficiency (MPG): ");
                    double efficiency = in2.nextDouble();
                    printMPG(conn, efficiency);
                }
            }
        }
    }

    // If user input "A", add car
    public static void addCar(Connection conn, String newManufacturer, String newModel, double newEfficiency, double newPrice) throws SQLException {
        try (PreparedStatement stat = conn.prepareStatement("INSERT INTO Car VALUES(?,?,?,?)")) {
            stat.setString(1, newManufacturer);
            stat.setString(2, newModel);
            stat.setDouble(3, newEfficiency);
            stat.setDouble(4, newPrice);
        }
        System.out.println();
    }

    // If user input "C", calculate average Efficiency
    public static void averageMPG(Connection conn) throws SQLException {
        try (Statement stat = conn.createStatement()) {
            ResultSet result = stat.executeQuery("SELECT avg(Efficiency) FROM Car");
            result.next();
            double average = result.getDouble(3);
            System.out.println(average);
        }
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

    // If user input "M", print subnet of cars based on MPG
    public static void printMPG(Connection conn, double efficiency) throws SQLException {
        try (PreparedStatement stat = conn.prepareStatement("SELECT * FROM Car WHERE Efficiency < ?")) {
            stat.setDouble(1, efficiency);
            ResultSet result = stat.executeQuery();

            while (result.next()) {
                for (int i = 1; i <= 4; i++) {
                    System.out.print(result.getString(i) + "\t");
                }
                System.out.println();
            }
            result.close();
        }
    }
    /*
    @author Duncan Marquis
    Responsible for lines 247-259
    */
    private static int addAccount(String account)throws Exception {
        try(Connection conn = SimpleDataSource.getConnection(), Scanner in = new Scanner(System.in)){
            
        }
    } 
    public void removeAccount(String remove)throws Exception {
        
    }
    public void updateAccount (int amount){
        balance += amount;
        if(amount < 100){
            
        }
        /*
        @author Dustin Toy
        Responsible for lines 264-370
        */
        static String writeFile(Connection conn, Statement stat) throws SQLException, FileNotFoundException{
	    	 		   
	    	 		   Scanner fileInput = new Scanner(System.in);
	    	 		   		System.out.print("Please enter Output File: ");
	    	 		   		String outputFile = fileInput.next();
	    	 		   		PrinterWriter out = new PrinterWriter(outputFile);
	    	 		   		ResultSet whole_table = stat.executeQuery("SELECT * FROM DailyReport");
	    	 		   			output.print("OrderID	EmployeeID	ItemName	ItemPrice	Quantity	Total	Print\n")
	    	 		   			while(whole_table.next()) {
	    	 		   				String orderId = whole_table.getString(1);
	    	 		   				String employeeId = whole_table.getString(2);
	    	 		   				String itemName = whole_table.getDouble(3);
	    	 		   				Double itemPrice = whole_table.getDouble(4);
	    	 		   				String quantity = whole_table.getDouble(5);
	    	 		   				String total = whole_table.getDouble(6);
	    	 		   			output.printf("%10s %10s %10.0f %10.0f \n" orderId, employeeId, itemName, itemPrice, quantity, total);
	    	 		   			
	    	 		   			}
	    	 		   	out.close();
	    	 		   	return " \n";
	    	 	   }
        static String printTable(Connection conn, Statement stat) throws SQLException{
	    	 		   
	    	 		   ResultSet whole_table = stat.executeQuery("SELECT * FROM DailyReport");
	    	 		   		System.out.print("OrderID	EmployeeID	ItemName	ItemPrice	Quantity	Total	Print\n");
	    	 		   		while(whole_table.next()) {
	    	 		   			String orderId = whole_table.getString(1);
	    	 		   			String employeeId = whole_table.getString(2);
	    	 		   			String itemName = whole_table.getDouble(3);
	    	 		   			Double itemPrice = whole_table.getDouble(4);
	    	 		   			String quantity = whole_table.getDouble(5);
	    	 		   			String total = whole_table.getDouble(6);
	    	 	   			System.out.printf("%10s %10s %10.0f %10.0f \n"  orderId, employeeId, itemName, itemPrice, quantity, total);
	    	 		   		}
	    	 		   return " \n";
	    	 	   }
        	     static String getAvgEfficiency(Connection conn, Statement stat) throws SQLException{
	   	   
	   	   ResultSet avgEfficiency = stat.executeQuery("SELECT AVG Efficiency FROM DailyReport");
	   	   		avgerageEfficiency.next();
	   	   		double avgEfficiency = avgerageEfficiency.getDouble(1);
	   	   		System.out.printf("Average Efficiency: %6.6f \n", avgEfficiency);
	   	   		return "\n";
	      }
                     static String writeFile(Connection conn, Statement stat) throws SQLException, FileNotFoundException {
	   		   
	   		   Scanner fileInput = new Scanner(System.in);
	   		   		System.out.print("Please enter Output File: ");
	   		   		String outputFile = fileInput.next();
	   		   		PrinterWriter out = new PrinterWriter(outputFile);
	   		   		ResultSet whole_table = stat.executeQuery("SELECT * FROM DailyReport");
	   		   			output.print("OrderID	EmployeeID	ItemName	ItemPrice	Quantity	Total	Print\n")
	   		   			while(whole_table.next()) {
	   		   				String orderId = whole_table.getString(1);
	   		   				String employeeId = whole_table.getString(2);
	   		   				String itemName = whole_table.getDouble(3);
	   		   				Double itemPrice = whole_table.getDouble(4);
	   		   				String quantity = whole_table.getDouble(5);
	   		   				String total = whole_table.getDouble(6);
	   		   			output.printf("%10s %10s %10.0f %10.0f \n"  orderId, employeeId, itemName, itemPrice, quantity, total);
	   		   			
	   		   			}
	   		   	out.close();
	   		   	return " \n";
	   	   }
                     static String printTable(Connection conn, Statement stat) throws SQLException{
	   		   
	   		   ResultSet whole_table = stat.executeQuery("SELECT * FROM DailyReport");
	   		   		System.out.print("OrderID	EmployeeID	ItemName	ItemPrice	Quantity	Total	Print\n");
	   		   		while(whole_table.next()) {
	   		   			String orderId = whole_table.getString(1);
		   				String employeeId = whole_table.getString(2);
		   				String itemName = whole_table.getDouble(3);
		   				Double itemPrice = whole_table.getDouble(4);
		   				String quantity = whole_table.getDouble(5);
		   				String total = whole_table.getDouble(6);
	   	   			System.out.printf("%10s %10s %10.0f %10.0f \n"  orderId, employeeId, itemName, itemPrice, quantity, total);
	   		   		}
	   		   return " \n";
	   	   }
                     Public static String filterTable(Connection conn, Statement stat) throws SQLException{
	   		   
	   		   Scanner in = new Scanner(System.in);
	   		   System.out.print("The largest order of the day: ");
	   		   			double largestOrder = in.nextDouble();
	   		   			try(PreparedStatement prepared = conn.prepareStatement("Select * FROM DailyReport WHERE Efficiency < ? ")){
	   		   				pstat.setDouble(1, maxEfficiency);
	   		   				
	   		   				ResultSet results = pstat.executeQuery();
	   		   				
	   		   				System.out.print("orderId	employeeId	itemName	itemPrice	quantity	total	Print\n");
	   		   		   		while(whole_table.next()) {
	   		   		   			String orderId = whole_table.getString(1);
	   		   		   			String employeeId = whole_table.getString(2);
	   		   		   			String itemName = whole_table.getDouble(3);
	   		   		   			Double itemPrice = whole_table.getDouble(4);
	   		   		   			String quantity = whole_table.getDouble(5);
	   		   		   			String total = whole_table.getDouble(6);
	   		   	   			System.out.printf("%10s %10s %10.0f %10.0f \n"  orderId, employeeId, itemName, itemPrice, quantity, total);
	   		   		   		}
	   		   			}
	   		   		   return " \n";
	   		   	   }
	   	          in2.close();
	   	          in.close();  
	   	         stat.close();
	   	         conn.close();
}
