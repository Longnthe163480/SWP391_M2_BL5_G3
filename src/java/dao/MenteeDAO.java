/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.HireRelationship;
import entity.Mentee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author longc
 */
public class MenteeDAO extends DBContext {

    public Mentee getMenteebyAccID(int accid) {
        query = "SELECT * FROM Mentee WHERE accountid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String menteename = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                java.sql.Date birthday = rs.getDate("birthday");
                String avatar = rs.getString("avatar");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                return new Mentee(id, accountid, menteename, address, phone, birthday, sex, introduce, avatar);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void inserHireRequest(int menteeid, int mentorid, String title, String content) {
        query = "INSERT INTO hirerequest VALUES(?,?,?,?,3);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menteeid);
            ps.setInt(2, mentorid);
            ps.setString(3, title);
            ps.setString(4, content);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
     
    public List<HireRelationship> getHireRelationship(){
        List<HireRelationship> list=new ArrayList<>();
        query = "SELECT * FROM HireRelatitonship";
        try {
            ps = connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int menteeid=rs.getInt("menteeid");
                int mentorid=rs.getInt("mentorid");
                list.add(new HireRelationship(id, menteeid, mentorid));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}
