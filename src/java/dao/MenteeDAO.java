/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.CodeRequest;
import entity.HireRelationship;
import entity.Mentee;
import java.sql.Date;
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

    public void inserCodeRequest(int mid, String title, String content, java.sql.Date deadline) {
        query = "INSERT INTO coderequest VALUES(?,?,?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setDate(3, deadline);
            ps.setInt(4, mid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public CodeRequest getNewInsertReqeust() {
        query = "SELECT TOP 1 * FROM coderequest ORDER BY id DESC";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeid");
                return new CodeRequest(id, title, content, deadline, menteeid);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void inserMentorCodeRequest(int requestid, int mentorid) {
        query = "INSERT INTO mentorcoderequest VALUES(?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.setInt(2, mentorid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void inserCodeRequestSkill(int requestid, int skillid) {
        query = "INSERT INTO coderequestskill VALUES(?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.setInt(2, skillid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
