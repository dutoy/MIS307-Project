import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DailyReport {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		
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
	    	 		
	    	 	}
	     }
	    	 		
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
}
