/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.Account;
import entity.Job;
import entity.Mentee;
import entity.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

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

    public Account getAccount(String username, String password) {
        Account account = null;
        query = "SELECT * FROM Account WHERE accountname=? AND password=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");                
                String name=rs.getString("accountname");
                String pass=rs.getString("password");
                int roleid=rs.getInt("roleid");
                String email=rs.getString("email");
                account=new Account(id, name, pass, roleid, email);
            }
        } catch (Exception e) {
        }
        return account;
    }

    public int checkrole(int accid) {
        query = "SELECT r.id id FROM roles r, ACCOUNT a WHERE r.id=a.roleid AND a.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int role = rs.getInt("id");
                return role;
            }
        } catch (Exception e) {
        }
        return 0;
    }    

    public boolean checkAccount(String username, String email){
        Account account = null;
        query = "SELECT * FROM Account WHERE accountname=? OR email=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,email);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");                
                String name=rs.getString("accountname");
                String pass=rs.getString("password");
                int roleid=rs.getInt("roleid");
                String emails=rs.getString("email");
                account=new Account(id, name, pass, roleid, emails);
            }
        } catch (Exception e) {
        }
        if(account==null) return true;
        else return false;
    }

    public void insertAccount(String username,String password,int roleid, String email){
        query = "INSERT INTO Account VALUES (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setInt(3,roleid);
            ps.setString(4,email);
            ps.setBoolean(5, false);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void changePassword(int accid,String newpassword){
        query = "UPDATE Account SET Password=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,newpassword);
            ps.setInt(2,accid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

   public List<Account> searchAccount(String infor,int index){
        List<Account> list=new ArrayList<>();
        query = "SELECT * FROM Account  WHERE (accountname LIKE ? OR email LIKE ?)\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + infor + "%");
            ps.setString(2, "%" + infor + "%");
            ps.setInt(3, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String accountname = rs.getString("accountname");
                String pass = rs.getString("password");
                int roleid = rs.getInt("roleid");
                String email = rs.getString("email");
                list.add(new Account(id, accountname, pass, roleid, email));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insertMentorAccount(String username,String password, String email){
        query = "INSERT INTO Account VALUES (?,?,2,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public boolean checkMentorAccount(String username){
        Account account = null;
        query = "SELECT * FROM Account WHERE accountname=? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");                
                String name=rs.getString("accountname");
                String pass=rs.getString("password");
                int roleid=rs.getInt("roleid");
                String emails=rs.getString("email");
                account=new Account(id, name, pass, roleid, emails);
            }
        } catch (Exception e) {
        }
        if(account==null) return true;
        else return false;
    }

  public List<Mentee> pagingMentee(int index) {
        List<Mentee> list = new ArrayList<>();
        query = "SELECT * FROM Mentee\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String mentorname = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                java.sql.Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String avatar = rs.getString("avatar");
                list.add(new Mentee(id, accountid, mentorname, address, phone, birthday, sex, introduce, avatar));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public int getTotalMentee(){
        query = "SELECT COUNT(*) count FROM Mentee";
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
    
    public List<Mentee> searchsomeMentee(String name, int index) {
        List<Mentee> list = new ArrayList<>();
        query = "SELECT * FROM Mentee m WHERE m.name LIKE ?\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, (index - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String mentorname = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                java.sql.Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String avatar = rs.getString("avatar");
                list.add(new Mentee(id, accountid, mentorname, address, phone, birthday, sex, introduce, avatar));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public boolean checkExistedEmail(String email) {
        String sql = "select email from account where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("checkExistedEmail: " + e.getMessage());
        }
        return false;
    }
    
    public boolean updateTempPassword(String email, String temporaryPassword) {
        String query = "UPDATE Account SET password = ?, forgoted = ? WHERE email = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setString(1, temporaryPassword); // Lưu mật khẩu dạng plain text
        ps.setBoolean(2, true); // Đặt forgoted = 1
        ps.setString(3, email);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        System.err.println("Lỗi trong updateTempPasswordWithForgoted: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
    }

    public boolean checkExistedUserWithUsername(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("checkExistedUserWithUsername: " + e.getMessage());
        }
        return false;
    }  
    
    public Account getAccountByUsername(String username) {
        Account account = null;
        query = "SELECT * FROM Account WHERE accountname=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");                
                String name=rs.getString("accountname");
                String pass=rs.getString("password");
                int roleid=rs.getInt("roleid");
                String email=rs.getString("email");
                account=new Account(id, name, pass, roleid, email);
            }
        } catch (Exception e) {
        }
        return account;
    }

    public Account getAccountByEmail(String email) {
        Account account = null;
        query = "SELECT * FROM Account WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("accountname");
                String pass = rs.getString("password");
                int roleid = rs.getInt("roleid");
                String emailFound = rs.getString("email");
                account = new Account(id, name, pass, roleid, emailFound);
            }
        } catch (Exception e) {
            System.out.println("Error at getAccountByEmail: " + e.getMessage());
        }
        return account;
    }
    
    public Boolean getForgotedStatusByUsername(String username) {
    String query = "SELECT forgoted FROM Account WHERE accountname = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setString(1, username);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getBoolean("forgoted");
        }
    } catch (Exception e) {
        System.err.println("Lỗi trong getForgotedStatusByUsername: " + e.getMessage());
        e.printStackTrace();
    }
    return null; // Trả về null nếu không tìm thấy tài khoản hoặc có lỗi
}

    public void addSkillAdmin(String name) {
        String sql = "INSERT INTO Skill(name) VALUES(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateSkillAdmin(int id, String name) {
        String sql = "UPDATE Skill SET name=? WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteSkillAdmin(int id) {
        String sql = "DELETE FROM Skill WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<entity.Skill> getAllSkills() {
        List<entity.Skill> list = new ArrayList<>();
        String sql = "SELECT * FROM Skill";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                entity.Skill s = new entity.Skill();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<entity.Skill> pagingSkill(String search, int page, int pageSize) {
        List<entity.Skill> list = new ArrayList<>();
        String sql = "SELECT * FROM Skill WHERE (? IS NULL OR name LIKE ?) ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, search == null || search.isEmpty() ? null : search);
            st.setString(2, "%" + (search == null ? "" : search) + "%");
            st.setInt(3, (page - 1) * pageSize);
            st.setInt(4, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                entity.Skill s = new entity.Skill();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public int countSkill(String search) {
        String sql = "SELECT COUNT(*) FROM Skill WHERE (? IS NULL OR name LIKE ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, search == null || search.isEmpty() ? null : search);
            st.setString(2, "%" + (search == null ? "" : search) + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public int insertAccountAndGetId(String username, String password, int roleid, String email) {
        String sql = "INSERT INTO Account(accountname, password, roleid, email) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, username);
            st.setString(2, password);
            st.setInt(3, roleid);
            st.setString(4, email);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    public void insertMentee(int accountid, String name, String address, String phone, java.sql.Date birthday, String sex, String introduce, String avatar) {
        String sql = "INSERT INTO Mentee(accountid, name, address, phone, birthday, sex, introduce, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountid);
            st.setString(2, name);
            st.setString(3, address);
            st.setString(4, phone);
            st.setDate(5, birthday);
            st.setString(6, sex);
            st.setString(7, introduce);
            st.setString(8, avatar);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void insertMentor(int accountid, String name, String address, String phone, java.sql.Date birthday, String sex, String introduce, String achievement, String avatar, float costHire) {
        String sql = "INSERT INTO Mentor(accountid, name, address, phone, birthday, sex, introduce, achievement, avatar, costHire) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountid);
            st.setString(2, name);
            st.setString(3, address);
            st.setString(4, phone);
            st.setDate(5, birthday);
            st.setString(6, sex);
            st.setString(7, introduce);
            st.setString(8, achievement);
            st.setString(9, avatar);
            st.setFloat(10, costHire);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public boolean updateAccountByAdmin(int id, String accountname, String email, int roleid) {
        String sql = "UPDATE Account SET accountname=?, email=?, roleid=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountname);
            ps.setString(2, email);
            ps.setInt(3, roleid);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccountByAdminWithPassword(int id, String accountname, String email, int roleid, String password) {
        String sql = "UPDATE Account SET accountname=?, email=?, roleid=?, password=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountname);
            ps.setString(2, email);
            ps.setInt(3, roleid);
            ps.setString(4, password);
            ps.setInt(5, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account checkEmail(String email) {
        Account account = null;
        String query = "SELECT * FROM Account WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("accountname");
                String pass = rs.getString("password");
                int roleid = rs.getInt("roleid");
                String emails = rs.getString("email");
                account = new Account(id, name, pass, roleid, emails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    // Lấy danh sách tất cả account mentee (roleid=1)
    public List<Account> getAllMenteeAccounts() {
        List<Account> list = new ArrayList<>();
        try {
            query = "SELECT * FROM Account WHERE roleid=1";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String accountname = rs.getString("accountname");
                String password = rs.getString("password");
                int roleid = rs.getInt("roleid");
                String email = rs.getString("email");
                list.add(new Account(id, accountname, password, roleid, email));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }

    // Cập nhật role của account sang mentor
    public void updateRoleToMentor(int accountId) {
        try {
            query = "UPDATE Account SET roleid = 2 WHERE id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
        }
    }

    public List<Job> pagingJob(String search, int page, int pageSize) {
        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM Job WHERE (? IS NULL OR jobname LIKE ?) ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, search == null || search.isEmpty() ? null : search);
            st.setString(2, "%" + (search == null ? "" : search) + "%");
            st.setInt(3, (page - 1) * pageSize);
            st.setInt(4, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Job j = new Job();
                j.setId(rs.getInt("id"));
                j.setJobname(rs.getString("jobname"));
                list.add(j);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public int countJob(String search) {
        String sql = "SELECT COUNT(*) FROM Job WHERE (? IS NULL OR jobname LIKE ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, search == null || search.isEmpty() ? null : search);
            st.setString(2, "%" + (search == null ? "" : search) + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public void addJobAdmin(String name) {
        String sql = "INSERT INTO Job(jobname) VALUES(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateJobAdmin(int id, String name) {
        String sql = "UPDATE Job SET jobname=? WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteJobAdmin(int id) {
        String sql = "DELETE FROM Job WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
