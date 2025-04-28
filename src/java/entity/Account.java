/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class Account {
    private int id;
    private String accountname;
    private String password;
    private int roleid;
    private String email;
    private boolean forgoted;

    public Account() {
    }

    public Account(int id, String accountname, String password, int roleid, String email) {
        this.id = id;
        this.accountname = accountname;
        this.password = password;
        this.roleid = roleid;
        this.email = email;
    }

    public Account(int id, String accountname, String password, int roleid, String email, boolean forgoted) {
        this.id = id;
        this.accountname = accountname;
        this.password = password;
        this.roleid = roleid;
        this.email = email;
        this.forgoted = forgoted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isForgoted() {
        return forgoted;
    }

    public void setForgoted(boolean forgoted) {
        this.forgoted = forgoted;
    }
    
    
    
}
