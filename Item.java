/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;
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
 */
public class Item {
    public static void main(String[] args) throws Exception{
        stat.execute("CREATE TABLE Items (Item CHAR(25), Description VARCHAR(100)");
        stat.execute("INSERT INTO Items VALUES('Iced Coffee', 'A chilled coffee.',");
        stat.execute("INSERT INTO Items VALUES('Iced Coffee with Milk', 'A chilled coffee with milk.',");
        stat.execute("INSERT INTO Items VALUES('Caffe Vanilla Frappuccino', 'A frosty frappccino with vanilla.',");
        stat.execute("INSERT INTO Items VALUES('Caramel Frappuccino', 'An item in the coffee shop.',");
        
    }
    
    
}
