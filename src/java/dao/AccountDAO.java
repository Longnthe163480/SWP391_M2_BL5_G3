/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.Account;
import entity.Role;
import java.util.ArrayList;
import java.util.List;

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

    public List<Account> pagingAccount(int index){
        List<Account> list=new ArrayList<>();
         query = "SELECT * FROM Account \n"
                 + "ORDER BY id\n"
                 + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
         try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String accountname = rs.getString("accountname");
                String pasword = rs.getString("password");
                int roleid = rs.getInt("roleid");
                String email = rs.getString("email");
                list.add(new Account(id, accountname, pasword, roleid, email));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalAccount(){
        query = "SELECT COUNT(*) count FROM Account";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt("count");
                return x;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    public List<Role> getRole(){
        List<Role> role = new ArrayList<>();
        query = "SELECT * FROM Roles WHERE [name] NOT LIKE 'admin'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");                
                String rname=rs.getString("name");
                role.add(new Role(id, rname));
            }
        } catch (Exception e) {
        }
        return role;
    }
    
}
