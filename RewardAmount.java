
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author isaum
 */
public class RewardAmount {

    // refer to Customer Database
    Customer customer = new Customer();
    
    public static void add(String name, String phone, String email) throws SQLException {
        
        // Call connection
        try(Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
            
            // Insert value into Database
            int pos = 0; //wip
            String query = "INSERT INTO Customer VALUES("+pos+","+name+","+phone+","+email+",'0')";
            stat.execute(query);
        }
    }
    
    public static void remove(String name, String phone, String email) throws SQLException {
        try(Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
            if(name!=null) {
                String query = "DELETE FROM Customer WHERE CName='"+name+"'";
                stat.execute(query);
            }
            else if(phone!=null) {
                String query = "DELETE FROM Customer WHERE CPhone='"+phone+"'";
                stat.execute(query);
            }
            else if(email!=null) {
                String query = "DELETE FROM Customer WHERE CEmail='"+email+"'";
                stat.execute(query);
            }
            else {
                System.out.println("Deletion failed");
            }
        }
    }
    
    public static void update(int point, String name) throws SQLException {
        try(Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
            try(ResultSet result = stat.executeQuery("SELECT CPoint FROM Customer WHERE CName='"+name+"'")) {
                int result1 = 1;
                int updatepoint = result1 + point;
                
                //Update value
                String query = "UPDATE Customer SET CPoint = '"+updatepoint+"' WHERE CName = '"+name+"'";
                stat.execute(query);
            }
        }
    }
    
}
