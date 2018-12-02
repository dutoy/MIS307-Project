/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isa Othman
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DailyReport {

    public static void main(String[] args) throws Exception {

        //Use database.properties
        if (args.length == 0) {
            System.out.println("Usage: java DailyReport propertiesFile");
            System.exit(0);
        }

        SimpleDataSource.init(args[0]);
        try (Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
            try {
                stat.execute("DROP TABLE DailyReport");
            } catch (SQLException e) {
                // get exception if table doesn't exist yet
            }

            //CREATE TABLE DailyReport
            stat.execute("CREATE TABLE DailyReport (OrderID VARCHAR(20), "
                    + "ItemName VARCHAR(20), "
                    + "ItemPrice VARCHAR(20), "
                    + "Quantity VARCHAR(20), "
                    + "Total VARCHAR(20))");

            stat.execute("INSERT INTO DailyReport VALUES('1','1','1','1','1')");

            try (ResultSet result = stat.executeQuery("SELECT * FROM DailyReport")) {
                while (result.next()) {
                    for (int i = 1; i <= 5; i++) {
                        System.out.print(result.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
            // TEST Saving File
            
            Scanner in2 = new Scanner(System.in);
            
            try (ResultSet result = stat.executeQuery("SELECT * FROM DailyReport")) {
                System.out.print("Output file name: ");
                String outputFileName = in2.next();
                PrintWriter out = new PrintWriter(outputFileName);
                
                System.out.printf("%-15s%-15s%-15s%-15s%-15s\n",
                        "OrderID", "ItemName", "ItemPrice", "Quantity", "Total");
                while (result.next()) {
                    out.printf("%-15s", result.getString("OrderID"));
                    out.printf("%-15s", result.getString("ItemName"));
                    out.printf("%-15s", result.getString("ItemPrice"));
                    out.printf("%-15s", result.getString("Quantity"));
                    out.printf("%-15s\n", result.getString("Total"));
                }
            }
        }
    }
    // Outside Database
}
