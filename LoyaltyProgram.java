/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author isaum
 */
public class LoyaltyProgram {
    
    Customer customer = new Customer();
    
    /**
     *
     * @param points
     */
    public static void view(int points){
        if(points >= 100){
            System.out.println("You are eligible for a free coffee! You have "+points+" points.");
        }
        else{
            System.out.println("You have "+points+" points.");
        }
    }
    public static void update(String name, String phone, String email){
        
    }
    
}
