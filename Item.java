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

/**
 *
 * @author Duncan 
 * Revised by Isa Othman
 */
public class Item {

    public static void main(String[] args) throws Exception {

        // Use database.properties
        if (args.length == 0) {
            System.out.println("Usage: java Item propertiesFile");
            System.exit(0);
        }

        SimpleDataSource.init(args[0]);
        try (Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
            try {
                stat.execute("DROP TABLE Items");
            } catch (SQLException e) {
                // get exception if table doesn't exist yet
            }

            // CREATE TABLE for Item
            stat.execute("CREATE TABLE Items (ItemID CHAR(2), Item CHAR(30), Description VARCHAR(100))");

            // INSERT value
            stat.execute("INSERT INTO Items VALUES('01', 'Iced Coffee', 'A chilled coffee')");
            stat.execute("INSERT INTO Items VALUES('02', 'Iced Coffee with Milk', 'A chilled coffee with milk')");
            stat.execute("INSERT INTO Items VALUES('03', 'Caffe Vanilla Frappuccino', 'A frosty frappccino with vanilla')");
            stat.execute("INSERT INTO Items VALUES('04', 'Caramel Frappuccino', 'An item in the coffee shop')");

            // TEST: Data is in the Database
            try (ResultSet result = stat.executeQuery("SELECT * FROM Items")) {
                System.out.printf("%-15s%-15s%-15s\n", "ItemID", "Item", "Description");
                while (result.next()) {
                    System.out.printf("%-15s", result.getString("ItemID"));
                    System.out.printf("%-15s", result.getString("Item"));
                    System.out.printf("%-15S\n", result.getString("Description"));
                }
            }
        }
    }
}