/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.Account;

/**
 *
 * @author longc
 */
public class AccountDAO extends DBContext {

     public Account getAccountByid(int ids){
        Account account= null;
        query = "SELECT * FROM Account WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,ids);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");                
                String name=rs.getString("accountname");
                String pass=rs.getString("password");
                int roleid=rs.getInt("roleid");
                String emails=rs.getString("email");
                account=new Account(id, name, pass, roleid, emails);
            }
        } catch (Exception e) {
        }
        return account;
    }
    
           
}
